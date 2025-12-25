package com.cropprice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cropprice.dto.MandiPriceEntity;

@Repository
public interface CropPriceRepository extends JpaRepository<MandiPriceEntity, Long> {

    @Query("""
    SELECT c
    FROM MandiPriceEntity c
    WHERE c.state = :state
      AND c.district = :district
      AND c.market = :market
      AND c.commodity = :commodity
      AND c.arrivalDate = :arrivalDate
    ORDER BY c.arrivalDate DESC
    """)
    List<MandiPriceEntity> findPricesForDashboard(
        String state,
        String district,
        String market,
        String commodity,
        String arrivalDate
    );
    
    @Query("""
        SELECT c FROM MandiPriceEntity c
        WHERE c.commodity = :commodity
          AND c.arrivalDate = :arrivalDate
          
          AND c.maxPrice = (
              SELECT MAX(c2.maxPrice)
              FROM MandiPriceEntity c2
              WHERE c2.commodity = :commodity
                AND c2.arrivalDate = :arrivalDate
          )
    """)
    List<MandiPriceEntity> findMaxPrice(
    		@Param("commodity") String commodity,
            @Param("arrivalDate") String arrivalDate
    );
    
    @Query("""
            SELECT c FROM MandiPriceEntity c
            WHERE c.commodity = :commodity
              AND c.arrivalDate = :arrivalDate
              
              AND c.minPrice = (
                  SELECT MIN(c2.minPrice)
                  FROM MandiPriceEntity c2
                  WHERE c2.commodity = :commodity
                    AND c2.arrivalDate = :arrivalDate
              )
        """)
    
    List<MandiPriceEntity> findMinPrice(
    		@Param("commodity") String commodity,
            @Param("arrivalDate") String arrivalDate
    		);
}

