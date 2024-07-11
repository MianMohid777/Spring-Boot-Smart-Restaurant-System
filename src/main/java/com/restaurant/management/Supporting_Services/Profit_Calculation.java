package com.restaurant.management.Supporting_Services;


import java.util.List;

import com.restaurant.management.Helper.orderDish;
import com.restaurant.management.Helper.orderDrink;
import com.restaurant.management.Supporting_Entity.Core.*;
import com.restaurant.management.Supporting_Entity.Product.*;

public class Profit_Calculation  {
    
    
    public float calculateProfit(Order order)
    {
        List<orderDish> dishes = order.getDishList();
        List<orderDrink> drinks = order.getDrinkList();

        float profit = 0;
        float subProfit = 0;

        for(int i = 0; i < dishes.size();i++)
        {
          Dish dish = dishes.get(i).getDish();
          Long qty = dishes.get(i).getQty();

          subProfit = dish.getSellingPrice() - dish.getCostPrice();
          profit+= (subProfit*qty);
        }

        subProfit = 0;
        for(int i = 0; i < drinks.size();i++)
        {
          Drink drink = drinks.get(i).getDrink();
          Long qty = drinks.get(i).getQty();

          subProfit = (float) (drink.getSellingPrice() - drink.getCostPrice());
          profit+=(subProfit*qty);
        }
        
        
        return profit;
    }
}