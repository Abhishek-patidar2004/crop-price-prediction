package com.cropprice.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cropprice.dto.CropPriceApiDto;
import com.cropprice.dto.SuggestionRespnseApiDto;
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
    
    @GetMapping ("/max-min-price")
    public List<CropPriceApiDto> getMaxMinPrice(
    		@RequestParam String commodity,
    		@RequestParam String arrivalDate,
    		@RequestParam String type
    		){
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		return mandiPriceService.getMaxMinPrice(commodity, LocalDate.parse(arrivalDate, formatter), type);
    		
    		}

    
    @GetMapping("/checkprice")
    public SuggestionRespnseApiDto checkPrice(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String market,
            @RequestParam(required = false) String commodity
    ) {
		
		state = state != null ? state.trim() : null;
		district = district != null ? district.trim() : null;
		market = market != null ? market.trim() : null;
		commodity = commodity != null ? commodity.trim() : null;
		
		//String suggestion = askGpt(); // commented this code as I don't have paid version of openai
		
		//currently using simulation for the functionality
		String suggestion = askGPTSimulation();
		List<CropPriceApiDto> list = mandiPriceService.getPrices(state, district, market, commodity);
		
		SuggestionRespnseApiDto response = new SuggestionRespnseApiDto();
		response.setCropPriceApiDto(list);
		response.setSuggestion(suggestion);
		
		
		return response;
        
    }
         
    public String askGPTSimulation() {
    	ArrayList<String> list = new ArrayList<>();
    	
    	list.add("Sell crop within 7 days to get better profit");
    	list.add("Hold this crop for 20 days");
    	list.add("Sell it as soon as possible");
    	list.add("This crop having more chance of improvement in next month");
    	list.add("Price of this crop is stable you are free to sell according to you");
    	list.add( "It's hard to predict the price of this crop because it's rate is unstable");
    	
    	Random random = new Random();
    	int index = random.nextInt(list.size()); 

        return list.get(index);
    	
    }
    
   /* -------------
       OPENAI
      -------------   
    This is my openai integration part to get suggestion from AI...
   */ 
    public String askGpt() {

        String apiKey = "UPDATE IT WITH ACTUAL API KEY";
        String url = "https://api.openai.com/v1/chat/completions";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String requestBody = """
            {
              "model": "gpt-4.1-mini",
              "messages": [
                {
                  "role": "user",
                  "content": "Predict onion price for next 7 days"
                }
              ]
            }
            """; //.formatted(prompt.replace("\"", "\\\""));

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
        );

        // Extract only the message content
        String body = response.getBody();

        int index = body.indexOf("\"content\":\"");
        if (index == -1) return "No response";

        int start = index + 11;
        int end = body.indexOf("\"", start);

        return body.substring(start, end)
                .replace("\\n", "\n")
                .replace("\\\"", "\"");
    }

        
    
    
}


