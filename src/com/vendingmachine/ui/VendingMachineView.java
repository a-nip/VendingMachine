package com.vendingmachine.ui;

import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class VendingMachineView {
    private final UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int displayMenuAndGetSelection() {
        io.print("Vending Machine Menu:");
        io.print("1. Display Items");
        io.print("2. Add Money");
        io.print("3. Purchase Item");
        io.print("4. Add Item");
        io.print("5. Exit");
        return io.readInt("Please select an option: ",1, 5);
    }

    //Displays in the format: Name - $Price (xQuantity)
    public void displayItems(List<Item> itemList) {
        io.print("Available Items:");
        for (Item item : itemList) {
            io.print(item.getName()
                    + " - $" + item.getPrice()
                    + " (x" + item.getQuantity() + ")");
        }
    }

    public BigDecimal promptMoneyInput() {
        return io.readBigDecimal("Enter amount to add: $",
                BigDecimal.ZERO, BigDecimal.valueOf(Double.MAX_VALUE)); //Bounds to positive value
    }

    public void displayMessage(String message) {
        io.print(message);
    }

    public void displayError(String errorMessage) {
        io.print("Error: " + errorMessage);
    }

    //Displays a title banner to declare each menu option (Obsolete? To remove or use?)
    public void displayBanner(String banner) {
        io.print("=== " + banner + " ===");
    }

    public BigDecimal promptItemPrice() {
        return io.readBigDecimal("Enter the price of the item: ",
                BigDecimal.ZERO, BigDecimal.valueOf(Double.MAX_VALUE));
    }

    public String promptItemName() {
        return io.readString("Please enter an item name: ");
    }

}