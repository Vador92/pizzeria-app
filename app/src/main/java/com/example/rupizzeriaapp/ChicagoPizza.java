package com.example.rupizzeriaapp;

import java.util.ArrayList;

/**
 * This class is the ChicagoPizza class, that implements the pizza factory
 * Used to create pizzas that revolve around the Chicago style options.
 * @author Varun Doreswamy
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * This is the default constructor for creating a Chicago Style Deluxe Pizza
     * @return a Chicago Style Deluxe Pizza object
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEPDISH, Size.SMALL, "Chicago Style");
    }

    /**
     * This is the default constructor for creating a Chicago Style Meatzza Pizza
     * @return a Chicago Style Meatzza Pizza object
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED, Size.SMALL, "Chicago Style");
    }

    /**
     * This is the default constructor for creating a Chicago Style BBQChicken Pizza
     * @return a Chicago Style BBQChicken Pizza object
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN, Size.SMALL, "Chicago Style");
    }

    /**
     * This is the default constructor for creating a Chicago Style BuildYourOwn Pizza
     * @return a Chicago Style BuildYourOwn Pizza object
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(new ArrayList<>(), Crust.PAN, Size.SMALL, "Chicago Style");
    }
}
