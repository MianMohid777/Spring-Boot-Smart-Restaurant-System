package com.restaurant.management.Services;
import com.restaurant.management.Supporting_Entity.Core.*;
import com.restaurant.management.Supporting_Entity.Product.*;
import com.restaurant.management.Supporting_Services.*;

import java.util.Date;
import java.util.List;

import com.restaurant.management.Graphs.*;

public interface OwnerServices {
    


    public List<Dish> getMenuDishes();

    public List<Drink> getMenuDrinks();

    public List<Float> getAllGSTforDates(Date start,Date end);

    public List<Order> getAllOrderforDates(Date start,Date end);

    public List<Object[]> getHotFavDish(Date start,Date end);

    public Dish getDishById(Long id);

    public List<Order> getOrderByDate(Date date);

}
    
