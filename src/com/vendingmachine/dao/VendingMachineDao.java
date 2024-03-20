package com.vendingmachine.dao;

import com.vendingmachine.dto.Item;
import com.vendingmachine.enums.Coins;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {
    //Takes money input and adds to wallet.
    BigDecimal addMoney(BigDecimal moneyToAdd);

    //Gets item value and calculates change from interval between item value and wallet funds.
    Map<Coins, BigDecimal> getChange(Item item);

    //Selects item by name, decrementing stock quantity and wallet funds.
    Item getItem(String name);

    //Returns all items currently saved in memory.
    List<Item> getAllItems();

    //Unmarshalls item from file line read-in.
    Item unmarshallItem(String itemAsText);

    //Converts item into text string.
    String marshallItem(Item item);

    //Writes stock to file.
    void writeStock() throws VendingMachinePersistenceException;

    //Reads stock from file.
    void readStock() throws VendingMachinePersistenceException;

    BigDecimal getDepositedAmount();

    void setStock(List<Item> itemList);

    void addItem(Item item);
}
