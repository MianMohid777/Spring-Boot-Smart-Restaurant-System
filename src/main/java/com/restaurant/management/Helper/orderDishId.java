package com.restaurant.management.Helper;

import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class orderDishId implements Serializable {

    private Long orderId;

    private Long dishId;

    public orderDishId(Long orderId, Long dishId) {
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public orderDishId() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

   

    
}
