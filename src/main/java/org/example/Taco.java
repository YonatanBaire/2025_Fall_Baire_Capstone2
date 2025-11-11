package org.example;

import org.example.enums.*;

import java.util.List;

public class Taco implements OrderItem {
    private TacoSize size;
    private TortillaType tortilla;
    private Meat meat;
    private Cheese cheese;
    private List<RegularTopping> regularToppings;
    private List<Sauce> sauces;
    private boolean extraMeat;
    private boolean extraCheese;
    private boolean deepFried;

    public TacoSize getSize() {
        return size;
    }

    public void setSize(TacoSize size) {
        this.size = size;
    }

    public TortillaType getTortilla() {
        return tortilla;
    }

    public void setTortilla(TortillaType tortilla) {
        this.tortilla = tortilla;
    }

    public Meat getMeat() {
        return meat;
    }

    public void setMeat(Meat meat) {
        this.meat = meat;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public List<RegularTopping> getRegularToppings() {
        return regularToppings;
    }

    public void setRegularToppings(List<RegularTopping> regularToppings) {
        this.regularToppings = regularToppings;
    }

    public List<Sauce> getSauces() {
        return sauces;
    }

    public void setSauces(List<Sauce> sauces) {
        this.sauces = sauces;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    public boolean isDeepFried() {
        return deepFried;
    }

    public void setDeepFried(boolean deepFried) {
        this.deepFried = deepFried;
    }

    public void addTopping(RegularTopping topping) {
        if (!regularToppings.contains(topping)) {
            regularToppings.add(topping);
        }
    }

    public void addSauce(Sauce sauce) {
        if (!sauces.contains(sauce)) {
            sauces.add(sauce);
        }
    }

    @Override
    public double getPrice() {
        return PriceCalculator.calculateTacoPrice(this);
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append(size).append(" - ").append(tortilla).append(" Tortilla");
   if (deepFried) {
            description.append(" (Deep Fried)");
        }

        description.append("\n   ");


        if (meat != null) {
            description.append(meat);
            if (extraMeat) {
                description.append(" (Extra)");
            }
        }


        if (cheese != null) {
            if (meat != null) {
                description.append(", ");
            }
            description.append(cheese);
            if (extraCheese) {
                description.append(" (Extra)");
            }
        }


        if (!regularToppings.isEmpty()) {
            description.append("\n   ");
            for (int i = 0; i < regularToppings.size(); i++) {
                description.append(regularToppings.get(i));
                if (i < regularToppings.size() - 1) {
                    description.append(", ");
                }
            }
        }


        if (!sauces.isEmpty()) {
            description.append("\n   Sauces: ");
            for (int i = 0; i < sauces.size(); i++) {
                description.append(sauces.get(i));
                if (i < sauces.size() - 1) {
                    description.append(", ");
                }
            }
        }

        return description.toString();
    }
}


