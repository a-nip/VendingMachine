package com.vendingmachine.service;

import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineServiceLayer {
    private Map<String, Item> items;
    private BigDecimal depositedAmount;

    // Constructor to initialize the service
    public VendingMachineServiceLayer() {
        this.items = new HashMap<>(); // Initialize the items map
        this.depositedAmount = BigDecimal.ZERO; // Initialize the deposited amount to zero
    }

    // Method to get the items map
    public Map<String, Item> getItems() {
        return items;
    }

    // Method to add an item to the items map
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    // Method to deposit money
    public void depositMoney(BigDecimal amount) {
        depositedAmount = depositedAmount.add(amount);
    }

    // Method to purchase an item
    public String purchaseItem(String itemName) throws InsufficientFundsException, NoItemInventoryException {
        Item selectedItem = items.get(itemName); // Get the selected item
        if (selectedItem == null) {
            // If the selected item is not found in the inventory, throw an exception
            throw new NoItemInventoryException("Item not found in inventory: " + itemName);
        }
        BigDecimal itemCost = selectedItem.getPrice(); // Get the cost of the selected item
        if (depositedAmount.compareTo(itemCost) < 0) {
            // If the deposited amount is less than the item cost, throw an exception
            throw new InsufficientFundsException("Insufficient funds to purchase item: " + itemName);
        }
        // Calculate change
        BigDecimal changeAmount = depositedAmount.subtract(itemCost);
        depositedAmount = BigDecimal.ZERO; // Reset deposited amount
        return "Change returned: $" + changeAmount;
    }

    // Method to display items
    public void displayItems() {
        System.out.println("Available Items:");
        // Iterate through the items map and print each item with its cost
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            String itemName = entry.getKey();                 // Get the item name
            Item item = entry.getValue();                      // Get the item object
            BigDecimal itemCost = item.getPrice();             // Get the cost of the item
            System.out.println(itemName + " - $" + itemCost); // Print item name and cost
        }
    }
}