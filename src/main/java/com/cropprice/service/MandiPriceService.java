package com.cropprice.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cropprice.dto.CropPriceApiDto;
import com.cropprice.entity.CommodityEntity;
import com.cropprice.entity.LocationEntity;
import com.cropprice.entity.MandiPriceEntity;
import com.cropprice.repository.CommodityRepository;
import com.cropprice.repository.LocationRepository;
import com.cropprice.repository.MandiPriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MandiPriceService {
	private final MandiPriceRepository mandiPriceRepository;
	private final LocationRepository locationRepository;
	private final CommodityRepository commodityRepository;

    public MandiPriceService(MandiPriceRepository mandiPriceRepository, LocationRepository locationRepository, CommodityRepository commodityRepository) {
        this.mandiPriceRepository = mandiPriceRepository;
        this.locationRepository = locationRepository;
        this.commodityRepository = commodityRepository;
    }

    
 public void saveMandiPriceEntity(List<CropPriceApiDto> records) {
    	
    	for(CropPriceApiDto record : records) {
    		try {
    			MandiPriceEntity lMaster = new MandiPriceEntity();
    			
    			//Scope for improvement -- can we add parameterized constuctor
    			LocationEntity newLocation = new LocationEntity();
    			newLocation.setState(record.getState());
    			newLocation.setDistrict(record.getDistrict());
    			newLocation.setMarket(record.getMarket());
    			
    			CommodityEntity newCommodity = new CommodityEntity();
    			newCommodity.setName(record.getCommodity());
    			newCommodity.setVariety(record.getVariety());
    			newCommodity.setGrade(record.getGrade());
    			
    			LocationEntity location = locationRepository
    			        .findByStateAndDistrictAndMarket(record.getState(), record.getDistrict(), record.getMarket())
    			        .orElseGet(() -> locationRepository.save(newLocation));

    			CommodityEntity commodity = commodityRepository
    			        .findByNameAndVarietyAndGrade(record.getCommodity(), record.getVariety(), record.getGrade())
    			        .orElseGet(() -> commodityRepository.save(newCommodity));
    			
    			lMaster.setLocation(location);
    			lMaster.setCommodity(commodity);
    			
    			DateTimeFormatter formatter =
    			        DateTimeFormatter.ofPattern("dd/MM/yyyy");

    			LocalDate date =
    			        LocalDate.parse(record.getarrival_date(), formatter);

    			lMaster.setArrivalDate(date);
    			
    			lMaster.setMinPrice(record.getmin_price());
    			lMaster.setMaxPrice(record.getmax_price());
    			lMaster.setModalPrice(record.getmodal_price());
    			
    			mandiPriceRepository.save(lMaster);
    		}
    		catch (DataIntegrityViolationException e) {
				// TODO: handle exception
    			System.out.println("Inside Catch Block");
			}
    	}
 }

}