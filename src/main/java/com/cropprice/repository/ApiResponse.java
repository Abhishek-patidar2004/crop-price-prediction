package com.cropprice.repository;
import java.util.List;

import com.cropprice.dto.CropPriceApiDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
	 private List<CropPriceApiDto> records;

	    public List<CropPriceApiDto> getRecords() {
	        return records;
	    }

	    public void setRecords(List<CropPriceApiDto> records) {
	        this.records = records;
	    }
}
