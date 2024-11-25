package com.example.rupizzeriaapp;

/**
 * This is the PizzaFactory class, it manages the different types of pizza a user can place
 * @author Varun Doreswamy
 */
public interface PizzaFactory {

    /**
     * This is interface method for creating Deluxe pizzas
     * @return the Deluxe pizza object
     */
    Pizza createDeluxe();

    /**
     * This is interface method for creating Meatzza pizzas
     * @return the Meatzza pizza object
     */
    Pizza createMeatzza();

    /**
     * This is the interface method for creating BBQChicken pizzas
     * @return the BBQChicken pizza object
     */
    Pizza createBBQChicken();

    /**
     * This is the interface method for creating BuildYourOwn pizzas
     * @return the BuildYourOwn pizza object
     */
    Pizza createBuildYourOwn();
}
