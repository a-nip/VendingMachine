package com.vendingmachine.dao;

import com.vendingmachine.dto.Item;
import com.vendingmachine.enums.Coins;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {
    //Takes money input and adds to wallet.
    BigDecimal addMoney(BigDecimal moneyToAdd);

    //Returns wallet amount.
    BigDecimal getMoney();

    //Gets item value and calculates change from interval between item value and wallet funds.
    Map<Coins, BigDecimal> getChange(Item item);

    //Selects item by name, decrementing stock quantity and wallet funds.
    Item getItem(String name) throws VendingMachinePersistenceException;

    //Returns all items currently saved in memory.
    List<Item> getAllItems();

    //Adds a new item to stock.
    Item addItem(String name, BigDecimal price, int quantity);

    //Removes an item from stock.
    Item removeItem(String name);

    //Unmarshalls item from file line read-in.
    Item unmarshallItem(String itemAsText);

    //Converts item into text string.
    String marshallItem(Item item);

    //Writes stock to file.
    void writeStock() throws VendingMachinePersistenceException;

    BigDecimal getDepositedAmount();

    //Reads stock from file.
    void readStock() throws VendingMachinePersistenceException;

    void addItem(Item item);

    void setStock(List<Item> itemList);
}
