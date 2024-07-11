package com.restaurant.management.Helper;



import com.restaurant.management.Supporting_Entity.Product.*;

import jakarta.persistence.*;


@Entity
@Table(name = "dishes_Vegetable")
public class dishVeg {

    @EmbeddedId
    private DishVegId id;

    @ManyToOne
    @MapsId("dishId")
    private Dish dish;

    @ManyToOne
    @MapsId("vegId")
    private Vegetable veg;

    @Column(name = "quantity")
    private Long qty;

    public dishVeg(DishVegId id, Dish dish, Vegetable Veg, Long qty) {
        this.id = id;
        this.dish = dish;
        this.veg = Veg;
        this.qty = qty;
    }

    public DishVegId getId() {
        return id;
    }

    public void setId(DishVegId id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Vegetable getVeg() {
        return veg;
    }

    public void setVeg(Vegetable Veg) {
        this.veg = Veg;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    

    

}


    
    
