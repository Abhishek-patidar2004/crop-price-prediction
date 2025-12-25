package com.cropprice.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "mandi_price",
    uniqueConstraints = { @UniqueConstraint(
        columnNames = {"location_id", "commodity_id", "arrival_date"}
    )
    }
)
public class MandiPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commodity_id", nullable = false)
    private CommodityEntity commodity;

    private LocalDate arrivalDate;

    private int minPrice;
    private int maxPrice;
    private int modalPrice;

    // getters & setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter & Setter for location
    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    // Getter & Setter for commodity
    public CommodityEntity getCommodity() {
        return commodity;
    }

    public void setCommodity(CommodityEntity commodity) {
        this.commodity = commodity;
    }

    // Getter & Setter for arrivalDate
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    // Getter & Setter for minPrice
    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    // Getter & Setter for maxPrice
    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    // Getter & Setter for modalPrice
    public int getModalPrice() {
        return modalPrice;
    }

    public void setModalPrice(int modalPrice) {
        this.modalPrice = modalPrice;
    }
}

