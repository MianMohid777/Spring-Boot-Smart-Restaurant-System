package com.restaurant.management.Helper;

import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class DishVegId implements Serializable {

    private Long dishId;

    private Long vegId;

    public DishVegId(Long dishId, Long VegId) {
        this.dishId = dishId;
        this.vegId = VegId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getVegId() {
        return vegId;
    }

    public void setVegId(Long VegId) {
        this.vegId = VegId;
    }

    
}
