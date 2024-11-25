package com.example.rupizzeriaapp;

import java.util.ArrayList;

/**
 * This class is the NYPizza class, that implements the pizza factory
 * Used to create pizzas that revolve around the New York style options
 * @author Yuet Yue
 */
public class NYPizza implements PizzaFactory {

    /**
     * This is the default constructor for creating a New York Style Deluxe Pizza
     * @return a New York Style Deluxe Pizza object
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEPDISH, Size.SMALL, "NY Style");
    }

    /**
     * This is the default constructor for creating a New York Style Meatzza Pizza
     * @return a New York Style Meatzza Pizza object
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED, Size.SMALL, "NY Style");
    }

    /**
     * This is the default constructor for creating a New York Style BBQChicken Pizza
     * @return a New York Style BBQChicken Pizza object
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN, Size.SMALL, "NY Style");
    }

    /**
     * This is the default constructor for creating a New York Style BuildYourOwn Pizza
     * @return a New York Style BuildYourOwn Pizza object
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(new ArrayList<>(), Crust.PAN, Size.SMALL, "NY Style");
    }
}
