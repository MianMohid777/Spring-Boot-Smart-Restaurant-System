package com.restaurant.management.Controller;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.*;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import com.restaurant.management.Helper.*;
import com.restaurant.management.Repository.PosRepository;
import com.restaurant.management.Services.*;
import com.restaurant.management.Supporting_Entity.Core.Order;
import com.restaurant.management.Supporting_Entity.Core.POS;
import com.restaurant.management.Supporting_Entity.Product.*;
import com.restaurant.management.Supporting_Services.Build_Order;
import com.restaurant.management.Supporting_Services.POS_Receipt;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Image;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class ManagerController {
    

    private static final String PdfWriter = null;

    @Autowired
    private ManagerServices managerService;

    @Autowired
    private Build_Order build_Order;
    
    @Autowired
    private PosRepository posRepo;
    

    public ManagerController(ManagerServices managerService,  Build_Order build_Order,PosRepository posRepo) 
    {   super();
        this.managerService = managerService;
        this.build_Order = build_Order;
        this.posRepo = posRepo;
    }


    @GetMapping("/manager-dash")
    public String manager_Dashboard()
    {    
        return "manager-dash";
    }

    @GetMapping("/inv")
    public String manage_inventory()
    {
        return "manage-inv";
    }

    @GetMapping("/inv/meat")
    public String manage_meat(Model model)
    {
        List<Meat> meat = managerService.getAllMeat();

        model.addAttribute("meats", meat);
      return "meat-inv";
    }

    @GetMapping("/inv/meat/select")
    public String update_meat(Model model)
    {
        List<Meat> meat = managerService.getAllMeat();

        model.addAttribute("meats", meat);
      return "meat-list";
    }

    @PostMapping("/meat/list/")
    public String select_meat(@RequestParam Long id,Model model)
    {
        
        Meat meat = managerService.getMeatById(id);
        meat.setId(id);
        model.addAttribute("meat", meat);
        return "update-meat";
    }

    @PostMapping("/meat/update")
    public String save_meat(@RequestParam Long id,@ModelAttribute("meat") Meat meat)
    {
        Meat updated = managerService.getMeatById(id);

        updated.setMass(meat.getMass());
        updated.setThreshMass(meat.getThreshMass());
        
        managerService.addMeat(updated);
        return "redirect:/inv/meat";
    }



    ///////////////////////////////////////////////////////////////////


   
     @GetMapping("/inv/dairy")
    public String manage_dairy(Model model)
    {
        List<Dairy> dairy = managerService.getAllDairy();

        model.addAttribute("dairys", dairy);
      return "dairy-inv";
    }

    @GetMapping("/inv/dairy/select")
    public String update_dairy(Model model)
    {
        List<Dairy> dairy = managerService.getAllDairy();

        model.addAttribute("dairys", dairy);
      return "dairy-list";
    }

    @PostMapping("/dairy/list/")
    public String select_dairy(@RequestParam Long id,Model model)
    {
        
        Dairy dairy = managerService.getDairyById(id);
        dairy.setId(id);
        model.addAttribute("dairy", dairy);
        return "update-dairy";
    }

    @PostMapping("/dairy/update")
    public String save_dairy(@RequestParam Long id,@ModelAttribute("dairy") Dairy dairy)
    {
        Dairy updated = managerService.getDairyById(id);

        updated.setMass(dairy.getMass());
        updated.setThreshMass(dairy.getThreshMass());
        
        managerService.addDairy(updated);
        return "redirect:/inv/dairy";
    }


    ///////////////////////////////////////////////////////////////////


    @GetMapping("/inv/vege")
    public String manage_vege(Model model)
    {
        List<Vegetable> vege = managerService.getAllVegetables();

        model.addAttribute("veges", vege);
      return "vege-inv";
    }

    @GetMapping("/inv/vege/select")
    public String update_vege(Model model)
    {
       
        List<Vegetable> vege = managerService.getAllVegetables();

        model.addAttribute("veges", vege);
      return "vege-list";
    }

    @PostMapping("/vege/list/")
    public String select_vege(@RequestParam Long id,Model model)
    {
        
        Vegetable vege = managerService.getVegetableById(id);
        vege.setId(id);
        model.addAttribute("vege", vege);
        return "update-vege";
    }

    @PostMapping("/vege/update")
    public String save_vege(@RequestParam Long id,@ModelAttribute("vege") Vegetable vege)
    {
        Vegetable updated = managerService.getVegetableById(id);

        updated.setMass(vege.getMass());
        updated.setThreshMass(vege.getThreshMass());
        
        managerService.addVegetable(updated);
        return "redirect:/inv/vege";
    }
    



////////////////////////////////////////////////////////////////////////

@GetMapping("/inv/spice")
    public String manage_spice(Model model)
    {
        List<Spice> spice = managerService.getAllSpices();

        model.addAttribute("spices", spice);
      return "spice-inv";
    }

    @GetMapping("/inv/spice/select")
    public String update_spice(Model model)
    {
        List<Spice> spice = managerService.getAllSpices();

        model.addAttribute("spices", spice);
      return "spice-list";
    }

    @PostMapping("/spice/list/")
    public String select_spice(@RequestParam Long id,Model model)
    {
        
        Spice spice = managerService.getSpiceById(id);
        spice.setId(id);
        model.addAttribute("spice", spice);
        return "update-spice";
    }

    @PostMapping("/spice/update")
    public String save_spice(@RequestParam Long id,@ModelAttribute("spice") Spice spice)
    {
        Spice updated = managerService.getSpiceById(id);

        updated.setMass(spice.getMass());
        updated.setThreshMass(spice.getThreshMass());
        
        managerService.addSpice(updated);
        return "redirect:/inv/spice";
    }
////////////////////////////////////////////////////////////////////////

@GetMapping("/inv/oil")
    public String manage_oil(Model model)
    {
        List<Cooking_Oil> oil = managerService.getAllCookingOils();

        model.addAttribute("oils", oil);
      return "oil-inv";
    }

    @GetMapping("/inv/oil/select")
    public String update_oil(Model model)
    {
        List<Cooking_Oil> oil = managerService.getAllCookingOils();

        model.addAttribute("oils", oil);
      return "oil-list";
    }

    @PostMapping("/oil/list/")
    public String select_oil(@RequestParam Long id,Model model)
    {
        
        Cooking_Oil oil = managerService.getCookingOilById(id);
        oil.setId(id);
        model.addAttribute("oil", oil);
        return "update-oil";
    }

    @PostMapping("/oil/update")
    public String save_oil(@RequestParam Long id,@ModelAttribute("oil") Cooking_Oil oil)
    {
        Cooking_Oil updated = managerService.getCookingOilById(id);

        updated.setVolume(oil.getVolume());
        updated.setThreshVolume(oil.getThreshVolume());
        
        managerService.addCookingOil(updated);
        return "redirect:/inv/oil";
    }


    ///////////////////////////////////////////////////////////////////


   
	@GetMapping("/inv/drink")
    public String manage_drink(Model model)
    {
        List<Drink> drink = managerService.getAllDrinks();

        model.addAttribute("drinks", drink);
      return "drink-inv";
    }

    @GetMapping("/inv/drink/select")
    public String update_drink(Model model)
    {
        List<Drink> drink = managerService.getAllDrinks();

        model.addAttribute("drinks", drink);
      return "drink-list";
    }

    @PostMapping("/drink/list/")
    public String select_drink(@RequestParam Long id,Model model)
    {
        
        Drink drink = managerService.getDrinkById(id);
        drink.setId(id);
        model.addAttribute("drink", drink);
        return "update-drink";
    }

    @PostMapping("/drink/update")
    public String save_drink(@RequestParam Long id,@ModelAttribute("drink") Drink drink)
    {
        Drink updated = managerService.getDrinkById(id);

        updated.setQty(drink.getQty());
        updated.setThreshQty(drink.getThreshQty());
        updated.setCostPrice(drink.getCostPrice());
        updated.setSellingPrice(drink.getSellingPrice());
        
        managerService.addDrink(updated);
        return "redirect:/inv/drink";
    }



    //////////////////// Create Dish //////////////////////////////

    
   @GetMapping("/create-dish")
   public String createDish(Model model)
   {
    
     List<Long> dairyIds = new ArrayList<>();
     List<Long> vegetableIds = new ArrayList<>();
     List<Long> spiceIds = new ArrayList<>();
    

      model.addAttribute("meats", managerService.getAllMeat());
      model.addAttribute("oils", managerService.getAllCookingOils());
      model.addAttribute("dairys", managerService.getAllDairy());
      model.addAttribute("veges", managerService.getAllVegetables());
      model.addAttribute("spices", managerService.getAllSpices());
      model.addAttribute("dish",new Dish());
      model.addAttribute("dairyIds",dairyIds);
      model.addAttribute("vegetableIds",vegetableIds);
      model.addAttribute("spiceIds", spiceIds);
      
    
      

    return "create-dish";
   }

   @PostMapping("/create/dish/select")
   public String storeDish(@ModelAttribute("dish") Dish dish,
   @RequestParam("meatId") Long meatId, 
   @RequestParam("oilId") Long oilId,
   @RequestParam(value = "dairyIds", required = false) List<Long> dairyIds, 
   @RequestParam(value = "spiceIds", required = false) List<Long> spiceIds,
   @RequestParam(value = "vegetableIds", required = false) List<Long> vegetableIds,  
   Model model)

   {

    Meat m = new Meat();
    m = managerService.getMeatById(meatId);
    Cooking_Oil oil =new Cooking_Oil();
    oil =  managerService.getCookingOilById(oilId);


        
        dish.setMeat(m);
        dish.setCookingOil(oil);
        
        String name = dish.getName();
        dish.setName(name.toUpperCase());

        managerService.temporarySaveDish(dish);
        
        List<Dairy> dairyList = new ArrayList<>();
        List<Vegetable> vegeList = new ArrayList<>();
        List<Spice> spiceList = new ArrayList<>();

        List<Long> dairyQuantities = new ArrayList<>();
        List<Long> vegeQuantities = new ArrayList<>();
        List<Long> spiceQuantities = new ArrayList<>();

        managerService.saveDairyIds(dairyIds);
        managerService.saveVegeIds(vegetableIds);
        managerService.saveSpiceIds(spiceIds);

        for(int i = 0; i < dairyIds.size();i++ )
        {
          dairyList.add(managerService.getDairyById(dairyIds.get(i)));
          System.out.println("\n" + "Before Saving " + dairyIds.get(i) + "\n");
        }

        for(int i = 0; i < vegetableIds.size();i++ )
        {
          vegeList.add(managerService.getVegetableById(vegetableIds.get(i)));
        }

        for(int i = 0; i < spiceIds.size();i++ )
        {
          spiceList.add(managerService.getSpiceById(spiceIds.get(i)));
        }

        model.addAttribute("meat", managerService.getMeatById(meatId));
        model.addAttribute("oil", managerService.getCookingOilById(oilId));
        model.addAttribute("dairyList", dairyList);
        model.addAttribute("vegeList", vegeList);
        model.addAttribute("spiceList", spiceList);
        model.addAttribute("dish",dish);
     
        model.addAttribute("dairyIds",dairyIds);
        model.addAttribute("vegetableIds",vegetableIds);
        model.addAttribute("spiceIds", spiceIds);

        model.addAttribute("dairyQuantities",dairyQuantities);
        model.addAttribute("vegeQuantities",vegeQuantities);
        model.addAttribute("spiceQuantities",spiceQuantities);
        
    
        return "dish-qty";
   }


   @PostMapping("create/dish/qty")
   public String addQty(@ModelAttribute("dish") Dish dish,
   @RequestParam(value="meatQty",required = false) Integer meatQty, 
   @RequestParam(value="oilQty",required = false) Integer oilQty,
   @RequestParam(value = "dairyQuantities", required = false) List<Long> dairyQuantities, 
   @RequestParam(value = "spiceQuantities", required = false) List<Long> spiceQuantities,
   @RequestParam(value = "vegeQuantities", required = false) List<Long> vegeQuantities,
   Model model)
   {

     List<Long> dairyIds = managerService.getDairyIds();
     List<Long> vegetableIds = managerService.getVegeIds();
     List<Long> spiceIds = managerService.getSpiceIds();
     dish = managerService.getTempDish();


    List<dishDairy> dishDairyList = new ArrayList<>();
    List<dishVeg> dishVegList = new ArrayList<>();
    List<dishSpice> dishSpiceList = new ArrayList<>();
    

    if(dairyIds != null && dairyQuantities!= null && dairyIds.size() == dairyQuantities.size())
    {

          for(int i = 0; i < dairyQuantities.size();i++)
          {
                System.out.println("\n" + dairyIds.get(i) + "\n" + dairyQuantities.get(i) + "\n") ;
                Dairy dairy = managerService.getDairyById(dairyIds.get(i));
                dishDairy dishDairy = new dishDairy(new DishDairyId(dish.getId(), dairy.getId()), dish, dairy, dairyQuantities.get(i));
                dishDairyList.add(dishDairy);
        }
    }
        

        if(vegetableIds != null) {
              
            for(int i = 0; i < vegeQuantities.size();i++)
             { 
                Vegetable veg = managerService.getVegetableById(vegetableIds.get(i));
                DishVegId vegId = new DishVegId(dish.getId(),veg.getId());
                dishVeg dishVege = new dishVeg(vegId, dish, veg,(vegeQuantities.get(i)));
                dishVegList.add(dishVege);
            }
        }
       

        if(spiceIds != null ) 
        {
            for(int i = 0; i < spiceQuantities.size(); i++) {
   
                Spice spice = managerService.getSpiceById(spiceIds.get(i));
                DishSpiceId spiceId = new DishSpiceId(dish.getId(),spice.getId());
                dishSpice dishSpice = new dishSpice(spiceId, dish, spice,(spiceQuantities.get(i)));
                dishSpiceList.add(dishSpice);
            }
        }
        
        
        dish.setMass(meatQty);
        dish.setVolume(oilQty);
        dish.setDairyList(dishDairyList);
        dish.setVegetableList(dishVegList);
        dish.setSpiceList(dishSpiceList);


        managerService.saveDish(dish);

    return "redirect:/manager-dash";
    }



    @GetMapping("/menu")
    public String myMenu(Model model)
    {

      List<Dish> menuDishes = managerService.getMenuDishes();
      List<Drink> menuDrinks = managerService.getMenuDrinks();

      model.addAttribute("dishes", menuDishes);
      model.addAttribute("drinks", menuDrinks);
      return "manage-menu";
    }

    @GetMapping("/menu/add")
    public String addMenu(Model model)
    {
      List<Dish> dishes = managerService.getRemainingDishes();
      List<Drink> drinks = managerService.getRemainingDrinks();

      model.addAttribute("dishes", dishes);
      model.addAttribute("drinks", drinks);
       return "add-menu";
    }

    @PostMapping("/menu/add/dish-drink")
    public String saveMenu(@RequestParam(value = "dishId",required = false) Long dishId ,
    @RequestParam(value = "drinkId",required = false) Long drinkId)
    {

      if(dishId!=null)
      {
        Dish dish = managerService.getDishById(dishId);
        dish.setStatus(true);
        managerService.saveDish(dish);
      }

      if(drinkId!= null)
      {
      Drink drink = managerService.getDrinkById(drinkId);   
      drink.setStatus(true);
      managerService.addDrink(drink);
      }

      return "redirect:/menu";
    }


    @GetMapping("/dish/update/{id}")
    public String updateDish(@PathVariable("id") Long id,Model model)
    {
      Dish d = managerService.getDishById(id);

      model.addAttribute("dish", d);


      return "update-menu-dish";
    }

    @PostMapping("/dish/update/save/{id}")
    public String saveUpdatedDish(@PathVariable("id") Long id,@ModelAttribute("dish") Dish dish)
    {
      Dish d = managerService.getDishById(id);

      d.setName(dish.getName().toUpperCase());
      d.setCostPrice(dish.getCostPrice());
      d.setSellingPrice(dish.getSellingPrice());

      managerService.saveDish(d);


      
      return "redirect:/menu";
    }


    @GetMapping("dish/delete/{id}")
    public String removeDish(@PathVariable("id") Long id,Model model)
    {
      Dish dish = managerService.getDishById(id);
      model.addAttribute("dish", dish);
      return "delete-dish";
    }

    @PostMapping("/dish/delete/{id}")
    public String finalizeRemovalDish(@RequestParam Long id, @RequestParam boolean confirm)
    {

      if(confirm)
      {
        Dish dish = managerService.getDishById(id);
        dish.setStatus(false);
        managerService.saveDish(dish);
      }


      return "redirect:/menu";
    }


    @GetMapping("/drink/update/{id}")
    public String updateDrink(@PathVariable("id") Long id,Model model)
    {
      Drink d = managerService.getDrinkById(id);

      model.addAttribute("drink", d);


      return "update-menu-drink";
    }

    @PostMapping("/drink/update/save/{id}")
    public String saveUpdatedDrink(@PathVariable("id") Long id,@ModelAttribute("drink") Drink drink)
    {
      Drink d = managerService.getDrinkById(id);

      d.setName(drink.getName().toUpperCase());
      d.setCostPrice(drink.getCostPrice());
      d.setSellingPrice(drink.getSellingPrice());

      managerService.addDrink(d);


      return "redirect:/menu";
    }



    @GetMapping("drink/delete/{id}")
    public String removeDrink(@PathVariable("id") Long id,Model model)
    {
      Drink drink = managerService.getDrinkById(id);
      model.addAttribute("drink", drink);
      return "delete-drink";
    }

    @PostMapping("/drink/delete/{id}")
    public String finalizeRemovalDrink(@RequestParam Long id, @RequestParam boolean confirm)
    {

      if(confirm)
      {
        Drink drink = managerService.getDrinkById(id);
        drink.setStatus(false);
        managerService.addDrink(drink);
      }


      return "redirect:/menu";
    }







    /////////////////////////////////////////////////////////////





    @GetMapping("/build-order")
    public String buildOrder(Model model)
    {    
      Order order = new Order();
      

      List<Long> dishIds = new ArrayList<>();
      List<Long> drinkIds = new ArrayList<>();

      List<Long> dishQty = new ArrayList<>();
      List<Long> drinkQty= new ArrayList<>();

// 
     build_Order.setDishIds(dishIds);
     build_Order.setDrinkIds(drinkIds);

     build_Order.setDishQty(dishQty);
     build_Order.setDrinkQty(drinkQty);

    
      order.setOrder_date(LocalDate.now());

      build_Order.setTempOrder(order);
      model.addAttribute("order", order);
      return "build-order";
    }

    @PostMapping("/build-order/dish/search")
    public String searchDish(@ModelAttribute("order") Order order,@RequestParam(value="search",required = false) String search,
    @RequestParam("btn") String btn,@RequestParam(value="qty",required = false) Long qty,
     Model model)
    {
      

      if(btn.equals("search"))
       
        {
         if(search!=null)
         {
           Dish dishes = managerService.getSearchedDishes(search.toUpperCase());

           if(dishes==null)
          {
          model.addAttribute("error", "No Result Found");
          }
          else
          {
            model.addAttribute("dishes", dishes);
            model.addAttribute("order", order);
            managerService.temporarySaveDish(dishes);
            
          }
        }
      }
      else if(btn.equals("add"))
      {
        List<Long> dishIds = build_Order.getDishIds();
        List<Long> dishQty = build_Order.getDishQty();
  
        Dish dishes = managerService.getTempDish();


        
        for(int i = 0;i<dishIds.size();i++)
        {
          if(dishes.getId() == dishIds.get(i))
          {
            model.addAttribute("repeat", "Already ! Added");
            return "build-order";
          }
        }


        if(qty != 0)
        {
        dishIds.add(dishes.getId());
        dishQty.add(qty);
        


        build_Order.setDishIds(dishIds);
        build_Order.setDishQty(dishQty);
        }

      }
      else if(btn.equals("next"))
      {
        
        model.addAttribute("order",order);
        
        return "build-order2";
      }
    
      return "build-order";
    }

   


    @PostMapping("/build-order/drink/search")
    public String searchDrink(@ModelAttribute("order") Order order,@RequestParam(value="search",required = false) String search,
    @RequestParam("btn") String btn,@RequestParam(value="qty",required = false) Long qty,
     Model model)
    {
      

      if(btn.equals("search"))
       
      {
         if(search!=null)
        {
          Drink drink = managerService.getSearchedDrink(search.toUpperCase());
          

        if(drink==null)
        {
          model.addAttribute("error", "No Result Found");
        }
        else
        {
        System.out.println(drink.getName());
        model.addAttribute("drinks", drink);
        build_Order.saveTempDrink(drink);
        
        }
        }
      }
      else if(btn.equals("add"))
      {
        List<Long> drinkIds = build_Order.getDrinkIds();
        List<Long> drinkQty = build_Order.getDrinkQty();

        Drink drink = build_Order.getTempDrink();


        for(int i = 0;i<drinkIds.size();i++)
        {
          if(drink.getId() == drinkIds.get(i))
          {
            model.addAttribute("repeat", "Already ! Added");
            return "build-order2";
          }
        }


        if(qty != 0)
        {
        drinkIds.add(drink.getId());
        drinkQty.add(qty);


        build_Order.setDrinkIds(drinkIds);
        build_Order.setDrinkQty(drinkQty);

        }
        
      }

      else if(btn.equals("complete"))
      {

        List<Long> dishIds = build_Order.getDishIds();
        List<Long> dishQty = build_Order.getDishQty();
        order = build_Order.getTempOrder();

        List<orderDish> dishList = new ArrayList<>();


        System.out.print("\n\n\n For DISH " + dishIds.size() + "\n\n" + dishQty.size() + "\n\n");
        for(int i = 0; i < dishIds.size();i++ )
        {
          System.out.print("\n\n\n LOOP Ids " + dishIds.get(i) + "\n\n Qty: " + dishQty.get(i) + "\n\n");
         orderDishId orderDishId = new orderDishId(order.getId(), dishIds.get(i));
         Dish dish = managerService.getDishById(dishIds.get(i));
         orderDish od = new orderDish(orderDishId, order, dish, dishQty.get(i));
         dishList.add(od);
        }

        order.setDishList(dishList);



        List<Long> drinkIds = build_Order.getDrinkIds();
        List<Long> drinkQty = build_Order.getDrinkQty();


        List<orderDrink> drinkList = new ArrayList<>();

        for(int i = 0; i < drinkIds.size();i++ )
        {
         orderDrinkId orderDrinkId = new orderDrinkId(order.getId(), drinkIds.get(i));
         Drink drink = managerService.getDrinkById(drinkIds.get(i));
         orderDrink od = new orderDrink(orderDrinkId, order, drink, drinkQty.get(i));
         drinkList.add(od);
        }


        order.setDrinkList(drinkList);


        int price = build_Order.calculateOrderPrice(dishList,drinkList);
        order.setOrder_price(price);
        

        System.out.println("\n" + order.getOrder_price() + "\n" + order.getOrder_date() + "\n" + order.getId());
      

        build_Order.saveOrder(order);
  
        return "redirect:/orderDetail";
      }
    
      return "build-order2";
    }

    @GetMapping("/orderDetail")
    public String orderDetail(Model model)
    {

      Order order = build_Order.getLatestOrder();

      List<orderDish> orderDishList = order.getDishList();
      List<orderDrink> orderDrinkList = order.getDrinkList();

      model.addAttribute("orderDishList", orderDishList);
      model.addAttribute("orderDrinkList", orderDrinkList);  
      return "orderDetail";
    }


    @GetMapping("/orderDetail/update/dish/{name}")
    public String updateOrderDish(@PathVariable("name") String name,Model model)
    {

      Order order = build_Order.getLatestOrder();

      List<orderDish> orderDishList = order.getDishList();

      orderDish od = new orderDish();
      boolean flag = false;

      for(int i = 0; i < orderDishList.size() && !flag;i++)
      {
        if(name.equals(orderDishList.get(i).getDish().getName()))
        {
          od = orderDishList.get(i);
          flag = true;
        }
      }

      System.out.println("\n\n" + od.getQty()+ "\n\n");

      model.addAttribute("orderDish", od);

      return "update-orderDish";
    }

    @PostMapping("/orderDetail/update/Qty/dish/{name}")
    public String saveOrderDish(@PathVariable("name") String name, @ModelAttribute("orderDish") orderDish odish)
    {

      Order order = build_Order.getLatestOrder();

      List<orderDish> orderDishList = order.getDishList();

      orderDish od = new orderDish();
      boolean flag = false;

      for(int i = 0; i < orderDishList.size() && !flag;i++)
      {
        if(name.equals(orderDishList.get(i).getDish().getName()))
        {
          od = orderDishList.get(i);
          flag = true;
        }
      }

      od.setQty(odish.getQty());

      int price = build_Order.calculateOrderPrice(orderDishList,order.getDrinkList());
      order.setOrder_price(price);
      
      build_Order.saveOrder(order);
      
      return "redirect:/orderDetail";
    }



    @GetMapping("/orderDetail/update/drink/{name}")
    public String updateOrderDrink(@PathVariable("name") String name,Model model)
    {

      Order order = build_Order.getLatestOrder();

      List<orderDrink> orderDrinkList = order.getDrinkList();

      orderDrink od = new orderDrink();
      boolean flag = false;

      for(int i = 0; i < orderDrinkList.size() && !flag;i++)
      {
        if(name.equals(orderDrinkList.get(i).getDrink().getName()))
        {
          od = orderDrinkList.get(i);
          flag = true;
        }
      }

      System.out.println("\n\n" + od.getQty()+ "\n\n");

      model.addAttribute("orderDrink", od);

      return "update-orderDrink";
    }

    @PostMapping("/orderDetail/update/Qty/drink/{name}")
    public String saveOrderDrink(@PathVariable("name") String name, @ModelAttribute("orderDish") orderDish odish)
    {

      Order order = build_Order.getLatestOrder();

      List<orderDrink> orderDrinkList = order.getDrinkList();

      orderDrink od = new orderDrink();
      boolean flag = false;

      for(int i = 0; i < orderDrinkList.size() && !flag;i++)
      {
        if(name.equals(orderDrinkList.get(i).getDrink().getName()))
        {
          od = orderDrinkList.get(i);
          flag = true;
        }
      }


      od.setQty(odish.getQty());

      
      int price = build_Order.calculateOrderPrice(order.getDishList(), orderDrinkList);      
      order.setOrder_price(price);
      
      build_Order.saveOrder(order);
      
      return "redirect:/orderDetail";
    }


    ///////////////////INVOICE///////////////////////


    @GetMapping("/pos")
    public String displayPOS(Model model)
    {
      Order order = build_Order.getLatestOrder();
      POS_Receipt invoice = new POS_Receipt();

      POS pos = invoice.generatePOS(order,"Mohid");


      managerService.storePOS(pos);

      List<orderDish> orderDishList = order.getDishList();
      List<orderDrink> orderDrinkList = order.getDrinkList();

      model.addAttribute("pos", pos);
      model.addAttribute("orderDishList", orderDishList);
      model.addAttribute("orderDrinkList", orderDrinkList);  
      return "pos";
    }
   

    @PostMapping("/pos/pdf")
     public ResponseEntity<byte[]> makePDF() throws IOException, DocumentException, WriterException 
     {

      POS pos = managerService.getStoredPos();
      boolean flag = false;

      if(managerService.getLatestOrderId() != (pos.getOrderId()))
      {   
         managerService.savePOS(pos);
         flag = true;
      }
     ByteArrayOutputStream baos = new ByteArrayOutputStream();
     

     // set the page size to A4 and add margins

     float width = 100f; // in mm
     float height = PageSize.A4.getHeight(); // use A4 height as reference
     Rectangle pageSize = new Rectangle(width, height);
    
    float marginLeft = 2f;
    float marginRight = 2f;
    float marginTop = 2f;
    float marginBottom = 22f;

    Document document = new Document(pageSize, marginLeft, marginRight, marginTop, marginBottom);
    PdfWriter writer = com.itextpdf.text.pdf.PdfWriter.getInstance(document, baos);
   

    document.open();

    // add content to the PDF file using the POS object
    Paragraph para = new Paragraph("Order Invoice", new Font(Font.FontFamily.TIMES_ROMAN,8, Font.BOLD));
    para.setAlignment(Element.ALIGN_CENTER);
    para.setSpacingAfter(5f); // add some space after the paragraph
    document.add(para);

    Phrase posHeader = new Phrase();
    posHeader.add(new Chunk("Invoice ID: ", new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
posHeader.add(new Chunk(String.valueOf(pos.getId()), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
posHeader.add(new Chunk("\n"));
posHeader.add(new Chunk("Order ID: ", new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
posHeader.add(new Chunk(String.valueOf(pos.getOrderId()), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
posHeader.add(new Chunk("\n"));
posHeader.add(new Chunk("Manager: ", new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
posHeader.add(new Chunk(pos.getManagerName(), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
posHeader.add(new Chunk("\n"));
posHeader.add(new Chunk("Date: ", new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
posHeader.add(new Chunk(pos.getDate().toString(), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
posHeader.add(new Chunk("\n"));
posHeader.add(new Chunk("Time: ", new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
posHeader.add(new Chunk(pos.getTime().toString(), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
posHeader.add(new Chunk("\n", new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));

document.add(posHeader);

    // add a table with the order details
    PdfPTable table = new PdfPTable(new float[] {3, 2, 2, 2});
    table.setWidthPercentage(100);
    table.setHeaderRows(1); // make the first row a header

    // add the header row
    PdfPCell cell = new PdfPCell(new Phrase("Item Name",new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.BOLD, BaseColor.RED)));
    cell.setBackgroundColor(new BaseColor(217, 217, 217)); // set the background color
    table.addCell(cell);


    cell = new PdfPCell(new Phrase("Price",new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.BOLD)));
    cell.setBackgroundColor(new BaseColor(217, 217, 217));
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Qty",new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.BOLD)));
    cell.setBackgroundColor(new BaseColor(217, 217, 217));
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase("Amount",new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.BOLD)));
    cell.setBackgroundColor(new BaseColor(217, 217, 217));
    table.addCell(cell);

    // add the order details
    Order order = build_Order.getOrderById(pos.getOrderId());
    List<orderDish> orderDishList = order.getDishList();
    List<orderDrink> orderDrinkList = order.getDrinkList();

  
    for (orderDish od : orderDishList) {
      table.addCell(new Phrase (od.getDish().getName(), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL)));
      table.addCell(new Phrase (String.format("%.2f", od.getDish().getSellingPrice()), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL))); 
      table.addCell(new Phrase (String.valueOf(od.getQty()), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL)));
      table.addCell(new Phrase (String.format("%.2f", od.getDish().getSellingPrice() * od.getQty()), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL)));
  }

    for (orderDrink od : orderDrinkList) {
      table.addCell(new Phrase (od.getDrink().getName(), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL)));
      table.addCell(new Phrase (String.format("%.2f", od.getDrink().getSellingPrice()), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL))); 
      table.addCell(new Phrase (String.valueOf(od.getQty()), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL)));
      table.addCell(new Phrase (String.format("%.2f", od.getDrink().getSellingPrice() * od.getQty()), new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL)));
  }
  

    document.add(table);

    // add GST/subtotal and total amount
PdfPTable totalTable = new PdfPTable(new float[] {3, 1});
totalTable.setWidthPercentage(100);
totalTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
totalTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
totalTable.addCell(new Phrase("Subtotal:", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
totalTable.addCell(new Phrase(String.format("%.2f", pos.getSubTotal() ), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.NORMAL)));

totalTable.addCell(new Phrase("GST (16%):", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
totalTable.addCell(new Phrase(String.format("%.2f", pos.getTotalGST()), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.NORMAL)));

totalTable.addCell(new Phrase("Total:", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));
totalTable.addCell(new Phrase(String.format("%.2f", pos.getTotal_Amount() ), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD)));

document.add(totalTable);

    // generate a QR code image for the PDF file URL
    String pdfUrl = "http://localhost:8080/manager/invoice" + pos.getOrderId() + ".pdf"; // change the URL as needed
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(pdfUrl, com.google.zxing.BarcodeFormat.QR_CODE, 25, 25);
    ByteArrayOutputStream qrCodeStream = new ByteArrayOutputStream();
     MatrixToImageWriter.writeToStream(bitMatrix, "PNG", qrCodeStream);

    // add the QR code image to the PDF file
    Image qrCodeImage = Image.getInstance(qrCodeStream.toByteArray());
    qrCodeImage.scalePercent(50);
    qrCodeImage.setAlignment(Image.ALIGN_CENTER);
    qrCodeImage.setSpacingBefore(20f); 
    document.add(qrCodeImage);

    document.close();
    writer.close();

    // Write the PDF to a file
    byte[] pdfBytes = baos.toByteArray();
    FileOutputStream fileStream = new FileOutputStream("invoice" + pos.getOrderId() + ".pdf");
    fileStream.write(pdfBytes);
    fileStream.close();

    // store the PDF file in the POS object

    pos.setPdfFile(pdfBytes);  
    if(flag)
    {  
    managerService.savePOS(pos);
    }
     
    
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_MIXED);
    headers.setContentDisposition(ContentDisposition.builder("inline").filename("invoice"+pos.getOrderId()+".pdf").build());
    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("file", new ByteArrayResource(pdfBytes));
    body.add("qr_code", new ByteArrayResource(qrCodeStream.toByteArray()));

    return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

  }

}

    
   