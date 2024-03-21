package com.vendingmachine.controller;

import java.math.BigDecimal;
import java.util.List;
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
    private VendingMachineServiceLayer serviceLayer;
    private VendingMachineView view;

    public VendingMachineController(VendingMachineServiceLayer serviceLayer, VendingMachineView view) {
        this.serviceLayer = serviceLayer;
        this.view = view;
    }

    public void run() {
        boolean keepRunning = true;
        while (keepRunning) {
            int choice = view.displayMenuAndGetSelection();
            switch (choice) {
                case 1:
                    displayItems();
                    break;
                case 2:
                    addMoney();
                    break;
                case 3:
                    purchaseItem();
                    break;
                case 4:
                    addItem();
                    break;
                case 5:
                    keepRunning = false;
                    break;
                default:
                    view.displayError("Invalid choice. Please try again.");
            }
        }
    }

    private void displayItems() {
        try {
            List<Item> items = serviceLayer.getItems();
            view.displayItems(items);
        } catch (Exception e) {
            view.displayError("Error occurred while fetching items: " + e.getMessage());
        }
    }

    private void addMoney() {
        BigDecimal amount = view.promptMoneyInput();
        try {
            serviceLayer.depositMoney(amount);
            view.displayMessage("Money added successfully.");
        } catch (Exception e) {
            view.displayError("Error occurred while adding money: " + e.getMessage());
        }
    }

    private void purchaseItem() {
        String itemName = view.promptItemSelection();
        try {
            Item item = serviceLayer.removeItem(itemName);
            if (item != null) {
                view.displayMessage("Item purchased successfully: " + item.getName());
            } else {
                view.displayMessage("Item not found: " + itemName);
            }
        } catch (Exception e) {
            view.displayError("Error occurred while purchasing item: " + e.getMessage());
        }
    }

    private void addItem() {
        String name = view.promptItemName();
        BigDecimal price = view.promptItemPrice();
        try {
            Item newItem = new Item(name, price);
            serviceLayer.addItem(newItem);
            view.displayMessage("Item added successfully: " + newItem.getName());
        } catch (Exception e) {
            view.displayError("Error occurred while adding item: " + e.getMessage());
        }
    }
}