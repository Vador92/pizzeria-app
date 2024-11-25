package com.example.rupizzeriaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The class is the BBQChicken pizza class with preset toppings
 * @author Varun Doreswamy, Yuet Yue
 */
public class Meatzza extends Pizza{

    // Constants
    private static final double SMALL = 17.99;
    private static final double MEDIUM = 19.99;
    private static final double LARGE = 21.99;
    private static final ArrayList<Topping> TOPPINGS = new ArrayList<>(Arrays.asList(
            Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM
    ));

    // Instance Variables
    private String style;

    /**
     * This is the default constructor for creating a Meatzza Pizza object
     * @param crust is the type of pizza crust
     * @param size is the size of the pizza
     * @param style is the style of the pizza (NY or Chicago)
     */
    public Meatzza(Crust crust, Size size, String style) {
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
     * This is a copy constructor of a Meatzza Pizza object
     * @return a clone of the Meatzza Pizza object
     */
    @Override
    public Pizza copyPizza(){
        return new Meatzza(this.getCrust(), this.getSize(), this.style);
    }

    /**
     * This method formats the Meatzza Pizza in the desired string format
     * @return a string that represents the Meatzza Pizza in a formatted string
     */
    @Override
    public String toString(){
        return String.format("%s (%s%s", getClass().getSimpleName(), style, super.toString());
    }
}
