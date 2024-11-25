package com.example.rupizzeriaapp;

public enum Topping {
    SAUSAGE(),
    PEPPERONI(),
    GREENPEPPER(),
    ONION(),
    MUSHROOM(),
    BBQCHICKEN(),
    PROVOLONE(),
    CHEDDAR(),
    BEEF(),
    SPINACH(),
    PINEAPPLE(),
    OLIVE(),
    JALEPENO(),
    HAM();

    /**
     * This method converts the enums to the desired string format
     * @return enum constant in a proper string format
     */
    @Override
    public String toString(){
        return switch (name()){
            case "GREENPEPPER" -> name().charAt(0) + name().substring(1,5).toLowerCase()
                    + " " + name().charAt(5) + name().substring(6).toLowerCase();
            case "BBQCHICKEN" -> name().substring(0,3) + " " + name().charAt(3)
                    + name().substring(4).toLowerCase();
            default -> name().charAt(0) + name().substring(1).toLowerCase();
        };
    }
}
