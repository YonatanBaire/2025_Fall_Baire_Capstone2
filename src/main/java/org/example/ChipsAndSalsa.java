package org.example;

import org.example.enums.SalsaType;

public class ChipsAndSalsa implements OrderItem {
    private SalsaType salsaType;

    public ChipsAndSalsa(SalsaType salsaType) {
        this.salsaType = salsaType;
    }

    @Override
    public double getPrice() {
        return  1.50;
        }
    @Override
        public String getDescription() {
            return "Chips & Salsa - " + salsaType;
        }
}
