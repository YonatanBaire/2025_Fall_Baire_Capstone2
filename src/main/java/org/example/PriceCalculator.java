package org.example;

import org.example.enums.DrinkSize;

public class PriceCalculator {

    public static double calculateTacoPrice(Taco taco){
         double price = 0.0;

        switch (taco.getSize()) {
            case SINGLE:
                price = 3.50;
                break;
            case THREE_TACO_PLATE:
                price = 9.00;
                break;
            case BURRITO:
                price = 8.50;
                break;
        }

        if (taco.getMeat() != null) {
            switch (taco.getSize()) {
                case SINGLE:
                    price += 1.00;
                    if (taco.hasExtraMeat()) price += 0.50;
                    break;
                case THREE_TACO_PLATE:
                    price += 2.00;
                    if (taco.hasExtraMeat()) price += 1.00;
                    break;
                case BURRITO:
                    price += 3.00;
                    if (taco.hasExtraMeat()) price += 1.50;
                    break;
            }
        }

        if (taco.getCheese() != null) {
            switch (taco.getSize()) {
                case SINGLE:
                    price += 0.75;
                    if (taco.hasExtraCheese()) price += 0.30;
                    break;
                case THREE_TACO_PLATE:
                    price += 1.50;
                    if (taco.hasExtraCheese()) price += 0.60;
                    break;
                case BURRITO:
                    price += 2.25;
                    if (taco.hasExtraCheese()) price += 0.90;
                    break;
            }
        }

        return price;
    }

    public static double calculateDrinkPrice(DrinkSize size){
        switch (size) {
            case SMALL:
                return 2.00;
            case MEDIUM:
                return 2.50;
            case LARGE:
                return 3.00;
            default:
                return 0.00;
        }
    }

    public static double getChipsSalsaPrice(){
        return 1.50;
    }
}
