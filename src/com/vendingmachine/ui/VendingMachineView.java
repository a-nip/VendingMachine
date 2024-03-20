package com.vendingmachine.ui;



import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

// VendingMachineView.java

import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class VendingMachineView {
    private Scanner scanner;

    public VendingMachineView(UserIO io) {
        scanner = new Scanner(System.in);
    }

    public int displayMenuAndGetSelection() {
        System.out.println("Vending Machine Menu:");
        System.out.println("1. Display Items");
        System.out.println("2. Add Money");
        System.out.println("3. Purchase Item");
        System.out.println("4. Add Item");
        System.out.println("5. Exit");
        System.out.print("Please select an option: ");
        return scanner.nextInt();
    }

    public void displayItems(List<Item> itemList) {
        System.out.println("Available Items:");
        for (Item item : itemList) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }

    public BigDecimal promptMoneyInput() {
        System.out.print("Enter amount to add: $");
        return scanner.nextBigDecimal();
    }

    public String promptItemSelection() {
        System.out.print("Enter the name of the item you want to purchase: ");
        return scanner.next();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }

    // Method to prompt the user for the name of the item
    public String promptItemName() {
        System.out.print("Enter the name of the item: ");
        return scanner.next();
    }

    // Method to prompt the user for the price of the item
    public BigDecimal promptItemPrice() {
        System.out.print("Enter the price of the item: $");
        return scanner.nextBigDecimal();
    }
}
