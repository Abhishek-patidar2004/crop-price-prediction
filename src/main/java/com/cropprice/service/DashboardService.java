package com.cropprice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cropprice.dto.MandiPriceEntity;
import com.cropprice.repository.CropPriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CropPriceRepository repository;

    public DashboardService(CropPriceRepository repository) {
    this.repository = repository;
}

    public MandiPriceEntity getDashboardData(
            String state,
            String district,
            String market,
            String commodity,
            String arrivalDate
    ) {

        List<MandiPriceEntity> prices =
            repository.findPricesForDashboard(
                state, district, market, commodity, arrivalDate
            );

        if (prices.isEmpty()) {
            throw new RuntimeException("No data found");
        }

        MandiPriceEntity latest = prices.get(0);

        return latest;
    }
    
    
    public List<MandiPriceEntity> getMaxPrice(
            String commodity,
            String arrivalDate,
            String type
    ) {
    	List<MandiPriceEntity> prices = null;
    	if("max".equalsIgnoreCase(type)) {
    		 prices =
                    repository.findMaxPrice(
                        commodity, arrivalDate
                    );
    	}
    	
    	if("min".equalsIgnoreCase(type)) {
    		prices = repository.findMinPrice(
    				commodity,arrivalDate
    				);
    	}
    	   	
    	return prices;
    }
     
}


