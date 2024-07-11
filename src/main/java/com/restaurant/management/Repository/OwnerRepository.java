package com.restaurant.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.management.Entity.*;


public interface OwnerRepository extends JpaRepository<Owner,Long>{
    
    @Query(value="SELECT * FROM owner WHERE user_name = :userName",nativeQuery=true)
    public Owner findByUserName(String userName);
}

