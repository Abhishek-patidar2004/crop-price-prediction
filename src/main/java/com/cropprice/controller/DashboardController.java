package com.cropprice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cropprice.dto.MandiPriceEntity;
import com.cropprice.service.DashboardService;

import lombok.RequiredArgsConstructor;

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
        @RequestParam String commodity
    ) {
        return service.getDashboardData(
            state, district, market, commodity
        );
    }
}


