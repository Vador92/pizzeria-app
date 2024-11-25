package com.example.rupizzeriaapp;

/**
 * This is an enum class for the type crust for the Pizza
 * @author Varun Doreswamy, Yuet Yue
 */
public enum Crust {
    DEEPDISH(),
    BROOKLYN(),
    PAN(),
    THIN(),
    STUFFED(),
    HANDTOSSED();

    /**
     * This method formats the HANDTOSSED and DEEPDISH strings into a desired format
     * @return the formatted string of "HandTossed" or "DeepDish"
     */
    @Override
    public String toString(){
        String conv;
        switch (name()){
            case "DEEPDISH", "HANDTOSSED":
                conv = name().charAt(0) + name().substring(1, 4).toLowerCase()
                        + name().charAt(4) + name().substring(5).toLowerCase();
                break;
            default:
                conv = name().charAt(0) + name().substring(1).toLowerCase();
        }
        return conv;
    }
}