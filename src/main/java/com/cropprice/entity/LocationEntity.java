package com.cropprice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "location",
    		 uniqueConstraints = {
    			        @UniqueConstraint(
    			            columnNames = {"state", "district", "market"}
    			        )
    			    }
)
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;
    private String district;
    private String market;

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter & Setter for state
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Getter & Setter for district
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    // Getter & Setter for market
    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
