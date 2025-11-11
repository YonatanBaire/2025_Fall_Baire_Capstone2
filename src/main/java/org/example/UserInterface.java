package org.example;

import org.example.enums.*;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Order currentOrder;


    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }


    public void run() {
        boolean running = true;

        while (running) {
            displayHomeScreen();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    startNewOrder();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nThank you for visiting TACO-licious!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }


    private void displayHomeScreen() {
        System.out.println("\n========================================");
        System.out.println("       Welcome to TACO-licious!        ");
        System.out.println("========================================");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Enter your choice: ");
    }


    private void startNewOrder() {
        currentOrder = new Order();
        displayOrderScreen();
    }


    private void displayOrderScreen() {
        boolean ordering = true;

        while (ordering) {

            System.out.println(currentOrder.displayOrderSummary());

            System.out.println("\n--- Order Menu ---");
            System.out.println("1) Add Custom Taco");
            System.out.println("2) Add Signature Street Taco");
            System.out.println("3) Add Signature Super Burrito");
            System.out.println("4) Add Drink");
            System.out.println("5) Add Chips & Salsa");
            System.out.println("6) Remove Item");
            System.out.println("7) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Taco taco = addTacoScreen();
                    if (taco != null) {
                        currentOrder.addItem(taco);
                        System.out.println("\n✓ Custom taco added to order!");
                    }
                    break;
                case 2:
                    addSignatureStreetTaco();  // NEW!
                    break;
                case 3:
                    addSignatureSuperBurrito();  // NEW!
                    break;
                case 4:
                    Drink drink = addDrinkScreen();
                    if (drink != null) {
                        currentOrder.addItem(drink);
                        System.out.println("\n✓ Drink added to order!");
                    }
                    break;
                case 5:
                    ChipsAndSalsa chips = addChipsScreen();
                    if (chips != null) {
                        currentOrder.addItem(chips);
                        System.out.println("\n✓ Chips & Salsa added to order!");
                    }
                    break;
                case 6:
                    removeItemScreen();
                    break;
                case 7:
                    if (currentOrder.isValid()) {
                        checkout();
                        ordering = false;
                    } else {
                        System.out.println("\n✗ Invalid order! Must have at least one item.");
                    }
                    break;
                case 0:
                    currentOrder = null;
                    ordering = false;
                    System.out.println("\n✗ Order cancelled.");
                    break;
                default:
                    System.out.println("\n✗ Invalid option.");
            }
        }
    }


    private Taco addTacoScreen() {
        System.out.println("\n========================================");
        System.out.println("       Customize Your Taco");
        System.out.println("========================================");

        TacoBuilder builder = new TacoBuilder();


        System.out.println("\nSelect taco size:");
        System.out.println("1) Single Taco ($3.50)");
        System.out.println("2) 3-Taco Plate ($9.00)");
        System.out.println("3) Burrito ($8.50)");
        System.out.print("Enter choice: ");
        int sizeChoice = scanner.nextInt();
        scanner.nextLine();

        switch (sizeChoice) {
            case 1:
                builder.setSize(TacoSize.SINGLE);
                break;
            case 2:
                builder.setSize(TacoSize.THREE_TACO_PLATE);
                break;
            case 3:
                builder.setSize(TacoSize.BURRITO);
                break;
            default:
                System.out.println("Invalid choice. Taco not added.");
                return null;
        }


        System.out.println("\nSelect tortilla:");
        System.out.println("1) Corn");
        System.out.println("2) Flour");
        System.out.println("3) Hard Shell");
        System.out.println("4) Bowl");
        System.out.print("Enter choice: ");
        int tortillaChoice = scanner.nextInt();
        scanner.nextLine();

        switch (tortillaChoice) {
            case 1:
                builder.setTortilla(TortillaType.CORN);
                break;
            case 2:
                builder.setTortilla(TortillaType.FLOUR);
                break;
            case 3:
                builder.setTortilla(TortillaType.HARD_SHELL);
                break;
            case 4:
                builder.setTortilla(TortillaType.BOWL);
                break;
            default:
                System.out.println("Invalid choice. Taco not added.");
                return null;
        }


        System.out.println("\nSelect meat:");
        System.out.println("1) Carne Asada");
        System.out.println("2) Al Pastor");
        System.out.println("3) Carnitas");
        System.out.println("4) Pollo");
        System.out.println("5) Chorizo");
        System.out.println("6) Pescado");
        System.out.println("0) No meat");
        System.out.print("Enter choice: ");
        int meatChoice = scanner.nextInt();
        scanner.nextLine();

        switch (meatChoice) {
            case 1:
                builder.setMeat(Meat.CARNE_ASADA);
                break;
            case 2:
                builder.setMeat(Meat.AL_PASTOR);
                break;
            case 3:
                builder.setMeat(Meat.CARNITAS);
                break;
            case 4:
                builder.setMeat(Meat.POLLO);
                break;
            case 5:
                builder.setMeat(Meat.CHORIZO);
                break;
            case 6:
                builder.setMeat(Meat.PESCADO);
                break;
            case 0:

                break;
            default:
                System.out.println("Invalid choice. No meat added.");
        }


        if (meatChoice != 0) {
            System.out.print("\nWould you like extra meat? (y/n): ");
            String extraMeatResponse = scanner.nextLine().toLowerCase();
            if (extraMeatResponse.equals("y")) {
                builder.setExtraMeat(true);
            }
        }


        System.out.println("\nSelect cheese:");
        System.out.println("1) Queso Fresco");
        System.out.println("2) Oaxaca");
        System.out.println("3) Cotija");
        System.out.println("4) Cheddar");
        System.out.println("0) No cheese");
        System.out.print("Enter choice: ");
        int cheeseChoice = scanner.nextInt();
        scanner.nextLine();

        switch (cheeseChoice) {
            case 1:
                builder.setCheese(Cheese.QUESO_FRESCO);
                break;
            case 2:
                builder.setCheese(Cheese.OAXACA);
                break;
            case 3:
                builder.setCheese(Cheese.COTIJA);
                break;
            case 4:
                builder.setCheese(Cheese.CHEDDAR);
                break;
            case 0:

                break;
            default:
                System.out.println("Invalid choice. No cheese added.");
        }


        if (cheeseChoice != 0) {
            System.out.print("\nWould you like extra cheese? (y/n): ");
            String extraCheeseResponse = scanner.nextLine().toLowerCase();
            if (extraCheeseResponse.equals("y")) {
                builder.setExtraCheese(true);
            }
        }


        System.out.println("\nSelect toppings (enter 0 when done):");
        boolean addingToppings = true;
        while (addingToppings) {
            System.out.println("1) Lettuce");
            System.out.println("2) Cilantro");
            System.out.println("3) Onions");
            System.out.println("4) Tomatoes");
            System.out.println("5) Jalapeños");
            System.out.println("6) Radishes");
            System.out.println("7) Pico de Gallo");
            System.out.println("8) Guacamole");
            System.out.println("9) Corn");
            System.out.println("0) Done adding toppings");
            System.out.print("Enter choice: ");
            int toppingChoice = scanner.nextInt();
            scanner.nextLine();

            switch (toppingChoice) {
                case 1:
                    builder.addTopping(RegularTopping.LETTUCE);
                    System.out.println("Added Lettuce");
                    break;
                case 2:
                    builder.addTopping(RegularTopping.CILANTRO);
                    System.out.println("Added Cilantro");
                    break;
                case 3:
                    builder.addTopping(RegularTopping.ONIONS);
                    System.out.println("Added Onions");
                    break;
                case 4:
                    builder.addTopping(RegularTopping.TOMATOES);
                    System.out.println("Added Tomatoes");
                    break;
                case 5:
                    builder.addTopping(RegularTopping.JALAPENOS);
                    System.out.println("Added Jalapeños");
                    break;
                case 6:
                    builder.addTopping(RegularTopping.RADISHES);
                    System.out.println("Added Radishes");
                    break;
                case 7:
                    builder.addTopping(RegularTopping.PICO_DE_GALLO);
                    System.out.println("Added Pico de Gallo");
                    break;
                case 8:
                    builder.addTopping(RegularTopping.GUACAMOLE);
                    System.out.println("Added Guacamole");
                    break;
                case 9:
                    builder.addTopping(RegularTopping.CORN);
                    System.out.println("Added Corn");
                    break;
                case 0:
                    addingToppings = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }


        System.out.println("\nSelect sauces (enter 0 when done):");
        boolean addingSauces = true;
        while (addingSauces) {
            System.out.println("1) Salsa Verde");
            System.out.println("2) Salsa Roja");
            System.out.println("3) Chipotle");
            System.out.println("4) Habanero");
            System.out.println("5) Mild");
            System.out.println("6) Extra Hot");
            System.out.println("0) Done adding sauces");
            System.out.print("Enter choice: ");
            int sauceChoice = scanner.nextInt();
            scanner.nextLine();

            switch (sauceChoice) {
                case 1:
                    builder.addSauce(Sauce.SALSA_VERDE);
                    System.out.println("Added Salsa Verde");
                    break;
                case 2:
                    builder.addSauce(Sauce.SALSA_ROJA);
                    System.out.println("Added Salsa Roja");
                    break;
                case 3:
                    builder.addSauce(Sauce.CHIPOTLE);
                    System.out.println("Added Chipotle");
                    break;
                case 4:
                    builder.addSauce(Sauce.HABANERO);
                    System.out.println("Added Habanero");
                    break;
                case 5:
                    builder.addSauce(Sauce.MILD);
                    System.out.println("Added Mild");
                    break;
                case 6:
                    builder.addSauce(Sauce.EXTRA_HOT);
                    System.out.println("Added Extra Hot");
                    break;
                case 0:
                    addingSauces = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }


        System.out.print("\nWould you like this taco deep fried? (y/n): ");
        String deepFriedResponse = scanner.nextLine().toLowerCase();
        if (deepFriedResponse.equals("y")) {
            builder.setDeepFried(true);
        }


        return builder.build();
    }


    private Drink addDrinkScreen() {
        System.out.println("\n========================================");
        System.out.println("         Add Drink");
        System.out.println("========================================");

        System.out.println("\nSelect drink size:");
        System.out.println("1) Small ($2.00)");
        System.out.println("2) Medium ($2.50)");
        System.out.println("3) Large ($3.00)");
        System.out.print("Enter choice: ");
        int sizeChoice = scanner.nextInt();
        scanner.nextLine();

        DrinkSize size = null;
        switch (sizeChoice) {
            case 1:
                size = DrinkSize.SMALL;
                break;
            case 2:
                size = DrinkSize.MEDIUM;
                break;
            case 3:
                size = DrinkSize.LARGE;
                break;
            default:
                System.out.println("Invalid choice. Drink not added.");
                return null;
        }

        System.out.print("\nEnter drink flavor (e.g., Coca Cola, Sprite, Water): ");
        String flavor = scanner.nextLine();

        return new Drink(size, flavor);
    }


    private ChipsAndSalsa addChipsScreen() {
        System.out.println("\n========================================");
        System.out.println("       Add Chips & Salsa");
        System.out.println("========================================");

        System.out.println("\nSelect salsa type:");
        System.out.println("1) Verde");
        System.out.println("2) Roja");
        System.out.println("3) Chipotle");
        System.out.print("Enter choice: ");
        int salsaChoice = scanner.nextInt();
        scanner.nextLine();

        switch (salsaChoice) {
            case 1:
                return new ChipsAndSalsa(SalsaType.VERDE);
            case 2:
                return new ChipsAndSalsa(SalsaType.ROJA);
            case 3:
                return new ChipsAndSalsa(SalsaType.CHIPOTLE);
            default:
                System.out.println("Invalid choice. Chips not added.");
                return null;
        }
    }


    private void removeItemScreen() {
        if (currentOrder.getItemCount() == 0) {
            System.out.println("\n✗ No items to remove.");
            return;
        }

        System.out.println(currentOrder.displayOrderSummary());
        System.out.print("\nEnter item number to remove (0 to cancel): ");

        int itemNum = scanner.nextInt();
        scanner.nextLine();

        if (itemNum == 0) {
            return;
        }

        if (itemNum > 0 && itemNum <= currentOrder.getItemCount()) {
            OrderItem removed = currentOrder.removeItem(itemNum - 1);
            System.out.println("\n✓ Removed: " + removed.getDescription());
        } else {
            System.out.println("\n✗ Invalid item number.");
        }
    }


    private void checkout() {
        System.out.println(currentOrder.getOrderDetails());

        System.out.println("\n1) Confirm Order");
        System.out.println("0) Cancel");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            currentOrder.saveReceipt();
            System.out.println("\n✓ Order confirmed! Receipt saved.");
            System.out.println("Thank you for your order!");
        } else {
            System.out.println("\n✗ Checkout cancelled. Returning to order menu.");
        }
    }

    private void addSignatureStreetTaco() {
        System.out.println("\n========================================");
        System.out.println("      Signature Street Taco");
        System.out.println("========================================");
        System.out.println("Includes:");
        System.out.println("- 3-Taco Plate");
        System.out.println("- Corn Tortillas");
        System.out.println("- Carne Asada");
        System.out.println("- Onions");
        System.out.println("- Cilantro");
        System.out.println("- Salsa Verde");
        System.out.println("========================================");

        StreetTaco taco = new StreetTaco();

        System.out.println("\nWould you like to customize this taco?");
        System.out.println("1) Add as-is");
        System.out.println("2) Customize toppings");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 2) {
            customizeSignatureTaco(taco);
        }

        currentOrder.addItem(taco);
        System.out.println("\n✓ Street Taco added to order!");
    }

    private void addSignatureSuperBurrito() {
        System.out.println("\n========================================");
        System.out.println("      Signature Super Burrito");
        System.out.println("========================================");
        System.out.println("Includes:");
        System.out.println("- Burrito");
        System.out.println("- Flour Tortilla");
        System.out.println("- Carnitas");
        System.out.println("- Cheddar Cheese");
        System.out.println("- Pico de Gallo");
        System.out.println("- Lettuce");
        System.out.println("- Tomatoes");
        System.out.println("- Birria Dipped (Deep Fried)");
        System.out.println("========================================");

        SuperBurrito burrito = new SuperBurrito();

        System.out.println("\nWould you like to customize this burrito?");
        System.out.println("1) Add as-is");
        System.out.println("2) Customize toppings");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 2) {
            customizeSignatureTaco(burrito);
        }

        currentOrder.addItem(burrito);
        System.out.println("\n✓ Super Burrito added to order!");
    }

    private void customizeSignatureTaco(Taco taco) {
        System.out.println("\n--- Customize Your Signature Taco ---");

        boolean customizing = true;
        while (customizing) {
            System.out.println("\n1) Add extra meat");
            System.out.println("2) Add extra cheese");
            System.out.println("3) Add more toppings");
            System.out.println("4) Add more sauces");
            System.out.println("5) Remove a topping");
            System.out.println("0) Done customizing");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    taco.setExtraMeat(true);
                    System.out.println("✓ Extra meat added");
                    break;
                case 2:
                    taco.setExtraCheese(true);
                    System.out.println("✓ Extra cheese added");
                    break;
                case 3:
                    addMoreToppings(taco);
                    break;
                case 4:
                    addMoreSauces(taco);
                    break;
                case 5:
                    removeTopping(taco);
                    break;
                case 0:
                    customizing = false;
                    break;
                default:
                    System.out.println("✗ Invalid option");
            }
        }
    }

    private void addMoreToppings(Taco taco) {
        System.out.println("\nSelect topping to add:");
        System.out.println("1) Lettuce");
        System.out.println("2) Cilantro");
        System.out.println("3) Onions");
        System.out.println("4) Tomatoes");
        System.out.println("5) Jalapeños");
        System.out.println("6) Radishes");
        System.out.println("7) Pico de Gallo");
        System.out.println("8) Guacamole");
        System.out.println("9) Corn");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                taco.addTopping(RegularTopping.LETTUCE);
                break;
            case 2:
                taco.addTopping(RegularTopping.CILANTRO);
                break;
            case 3:
                taco.addTopping(RegularTopping.ONIONS);
                break;
            case 4:
                taco.addTopping(RegularTopping.TOMATOES);
                break;
            case 5:
                taco.addTopping(RegularTopping.JALAPENOS);
                break;
            case 6:
                taco.addTopping(RegularTopping.RADISHES);
                break;
            case 7:
                taco.addTopping(RegularTopping.PICO_DE_GALLO);
                break;
            case 8:
                taco.addTopping(RegularTopping.GUACAMOLE);
                break;
            case 9:
                taco.addTopping(RegularTopping.CORN);
                break;
            default:
                System.out.println("✗ Invalid choice");
                return;
        }
        System.out.println("✓ Topping added");
    }

    private void addMoreSauces(Taco taco) {
        System.out.println("\nSelect sauce to add:");
        System.out.println("1) Salsa Verde");
        System.out.println("2) Salsa Roja");
        System.out.println("3) Chipotle");
        System.out.println("4) Habanero");
        System.out.println("5) Mild");
        System.out.println("6) Extra Hot");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                taco.addSauce(Sauce.SALSA_VERDE);
                break;
            case 2:
                taco.addSauce(Sauce.SALSA_ROJA);
                break;
            case 3:
                taco.addSauce(Sauce.CHIPOTLE);
                break;
            case 4:
                taco.addSauce(Sauce.HABANERO);
                break;
            case 5:
                taco.addSauce(Sauce.MILD);
                break;
            case 6:
                taco.addSauce(Sauce.EXTRA_HOT);
                break;
            default:
                System.out.println("✗ Invalid choice");
                return;
        }
        System.out.println("✓ Sauce added");
    }

    private void removeTopping(Taco taco) {
        if (taco.getRegularToppings().isEmpty()) {
            System.out.println("No toppings to remove!");
            return;
        }

        System.out.println("\nCurrent toppings:");
        for (int i = 0; i < taco.getRegularToppings().size(); i++) {
            System.out.println((i + 1) + ") " + taco.getRegularToppings().get(i));
        }

        System.out.print("Enter topping number to remove: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= taco.getRegularToppings().size()) {
            RegularTopping removed = taco.getRegularToppings().remove(choice - 1);
            System.out.println("✓ Removed " + removed);
        } else {
            System.out.println("✗ Invalid choice");
        }
    }
}