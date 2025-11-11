package org.example;

import org.example.enums.*;

public class StreetTaco extends Taco {

    public StreetTaco() {
        // Call parent constructor with predefined values
        super(TacoSize.THREE_TACO_PLATE, TortillaType.CORN);

        // Set signature toppings
        setMeat(Meat.CARNE_ASADA);
        addTopping(RegularTopping.ONIONS);
        addTopping(RegularTopping.CILANTRO);
        addSauce(Sauce.SALSA_VERDE);
        // Note: Lime wedges are included by default (in requirements)
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append("Street Taco (Signature)\n");
        description.append(super.getDescription()); // Call parent's description
        return description.toString();
    }
}