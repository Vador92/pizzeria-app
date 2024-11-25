package com.example.rupizzeriaapp;

import java.util.ArrayList;

/**
 * This class is the Order class, it manages all the orders a user makes
 * @author Varun Doreswamy, Yuet Yue
 */
public class Order {

    // Constants
    public static final double TAX = .06625;

    // Instance Variables
    private int number;
    private ArrayList<Pizza> pizzas;

    /**
     * This is the default constructor for creating a user order object
     * @param number is the order number for order differentiation
     * @param pizzas is the list of pizzas within this order
     */
    public Order(int number, ArrayList<Pizza> pizzas) {
        this.number = number;
        this.pizzas = pizzas;
    }

    /**
     * This is a copy constructor of a user order object
     * @param order the existing user order object
     */
    public Order(Order order) {
        this.number = order.number;
        this.pizzas = order.pizzas;
    }

    /**
     * This is a getter method for the pizzas in the user order object
     * @return the list of pizzas in the user order object
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * This method adds a pizza into the list of pizzas
     * @param p is the pizza being added
     */
    public void addPizza(Pizza p) {
        pizzas.add(p);
    }

    /**
     * This method removes a pizza from the list of pizzas
     * @param p is the pizza being removed
     */
    public void removePizza(Pizza p) {
        pizzas.remove(p);
    }

    /**
     * This method removes all the pizzas from the order
     */
    public void clearOrder() {
        while (!pizzas.isEmpty()) {
            removePizza(pizzas.get(0));
        }
    }

    /**
     * This is the getter method for the user order number
     * @return the number associated with the order
     */
    public int getNumber() {
        return number;
    }

    /**
     * This is the getter method for the tax of the order
     * @return the tax based on the subtotal cost of the order
     */
    public double getTax(){
        return TAX * getSubTotal();
    }

    /**
     * This is the getter method for the subtotal of the order
     * @return the total cost of the pizzas, tax excluded
     */
    public double getSubTotal(){
        double sub = 0;
        for (Pizza p : pizzas) {
            sub += p.price();
        }
        return sub;
    }

    /**
     * This is the getter method for the total cost of the order
     * @return the total cost, which is the subtotal and the tax cost
     */
    public double getTotal(){
        return getSubTotal() + getTax();
    }
}
