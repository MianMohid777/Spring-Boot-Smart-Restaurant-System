package com.restaurant.management.Helper;

import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class DishDairyId implements Serializable {

    private Long dishId;

    private Long dairyId;

    public DishDairyId(Long dishId, Long dairyId) {
        this.dishId = dishId;
        this.dairyId = dairyId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getDairyId() {
        return dairyId;
    }

    public void setDairyId(Long dairyId) {
        this.dairyId = dairyId;
    }

    public DishDairyId() {
    }

    
}
