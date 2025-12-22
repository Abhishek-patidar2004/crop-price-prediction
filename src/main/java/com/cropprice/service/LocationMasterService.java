package com.cropprice.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cropprice.dto.CropPriceApiDto;
import com.cropprice.dto.LocationMaster;
import com.cropprice.repository.LocationMasterRepository;

@Service
@RequiredArgsConstructor
public class LocationMasterService {

    private final LocationMasterRepository repository;
    public LocationMasterService(LocationMasterRepository repository) {
        this.repository = repository;
    }

    public void saveLocationData(List<CropPriceApiDto> records) {
    	
    	for(CropPriceApiDto record : records) {
    		try {
    			LocationMaster lMaster = new LocationMaster();
    			
    			lMaster.setDistrict(record.getDistrict());
    			lMaster.setMarket(record.getMarket());
    			lMaster.setState(record.getState());
    			
    			repository.save(lMaster);
    		}
    		catch (DataIntegrityViolationException e) {
				// TODO: handle exception
    			System.out.println("Inside Catch Block");
			}
    	}

    }
    
    private LocationMaster mapToEntity(CropPriceApiDto record) {

    	LocationMaster entity = new LocationMaster();

	    entity.setState(record.getState());
	    entity.setDistrict(record.getDistrict());
	    entity.setMarket(record.getMarket());

	    return entity;
	}
}
