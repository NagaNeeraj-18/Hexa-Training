package org.example.Day1Assessment;

public class Order {
    long orderId;
    String customerId;
    double amount;
    String createdAt;

    public Order(long orderId, String customerId, double amount, String createdAt) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return orderId + ", " + customerId + ", " + amount + ", " + createdAt;
    }
}