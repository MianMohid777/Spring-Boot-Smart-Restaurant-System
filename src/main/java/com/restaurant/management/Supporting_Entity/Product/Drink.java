package com.restaurant.management.Supporting_Entity.Product;
import org.springframework.stereotype.Component;

import com.restaurant.management.Enums.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Component
@Entity
@Table(name="drink")
public class Drink extends Item {
    
    @Transient
    private DrinkSize size;

    private int qty;
    private int threshQty;
     
    private double costPrice;
    private double sellingPrice;
    private boolean status = false;

    public Drink()
    {
        super();
        
    }



    public Drink(Long id, String name, Item_Type type, DrinkSize size, int qty, int threshQty, double costPrice,
            double sellingPrice, boolean status) {
        super(id, name, type);
        this.size = size;
        this.qty = qty;
        this.threshQty = threshQty;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.status = status;
    }



    public DrinkSize getSize() {
        return size;
    }




    public void setSize(DrinkSize size) {
        this.size = size;
    }




    public double getCostPrice() {
        return costPrice;
    }




    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }




    public double getSellingPrice() {
        return sellingPrice;
    }




    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }




    public int getQty() {
        return qty;
    }




    public void setQty(int qty) {
        this.qty = qty;
    }




    public int getThreshQty() {
        return threshQty;
    }




    public void setThreshQty(int threshQty) {
        this.threshQty = threshQty;
    }




    @Override
     public Item createItem()
     {
         return new Drink();
     }



    public boolean isStatus() {
        return status;
    }



    public void setStatus(boolean status) {
        this.status = status;
    }
}
