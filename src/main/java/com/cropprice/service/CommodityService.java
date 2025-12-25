package com.cropprice.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cropprice.dto.CropPriceApiDto;
import com.cropprice.entity.CommodityEntity;
import com.cropprice.repository.CommodityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommodityService {
	private final CommodityRepository commodityRepository;

    public CommodityService(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    
 public void saveCommodityEntity(List<CropPriceApiDto> records) {
    	
    	for(CropPriceApiDto record : records) {
    		try {
    			CommodityEntity lMaster = new CommodityEntity();
    			
    			lMaster.setName(record.getCommodity());
    			lMaster.setVariety(record.getVariety());
    			lMaster.setGrade(record.getGrade());
    			
    			commodityRepository.save(lMaster);
    		}
    		catch (DataIntegrityViolationException e) {
				// TODO: handle exception
    			System.out.println("Inside Catch Block");
			}
    	}
 }
}
