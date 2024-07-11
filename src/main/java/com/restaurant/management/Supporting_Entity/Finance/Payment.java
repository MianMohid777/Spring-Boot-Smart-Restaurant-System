package com.restaurant.management.Supporting_Entity.Finance;

public class Payment {
    
    private int payableAmount;
    private int amountPaid;
    private String accountNo;
    private boolean status;
    private String customerName;
    

    public Payment(int payableAmount, int amountPaid, String accountNo, boolean status, String customerName) {
        this.payableAmount = payableAmount;
        this.amountPaid = amountPaid;
        this.accountNo = accountNo;
        this.status = status;
        this.customerName = customerName;
    }

    public int getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(int payableAmount) {
        this.payableAmount = payableAmount;
    }
    
    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
}
