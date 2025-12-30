package com.cropprice.scheduler;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cropprice.dto.CropPriceApiDto;
import com.cropprice.repository.ApiResponse;
import com.cropprice.service.MandiPriceService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CropPriceScheduler {

//	private final MandiService mandiService;
//	private final LocationMasterService locationMasterService;
	
	private final MandiPriceService mandiPriceService;
	
	public CropPriceScheduler(MandiPriceService mandiPriceService) {
		
		this.mandiPriceService = mandiPriceService;
	}
	// @Scheduled(cron = "0 0 6 * * ?")
	@Scheduled(initialDelay = 3000)
    public void syncCropPricesDaily() throws IOException, InterruptedException  {

        List<CropPriceApiDto> apiData = fetchFromGovApi();
     
        //locationService.saveLocationEntity(apiData);
        //commodityService.saveCommodityEntity(apiData);
        mandiPriceService.saveMandiPriceEntity(apiData);
        
        System.out.println("✅ Crop prices synced successfully");
    }

    // Mock method (replace with actual API call)
	
	  private List<CropPriceApiDto> fetchFromGovApi() throws IOException, InterruptedException  {
		  String url = "https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cbb10544313342395a9463157cacbbf2&format=json&limit=1000";
		  HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		  HttpClient client = HttpClient.newBuilder().build();
		  HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		  
		  ObjectMapper mapper = new ObjectMapper();

		  ApiResponse resp =
	                mapper.readValue(response.body(), ApiResponse.class);
		  
		  System.out.println(response.statusCode());
		  System.out.println(response.body());
		 
		  return resp.getRecords(); 
	  } 
}

