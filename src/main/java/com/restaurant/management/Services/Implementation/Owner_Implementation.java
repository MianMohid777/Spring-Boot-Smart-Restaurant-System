package com.restaurant.management.Services.Implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.restaurant.management.Services.OwnerServices;
import com.restaurant.management.Supporting_Entity.Core.*;
import com.restaurant.management.Supporting_Entity.Product.*;
import com.restaurant.management.Supporting_Services.*;
import com.restaurant.management.Graphs.*;
import com.restaurant.management.Repository.*;

@Service
public class Owner_Implementation implements OwnerServices{


    @Autowired
    private DishRepository dishRepo;

    @Autowired
    private DrinkRepository drinkRepo;

    @Autowired
    private PosRepository posRepo;

    @Autowired
    private OrderRepository orderRepo;
    
    public Owner_Implementation(DishRepository dishRepo, DrinkRepository drinkRepo, PosRepository posRepo,
            OrderRepository orderRepo) {
        this.dishRepo = dishRepo;
        this.drinkRepo = drinkRepo;
        this.posRepo = posRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public List<Dish> getMenuDishes()
    {
      return dishRepo.findDishesByStatusTrue();
    }


    @Override
    public List<Drink> getMenuDrinks()
    {
        return drinkRepo.findDrinksByStatusTrue();
    }

    @Override
    public List<Float> getAllGSTforDates(Date start,Date end)
    {
        return posRepo.findGstByDates(start, end);
    }

    @Override
    public List<Order> getAllOrderforDates(Date start,Date end)
    {
        return orderRepo.findOrderByDates(start, end);
    }

    @Override
    public List<Object[]> getHotFavDish(Date start,Date end)
    {
        return orderRepo.findDishQuantitiesByOrderDate(start, end);
    }

    @Override
    public Dish getDishById(Long id) {
       return dishRepo.findById(id).get();
    }

    @Override
    public List<Order> getOrderByDate(Date date)
    {
        return orderRepo.findOrderByDate(date);
    }
 }
