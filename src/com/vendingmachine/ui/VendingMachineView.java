package com.vendingmachine.ui;

import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {
    private final UserIO io;
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Students");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public void displayCreateStudentBanner() {
        io.print("=== Create Item ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Item successfully created.  Please hit enter to continue");
    }

    public void displayStudentList(List<Item> studentList) {
//        for (Map.Entry<Integer, Item> entry : studentList.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
        for (Item currentStudent : studentList) {
            String studentInfo = String.format("#%s : %s",
                    currentStudent.getName(),
                    currentStudent.getPrice());
            io.print(studentInfo);
        }
        walletAmnt();
        io.readString("Please hit enter to continue.");
    }

    public void walletAmnt(){
        io.readString("Please Enter an amount.");

    }
    public void displayDisplayAllBanner() {
        io.print("=== Display All Items ===");
    }

    public void displayDisplayStudentBanner () {
        io.print("=== Display Item ===");
    }

    public String getStudentIdChoice() {
        return io.readString("Please enter the Item ID.");
    }

    public Item getNewStudentInfo() {
        String name = io.readString("Please enter Student ID");
        String price = io.readString("Please enter First Name");
        Item currentStudent = new Item(name); //Also needs price; see Item class
        currentStudent.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        return currentStudent;
    }
    public void displayStudent(Item student) {
        if (student != null) {
            io.print(student.getName());
            io.print(String.valueOf(student.getPrice()));
            io.print("");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveStudentBanner () {
        io.print("=== Remove Item ===");
    }

    public void displayRemoveResult(Item studentRecord) {
        if(studentRecord != null){
            io.print("Item successfully removed.");
        }else{
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
