package com.vendingmachine.service;

import com.vendingmachine.dao.VendingMachineDao;
import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    //TODO - private dao,
    private List<Item> items;
    private BigDecimal depositedAmount;

    // Constructor to initialize the service
    //TODO (DAO) this.dao = dao
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
//        this.items = new HashMap<>(); // Initialize the items map
//        this.depositedAmount = BigDecimal.ZERO; // Initialize the deposited amount to zero//to be rid
    }

    // Method to get the items map
    //TODO-ADD checks for different inputs
    public List<Item> getItems() {
        return items;
    }

    // Method to add an item to the items map
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    // Method to deposit money
    //TODO check for valid input
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
//    public void displayItems() {
//        System.out.println("Available Items:");
//        // Iterate through the items map and print each item with its cost
//        for (Map.Entry<String, Item> entry : items.entrySet()) {
//            String itemName = entry.getKey();                 // Get the item name
//            Item item = entry.getValue();                      // Get the item object
//            BigDecimal itemCost = item.getPrice();             // Get the cost of the item
//            System.out.println(itemName + " - $" + itemCost); // Print item name and cost
//        }
//    }
}