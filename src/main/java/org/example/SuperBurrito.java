package org.example;

import org.example.enums.*;

public class SuperBurrito extends Taco {

    public SuperBurrito() {
        // Call parent constructor with predefined values
        super(TacoSize.BURRITO, TortillaType.FLOUR);

        // Set signature toppings
        setMeat(Meat.CARNITAS);
        setCheese(Cheese.CHEDDAR);
        addTopping(RegularTopping.PICO_DE_GALLO);
        addTopping(RegularTopping.LETTUCE);
        addTopping(RegularTopping.TOMATOES);

        // Mark as deep fried (for "Birria Dipped" effect)
        setDeepFried(true);
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append("Super Burrito (Signature)\n");
        description.append(super.getDescription()); // Call parent's description
        return description.toString();
    }
}