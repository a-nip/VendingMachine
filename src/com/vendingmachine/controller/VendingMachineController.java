package com.vendingmachine.controller;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import com.service.InsufficientFundsException;

public class VendingMachineController {
    private final VendingMachineService vendingMachineService;
    private final Scanner scanner;

    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
        this.scanner = new Scanner(System.in);
    }

    // Start the vending machine
    public void start() {
        displayItems(); // Display available items
        depositMoney(); // Prompt user to deposit money
        selectItem(); // Prompt user to select an item
    }

    // Display available items
    private void displayItems() {
        System.out.println("Available Items:");
        // Iterate through the items map
        for (Map.Entry<String, Item> entry : vendingMachineService.getItems().entrySet()) {
            String itemName = entry.getKey();    // Get item name
            Item item = entry.getValue();        // Get item object
            BigDecimal itemCost = item.getCost(); // Get item cost
            // Display item name and cost
            System.out.println(itemName + " - $" + itemCost);
        }
    }

    // Prompt user to deposit money
    private void depositMoney() {
        System.out.print("Please deposit money (in dollars): ");
        BigDecimal amount = scanner.nextBigDecimal(); // Get user input
        vendingMachineService.depositMoney(amount); // Deposit money
    }
    // Prompt user to select an item
    private void selectItem() {
        System.out.print("Enter the name of the item you want to purchase: ");
        String itemName = scanner.next(); // Get user input
        try {
            Item selectedItem = vendingMachineService.getItems().get(itemName); // Get selected item
            if (selectedItem == null) {
                // If item is not available
                System.out.println("Sorry, the item '" + itemName + "' is not available.");
                return;
            }
            BigDecimal itemCost = selectedItem.getCost(); // Get item cost
            System.out.println("Item selected: " + selectedItem.getName() + " - Cost: $" + itemCost);
            // Proceed with the purchase logic...
        } catch (InsufficientFundsException e) {
            // If user has insufficient funds
            System.out.println("Insufficient funds. Please deposit more money.");
        } catch (NoItemInventoryException e) {
            // If selected item is out of stock
            System.out.println("Sorry, this item is out of stock.");
        }
    }
}