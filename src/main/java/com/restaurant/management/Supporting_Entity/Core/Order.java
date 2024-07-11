package com.restaurant.management.Supporting_Entity.Core;

import com.restaurant.management.Helper.*;
import com.restaurant.management.Supporting_Entity.Product.Drink;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="orders")
public class Order {
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate order_date;
    private int order_price;
    
      
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<orderDish> dishList;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<orderDrink> drinkList;

   
    public Order( Long id, LocalDate order_date, int order_price, List<orderDish> dishList,
    List<orderDrink> drinkList) {

this.id = id;
this.order_date = order_date;
this.order_price = order_price;
this.dishList = dishList;
this.drinkList = drinkList;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public int getOrder_price() {
        return order_price;
    }

    public void setOrder_price(int order_price) {
        this.order_price = order_price;
    }

    public List<orderDish> getDishList() {
        return dishList;
    }

    public void setDishList(List<orderDish> dishList) {
        this.dishList = dishList;
    }

    public List<orderDrink> getDrinkList() {
        return drinkList;
    }

    public void setDrinkList(List<orderDrink> drinkList) {
        

        this.drinkList = drinkList;
       }
    

    public Order() {
    } 
}       