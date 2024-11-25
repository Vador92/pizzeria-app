package com.example.rupizzeriaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the Deluxe pizza class, with preset toppings
 * @author Varun Doreswamy, Yuet Yue
 */
public class Deluxe extends Pizza{

    // Constants
    private static final double SMALL = 16.99;
    private static final double MEDIUM = 18.99;
    private static final double LARGE = 20.99;
    private static final ArrayList<Topping> TOPPINGS = new ArrayList<>(Arrays.asList(
            Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREENPEPPER,
            Topping.ONION, Topping.MUSHROOM
    ));

    // Instance Variables
    private String style;

    /**
     * This is the default constructor for creating a Deluxe Pizza Object
     * @param crust is the type of pizza crust
     * @param size is the size of the pizza
     * @param style is the style of the pizza (NY or Chicago)
     */
    public Deluxe(Crust crust, Size size, String style) {
        super(TOPPINGS, crust, size);
        this.style = style;
    }

    /**
     * This method returns the price of the pizza based on its customization
     * @return the total price of the pizza based on its customization
     */
    @Override
    public double price() {
        return switch (this.getSize()){
            case SMALL -> SMALL;
            case MEDIUM -> MEDIUM;
            case LARGE -> LARGE;
        };
    }

    /**
     * This is a copy constructor of a Deluxe Pizza object
     * @return a clone of the Deluxe Pizza object
     */
    @Override
    public Pizza copyPizza(){
        return new Deluxe(this.getCrust(), this.getSize(), this.style);
    }

    /**
     * This method formats the Deluxe Pizza in the desired string format
     * @return a string that represents the Deluxe Pizza in a formatted string
     */
    @Override
    public String toString(){
        return String.format("%s (%s%s", getClass().getSimpleName(), style, super.toString());
    }
}
