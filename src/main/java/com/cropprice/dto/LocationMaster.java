package com.cropprice.dto;

	import jakarta.persistence.*;

	@Entity
	@Table(
	    name = "location_master",
	    uniqueConstraints = {
	        @UniqueConstraint(columnNames = {"state", "district", "market"})
	    }
	)
	public class LocationMaster {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String state;

	    @Column(nullable = false)
	    private String district;

	    @Column(nullable = false)
	    private String market;

	    // getters & setters
	    public Long getId() {
	        return id;
	    }

	    public String getState() {
	        return state;
	    }

	    public String getDistrict() {
	        return district;
	    }

	    public String getMarket() {
	        return market;
	    }

	    // ===== Setters =====

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public void setState(String state) {
	        this.state = state;
	    }

	    public void setDistrict(String district) {
	        this.district = district;
	    }

	    public void setMarket(String market) {
	        this.market = market;
	    }
	}


