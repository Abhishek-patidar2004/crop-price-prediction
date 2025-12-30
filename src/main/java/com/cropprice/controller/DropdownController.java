package com.cropprice.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cropprice.dto.CropPriceApiDto;
import com.cropprice.repository.CommodityRepository;
import com.cropprice.repository.LocationRepository;
import com.cropprice.service.MandiPriceService;

@RestController
@RequestMapping("/api/dropdown")

public class DropdownController {

    private final LocationRepository locationRepo;
    private final CommodityRepository commodityRepo;
    private final MandiPriceService mandiPriceService;

    public DropdownController(
            LocationRepository locationRepo,
            CommodityRepository commodityRepo,
            MandiPriceService mandiPriceService) {
        this.locationRepo = locationRepo;
        this.commodityRepo = commodityRepo;
        this.mandiPriceService = mandiPriceService;
    }

    @GetMapping("/states")
    public List<String> getStates() {
        return locationRepo.findAllStates();
    }

    @GetMapping("/districts")
    public List<String> getDistricts(@RequestParam String state) {
        return locationRepo.findDistrictsByState(state);
    }

    @GetMapping("/markets")
    public List<String> getMarkets(
            @RequestParam String state,
            @RequestParam String district) {
        return locationRepo.findMarketsByStateAndDistrict(state, district);
    }

    @GetMapping("/commodities")
    public List<String> getCommodities() {
        return commodityRepo.findAllCommodityNames();
    }
    
    @GetMapping("/checkprice")
    public List<CropPriceApiDto> getCropPrice(
    		@RequestParam String state,
            @RequestParam String district,
            @RequestParam String market,
            @RequestParam String commodity,
            @RequestParam String arrivalDate
    		){
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return mandiPriceService.getMandiPriceEntity(state, district, market, commodity, LocalDate.parse(arrivalDate, formatter));
    }
    
    @GetMapping ("/max-min-price")
    public List<CropPriceApiDto> getMaxMinPrice(
    		@RequestParam String commodity,
    		@RequestParam String arrivalDate,
    		@RequestParam String type
    		){
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		return mandiPriceService.getMaxMinPrice(commodity, LocalDate.parse(arrivalDate, formatter), type);
    		
    		}
}


