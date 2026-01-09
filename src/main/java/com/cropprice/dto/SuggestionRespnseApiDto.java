package com.cropprice.dto;

import java.util.List;

public class SuggestionRespnseApiDto {
	private String suggestion;
	private List<CropPriceApiDto> cropPriceApiDto;
	
	public SuggestionRespnseApiDto(String suggestion, List<CropPriceApiDto> cropPriceApiDto) {
		this.suggestion = suggestion;
		this.cropPriceApiDto = cropPriceApiDto;
	}
	
	
	public String getSuggestion() {
		return suggestion;
	}
	
	public List<CropPriceApiDto> getCropPriceApiDto(){
		return cropPriceApiDto;
	}
}
