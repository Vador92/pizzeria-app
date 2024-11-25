package com.example.rupizzeriaapp;

import java.util.ArrayList;

/**
 * The abstract class for the Pizza
 * @author Varun Doreswamy, Yuet Yue
 */
public abstract class Pizza {

    // Constants
    private static final int MAX = 7;

    // Instance Variables
    private ArrayList<Topping> toppings; // Topping is an Enum class
    private Crust crust; // Crust is an Enum class
    private Size size; // Size is an Enum class

    /**
     * This is the default constructor for creating a Pizza object
     * @param toppings is the choice of toppings on the pizza
     * @param crust is the type of crust on the pizza
     * @param size is the size of the pizza
     */
    public Pizza(ArrayList<Topping> toppings, Crust crust, Size size){
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    /**
     * This is a copy constructor of a Pizza object
     * @param p is the pizza object used for copying
     */
    public Pizza(Pizza p){
        this.toppings = new ArrayList<>(p.getToppings());
        this.crust = p.getCrust();
        this.size = p.getSize();
    }

    /**
     * This is an abstract method to represent the price of the pizza selected
     * @return the price of the pizza as a double
     */
    public abstract double price();

    /**
     * This is an abstract method to represent a clone of a pizza object
     * @return the clone pizza object to avoid rewriting the reference pizza object
     */
    public abstract Pizza copyPizza();

    /**
     * This method adds toppings to the Pizza object
     * @param topping is the type of topping being added to the pizza
     */
    public void addTopping(Topping topping){
        if (toppings.size() < MAX){
            toppings.add(topping);
        }
    }

    /**
     * This method removes toppings from the Pizza object
     * @param topping is the type of topping being removed from the pizza
     */
    public void removeTopping(Topping topping){
        toppings.remove(topping);
    }

    /**
     * This method moves all toppings from the Pizza object
     */
    public void removeAllToppings(){
        while (!toppings.isEmpty()){
            removeTopping(toppings.remove(0));
        }
    }

    /**
     * This is the getter method for the toppings currently on the Pizza object
     * @return the toppings that are on the pizza
     */
    public ArrayList<Topping> getToppings(){
        return new ArrayList<>(toppings);
    }

    /**
     * This is the getter method for the selected Crust type of the Pizza object
     * @return the type of Crust on the Pizza
     */
    public Crust getCrust(){
        return crust;
    }

    /**
     * This is the getter method for the size of the Pizza object
     * @return the size of the pizza
     */
    public Size getSize(){
        return size;
    }

    /**
     * This is the setter method for the size of a Pizza object
     * @param size is the desired size that will be set for a pizza
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * This method formats the Pizza object in the desired string format
     * @return the combination of toppings, crust, and size of a Pizza object in string format
     */
    @Override
    public String toString(){
        StringBuilder toppingsList = new StringBuilder();
        if (toppings.size() > 0){
            for (int i = 0; i < toppings.size(); i++) {
                toppingsList.append(toppings.get(i));
                if (i < toppings.size() - 1) {
                    toppingsList.append(", ");
                }
            }
        }
        else{
            toppingsList.append("No toppings");
        }

        return String.format(" - %s), %s, %s, $%.2f",
                this.getCrust(),
                toppingsList,
                this.getSize(),
                price()
        );
    }
}