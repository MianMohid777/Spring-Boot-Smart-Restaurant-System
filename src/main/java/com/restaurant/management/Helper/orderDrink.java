package com.restaurant.management.Helper;


import com.restaurant.management.Supporting_Entity.Core.*;
import com.restaurant.management.Supporting_Entity.Product.*;

import jakarta.persistence.*;


@Entity
@Table(name = "order_drinks")
public class orderDrink {

    @EmbeddedId
    private orderDrinkId id;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("drinkId")
    public Drink drink;

    @Column(name = "quantity")
    private Long qty;

    public orderDrink(orderDrinkId id, Order order, Drink drink, Long qty) {
        this.id = id;
        this.order = order;
        this.drink = drink;
        this.qty = qty;
    }

    public orderDrink() {
    }

    public orderDrinkId getId() {
        return id;
    }

    public void setId(orderDrinkId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Drink getDrink() {
        return this.drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    

}


    
    
