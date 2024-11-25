package com.example.rupizzeriaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the BBQChicken pizza class, with preset toppings
 * @author Varun Doreswamy, Yuet Yue
 */
public class BBQChicken extends Pizza{

    // Constants
    private static final double SMALL = 14.99;
    private static final double MEDIUM = 16.99;
    private static final double LARGE = 19.99;
    private static final ArrayList<Topping> TOPPINGS = new ArrayList<>(Arrays.asList(
            Topping.BBQCHICKEN, Topping.GREENPEPPER, Topping.PROVOLONE, Topping.CHEDDAR));

    // Instance Variables
    private String style;

    /**
     * This is the default constructor for creating a BBQChicken Pizza object
     * @param crust is the type of pizza crust
     * @param size is the size of the pizza
     * @param style is the style of the pizza (NY or Chicago)
     */
    public BBQChicken(Crust crust, Size size, String style) {
        super(TOPPINGS, crust, size);
        this.style = style;
    }

    /**
     * This method returns the price of the pizza based on its customization
     * @return the total price of the pizza based on its customization
     */
    @Override
    public double price() {
        return switch (this.getSize()) {
            case SMALL -> SMALL;
            case MEDIUM -> MEDIUM;
            case LARGE -> LARGE;
        };
    }

    /**
     * This is a copy constructor of a BBQChicken Pizza object
     * @return a clone of the BBQChicken Pizza object
     */
    @Override
    public Pizza copyPizza(){
        return new BBQChicken(this.getCrust(), this.getSize(), this.style);
    }

    /**
     * This method formats the BBQChicken Pizza in the desired string format
     * @return a string that represents the BBQChicken Pizza in a formatted string
     */
    @Override
    public String toString(){
        return String.format("%s (%s%s", getClass().getSimpleName(), style, super.toString());
    }
}
