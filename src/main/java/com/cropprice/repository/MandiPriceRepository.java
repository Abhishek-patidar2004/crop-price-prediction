package com.cropprice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cropprice.entity.CommodityEntity;
import com.cropprice.entity.LocationEntity;
import com.cropprice.entity.MandiPriceEntity;

public interface MandiPriceRepository extends JpaRepository<MandiPriceEntity, Long> {
	boolean existsByLocationAndCommodityAndArrivalDateAndMaxPriceAndMinPriceAndModalPrice(LocationEntity location,
			CommodityEntity commodity, LocalDate arrival_date, int min_price, int max_price, int modal_price);

	 @Query(
			value = """
		        select mp.*
		        from mandi_price mp join commodity c on mp.commodity_id=c.id join location l on mp.location_id=l.id
	 			where c.name = :commodity
	 			and mp.arrival_date = :arrivalDate
	 			and mp.max_price = (
	 			select MAX(mp2.max_price)
	 			from mandi_price mp2 join commodity c2 on mp2.commodity_id=c2.id join location l2 on mp2.location_id=l2.id 
	 			where c2.name = :commodity
	 			and mp2.arrival_date = :arrivalDate
	 			)
		    """,
		    nativeQuery = true
			 )
	List<MandiPriceEntity> findMaxPrice(String commodity, LocalDate arrivalDate);
	
	
	 @Query(
			value =  """
	            select mp.*
	            from mandi_price mp join commodity c on mp.commodity_id=c.id join location l on mp.location_id=l.id
	 			where c.name = :commodity
	 			and mp.arrival_date = :arrivalDate
	 			and mp.min_price = (
	 			select MIN(mp2.min_price)
	 			from mandi_price mp2 join commodity c2 on mp2.commodity_id=c2.id join location l2 on mp2.location_id=l2.id 
	 			where c2.name = :commodity
	 			and mp2.arrival_date = :arrivalDate
	 			)
	        """,
	        nativeQuery = true
			 )
	List<MandiPriceEntity> findMinPrice(String commodity, LocalDate arrivalDate);
	 
	 
	 @Query("""
		        SELECT mp FROM MandiPriceEntity mp
		        JOIN FETCH mp.location l
		        JOIN FETCH mp.commodity c
		        WHERE (:state IS NULL OR l.state = :state)
		          AND (:district IS NULL OR l.district = :district)
		          AND (:market IS NULL OR l.market = :market)
		          AND (:commodity IS NULL OR c.name = :commodity)
		          ORDER BY mp.arrivalDate DESC
		    """)
		    List<MandiPriceEntity> findPrices(
		        @Param("state") String state,
		        @Param("district") String district,
		        @Param("market") String market,
		        @Param("commodity") String commodity,
		        Pageable pageable
		       // @Param("arrivalDate") LocalDate arrivalDate
		    );
	 
}
