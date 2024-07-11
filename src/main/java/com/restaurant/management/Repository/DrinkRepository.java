package com.restaurant.management.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.management.Supporting_Entity.Product.*;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    
    @Query(value = "SELECT * FROM drink WHERE status = '1'", nativeQuery = true)
    List<Drink> findDrinksByStatusTrue();

    @Query(value = "SELECT * FROM drink WHERE status = '0'", nativeQuery = true)
    List<Drink> findDrinksByStatusFalse();

    @Query(value = "SELECT * FROM drink WHERE name = :name AND status = '1'", nativeQuery = true)
    Drink findDrinkByName(@Param("name") String name);
}
