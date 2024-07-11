package com.restaurant.management.Supporting_Entity.Core;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.restaurant.management.Supporting_Entity.Product.Dish;
import com.restaurant.management.Supporting_Entity.Product.Drink;

import jakarta.persistence.*;

@Entity
@Table(name="pos")
public class POS {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long  orderId;
    private String managerName;
    private float subTotal;
    private float totalGST;
    private float total_Amount;
    private LocalDate date;
    private LocalTime time;
    
    @Transient
    private List<Dish> food;
    @Transient
    private List<Drink> drinks;

    
    @Lob
    private byte[] pdfFile;


    public POS(int id, Long orderId, String managerName, float subTotal, float totalGST, float total_Amount,
            LocalDate date, LocalTime time, List<Dish> food, List<Drink> drinks, byte[] pdfFile) {
        this.id = id;
        this.orderId = orderId;
        this.managerName = managerName;
        this.subTotal = subTotal;
        this.totalGST = totalGST;
        this.total_Amount = total_Amount;
        this.date = date;
        this.time = time;
        this.food = food;
        this.drinks = drinks;
        this.pdfFile = pdfFile;
    }


    public POS() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Long getOrderId() {
        return orderId;
    }


    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    public String getManagerName() {
        return managerName;
    }


    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }


    public float getSubTotal() {
        return subTotal;
    }


    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }


    public float getTotalGST() {
        return totalGST;
    }


    public void setTotalGST(float totalGST) {
        this.totalGST = totalGST;
    }


    public float getTotal_Amount() {
        return total_Amount;
    }


    public void setTotal_Amount(float total_Amount) {
        this.total_Amount = total_Amount;
    }


    public LocalDate getDate() {
        return date;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }


    public LocalTime getTime() {
        return time;
    }


    public void setTime(LocalTime time) {
        this.time = time;
    }


    public List<Dish> getFood() {
        return food;
    }


    public void setFood(List<Dish> food) {
        this.food = food;
    }


    public List<Drink> getDrinks() {
        return drinks;
    }


    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }


    public byte[] getPdfFile() {
        return pdfFile;
    }


    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }




    


    
    
    
}