package com.cropprice.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "mandi_price",
uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "state",
                "district",
                "market",
                "commodity",
                "variety",
                "grade",
                "arrival_date",
                "min_price",
                "max_price",
                "modal_price"
            }
        )
    }
		)
public class MandiPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;
    private String district;
    private String market;
    private String commodity;
    private String variety;
    private String grade;
    @Column(name = "arrival_date")
    private String arrivalDate;
    @Column(name = "min_price")
    private int minPrice;
    @Column(name = "max_price")
    private int maxPrice;
    @Column(name = "modal_price")
    private int modalPrice;

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
    
    public String getarrivalDate() {return arrivalDate;}
    public void setarrivalDate(String arrivalDate) {this.arrivalDate = arrivalDate;}
    
    public int getminPrice() {return minPrice;}
    public void setminPrice(int minPrice) {this.minPrice = minPrice;}
    
    public int getmaxPrice() {return maxPrice;}
    public void setmaxPrice(int maxPrice) {this.maxPrice=maxPrice;}

    public int getModalPrice() { return modalPrice; }
    public void setModalPrice(int modalPrice) { this.modalPrice = modalPrice; }
    
   

}

