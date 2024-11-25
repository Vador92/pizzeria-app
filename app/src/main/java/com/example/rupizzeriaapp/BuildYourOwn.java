package com.example.rupizzeriaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the BuildYourOwn pizza class, with preset toppings.
 * @author Varun Doreswamy, Yuet Yue
 */
public class BuildYourOwn extends Pizza{

    // Constants
    private static final double SMALL = 8.99;
    private static final double MEDIUM = 10.99;
    private static final double LARGE = 12.99;
    private static final double TOPPING = 1.69;

    // Instance Variables
    private String style;

    /**
     * This is the default constructor for creating a BuildYourOwn Pizza object
     * @param toppings is the choice of toppings added onto the pizza
     * @param crust is the type of pizza crust
     * @param size is the size of the pizza
     * @param style is the style of the pizza (NY or Chicago)
     */
    public BuildYourOwn(ArrayList<Topping> toppings, Crust crust, Size size, String style) {
        super(toppings, crust, size);
        this.style = style;
    }

    /**
     * This method returns the price of the pizza based on its customization
     * @return the total price of the pizza based on its customization
     */
    @Override
    public double price() {
        double price = switch (this.getSize()) {
            case SMALL -> SMALL + this.getToppings().size() * TOPPING;
            case MEDIUM -> MEDIUM + this.getToppings().size() * TOPPING;
            case LARGE -> LARGE + this.getToppings().size() * TOPPING;
        };
        return Double.parseDouble(String.format("%.2f", price));
    }

    /**
     * This is a copy constructor of a BuildYourOwn Pizza object
     * @return a clone of the BuildYourOwn Pizza Object
     */
    @Override
    public Pizza copyPizza(){
        return new BuildYourOwn(new ArrayList<>(this.getToppings()), this.getCrust(), this.getSize(), this.style);
    }

    /**
     * THis method formats the BuildYourOwn Pizza in the desired string format
     * @return a string that represents the BuildYourOwn Pizza in a formatted string
     */
    @Override
    public String toString(){
        return String.format("%s (%s%s", getClass().getSimpleName(), style, super.toString());
    }
}
