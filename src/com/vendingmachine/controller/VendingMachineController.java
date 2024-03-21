package com.vendingmachine.controller;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import com.vendingmachine.service.InsufficientFundsException;
import com.vendingmachine.service.NoItemInventoryException;
import com.vendingmachine.dao.VendingMachinePersistenceException;
import com.vendingmachine.dto.Item;
import com.vendingmachine.service.VendingMachineDataValidationException;
import com.vendingmachine.service.VendingMachineServiceLayer;
import com.vendingmachine.ui.VendingMachineView;


public class VendingMachineController {
    private final VendingMachineServiceLayer vendingMachineService;
    private final VendingMachineView vendingMachineView;
    private final Scanner scanner;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.vendingMachineService = service;
        this.vendingMachineView = view;
        this.scanner = new Scanner(System.in);
    }

    // Start the vending machine
    public void start() throws VendingMachineDataValidationException, VendingMachinePersistenceException {
        displayItems(); // Display available items
        depositMoney(); // Prompt user to deposit money
        selectItem(); // Prompt user to select an item
    }

    // Display available items
    private void displayItems() throws VendingMachinePersistenceException {
        System.out.println("Available Items:");
        // Iterate through the items map
        for (Map.Entry<String, Item> entry : vendingMachineService.getItems().entrySet()) {
            String itemName = entry.getKey(); // Get item name
            Item item = entry.getValue(); // Get item object
            BigDecimal itemCost = item.getPrice(); // Get item cost
            // Display item name and cost
            System.out.println(itemName + " - $" + itemCost);
        }
    }

    // Prompt user to deposit money
    private void depositMoney() throws VendingMachineDataValidationException {
        System.out.print("Please deposit money (in dollars): ");
        BigDecimal amount = scanner.nextBigDecimal(); // Get user input
        vendingMachineService.depositMoney(amount); // Deposit money
    }

    // Prompt user to select an item
    private void selectItem() throws VendingMachinePersistenceException {
        System.out.print("Enter the name of the item you want to purchase: ");
        String itemName = scanner.next(); // Get user input
        try {
            Item selectedItem = vendingMachineService.getItems().get(Integer.parseInt(itemName)); // Get selected item
            if (selectedItem == null) {
                // If item is not available
                System.out.println("Sorry, the item '" + itemName + "' is not available.");
                return;
            }
            BigDecimal itemCost = selectedItem.getPrice(); // Get item cost
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
