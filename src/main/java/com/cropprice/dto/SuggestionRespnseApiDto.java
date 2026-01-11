package com.cropprice.dto;

import java.util.List;

public class SuggestionRespnseApiDto {

	private String suggestion;
	private List<CropPriceApiDto> cropPriceApiDto;
	
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	public String getSuggestion() {
		return suggestion;
	}
	
	public void setCropPriceApiDto(List<CropPriceApiDto> cropPriceApiDto) {
		this.cropPriceApiDto = cropPriceApiDto;
	}
	
	public List<CropPriceApiDto> getCropPriceApiDto() {
		return cropPriceApiDto;
	}
}
