package com.vendingmachine.service;

import com.vendingmachine.dao.VendingMachineDao;
import com.vendingmachine.dao.VendingMachinePersistenceException;
import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendingMachineServiceImpl  implements VendingMachineServiceLayer{
    VendingMachineDao dao;

    // Constructor to initialize the service
    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    // Method to get the items map
    public List<Item> getItems() {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String item) throws VendingMachinePersistenceException {
        return dao.getItem(item);
    }

    @Override
    public Item removeItem(String item) throws VendingMachinePersistenceException {
        return dao.removeItem(item);
    }

    // Method to add an item to the items map

    public void addItem(Item item) throws VendingMachineDuplicateIdException,
            VendingMachineDataValidationException,
            VendingMachinePersistenceException {
        // First check to see if there is alreay a student
        // associated with the given student's id
        // If so, we're all done here -
        // throw a ClassRosterDuplicateIdException
        if (dao.getItem(item.getName()) != null) {
            throw new VendingMachineDuplicateIdException(
                    "ERROR: Could not create student.  Student Id "
                            + item.getName()
                            + " already exists");
        }
        // Now validate all the fields on the given Student object.
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateStudentData(item);

        // We passed all our business rules checks so go ahead
        // and persist the Student object
        dao.addMoney(item.getName(), item);

        // The student was successfully created, now write to the audit log
//        auditDao.writeAuditEntry(
//                "Student " + student.getStudentId() + " CREATED.");
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

    // Method to display items
    public void displayItems() {
        System.out.println("Available Items:");
        if(dao.get)
        dao.getAllItems();
    }
    private void validateStudentData(Item item) throws
            VendingMachineDataValidationException {

        if (item.getName() == null
                || item.getName().trim().isEmpty()
                || item.getPrice() == null
                || item.getPrice().equals(BigDecimal.valueOf(0))) {

            throw new VendingMachineDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }
}