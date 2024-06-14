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
    private String dateString;

    public Transaction(int transactionId,String dateString, double totalAmount, String customerName,String customerEmail) {
        this.transactionId = transactionId;
        this.dateString = dateString;
        this.totalAmount = totalAmount;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
    }
    public Transaction(){
        this.details = new ArrayList<TransactionDetail>();
        this.totalAmount = 0;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public String getDateString(){
        return dateString;
    }

    public void setDateString(String dateString){
        this.dateString =dateString;
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
