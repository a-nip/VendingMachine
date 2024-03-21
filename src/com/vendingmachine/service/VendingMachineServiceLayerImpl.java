package com.vendingmachine.service;

import com.vendingmachine.dao.VendingMachineDao;
import com.vendingmachine.dao.VendingMachinePersistenceException;
import com.vendingmachine.dto.Item;
import com.vendingmachine.enums.Coins;
import java.math.BigDecimal;
import java.util.List;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    private VendingMachineDao vendingMachineDao;
    public VendingMachineServiceLayerImpl(VendingMachineDao vendingMachineDao) {
        this.vendingMachineDao = vendingMachineDao;
    }
    @Override
    public void addItem(Item item) throws VendingMachineDuplicateIdException, VendingMachineDataValidationException, VendingMachinePersistenceException {
        // Delegate to the DAO layer method
        vendingMachineDao.addItem(item.getName(), item.getPrice(), item.getQuantity());
    }
    @Override
    public List<Item> getItems() throws VendingMachinePersistenceException {
        return vendingMachineDao.getAllItems();
    }
    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        return vendingMachineDao.getItem(itemName);
    }
    @Override
    public Item removeItem(String itemName) throws VendingMachinePersistenceException {
        return vendingMachineDao.removeItem(itemName);
    }
    @Override
    public void depositMoney(BigDecimal amount) throws VendingMachineDataValidationException {
        vendingMachineDao.addMoney(amount);
    }
    @Override
    public String purchaseItem(String itemName) throws InsufficientFundsException, NoItemInventoryException {
        try {
            // First, get the item from DAO
            Item item = vendingMachineDao.getItem(itemName);
            if (item == null) {
                throw new NoItemInventoryException("Item not found in inventory");
            }
            BigDecimal itemPrice = item.getPrice();
            BigDecimal currentMoney = vendingMachineDao.getMoney();
            if (currentMoney.compareTo(itemPrice) < 0) {
                throw new InsufficientFundsException("Not enough money to purchase item");
            }
            // Calculate change
            Map<Coins, BigDecimal> change = vendingMachineDao.getChange(item);
            // Perform purchase
            vendingMachineDao.removeItem(itemName);
            // Return a message indicating successful purchase
            return "Item purchased successfully: " + itemName + ". Change: " + change.toString();
        } catch (VendingMachinePersistenceException e) {
            // Rethrow the exception
            throw new NoItemInventoryException("Error purchasing item", e);
        }
    }

}
