package com.vendingmachine.service;

import com.vendingmachine.dao.VendingMachineDao;
import com.vendingmachine.dao.VendingMachinePersistenceException;
import com.vendingmachine.dto.Change;
import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private final VendingMachineDao dao;
    private final Change wallet;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
        this.wallet = new Change();
    }

    @Override
    public List<Item> getItems() {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        return dao.getItem(itemName);
    }

    @Override
    public void depositMoney(BigDecimal amount) {
        dao.addMoney(amount);
    }

    @Override
    public void addItem(Item item) {
        dao.addItem(item);
    }

    @Override
    public BigDecimal getDepositedAmount() {
        return dao.getDepositedAmount();
    }

    @Override
    public void setStock(List<Item> itemList) {
        dao.setStock(itemList);
    }


    public String purchaseItem(String itemName) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException {
        Item selectedItem = dao.getItem(itemName);
        if (selectedItem == null) {
            throw new NoItemInventoryException("Item not found in inventory: " + itemName);
        }
        BigDecimal itemCost = selectedItem.getPrice();
        BigDecimal depositedAmount = dao.getDepositedAmount();
        if (depositedAmount.compareTo(itemCost) < 0) {
            throw new InsufficientFundsException("Insufficient funds to purchase item: " + itemName);
        }
        BigDecimal changeAmount = depositedAmount.subtract(itemCost);
        dao.addMoney(changeAmount.negate());
        return "Change returned: $" + changeAmount;
    }

    @Override
    public Item removeItem(String itemId) throws VendingMachinePersistenceException {
        List<Item> itemList = dao.getAllItems();
        for (Item item : itemList) {
            if (item.getName().equals(itemId)) {
                itemList.remove(item);
                dao.setStock(itemList);
                return item;
            }
        }
        throw new VendingMachinePersistenceException("Item not found: " + itemId);
    }


    // Method to deposit money
    //TODO check for valid input

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