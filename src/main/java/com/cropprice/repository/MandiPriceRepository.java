package com.cropprice.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cropprice.entity.CommodityEntity;
import com.cropprice.entity.LocationEntity;
import com.cropprice.entity.MandiPriceEntity;

public interface MandiPriceRepository extends JpaRepository<MandiPriceEntity, Long> { boolean existsByLocationAndCommodityAndArrivalDateAndMaxPriceAndMinPriceAndModalPrice(
			LocationEntity location,
			CommodityEntity commodity,
			LocalDate arrival_date,
			int min_price,
			int max_price,
			int modal_price
		);
	

}
