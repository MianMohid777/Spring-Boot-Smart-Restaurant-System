package com.restaurant.management.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.management.Supporting_Entity.Core.*;;


public interface OrderRepository extends JpaRepository<Order,Long>{
    

    @Query(value="SELECT TOP ( 1 ) * FROM orders ORDER BY id DESC",nativeQuery = true)
    public Order findLastOrder();

    @Query(value="SELECT * FROM orders WHERE order_date Between :start AND :end",nativeQuery = true)
    public List<Order> findOrderByDates(@Param("start") Date start, @Param("end") Date end);


    @Query(nativeQuery = true, value = "SELECT TOP 1 d.dish_id, SUM(d.quantity) AS Total FROM order_dishes AS d INNER JOIN orders AS o ON d.order_id = o.id WHERE o.order_date BETWEEN :startDate AND :endDate GROUP BY d.dish_id ORDER BY Total DESC")
    List<Object[]> findDishQuantitiesByOrderDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value="SELECT * FROM orders WHERE order_date = :date",nativeQuery = true)
    List<Order> findOrderByDate(@Param("date") Date date);

    @Query(value="select MAX (id) from orders",nativeQuery = true)
    Long findLatestOrderId();

}


