package com.example.rupizzeriaapp;

import java.util.ArrayList;

public class OrderManager {
    private static OrderManager instance; // Singleton instance
    private ArrayList<Order> orders;           // Holds all placed orders
    private Order currentOrder;           // Holds the current active order
    private int orderId;                  // Auto-incremented order ID

    // Private constructor to prevent instantiation
    private OrderManager() {
        orders = new ArrayList<>();
        orderId = 1;
        currentOrder = new Order(orderId, new ArrayList<>());
    }

    // Public method to access the Singleton instance
    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void placeCurrentOrder() {
        if (currentOrder != null && !currentOrder.getPizzas().isEmpty()) {
            orders.add(currentOrder);         // Add current order to the list
            orderId++;                        // Increment order ID
            currentOrder = new Order(orderId, new ArrayList<>()); // Reset for new order
        }
    }

    public void cancelCurrentOrder() {
        currentOrder = new Order(orderId, new ArrayList<>()); // Reset the current order
    }

    public void addPizzaToCurrentOrder(Pizza pizza) {
        currentOrder.addPizza(pizza); // Add pizza to the current order
    }

    public void removePizzaFromCurrentOrder(Pizza pizza) {
        currentOrder.removePizza(pizza); // Remove pizza from the current order
    }
}
