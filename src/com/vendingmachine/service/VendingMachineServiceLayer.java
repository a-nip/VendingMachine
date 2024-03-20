package com.vendingmachine.service;

import com.vendingmachine.dao.VendingMachinePersistenceException;
import com.vendingmachine.dto.Item;

import java.util.List;

public interface VendingMachineServiceLayer {

    void addItem(Item items) throws
            VendingMachineDuplicateIdException,
            VendingMachineDataValidationException,
            VendingMachinePersistenceException;

    List<Item> getItems() throws
            VendingMachinePersistenceException;

    Item getStudent(String studentId) throws
            VendingMachinePersistenceException;

    Item removeStudent(String studentId) throws
            VendingMachinePersistenceException;

}
