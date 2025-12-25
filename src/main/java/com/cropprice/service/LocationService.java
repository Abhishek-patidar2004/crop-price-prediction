package com.cropprice.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cropprice.dto.CropPriceApiDto;
import com.cropprice.entity.LocationEntity;
import com.cropprice.repository.LocationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    
 public void saveLocationEntity(List<CropPriceApiDto> records) {
    	
    	for(CropPriceApiDto record : records) {
    		try {
    			LocationEntity lMaster = new LocationEntity();
    			
    			lMaster.setDistrict(record.getDistrict());
    			lMaster.setMarket(record.getMarket());
    			lMaster.setState(record.getState());
    			
    			locationRepository.save(lMaster);
    		}
    		catch (DataIntegrityViolationException e) {
				// TODO: handle exception
    			System.out.println("Inside Catch Block");
			}
    	}
 }
}

