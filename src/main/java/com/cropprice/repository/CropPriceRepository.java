package com.cropprice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    ORDER BY c.arrivalDate DESC
    """)
    List<MandiPriceEntity> findPricesForDashboard(
        String state,
        String district,
        String market,
        String commodity
    );
}

