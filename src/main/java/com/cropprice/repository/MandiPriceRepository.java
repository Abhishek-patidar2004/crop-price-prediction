package com.cropprice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cropprice.entity.CommodityEntity;
import com.cropprice.entity.LocationEntity;
import com.cropprice.entity.MandiPriceEntity;

public interface MandiPriceRepository extends JpaRepository<MandiPriceEntity, Long> {
	boolean existsByLocationAndCommodityAndArrivalDateAndMaxPriceAndMinPriceAndModalPrice(LocationEntity location,
			CommodityEntity commodity, LocalDate arrival_date, int min_price, int max_price, int modal_price);

	@Query(
	        value = """
	            SELECT mp.*
	            FROM mandi_price mp
	            JOIN location l ON l.id = mp.location_id
	            JOIN commodity c ON c.id = mp.commodity_id
	            WHERE l.state = :state
	              AND l.district = :district
	              AND l.market = :market
	              AND c.name = :commodity
	              AND mp.arrival_date = :arrivalDate
	        """,
	        nativeQuery = true
	    )
	List<MandiPriceEntity> findCropPrice(String state, String district, String market, String commodity,
			LocalDate arrivalDate);
	
	
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

}
