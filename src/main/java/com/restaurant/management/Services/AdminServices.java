package com.restaurant.management.Services;

import java.util.List;

import com.restaurant.management.Entity.*;

public interface AdminServices {
    
    public void addManager(Manager m);
    public void deleteManager(Long id);
    public void updateManager(Manager m);

    public void addOwner(Owner o);
    public void deleteOwner(Long id);
    public void updateOwner(Owner o);

    public List<Manager> getAllManagers();
    public Manager getManagerById(Long id);

    public List<Owner> getAllOwner();
    public Owner getOwnerById(Long id);

    public Manager getManagerByUsername(String name);

    public Owner getOwnerByUsername(String name);
}

