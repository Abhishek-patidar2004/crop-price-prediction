package com.cropprice.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cropprice.dto.CropPriceApiDto;
import com.cropprice.entity.CommodityEntity;
import com.cropprice.entity.LocationEntity;
import com.cropprice.entity.MandiPriceEntity;
import com.cropprice.repository.CommodityRepository;
import com.cropprice.repository.LocationRepository;
import com.cropprice.repository.MandiPriceRepository;

@Service
 // @RequiredArgsConstructor
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
 
 @Transactional(readOnly = true)
 public List<CropPriceApiDto> getMaxMinPrice(String commodity, LocalDate arrivalDate, String type){
	 List<MandiPriceEntity> prices = null;
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

 	if("max".equalsIgnoreCase(type)) {
 		 prices =
 				mandiPriceRepository.findMaxPrice(
                     commodity, arrivalDate
                 );
 	}
 	
 	if("min".equalsIgnoreCase(type)) {
 		prices = mandiPriceRepository.findMinPrice(
 				commodity,arrivalDate
 				);
 	}
 	
 	 return prices.stream().map(mp -> {
         CropPriceApiDto dto = new CropPriceApiDto();

         dto.setState(mp.getLocation().getState());
         dto.setDistrict(mp.getLocation().getDistrict());
         dto.setMarket(mp.getLocation().getMarket());
         dto.setCommodity(mp.getCommodity().getName());

         dto.setarrival_date(mp.getArrivalDate().format(formatter));
         dto.setmin_price(mp.getMinPrice());
         dto.setmax_price(mp.getMaxPrice());
         dto.setmodal_price(mp.getModalPrice());

         return dto;
     }).toList();
 }
  
 @Transactional(readOnly = true)
 public List<CropPriceApiDto> getPrices(
		 String state, String district, String market , String commodity
		 ){
	 List<MandiPriceEntity> price = mandiPriceRepository.findPrices(state, district, market, commodity, PageRequest.of(0, 7));
	 
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	 return price.stream().map(mp -> {
         CropPriceApiDto dto = new CropPriceApiDto();

         dto.setState(mp.getLocation().getState());
         dto.setDistrict(mp.getLocation().getDistrict());
         dto.setMarket(mp.getLocation().getMarket());
         dto.setCommodity(mp.getCommodity().getName());

         dto.setarrival_date(mp.getArrivalDate().format(formatter));
         dto.setmin_price(mp.getMinPrice());
         dto.setmax_price(mp.getMaxPrice());
         dto.setmodal_price(mp.getModalPrice());

         return dto;
     }).toList();
 
 }
}