package com.cropprice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cropprice.entity.LocationEntity;

public interface LocationRepository extends  JpaRepository<LocationEntity, Long> {
	boolean existsByStateAndDistrictAndMarket(
		String state, 
		String district,
		String market
		);
	Optional<LocationEntity> findByStateAndDistrictAndMarket(
            String state,
            String district,
            String market
    );
}

