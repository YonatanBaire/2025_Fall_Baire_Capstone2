package org.example;

import org.example.enums.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class UserInterfaceGUI extends JFrame {
    private Order currentOrder;
    private JTextArea orderSummaryArea;
    private JLabel totalLabel = new JLabel();

    // Colors
    private static final Color TACO_ORANGE = new Color(255, 140, 0);
    private static final Color TACO_YELLOW = new Color(255, 200, 100);
    private static final Color TACO_BROWN = new Color(139, 90, 43);

    public UserInterfaceGUI() {
        setTitle("TACO-licious Point of Sale System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showHomeScreen();

        setVisible(true);
    }

    // ==================== HOME SCREEN ====================
    private void showHomeScreen() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(TACO_YELLOW);

    // Title
    JLabel title = new JLabel("Welcome to TACO-licious!", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 48));
    title.setForeground(TACO_BROWN);
    title.setBorder(new EmptyBorder(100, 0, 50, 0));

    // Buttons
    JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 20, 20));
    buttonPanel.setBackground(TACO_YELLOW);
    buttonPanel.setBorder(new EmptyBorder(0, 300, 200, 300));

    JButton newOrderBtn = new JButton("New Order");
    newOrderBtn.setFont(new Font("Arial", Font.BOLD, 28));
    newOrderBtn.setBackground(TACO_ORANGE);
    newOrderBtn.setForeground(Color.WHITE);
    newOrderBtn.setFocusPainted(false);
    newOrderBtn.addActionListener(e -> startNewOrder());

    JButton exitBtn = new JButton("Exit");
    exitBtn.setFont(new Font("Arial", Font.BOLD, 28));
    exitBtn.setBackground(new Color(200, 100, 100));
    exitBtn.setForeground(Color.WHITE);
    exitBtn.setFocusPainted(false);
    exitBtn.addActionListener(e -> System.exit(0));

    buttonPanel.add(newOrderBtn);
    buttonPanel.add(exitBtn);

    panel.add(title, BorderLayout.NORTH);
    panel.add(buttonPanel, BorderLayout.CENTER);

    setContentPane(panel);
    revalidate();
    repaint();
}
    private void startNewOrder() {
        currentOrder = new Order();
        showOrderScreen();
    }

    private void showOrderScreen() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        JLabel titleLabel = new JLabel("Current Order", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(TACO_BROWN);
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);


        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(450, 0));

        JLabel summaryLabel = new JLabel("Order Summary:");
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        summaryLabel.setBorder(new EmptyBorder(5, 5, 5, 5));

        orderSummaryArea = new JTextArea();
        orderSummaryArea.setEditable(false);
        orderSummaryArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        orderSummaryArea.setMargin(new Insets(10, 10, 10, 10));
        updateOrderSummary();

        JScrollPane scrollPane = new JScrollPane(orderSummaryArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(TACO_BROWN, 2));

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 24));
        totalLabel.setForeground(TACO_BROWN);
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        leftPanel.add(summaryLabel, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(totalLabel, BorderLayout.SOUTH);

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // RIGHT: Action Buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(9, 1, 10, 10));
        rightPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        rightPanel.setBackground(Color.WHITE);

        JButton addTacoBtn = createStyledButton("🌮 Add Custom Taco", new Font("Arial", Font.BOLD, 16));
        addTacoBtn.addActionListener(e -> addCustomTaco());

        JButton addStreetTacoBtn = createStyledButton("🌮 Street Taco (Signature)", new Font("Arial", Font.BOLD, 16));
        addStreetTacoBtn.setBackground(new Color(144, 238, 144));
        addStreetTacoBtn.addActionListener(e -> addStreetTaco());

        JButton addBurritoBtn = createStyledButton("🌯 Super Burrito (Signature)", new Font("Arial", Font.BOLD, 16));
        addBurritoBtn.setBackground(new Color(144, 238, 144));
        addBurritoBtn.addActionListener(e -> addSuperBurrito());

        JButton addDrinkBtn = createStyledButton("🥤 Add Drink", new Font("Arial", Font.BOLD, 16));
        addDrinkBtn.addActionListener(e -> addDrink());

        JButton addChipsBtn = createStyledButton("🍟 Add Chips & Salsa", new Font("Arial", Font.BOLD, 16));
        addChipsBtn.addActionListener(e -> addChips());

        JButton removeBtn = createStyledButton("🗑️ Remove Item", new Font("Arial", Font.BOLD, 16));
        removeBtn.setBackground(new Color(255, 200, 150));
        removeBtn.addActionListener(e -> removeItem());

        JButton checkoutBtn = createStyledButton("✅ Checkout", new Font("Arial", Font.BOLD, 18));
        checkoutBtn.setBackground(new Color(100, 200, 100));
        checkoutBtn.addActionListener(e -> checkout());

        JButton cancelBtn = createStyledButton("❌ Cancel Order", new Font("Arial", Font.BOLD, 18));
        cancelBtn.setBackground(new Color(255, 150, 150));
        cancelBtn.addActionListener(e -> cancelOrder());

        rightPanel.add(addTacoBtn);
        rightPanel.add(addStreetTacoBtn);
        rightPanel.add(addBurritoBtn);
        rightPanel.add(addDrinkBtn);
        rightPanel.add(addChipsBtn);
        rightPanel.add(new JLabel()); // Spacer
        rightPanel.add(removeBtn);
        rightPanel.add(checkoutBtn);
        rightPanel.add(cancelBtn);

        mainPanel.add(rightPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        revalidate();
        repaint();
    }

    // ==================== HELPER: UPDATE ORDER SUMMARY ====================
    private void updateOrderSummary() {
        if (currentOrder.getItemCount() == 0) {
            orderSummaryArea.setText("(No items in order yet)");
            totalLabel.setText("Total: $0.00");
        } else {
            StringBuilder summary = new StringBuilder();
            for (int i = 0; i < currentOrder.getItemCount(); i++) {
                OrderItem item = currentOrder.getItem(i);
                summary.append((i + 1)).append(". ");
                summary.append(item.getDescription().replace("\n", "\n   "));
                summary.append(String.format("\n   $%.2f\n\n", item.getPrice()));
            }
            orderSummaryArea.setText(summary.toString());
            totalLabel.setText(String.format("Total: $%.2f", currentOrder.calculateTotal()));
        }
        orderSummaryArea.setCaretPosition(0);
    }

    // ==================== ADD CUSTOM TACO ====================
    private void addCustomTaco() {
        JDialog dialog = new JDialog(this, "Build Your Custom Taco", true);
        dialog.setSize(600, 700);
        dialog.setLocationRelativeTo(this);

        TacoBuilder builder = new TacoBuilder();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Size
        panel.add(createLabel("Select Taco Size:"));
        JComboBox<String> sizeCombo = new JComboBox<>(new String[]{
            "Single Taco ($3.50)", "3-Taco Plate ($9.00)", "Burrito ($8.50)"
        });
        panel.add(sizeCombo);
        panel.add(Box.createVerticalStrut(10));

        // Tortilla
        panel.add(createLabel("Select Tortilla:"));
        JComboBox<String> tortillaCombo = new JComboBox<>(new String[]{
            "Corn", "Flour", "Hard Shell", "Bowl"
        });
        panel.add(tortillaCombo);
        panel.add(Box.createVerticalStrut(10));

        // Meat
        panel.add(createLabel("Select Meat:"));
        JComboBox<String> meatCombo = new JComboBox<>(new String[]{
            "No Meat", "Carne Asada", "Al Pastor", "Carnitas", "Pollo", "Chorizo", "Pescado"
        });
        panel.add(meatCombo);
        JCheckBox extraMeatCheck = new JCheckBox("Extra Meat (+$)");
        panel.add(extraMeatCheck);
        panel.add(Box.createVerticalStrut(10));

        // Cheese
        panel.add(createLabel("Select Cheese:"));
        JComboBox<String> cheeseCombo = new JComboBox<>(new String[]{
            "No Cheese", "Queso Fresco", "Oaxaca", "Cotija", "Cheddar"
        });
        panel.add(cheeseCombo);
        JCheckBox extraCheeseCheck = new JCheckBox("Extra Cheese (+$)");
        panel.add(extraCheeseCheck);
        panel.add(Box.createVerticalStrut(10));

        // Toppings
        panel.add(createLabel("Select Toppings:"));
        JCheckBox[] toppingChecks = {
            new JCheckBox("Lettuce"),
            new JCheckBox("Cilantro"),
            new JCheckBox("Onions"),
            new JCheckBox("Tomatoes"),
            new JCheckBox("Jalapeños"),
            new JCheckBox("Radishes"),
            new JCheckBox("Pico de Gallo"),
            new JCheckBox("Guacamole"),
            new JCheckBox("Corn")
        };
        JPanel toppingPanel = new JPanel(new GridLayout(3, 3));
        for (JCheckBox check : toppingChecks) {
            toppingPanel.add(check);
        }
        panel.add(toppingPanel);
        panel.add(Box.createVerticalStrut(10));

        // Sauces
        panel.add(createLabel("Select Sauces:"));
        JCheckBox[] sauceChecks = {
            new JCheckBox("Salsa Verde"),
            new JCheckBox("Salsa Roja"),
            new JCheckBox("Chipotle"),
            new JCheckBox("Habanero"),
            new JCheckBox("Mild"),
            new JCheckBox("Extra Hot")
        };
        JPanel saucePanel = new JPanel(new GridLayout(2, 3));
        for (JCheckBox check : sauceChecks) {
            saucePanel.add(check);
        }
        panel.add(saucePanel);
        panel.add(Box.createVerticalStrut(10));

        // Deep Fried
        JCheckBox deepFriedCheck = new JCheckBox("Deep Fried");
        panel.add(deepFriedCheck);
        panel.add(Box.createVerticalStrut(20));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add to Order");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.addActionListener(e -> {
            // Build taco
            int sizeIdx = sizeCombo.getSelectedIndex();
            builder.setSize(sizeIdx == 0 ? TacoSize.SINGLE :
                           sizeIdx == 1 ? TacoSize.THREE_TACO_PLATE : TacoSize.BURRITO);

            int tortillaIdx = tortillaCombo.getSelectedIndex();
            builder.setTortilla(tortillaIdx == 0 ? TortillaType.CORN :
                              tortillaIdx == 1 ? TortillaType.FLOUR :
                              tortillaIdx == 2 ? TortillaType.HARD_SHELL : TortillaType.BOWL);

            int meatIdx = meatCombo.getSelectedIndex();
            if (meatIdx > 0) {
                Meat[] meats = {Meat.CARNE_ASADA, Meat.AL_PASTOR, Meat.CARNITAS,
                               Meat.POLLO, Meat.CHORIZO, Meat.PESCADO};
                builder.setMeat(meats[meatIdx - 1]);
            }
            builder.setExtraMeat(extraMeatCheck.isSelected());

            int cheeseIdx = cheeseCombo.getSelectedIndex();
            if (cheeseIdx > 0) {
                Cheese[] cheeses = {Cheese.QUESO_FRESCO, Cheese.OAXACA, Cheese.COTIJA, Cheese.CHEDDAR};
                builder.setCheese(cheeses[cheeseIdx - 1]);
            }
            builder.setExtraCheese(extraCheeseCheck.isSelected());

            RegularTopping[] toppings = {RegularTopping.LETTUCE, RegularTopping.CILANTRO,
                RegularTopping.ONIONS, RegularTopping.TOMATOES, RegularTopping.JALAPENOS,
                RegularTopping.RADISHES, RegularTopping.PICO_DE_GALLO,
                RegularTopping.GUACAMOLE, RegularTopping.CORN};
            for (int i = 0; i < toppingChecks.length; i++) {
                if (toppingChecks[i].isSelected()) {
                    builder.addTopping(toppings[i]);
                }
            }

            Sauce[] sauces = {Sauce.SALSA_VERDE, Sauce.SALSA_ROJA, Sauce.CHIPOTLE,
                             Sauce.HABANERO, Sauce.MILD, Sauce.EXTRA_HOT};
            for (int i = 0; i < sauceChecks.length; i++) {
                if (sauceChecks[i].isSelected()) {
                    builder.addSauce(sauces[i]);
                }
            }

            builder.setDeepFried(deepFriedCheck.isSelected());

            Taco taco = builder.build();
            currentOrder.addItem(taco);
            updateOrderSummary();
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "✅ Custom taco added!");
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel);

        JScrollPane scrollPane = new JScrollPane(panel);
        dialog.add(scrollPane);
        dialog.setVisible(true);
    }

    // ==================== ADD SIGNATURE TACOS ====================
    private void addStreetTaco() {
        int choice = JOptionPane.showConfirmDialog(this,
            "Street Taco includes:\n" +
            "- 3-Taco Plate\n" +
            "- Corn Tortillas\n" +
            "- Carne Asada\n" +
            "- Onions, Cilantro\n" +
            "- Salsa Verde\n\n" +
            "Would you like to customize it?",
            "Street Taco", JOptionPane.YES_NO_CANCEL_OPTION);

        if (choice == JOptionPane.CANCEL_OPTION) return;

        StreetTaco taco = new StreetTaco();

        if (choice == JOptionPane.YES_OPTION) {
            customizeSignatureTaco(taco);
        }

        currentOrder.addItem(taco);
        updateOrderSummary();
        JOptionPane.showMessageDialog(this, "✅ Street Taco added!");
    }

    private void addSuperBurrito() {
        int choice = JOptionPane.showConfirmDialog(this,
            "Super Burrito includes:\n" +
            "- Burrito Size\n" +
            "- Flour Tortilla\n" +
            "- Carnitas\n" +
            "- Cheddar Cheese\n" +
            "- Pico de Gallo, Lettuce, Tomatoes\n" +
            "- Birria Dipped (Deep Fried)\n\n" +
            "Would you like to customize it?",
            "Super Burrito", JOptionPane.YES_NO_CANCEL_OPTION);

        if (choice == JOptionPane.CANCEL_OPTION) return;

        SuperBurrito burrito = new SuperBurrito();

        if (choice == JOptionPane.YES_OPTION) {
            customizeSignatureTaco(burrito);
        }

        currentOrder.addItem(burrito);
        updateOrderSummary();
        JOptionPane.showMessageDialog(this, "✅ Super Burrito added!");
    }

    private void customizeSignatureTaco(Taco taco) {
        String[] options = {"Add Extra Meat", "Add Extra Cheese", "Add Toppings", "Done"};

        while (true) {
            String choice = (String) JOptionPane.showInputDialog(this,
                "Customize your signature taco:", "Customize",
                JOptionPane.QUESTION_MESSAGE, null, options, options[3]);

            if (choice == null || choice.equals("Done")) break;

            if (choice.equals("Add Extra Meat")) {
                taco.setExtraMeat(true);
                JOptionPane.showMessageDialog(this, "✅ Extra meat added");
            } else if (choice.equals("Add Extra Cheese")) {
                taco.setExtraCheese(true);
                JOptionPane.showMessageDialog(this, "✅ Extra cheese added");
            } else if (choice.equals("Add Toppings")) {
                String[] toppings = {"Lettuce", "Cilantro", "Onions", "Tomatoes",
                                   "Jalapeños", "Radishes", "Pico de Gallo", "Guacamole", "Corn"};
                String topping = (String) JOptionPane.showInputDialog(this,
                    "Select topping:", "Add Topping",
                    JOptionPane.QUESTION_MESSAGE, null, toppings, toppings[0]);

                if (topping != null) {
                    RegularTopping[] toppingEnums = {RegularTopping.LETTUCE, RegularTopping.CILANTRO,
                        RegularTopping.ONIONS, RegularTopping.TOMATOES, RegularTopping.JALAPENOS,
                        RegularTopping.RADISHES, RegularTopping.PICO_DE_GALLO,
                        RegularTopping.GUACAMOLE, RegularTopping.CORN};
                    for (int i = 0; i < toppings.length; i++) {
                        if (toppings[i].equals(topping)) {
                            taco.addTopping(toppingEnums[i]);
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "✅ " + topping + " added");
                }
            }
        }
    }

    // ==================== ADD DRINK ====================
    private void addDrink() {
        String[] sizes = {"Small ($2.00)", "Medium ($2.50)", "Large ($3.00)"};
        String size = (String) JOptionPane.showInputDialog(this,
            "Select drink size:", "Add Drink",
            JOptionPane.QUESTION_MESSAGE, null, sizes, sizes[1]);

        if (size != null) {
            String flavor = JOptionPane.showInputDialog(this, "Enter drink flavor:");
            if (flavor != null && !flavor.trim().isEmpty()) {
                DrinkSize drinkSize = size.contains("Small") ? DrinkSize.SMALL :
                                     size.contains("Large") ? DrinkSize.LARGE : DrinkSize.MEDIUM;

                Drink drink = new Drink(drinkSize, flavor);
                currentOrder.addItem(drink);
                updateOrderSummary();
                JOptionPane.showMessageDialog(this, "✅ Drink added!");
            }
        }
    }

    // ==================== ADD CHIPS ====================
    private void addChips() {
        String[] types = {"Verde", "Roja", "Chipotle"};
        String type = (String) JOptionPane.showInputDialog(this,
            "Select salsa type:", "Add Chips & Salsa",
            JOptionPane.QUESTION_MESSAGE, null, types, types[0]);

        if (type != null) {
            SalsaType salsaType = type.equals("Roja") ? SalsaType.ROJA :
                                 type.equals("Chipotle") ? SalsaType.CHIPOTLE : SalsaType.VERDE;

            ChipsAndSalsa chips = new ChipsAndSalsa(salsaType);
            currentOrder.addItem(chips);
            updateOrderSummary();
            JOptionPane.showMessageDialog(this, "✅ Chips & Salsa added!");
        }
    }

    // ==================== REMOVE ITEM ====================
    private void removeItem() {
        if (currentOrder.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No items to remove!");
            return;
        }

        String[] items = new String[currentOrder.getItemCount()];
        for (int i = 0; i < currentOrder.getItemCount(); i++) {
            items[i] = (i + 1) + ". " + currentOrder.getItem(i).getDescription().split("\n")[0];
        }

        String selected = (String) JOptionPane.showInputDialog(this,
            "Select item to remove:", "Remove Item",
            JOptionPane.QUESTION_MESSAGE, null, items, items[0]);

        if (selected != null) {
            int index = Integer.parseInt(selected.split("\\.")[0]) - 1;
            currentOrder.removeItem(index);
            updateOrderSummary();
            JOptionPane.showMessageDialog(this, "✅ Item removed!");
        }
    }

    // ==================== CHECKOUT ====================
    private void checkout() {
        if (!currentOrder.isValid()) {
            JOptionPane.showMessageDialog(this,
                "❌ Order must have at least one item!",
                "Invalid Order", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextArea receiptArea = new JTextArea(currentOrder.getOrderDetails());
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        int confirm = JOptionPane.showConfirmDialog(this, scrollPane,
            "Confirm Order", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            currentOrder.saveReceipt();
            JOptionPane.showMessageDialog(this,
                "✅ Order confirmed!\n Receipt saved.\n\nThank you for your order!",
                "Order Complete", JOptionPane.INFORMATION_MESSAGE);
            currentOrder = null;
            showHomeScreen();
        }
    }

    // ==================== CANCEL ORDER ====================
    private void cancelOrder() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel this order?",
            "Cancel Order", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            currentOrder = null;
            showHomeScreen();
            JOptionPane.showMessageDialog(this, "Order cancelled.");
        }
    }

    // ==================== HELPER METHODS ====================
    private JButton createStyledButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(TACO_ORANGE);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
        Color originalColor = button.getBackground();

        public void mouseEntered(MouseEvent e) {
            button.setBackground(originalColor.brighter());
        }
        public void mouseExited(MouseEvent e) {
            button.setBackground(originalColor);
        }
        });

        return button;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
}