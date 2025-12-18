package com.cropprice.scheduler;



/*import com.yourname.cropprediction.dto.CropPriceApiDto;
import com.yourname.cropprediction.service.CropPriceIngestionService;
*/
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cropprice.dto.CropPriceApiDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CropPriceScheduler {

    //private final CropPriceIngestionService ingestionService;

   // @Scheduled(cron = "0 0 6 * * ?")
	@Scheduled(initialDelay = 10000, fixedRate = 600000)
    public void syncCropPricesDaily() {

        // 1️⃣ Fetch data from Govt API
        List<CropPriceApiDto> apiData = fetchFromGovApi();

        // 2️⃣ Store data into DB
       // ingestionService.ingestCropPrices(apiData);

        System.out.println("✅ Crop prices synced successfully");
    }

    // Mock method (replace with actual API call)
	
	  private List<CropPriceApiDto> fetchFromGovApi() {
		  // Call RestTemplate /WebClient here 
		  
		  /*
		   * 1. API call
		   * 2. pick 1 by record from json and put them in list of object (CropPriceApiDto)
		   * 	- convert json object to java object
		   * 3. return list
		   */
		  return List.of(); 
	  }
	 
}

