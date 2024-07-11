package com.restaurant.management.Services.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import com.restaurant.management.Helper.*;
import com.restaurant.management.Repository.*;
import com.restaurant.management.Services.ManagerServices;
import com.restaurant.management.Supporting_Entity.Core.*;
import com.restaurant.management.Supporting_Entity.Product.*;
import com.restaurant.management.Supporting_Services.*;


@Service
public class Manager_Implementation implements ManagerServices{
    
    private MeatRepository meatRepo;
    private DairyRepository dairyRepo;
    private VegeRepository vegeRepo;
    private DrinkRepository drinkRepo;
    private OilRepository oilRepo;
    private SpiceRepository spiceRepo;

    @Autowired
    private PosRepository posRepo;

    @Autowired
    private DishRepository dishRepo;
    @Autowired
    private OrderRepository orderRepo;
    

    
    private List<Long> dairyIds; 
    private List<Long> vegeIds; 
    private List<Long> spiceIds; 

    private Dish tempDish;

    private POS pos;
    
 

    public Manager_Implementation(MeatRepository meatRepo, DairyRepository dairyRepo, VegeRepository vegeRepo,
    DrinkRepository drinkRepo, OilRepository oilRepo, SpiceRepository spiceRepo, DishRepository dishRepo,
    List<Long> dairyIds, List<Long> vegeIds, List<Long> spiceIds,OrderRepository orderRepo,PosRepository posRepo) {
this.meatRepo = meatRepo;
this.dairyRepo = dairyRepo;
this.vegeRepo = vegeRepo;
this.drinkRepo = drinkRepo;
this.oilRepo = oilRepo;
this.spiceRepo = spiceRepo;
this.dishRepo = dishRepo;
this.dairyIds = dairyIds;
this.vegeIds = vegeIds;
this.spiceIds = spiceIds;
this.orderRepo = orderRepo;
this.posRepo = posRepo;
    }

    @Override
    public void addMeat(Meat m)
    {
        meatRepo.save(m);
    }
    @Override
    public void deleteMeat(Long id) {
        
        meatRepo.deleteById(id);
    }

    @Override
    public Meat getMeatById(Long id) {
       return meatRepo.findById(id).get();
    }
    
    @Override
    public List<Meat> getAllMeat()
    {
        return meatRepo.findAll();
    }

    @Override
    public void addVegetable(Vegetable v) {
      
        vegeRepo.save(v);
    }

    @Override
    public void deleteVegetable(Long id) {
       vegeRepo.deleteById(id);
    }

    @Override
    public Vegetable getVegetableById(Long id) {
    
    return vegeRepo.findById(id).get();
    }
       
    @Override
    public List<Vegetable> getAllVegetables() {
        return vegeRepo.findAll();
    }

    @Override
    public void addSpice(Spice s) {
        spiceRepo.save(s);
    }

    @Override
    public void deleteSpice(Long id) {
        spiceRepo.deleteById(id);
    }

    @Override
    public Spice getSpiceById(Long id) {
       return spiceRepo.findById(id).get();
    }

    @Override
    public void addDairy(Dairy d) {
        dairyRepo.save(d);
    }

    @Override
    public void deleteDairy(Long id) {
        dairyRepo.deleteById(id);
    }

    @Override
    public Dairy getDairyById(Long id) {
       return dairyRepo.findById(id).get();
    }

    @Override
    public void addDrink(Drink d) {
        drinkRepo.save(d);
    }

    @Override
    public void deleteDrink(Long id) {
        drinkRepo.deleteById(id);
    }

    @Override
    public Drink getDrinkById(Long id) {
        return drinkRepo.findById(id).get();
    }

    @Override
    public void addCookingOil(Cooking_Oil oil) {
        oilRepo.save(oil);
    }

    @Override
    public void deleteCookingOil(Long id) {
       oilRepo.deleteById(id);
    }

    @Override
    public Cooking_Oil getCookingOilById(Long id) {
       return oilRepo.findById(id).get();
    }
    
    @Override
    public void add_In_Menu(Menu M){} 
    
    @Override
    public void delete_In_Menu(Menu M){} 
    
    @Override
    public void updateMenu(Menu M){} 
    
    @Override
    
    public void processPayment(Payment_Process P){} 
    @Override
    public void build_Order(Build_Order b){} 
    
    @Override
    public void generatePOS(POS_Receipt pos){} 
    
    @Override
    public void viewMenu(Menu M){}

    

    @Override
    public List<Spice> getAllSpices() {
        return spiceRepo.findAll();
    }

    @Override
    public List<Dairy> getAllDairy() {
        return dairyRepo.findAll();
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkRepo.findAll();
    }

    @Override
    public List<Cooking_Oil> getAllCookingOils() {
        return oilRepo.findAll();
    }

    @Override
    public Cooking_Oil getCookingOilByName(String name)
    {
          return oilRepo.findByName(name);
    }
    @Override
    public void saveDish(Dish D) {
       dishRepo.save(D);
    }

    @Override
    public Dish getDishById(Long id) {
       return dishRepo.findById(id).get();
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepo.findAll();
    }

    @Override
    public void deleteDish(Long id) {
        dishRepo.deleteById(id);
    }

    @Override
    public void saveDairyIds(List<Long> Ids)
    {
      for(int i = 0;i<Ids.size();i++)
      {
        dairyIds.add(Ids.get(i));
      }
    }
    @Override
    public void saveVegeIds(List<Long> Ids)
    {
        for(int i = 0;i<Ids.size();i++)
        {
         vegeIds.add(Ids.get(i));
        }
    }
    @Override
    public void saveSpiceIds(List<Long> Ids)
    {
        for(int i = 0;i<Ids.size();i++)
        {
          spiceIds.add(Ids.get(i));
        }
    }

    @Override
    public List<Long> getDairyIds()
    {
      return dairyIds;
    }
    @Override
    public List<Long> getVegeIds()
    {
        return vegeIds;
    }
    @Override
    public List<Long> getSpiceIds()
    {
        return spiceIds;
    }
   
    @Override
    public void temporarySaveDish(Dish d)
    {
        tempDish = d;
    }

    @Override
    public Dish getTempDish()
    {
        return tempDish;
    }

    @Override
    public List<Dish> getMenuDishes()
    {
        return dishRepo.findDishesByStatusTrue();
    }

    @Override
    public List<Dish> getRemainingDishes()
    {
        return dishRepo.findDishesByStatusFalse();
    }


    @Override
    public List<Drink> getMenuDrinks()
    {
        return drinkRepo.findDrinksByStatusTrue();

    }
    @Override
    public List<Drink> getRemainingDrinks()
    {
        return drinkRepo.findDrinksByStatusFalse();
    }

    
    @Override
    public Dish getSearchedDishes(String name)
    {
        return dishRepo.findDishByName(name);
    }


    @Override
    public Drink getSearchedDrink(String name)
    {
        return drinkRepo.findDrinkByName(name);
    }

    @Override
    public void savePOS(POS p)
    {
        posRepo.save(p);
    }

    @Override
    public POS getPosById(Long id)
    {
        return posRepo.findById(id).get();
    }

    @Override
    public void storePOS(POS p)
    {
        pos = p;
    }

    @Override
    public POS getStoredPos()
    {
        return pos;
    }

    @Override
    public Long getLatestOrderId()
    {
        return posRepo.findLatestOrderId();
    }
}

