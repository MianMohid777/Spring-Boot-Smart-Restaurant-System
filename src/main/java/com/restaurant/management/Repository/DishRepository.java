package com.restaurant.management.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.management.Supporting_Entity.Product.*;

public interface DishRepository extends JpaRepository<Dish, Long> {
    
    @Query(value = "SELECT * FROM dishes WHERE status = '1'", nativeQuery = true)
    List<Dish> findDishesByStatusTrue();

    @Query(value = "SELECT * FROM dishes WHERE status = '0'", nativeQuery = true)
    List<Dish> findDishesByStatusFalse();

    @Query(value = "SELECT * FROM dishes WHERE name = :name AND status = '1'", nativeQuery = true)
    Dish findDishByName(@Param("name") String name);

    
}
