package com.restaurant.management.Helper;


import com.restaurant.management.Supporting_Entity.Product.*;

import jakarta.persistence.*;


@Entity
@Table(name = "dishes_dairy")
public class dishDairy {

    @EmbeddedId
    private DishDairyId id;

    @ManyToOne
    @MapsId("dishId")
    private Dish dish;

    @ManyToOne
    @MapsId("dairyId")
    private Dairy dairy;

    @Column(name = "quantity")
    private Long qty;

    public dishDairy(DishDairyId id, Dish dish, Dairy dairy, Long qty) {
        this.id = id;
        this.dish = dish;
        this.dairy = dairy;
        this.qty = qty;
    }

    public DishDairyId getId() {
        return id;
    }

    public void setId(DishDairyId id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Dairy getDairy() {
        return dairy;
    }

    public void setDairy(Dairy dairy) {
        this.dairy = dairy;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    
    

}


    
    
