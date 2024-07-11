package com.restaurant.management.Supporting_Entity.Product;
import com.restaurant.management.Enums.Item_Type;

import jakarta.persistence.*;

@Entity
@Table(name="oil")
public class Cooking_Oil extends Item {
    
    private double volume;
    private int threshVolume;
    
    
    
    public Cooking_Oil()
    {
        super();
        
    }
    
     
     
       public Cooking_Oil(double volume, int threshVolume) {
        this.volume = volume;
        this.threshVolume = threshVolume;
    }



    public Cooking_Oil(Long id, String name, Item_Type type, double volume, int threshVolume) {
        super(id, name, type);
        this.volume = volume;
        this.threshVolume = threshVolume;
    }



    @Override
     public Item createItem()
     {
         return new Cooking_Oil();
     }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }



    public int getThreshVolume() {
        return threshVolume;
    }



    public void setThreshVolume(int threshVolume) {
        this.threshVolume = threshVolume;
    }
}
