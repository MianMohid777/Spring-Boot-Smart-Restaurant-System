

package com.restaurant.management.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.management.Supporting_Entity.Product.*;

public interface VegeRepository extends JpaRepository<Vegetable, Long> {
    
    
}
