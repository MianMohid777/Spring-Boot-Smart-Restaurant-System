package com.restaurant.management.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "manager")
public class Manager extends User{
    
  
    
    public Manager()
    {}
    
    public Manager(String name, String userName, String passWord, String phoneNo) {
        super(name, userName, passWord, phoneNo);
    }
    
 
    
 

    @Override
    public User createUser()
    {
        return new Manager();
    }
}