package com.example.rupizzeriaapp;

import java.util.ArrayList;

/**
 * This is the Order Manager class it manages all the placed orders, current orders, and order IDs
 * @author Varun Doreswamy, Yuet Yue
 */
public class OrderManager {

    // Instance Variables
    private static OrderManager instance; // Singleton instance
    private ArrayList<Order> orders;           // Holds all placed orders
    private Order currentOrder;           // Holds the current active order
    private int orderId;                  // Auto-incremented order ID

    /**
     * This is the default constructor that creates a placed order object with an assigned ID
     */
    private OrderManager() {
        orders = new ArrayList<>();
        orderId = 1;
        currentOrder = new Order(orderId, new ArrayList<>());
    }

    /**
     * This method provides access to the Singleton instance of the Order Manager
     * @return the Singleton instance of the Order Manager
     */
    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    /**
     * This is the getter method for the current order
     * @return the current order the user is working on
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }


    /**
     * This is the getter method for the list of all placed orders
     * @return the list of all orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * This method places the current order by:
     * Adding it to the list of placed orders
     * Incrementing the order ID
     * Creates a new current order
     */
    public void placeCurrentOrder() {
        if (currentOrder != null && !currentOrder.getPizzas().isEmpty()) {
            orders.add(currentOrder);         // Add current order to the list
            orderId++;                        // Increment order ID
            currentOrder = new Order(orderId, new ArrayList<>()); // Reset for new order
        }
    }

    /**
     * This method cancels the current order by resetting it while retaining the current order ID
     */
    public void cancelCurrentOrder() {
        currentOrder = new Order(orderId, new ArrayList<>()); // Reset the current order
    }

    /**
     * This method adds a pizza to the current order
     * @param pizza is the pizza that is going to be added to the current order
     */
    public void addPizzaToCurrentOrder(Pizza pizza) {
        currentOrder.addPizza(pizza); // Add pizza to the current order
    }

    /**
     * This method removes a pizza from the current order
     * @param pizza is the pizza that is going to be added to the current order
     */
    public void removePizzaFromCurrentOrder(Pizza pizza) {
        currentOrder.removePizza(pizza); // Remove pizza from the current order
    }
}
