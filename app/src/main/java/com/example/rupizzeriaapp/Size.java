package com.example.rupizzeriaapp;

/**
 * This is the enum class for the pizza sizes
 * @author Varun Doreswamy, Yuet Yue
 */
public enum Size {
    SMALL(),
    MEDIUM(),
    LARGE();

    /**
     * This method converts the enums to the desired string format
     * @return enum constant in a proper string format
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
