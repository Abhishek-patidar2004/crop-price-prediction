package com.cropprice.dto;

public class CropPriceApiDto {
	private String state;
    private String district;
    private String market;
    private String commodity;
    private String variety;
    private String grade;
    private String arrival_date;
    private int min_price;
    private int max_price;
    private int modal_price;
   
    // getters and setters
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getMarket() { return market; }
    public void setMarket(String market) { this.market = market; }

    public String getCommodity() { return commodity; }
    public void setCommodity(String commodity) { this.commodity = commodity; }
    
    public String getvariety() { return variety;}
    public void setvariety(String variety) {this.variety = variety;}
    
    public String getgrade() {return grade;}
    public void setgrade(String grade) {this.grade = grade;}
    
    public String getarrival_date() {return arrival_date;}
    public void setarrival_date(String arrival_date) {this.arrival_date = arrival_date;}
    
    public int getmin_price() {return min_price;}
    public void setmin_price(int min_price) {this.min_price = min_price;}
    
    public int getmax_price() {return max_price;}
    public void setmax_price(int max_price) {this.max_price=max_price;}

    public int getModal_price() { return modal_price; }
    public void setModal_price(int modal_price) { this.modal_price = modal_price; }
}
