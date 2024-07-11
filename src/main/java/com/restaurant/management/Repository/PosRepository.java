package com.restaurant.management.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.management.Supporting_Entity.Core.*;;


public interface PosRepository extends JpaRepository<POS,Long>{
    

    @Query(value="SELECT totalgst FROM pos WHERE date Between :start AND :end",nativeQuery = true)
    public List<Float> findGstByDates(@Param("start") Date start, @Param("end") Date end);

    @Query(value="select MAX (order_id) from pos",nativeQuery = true)
    Long findLatestOrderId();
}

