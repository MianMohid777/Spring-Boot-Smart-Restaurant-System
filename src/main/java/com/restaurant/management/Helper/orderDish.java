package com.restaurant.management.Helper;


import com.restaurant.management.Supporting_Entity.Core.*;
import com.restaurant.management.Supporting_Entity.Product.*;

import jakarta.persistence.*;


@Entity
@Table(name = "order_dishes")
public class orderDish {

    @EmbeddedId
    private orderDishId id;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("dishId")
    private Dish dish;

    @Column(name = "quantity")
    private Long qty;

    public orderDish(orderDishId id, Order order, Dish dish, Long qty) {
        this.id = id;
        this.order = order;
        this.dish = dish;
        this.qty = qty;
    }

    public orderDish() {
    }

    public orderDishId getId() {
        return id;
    }

    public void setId(orderDishId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    

}


    
    
