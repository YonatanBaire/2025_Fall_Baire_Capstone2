package org.example;

import org.example.enums.*;

import java.util.ArrayList;
import java.util.List;

public class TacoBuilder {
        private TacoSize size;
        private TortillaType tortilla;
        private Meat meat;
        private Cheese cheese;
        private List<RegularTopping> regularToppings;
        private List<Sauce> sauces;
        private boolean extraMeat;
        private boolean extraCheese;
        private boolean deepFried;

        public TacoBuilder() {
            this.regularToppings = new ArrayList<>();
            this.sauces = new ArrayList<>();
            this.extraMeat = false;
            this.extraCheese = false;
            this.deepFried = false;
        }

        public TacoBuilder setSize(TacoSize size) {
            this.size = size;
            return this;
        }

        public TacoBuilder setTortilla(TortillaType tortilla) {
            this.tortilla = tortilla;
            return this;
        }

        public TacoBuilder setMeat(Meat meat) {
            this.meat = meat;
            return this;
        }

        public TacoBuilder setCheese(Cheese cheese) {
            this.cheese = cheese;
            return this;
        }

        public TacoBuilder addTopping(RegularTopping topping) {
            if (!regularToppings.contains(topping)) {
                regularToppings.add(topping);
            }
            return this;
        }

        public TacoBuilder addSauce(Sauce sauce) {
            if (!sauces.contains(sauce)) {
                sauces.add(sauce);
            }
            return this;
        }

        public TacoBuilder setExtraMeat(boolean extraMeat) {
            this.extraMeat = extraMeat;
            return this;
        }

        public TacoBuilder setExtraCheese(boolean extraCheese) {
            this.extraCheese = extraCheese;
            return this;
        }

        public TacoBuilder setDeepFried(boolean deepFried) {
            this.deepFried = deepFried;
            return this;
        }

        public Taco build() {

            if (size == null) {
                throw new IllegalStateException("Taco size is required");
            }
            if (tortilla == null) {
                throw new IllegalStateException("Tortilla type is required");
            }


            Taco taco = new Taco(size, tortilla);


            if (meat != null) {
                taco.setMeat(meat);
            }
            if (cheese != null) {
                taco.setCheese(cheese);
            }

            taco.setExtraMeat(extraMeat);
            taco.setExtraCheese(extraCheese);
            taco.setDeepFried(deepFried);


            for (RegularTopping topping : regularToppings) {
                taco.addTopping(topping);
            }


            for (Sauce sauce : sauces) {
                taco.addSauce(sauce);
            }

            return taco;
        }

//        Optional: Reset method to reuse the builder
//        public TacoBuilder reset() {
//            this.size = null;
//            this.tortilla = null;
//            this.meat = null;
//            this.cheese = null;
//            this.regularToppings.clear();
//            this.sauces.clear();
//            this.extraMeat = false;
//            this.extraCheese = false;
//            this.deepFried = false;
//            return this;
//        }
    }
