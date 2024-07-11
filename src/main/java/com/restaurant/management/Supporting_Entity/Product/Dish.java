package com.restaurant.management.Supporting_Entity.Product;

import java.util.*;


import com.restaurant.management.Helper.*;

import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private float costPrice;
    private float sellingPrice;
    private int mass;
    private int volume;
    private boolean status = false;
    
    @ManyToOne
    @JoinColumn(name = "meat_id")
    private Meat meat;

    @ManyToOne
    @JoinColumn(name = "cooking_oil_id")
    private Cooking_Oil cookingOil;
    
    
    
   

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<dishDairy> dairyList = new ArrayList<>();
    
    
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<dishVeg> vegetableList;
    
    
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<dishSpice> spiceList;
    
    
    

    public Dish() {}


    


    public Dish(Long id, String name, float costPrice, float sellingPrice, int mass, int volume, boolean status,
            Meat meat, Cooking_Oil cookingOil, List<dishDairy> dairyList, List<dishVeg> vegetableList,
            List<dishSpice> spiceList) {
        this.id = id;
        this.name = name;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.mass = mass;
        this.volume = volume;
        this.status = status;
        this.meat = meat;
        this.cookingOil = cookingOil;
        this.dairyList = dairyList;
        this.vegetableList = vegetableList;
        this.spiceList = spiceList;
    }





    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public Meat getMeat() {
        return meat;
    }



    public void setMeat(Meat meat) {
        this.meat = meat;
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public float getCostPrice() {
        return costPrice;
    }


    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }


    public float getSellingPrice() {
        return sellingPrice;
    }


    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }


    public int getMass() {
        return mass;
    }


    public void setMass(int mass) {
        this.mass = mass;
    }


    public int getVolume() {
        return volume;
    }


    public void setVolume(int volume) {
        this.volume = volume;
    }


    public List<dishDairy> getDairyList() {
        return dairyList;
    }



    public void setDairyList(List<dishDairy> dairyList) {
        this.dairyList = dairyList;
    }



    

    public List<dishVeg> getVegetableList() {
        return vegetableList;
    }

    public void setVegetableList(List<dishVeg> vegetableList) {
        this.vegetableList = vegetableList;
    }



    public List<dishSpice> getSpiceList() {
        return spiceList;
    }


    public void setSpiceList(List<dishSpice> spiceList) {
        this.spiceList = spiceList;
    }


    public Cooking_Oil getCookingOil() {
        return cookingOil;
    }



    public void setCookingOil(Cooking_Oil cookingOil) {
        this.cookingOil = cookingOil;
    }



    public boolean isStatus() {
        return status;
    }





    public void setStatus(boolean status) {
        this.status = status;
    }



    
    
}

