package com.team7;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {
    private int transactionId;
    private Date date;
    private double totalAmount;
    private List<TransactionDetail> details;
    private String customerName;
    private String customerEmail;

    public Transaction(Date date, double totalAmount, List<TransactionDetail> details) {
        this.date = date;
        this.totalAmount = totalAmount;
        this.details = details;
    }
    public Transaction(){
        this.details = new ArrayList<TransactionDetail>();
        this.totalAmount = 0;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<TransactionDetail> getDetails() {
        return details;
    }

    public void setDetails(List<TransactionDetail> details) {
        this.details = details;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
