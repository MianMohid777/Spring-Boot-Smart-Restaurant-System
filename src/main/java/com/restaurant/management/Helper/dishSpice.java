package com.restaurant.management.Helper;


import com.restaurant.management.Supporting_Entity.Product.*;

import jakarta.persistence.*;


@Entity
@Table(name = "dishes_spice")
public class dishSpice {

    @EmbeddedId
    private DishSpiceId id;

    @ManyToOne
    @MapsId("dishId")
    private Dish dish;

    @ManyToOne
    @MapsId("spiceId")
    private Spice Spice;

    @Column(name = "quantity")
    private Long qty;

    public dishSpice(DishSpiceId id, Dish dish, Spice Spice, Long qty) {
        this.id = id;
        this.dish = dish;
        this.Spice = Spice;
        this.qty = qty;
    }

    public DishSpiceId getId() {
        return id;
    }

    public void setId(DishSpiceId id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Spice getSpice() {
        return Spice;
    }

    public void setSpice(Spice Spice) {
        this.Spice = Spice;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    

}


    
    
