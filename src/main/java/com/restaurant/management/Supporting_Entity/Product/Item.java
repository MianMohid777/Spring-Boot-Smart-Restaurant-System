package com.restaurant.management.Supporting_Entity.Product;

import com.restaurant.management.Enums.Item_Type;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Transient
    private Item_Type type;
    
    public Item()
    {
        id = (long) 0;
        name="";
    }
   
     
    public Item(Long id, String name, Item_Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public Item_Type getType() {
        return type;
    }



    public void setType(Item_Type type) {
        this.type = type;
    }



    abstract public Item createItem();
}

