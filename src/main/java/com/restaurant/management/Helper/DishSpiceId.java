package com.restaurant.management.Helper;

import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class DishSpiceId implements Serializable {

    private Long dishId;

    private Long spiceId;

    public DishSpiceId(Long dishId, Long SpiceId) {
        this.dishId = dishId;
        this.spiceId = SpiceId;
    }

    public DishSpiceId() {
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getSpiceId() {
        return spiceId;
    }

    public void setSpiceId(Long SpiceId) {
        this.spiceId = SpiceId;
    }

    
}
