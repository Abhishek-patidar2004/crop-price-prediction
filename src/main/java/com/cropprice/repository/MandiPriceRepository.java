package com.cropprice.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cropprice.dto.MandiPriceEntity;

import jakarta.persistence.Column;

public interface MandiPriceRepository
        extends JpaRepository<MandiPriceEntity, Long> {
	boolean existsByStateAndDistrictAndMarketAndCommodityAndVarietyAndGradeAndArrivalDateAndMinPriceAndMaxPriceAndModalPrice(
			String state, 
			String district,
			String market, 
			String commodity, 
			String variety, 
			String grade, 
			String arrivalDate, 
			int minPrice,
			int maxPrice, 
			int modalPrice
			);

}

