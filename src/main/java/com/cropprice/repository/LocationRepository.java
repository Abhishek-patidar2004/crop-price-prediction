package com.cropprice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	
	@Query("SELECT DISTINCT l.state FROM LocationEntity l")
    List<String> findAllStates();

    @Query("SELECT DISTINCT l.district FROM LocationEntity l WHERE l.state = :state")
    List<String> findDistrictsByState(String state);

    @Query("""
           SELECT DISTINCT l.market
           FROM LocationEntity l
           WHERE l.state = :state AND l.district = :district
           """)
    List<String> findMarketsByStateAndDistrict(String state, String district);
}

