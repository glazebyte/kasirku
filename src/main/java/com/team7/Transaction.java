package com.team7;

public class Transaction {
    private int transactionId;
    private String date;
    private double totalAmount;

    // Constructor
    public Transaction(int transactionId, String date, double totalAmount) {
        this.transactionId = transactionId;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
