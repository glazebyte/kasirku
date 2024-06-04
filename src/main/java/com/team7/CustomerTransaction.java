package com.team7;

public class CustomerTransaction {
    private int customerTransactionId;
    private int customerId;
    private int transactionId;

    // Constructor
    public CustomerTransaction(int customerTransactionId, int customerId, int transactionId) {
        this.customerTransactionId = customerTransactionId;
        this.customerId = customerId;
        this.transactionId = transactionId;
    }

    // Getters and Setters
    public int getCustomerTransactionId() {
        return customerTransactionId;
    }

    public void setCustomerTransactionId(int customerTransactionId) {
        this.customerTransactionId = customerTransactionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
