package com.restaurant.management.Supporting_Services;

import com.restaurant.management.Supporting_Entity.Core.*;
import com.restaurant.management.Supporting_Entity.Product.Dish;
import com.restaurant.management.Supporting_Entity.Product.Drink;
import com.restaurant.management.Helper.*;
import com.restaurant.management.Repository.OrderRepository;

import java.util.List;


import org.springframework.stereotype.Service;


@Service
public class Build_Order  {
    
    private Order tempOrder;
    private Drink tempDrink;
    private List<orderDish> orderDishs;
    private List<orderDrink> orderDrinks;
    private OrderRepository orderRepo;

    private List<Long> dishIds;
    private List<Long> drinkIds;

    private List<Long> dishQty;
    private List<Long> drinkQty;

   


    public Build_Order(Order tempOrder, Drink tempDrink, List<orderDish> orderDishs, List<orderDrink> orderDrinks,
            OrderRepository orderRepo, List<Long> dishIds, List<Long> drinkIds, List<Long> dishQty,
            List<Long> drinkQty) {
        this.tempOrder = tempOrder;
        this.tempDrink = tempDrink;
        this.orderDishs = orderDishs;
        this.orderDrinks = orderDrinks;
        this.orderRepo = orderRepo;
        this.dishIds = dishIds;
        this.drinkIds = drinkIds;
        this.dishQty = dishQty;
        this.drinkQty = drinkQty;
    }


    public void setTempOrder(Order tempOrder) {
        this.tempOrder = tempOrder;
    }


    public void setOrderDishs(List<orderDish> orderDishs) {
        this.orderDishs = orderDishs;
    }


    public List<orderDrink> getOrderDrinks() {
        return orderDrinks;
    }

    public List<orderDish> getOrderDishs() {
        return orderDishs;
    }


    public void setOrderDrinks(List<orderDrink> orderDrinks) {
        this.orderDrinks = orderDrinks;
    }


    public void temporarySaveOrder(Order o)
    {
      tempOrder = o;
    }

   
    public void temporarySaveOrderDishes(orderDish orderDishs)
    {
       this.orderDishs.add(orderDishs);
    }
    
    public void temporarySaveOrderDrinks(orderDrink orderDrinks)
    {
        this.orderDrinks.add(orderDrinks);
    }

  
    public Order getTempOrder()
    {
        return tempOrder;
    }


    public Drink getTempDrink() {
        return tempDrink;
    }


    public void saveTempDrink(Drink tempDrink) {
        this.tempDrink = tempDrink;
    }


   public  int calculateOrderPrice(List<orderDish> x,List<orderDrink> y)
   {
    int subTotal = 0;
    int dishTotal = 0;
    int drinkTotal = 0;


    for(int i = 0; i < x.size();i++)
    {
        Dish temp = x.get(i).getDish();
        Long qty = x.get(i).getQty();
        subTotal = (int) (temp.getSellingPrice()*qty);
        dishTotal+=subTotal;
    }

    subTotal = 0;

    for(int i = 0; i < y.size();i++)
    {
        Drink temp = y.get(i).getDrink();
        Long qty = y.get(i).getQty();
        subTotal = (int) (temp.getSellingPrice()*qty);
        drinkTotal+=subTotal;
    }
    return dishTotal+drinkTotal;
   }


  
    public void saveOrder(Order o)
    {
        orderRepo.save(o);
        
    }

   
    public Order getOrderById(Long id)
    {
        return orderRepo.findById(id).get();
    }

    
    public List<Order> getAllOrder()
    {
        return orderRepo.findAll();
    }


    public void setTempDrink(Drink tempDrink) {
        this.tempDrink = tempDrink;
    }


    public OrderRepository getOrderRepo() {
        return orderRepo;
    }


    public void setOrderRepo(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }


    public List<Long> getDishIds() {
        return dishIds;
    }


    public void setDishIds(List<Long> dishIds) {
        this.dishIds = dishIds;
    }


    public List<Long> getDrinkIds() {
        return drinkIds;
    }


    public void setDrinkIds(List<Long> drinkIds) {
        this.drinkIds = drinkIds;
    }


    public List<Long> getDishQty() {
        return dishQty;
    }


    public void setDishQty(List<Long> dishQty) {
        this.dishQty = dishQty;
    }


    public List<Long> getDrinkQty() {
        return drinkQty;
    }


    public void setDrinkQty(List<Long> drinkQty) {
        this.drinkQty = drinkQty;
    }

    public Order getLatestOrder()
    {
        return orderRepo.findLastOrder();
    }

    
 }
