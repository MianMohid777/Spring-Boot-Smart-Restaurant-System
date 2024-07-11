package com.restaurant.management.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;


import com.restaurant.management.Entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager,Long>{
    
    @Query(value="SELECT * FROM manager WHERE user_name = :userName",nativeQuery=true)
    public Manager findByUserName(@Param("userName") String userName);
    
}
