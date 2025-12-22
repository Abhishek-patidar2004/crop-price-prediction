package com.cropprice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cropprice.dto.LocationMaster;

public interface LocationMasterRepository
        extends JpaRepository<LocationMaster, Long> {boolean existsByStateAndDistrictAndMarket(
    			String state, 
    			String district,
    			String market
    			);
}

