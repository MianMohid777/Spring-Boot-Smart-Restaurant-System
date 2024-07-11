package com.restaurant.management.Entity;

public class Admin extends User{
    
    public Admin()
    {
        super();
    }
    public Admin(String name, String userName, String passWord, String phoneNo) {
        super(name, userName, passWord, phoneNo);
        
       
    }
   
    
    @Override
    public User createUser()
    {
        return new Admin();
    }
    
}