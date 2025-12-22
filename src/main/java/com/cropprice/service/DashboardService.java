package com.cropprice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cropprice.dto.MandiPriceEntity;
import com.cropprice.repository.CropPriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CropPriceRepository repository;

    public DashboardService(CropPriceRepository repository) {
    this.repository = repository;
}

    public MandiPriceEntity getDashboardData(
            String state,
            String district,
            String market,
            String commodity
    ) {

        List<MandiPriceEntity> prices =
            repository.findPricesForDashboard(
                state, district, market, commodity
            );

        if (prices.isEmpty()) {
            throw new RuntimeException("No data found");
        }

        MandiPriceEntity latest = prices.get(0);

//        return new DashboardResponse(
//            latest.getState(),
//            latest.getDistrict(),
//            latest.getMarket(),
//            latest.getCommodity(),
//            latest.getminPrice(),
//            latest.getmaxPrice(),
//            latest.getModalPrice(),
//            latest.getarrivalDate()
//        );
        return latest;
    }
}


