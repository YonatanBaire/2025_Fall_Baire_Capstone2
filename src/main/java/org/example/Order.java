package org.example;

import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class Order {
    private String orderDate;
    private String orderTime;
    private List<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
        setDateTime();
    }

    private void setDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        this.orderDate = now.format(dateFormatter);
        this.orderTime = now.format(timeFormatter);
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }

    public OrderItem getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

 public void addItem(OrderItem item) {
        items.add(item);
    }


    public OrderItem removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.remove(index);
        }
        return null;
    }

     public double calculateTotal() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();

        details.append("========================================\n");
        details.append("         TACO-licious Receipt\n");
        details.append("========================================\n");
        details.append("Date: ").append(orderDate).append("\n");
        details.append("Time: ").append(orderTime).append("\n\n");

        details.append("Items Ordered:\n");
        details.append("----------------------------------------\n");

        for (int i = 0; i < items.size(); i++) {
            details.append((i + 1)).append(". ");
            details.append(items.get(i).getDescription()).append("\n");
            details.append(String.format("   $%.2f\n\n", items.get(i).getPrice()));
        }

        details.append("----------------------------------------\n");
        details.append(String.format("TOTAL: $%.2f\n", calculateTotal()));
        details.append("========================================\n");
        details.append("Thank you for your order!\n");

        return details.toString();
    }


    public String displayOrderSummary() {
        StringBuilder summary = new StringBuilder();

        summary.append("\n========================================\n");
        summary.append("          CURRENT ORDER\n");
        summary.append("========================================\n");

        if (items.isEmpty()) {
            summary.append("(No items in order yet)\n");
        } else {
            for (int i = 0; i < items.size(); i++) {
                summary.append((i + 1)).append(". ");
                summary.append(items.get(i).getDescription()).append("\n");
                summary.append(String.format("   $%.2f\n", items.get(i).getPrice()));
            }
            summary.append("----------------------------------------\n");
            summary.append(String.format("Current Total: $%.2f\n", calculateTotal()));
        }

        summary.append("========================================\n");

        return summary.toString();
    }


    public void saveReceipt() {
        FileManager fileManager = new FileManager();
        fileManager.saveReceipt(this);
    }


    public boolean isValid() {
        if (items.isEmpty()) {
            return false;
        }

        boolean hasTaco = false;
        boolean hasOtherItem = false;

        for (OrderItem item : items) {
            if (item instanceof Taco) {
                hasTaco = true;
            } else {
                hasOtherItem = true;
            }
        }


        if (!hasTaco && !hasOtherItem) {
            return false;
        }

        return true;
    }
}
