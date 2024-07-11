package com.restaurant.management.Helper;

import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class orderDrinkId implements Serializable {

    private Long orderId;

    private Long drinkId;

    public orderDrinkId(Long orderId, Long drinkId) {
        this.orderId = orderId;
        this.drinkId = drinkId;
    }

    public orderDrinkId() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Long drinkId) {
        this.drinkId = drinkId;
    }

   

    
}
