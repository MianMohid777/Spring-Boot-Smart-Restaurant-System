package com.restaurant.management.Services.Implementation;

import org.springframework.stereotype.Service;

import com.restaurant.management.Entity.*;
import com.restaurant.management.Repository.ManagerRepository;
import com.restaurant.management.Repository.OwnerRepository;
import com.restaurant.management.Services.AdminServices;
import java.util.*;

@Service
public class Admin_Implementation extends Admin implements AdminServices {
    
    private ManagerRepository managerRepo;
    private OwnerRepository ownerRepo;

    
   

    public Admin_Implementation(ManagerRepository managerRepo, OwnerRepository ownerRepo) {
        super();
        this.managerRepo = managerRepo;
        this.ownerRepo = ownerRepo;
    }

    @Override
    public void addManager(Manager m){
         managerRepo.save(m);
    }
    
     @Override
    public void deleteManager(Long id){

        managerRepo.deleteById(id);;
    }
    
     @Override
    public void addOwner(Owner o){
         ownerRepo.save(o);
    }
    
     @Override
    public void deleteOwner(Long id){
        ownerRepo.deleteById(id);
    }
    
    @Override
    public void updateManager(Manager m)
    {
        managerRepo.save(m);
    }

    @Override
    public void updateOwner(Owner o)
    {
        ownerRepo.save(o);
    }

    @Override
    public List<Manager> getAllManagers()
    {
        return managerRepo.findAll();
    }

    @Override
    public Manager getManagerById(Long id)
    {
        return managerRepo.findById(id).get();
    }

    @Override
    public List<Owner> getAllOwner()
    {
          return ownerRepo.findAll();
    }
    @Override
    public Owner getOwnerById(Long id)
    {
        return ownerRepo.findById(id).get();
    }

    
    @Override
    public Manager getManagerByUsername(String name)
    {
        return managerRepo.findByUserName(name);
    }

    @Override
    public Owner getOwnerByUsername(String name)
    {
        return ownerRepo.findByUserName(name);
    }
}
