package com.restaurant.management.Services;
import java.util.List;

import com.restaurant.management.Helper.*;
import com.restaurant.management.Supporting_Entity.Core.*;
import com.restaurant.management.Supporting_Entity.Product.*;
import com.restaurant.management.Supporting_Services.*;



public interface ManagerServices {
    
    
    //public Dish create_Dish(Meat m,Cooking_Oil oil,List<Dairy> dairy,List<Spice> spice,List<Vegetable> vege);
    public void saveDish(Dish D);
    public Dish getDishById(Long id);
    public List<Dish> getAllDishes();
    public void deleteDish(Long id);
    
    //Meat
    public void addMeat(Meat m);
    public void deleteMeat(Long id);
    public Meat getMeatById(Long id);
    public List<Meat> getAllMeat();

   // Vegetable:

    public void addVegetable(Vegetable v);
    public void deleteVegetable(Long id);
    public Vegetable getVegetableById(Long id);
    public List<Vegetable> getAllVegetables();

    //Spice:

    public void addSpice(Spice s);
    public void deleteSpice(Long id);
    public Spice getSpiceById(Long id);
    public List<Spice> getAllSpices();

    //Dairy:

    public void addDairy(Dairy d);
    public void deleteDairy(Long id);
    public Dairy getDairyById(Long id);
    public List<Dairy> getAllDairy();

    //Drink:

    public void addDrink(Drink d);
    public void deleteDrink(Long id);
    public Drink getDrinkById(Long id);
    public List<Drink> getAllDrinks();

    //Cooking_oil:

    public void addCookingOil(Cooking_Oil oil);
    public void deleteCookingOil(Long id);
    public Cooking_Oil getCookingOilById(Long id);
    public List<Cooking_Oil> getAllCookingOils();
    public Cooking_Oil getCookingOilByName(String name);
    
    public void add_In_Menu(Menu M);
    public void delete_In_Menu(Menu M);
    public void updateMenu(Menu M);
    
    public void processPayment(Payment_Process P);
    
    public void build_Order(Build_Order b);
    
    public void generatePOS(POS_Receipt pos);
    
    public void viewMenu(Menu M);


    public void saveDairyIds(List<Long> Ids);
    public void saveVegeIds(List<Long> Ids);
    public void saveSpiceIds(List<Long> Ids);

    public List<Long> getDairyIds();
    public List<Long> getVegeIds();
    public List<Long> getSpiceIds();

    public void temporarySaveDish(Dish d);
    public Dish getTempDish();


    public List<Dish> getMenuDishes();
    public List<Dish> getRemainingDishes();

    public List<Drink> getMenuDrinks();
    public List<Drink> getRemainingDrinks();

    public Dish getSearchedDishes(String name);

    public Drink getSearchedDrink(String name);

    public void savePOS(POS p);

    public POS getPosById(Long id);

    public void storePOS(POS p);

    public POS getStoredPos();

    public Long getLatestOrderId();


    
}
