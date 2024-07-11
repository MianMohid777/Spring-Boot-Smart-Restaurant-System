package com.restaurant.management.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "owner")
public class Owner extends User{
    
    public Owner(){}
    
    public Owner(String name, String userName, String passWord, String phoneNo) {
        super(name, userName, passWord, phoneNo);
    }
    
     
    
    @Override
    public User createUser()
    {
        return new Owner();
    }
    
}
