package com.restaurant.management.Supporting_Services;


import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;


import com.restaurant.management.Helper.*;

import com.restaurant.management.Supporting_Entity.Core.*;
import com.restaurant.management.Supporting_Entity.Product.*;


public class POS_Receipt {
    
    private POS p;

    public POS_Receipt(POS p) {
        this.p = p;
    }

    public POS_Receipt() {
    }

    public POS generatePOS(Order order,String name)
    {
        POS pos = new POS();
        
        pos.setDate(LocalDate.now());
        pos.setTime(LocalTime.now());
        pos.setManagerName(name);
    
        List<orderDish> orderDish = order.getDishList();
        List<Dish> dishes = new ArrayList<>();

        for(int i = 0;i < orderDish.size() ;i++)
        {
            Dish dish = orderDish.get(i).getDish();
            dishes.add(dish);

        }
        pos.setFood(dishes);

        List<orderDrink> orderDrink = order.getDrinkList();
        List<Drink> drinks = new ArrayList<>();

        for(int i = 0;i < orderDrink.size() ;i++)
        {
            Drink drink = orderDrink.get(i).getDrink();
            drinks.add(drink);
        }
        pos.setDrinks(drinks);

        float subTotal = order.getOrder_price();

        pos.setSubTotal(subTotal);

        
        float gst = (float) (subTotal*0.16);


        pos.setTotalGST(gst);

        pos.setTotal_Amount(subTotal + gst);

        pos.setOrderId(order.getId());

        return pos; 
    }

    public POS getP() {
        return p;
    }

    public void setP(POS p) {
        this.p = p;
    }

    
    
}

