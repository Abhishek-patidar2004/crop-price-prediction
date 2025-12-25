package com.cropprice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cropprice.dto.MandiPriceEntity;
import com.cropprice.service.DashboardService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

	  private final DashboardService service;
    
    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping(
    		value = "/prices",
            produces = "application/json")
    public MandiPriceEntity getDashboardPrices(
        @RequestParam String state,
        @RequestParam String district,
        @RequestParam String market,
        @RequestParam String commodity,
        @RequestParam String arrivalDate
    ) {
        return service.getDashboardData(
            state, district, market, commodity , arrivalDate
        );
    }
    
    

    @GetMapping("/max-price")
    public List<MandiPriceEntity> getMaxPrice(
            @RequestParam String commodity,
            @RequestParam String arrivalDate,
            @RequestParam String type
            
           
    ) {
        return service.getMaxPrice(commodity, arrivalDate, type);
    }
}


