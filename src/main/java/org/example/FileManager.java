package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {
private static final String RECEIPTS_FOLDER = "receipts";

    public void saveReceipt(Order order) {

        File folder = new File(RECEIPTS_FOLDER);
        if (!folder.exists()) {
            boolean created = folder.mkdir();
            if (!created) {
                System.out.println("Error: Could not create receipts folder.");
                return;
            }
        }


        String fileName = generateFileName();
        String filePath = RECEIPTS_FOLDER + "/" + fileName;


        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(formatReceipt(order));
            System.out.println("Receipt saved successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String generateFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return now.format(formatter) + ".txt";
    }

    private String formatReceipt(Order order) {
        StringBuilder receipt = new StringBuilder();


        receipt.append("========================================\n");
        receipt.append("         TACO-licious Receipt\n");
        receipt.append("========================================\n");
        receipt.append("Date: ").append(order.getOrderDate()).append("\n");
        receipt.append("Time: ").append(order.getOrderTime()).append("\n\n");


        receipt.append("Items Ordered:\n");
        receipt.append("----------------------------------------\n");

        int itemNumber = 1;
        for (OrderItem item : order.getItems()) {
            receipt.append(itemNumber).append(". ");
            receipt.append(item.getDescription()).append("\n");
            receipt.append(String.format("   Price: $%.2f\n\n", item.getPrice()));
            itemNumber++;
        }


        receipt.append("----------------------------------------\n");
        receipt.append(String.format("TOTAL: $%.2f\n", order.calculateTotal()));
        receipt.append("========================================\n");
        receipt.append("Thank you for dining with TACO-licious!\n");
        receipt.append("Come back soon!\n");
        receipt.append("========================================\n");

        return receipt.toString();
    }
}
