package com.restaurant.management.Supporting_Services;

import java.util.List;

import com.restaurant.management.Supporting_Entity.Core.*;

public class Revenue_Calculation {
    
    
    public float calculateRevenue(List<Order> order)
    {

        float revenue = 0;

        for(int i = 0; i < order.size();i++)
        {
            revenue+=order.get(i).getOrder_price();
        }
        return revenue;
    }
    
    
}
