package com.cropprice.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cropprice.dto.CropPriceApiDto;
import com.cropprice.dto.LocationMaster;
import com.cropprice.dto.MandiPriceEntity;
import com.cropprice.repository.MandiPriceRepository;

import java.util.List;

@Service
public class MandiService {

    private final MandiPriceRepository repository;

    public MandiService(MandiPriceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveMandiData(List<CropPriceApiDto> records) {

        List<MandiPriceEntity> entities = records.stream()
                .map(this::mapToEntity)
                .filter(entity ->
                !repository.existsByStateAndDistrictAndMarketAndCommodityAndVarietyAndGradeAndArrivalDateAndMinPriceAndMaxPriceAndModalPrice(
                    entity.getState(),
                    entity.getDistrict(),
                    entity.getMarket(),
                    entity.getCommodity(),
                    entity.getvariety(),
                    entity.getgrade(),
                    entity.getarrivalDate(),
                    entity.getminPrice(),
                    entity.getmaxPrice(),
                    entity.getModalPrice()
                )
            )
                .toList();

        repository.saveAll(entities);

    }
    
    private MandiPriceEntity mapToEntity(CropPriceApiDto record) {

	    MandiPriceEntity entity = new MandiPriceEntity();

	    entity.setState(record.getState());
	    entity.setDistrict(record.getDistrict());
	    entity.setMarket(record.getMarket());
	    entity.setCommodity(record.getCommodity());
	    entity.setvariety(record.getvariety());
	    entity.setgrade(record.getgrade());
	    entity.setarrivalDate(record.getarrival_date());

	    entity.setmaxPrice(record.getmax_price());
	    entity.setminPrice(record.getmin_price());
	    entity.setModalPrice(record.getModal_price());

	    return entity;
	}
}

