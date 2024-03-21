package com.vendingmachine.service;

import com.vendingmachine.dao.VendingMachinePersistenceException;
import com.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    void addItem(Item items) throws
            VendingMachineDuplicateIdException,
            VendingMachineDataValidationException,
            VendingMachinePersistenceException;

    List<Item> getItems() throws
            VendingMachinePersistenceException;

    Item getItem(String studentId) throws
            VendingMachinePersistenceException;

    Item removeItem(String studentId) throws
            VendingMachinePersistenceException;

    void depositMoney(BigDecimal b) throws VendingMachineDataValidationException;

    String purchaseItem(String itemName) throws InsufficientFundsException, NoItemInventoryException;
}
