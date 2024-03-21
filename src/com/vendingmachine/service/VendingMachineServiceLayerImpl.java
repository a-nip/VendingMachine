package com.vendingmachine.service;

import com.vendingmachine.dao.VendingMachineDao;
import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    // Method to get the items map
    public List<Item> getItems() {
        return items;
    }

    // Method to add an item to the items map
    public void addItem(Item item) {
        items.add(item.getName(), item);
    }

    // Method to deposit money
    public void depositMoney(BigDecimal amount) {
        dao.addMoney(amount);
    }

    // Method to purchase an item
    public String purchaseItem(String itemName) throws InsufficientFundsException, NoItemInventoryException {
        Item selectedItem = dao.getItem(itemName); // Get the selected item
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
    //TODO- validate big decimal
    
}