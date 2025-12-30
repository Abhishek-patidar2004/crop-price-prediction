package com.cropprice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cropprice.entity.CommodityEntity;

public interface CommodityRepository extends JpaRepository<CommodityEntity, Long> { 
	boolean existsByNameAndVarietyAndGrade(
			String name,
			String variety,
			String grade
			);
	Optional<CommodityEntity> findByNameAndVarietyAndGrade(
            String name,
            String variety,
            String grade
    );
	

    @Query("SELECT DISTINCT c.name FROM CommodityEntity c")
    List<String> findAllCommodityNames();
}
