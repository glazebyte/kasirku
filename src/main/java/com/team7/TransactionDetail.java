package com.team7;

public class TransactionDetail {
    private int detailId;
    private int transactionId;
    private Product product;
    private int quantity;
    private double subtotal;

    // Constructor
    public TransactionDetail(int detailId, int transactionId, Product product, int quantity, double subtotal) {
        this.detailId = detailId;
        this.transactionId = transactionId;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public TransactionDetail(Product product) {
        this.detailId = 0;
        this.transactionId = 0;
        this.product = product;
        this.quantity = 1;
        this.subtotal = product.getPrice();
    }

    // Getters and Setters
    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setSubtotal(quantity*this.product.getPrice());
    }

    public double getSubtotal() {
        return subtotal;
    }

    private void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if(obj.getClass() != this.getClass())
            return false;
            
        final TransactionDetail detail = (TransactionDetail) obj;
        if (detail.product == null)
            return false;

        if (this.product.getProductId() == detail.product.getProductId())
            return true;
        else 
            return false;
    }
}

