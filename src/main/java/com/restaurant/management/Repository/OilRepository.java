package com.restaurant.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.restaurant.management.Supporting_Entity.Product.*;;


public interface OilRepository extends JpaRepository<Cooking_Oil,Long>{
    
    Cooking_Oil findByName(String name);
}

