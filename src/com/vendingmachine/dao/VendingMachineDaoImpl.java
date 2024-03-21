package com.vendingmachine.dao;

import com.vendingmachine.dto.Change;
import com.vendingmachine.dto.Item;
import com.vendingmachine.enums.Coins;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.math.RoundingMode;

public class VendingMachineDaoImpl implements VendingMachineDao {
    public Change wallet = new Change();
    public final List<Item> stock = new ArrayList<>();
    public static final String STOCK_FILE = "stock.txt";
    public static final String DELIMITER = "::";
    @Override
    public BigDecimal addMoney(BigDecimal moneyToAdd) {
        wallet.setAmount(
                wallet.getAmount()
                        .add(moneyToAdd));
        return wallet.getAmount();
    }

    @Override
    public BigDecimal getMoney() {
        return wallet.getAmount();
    }

    @Override
    public Map<Coins, BigDecimal> getChange(Item item) {
        Map<Coins, BigDecimal> change = new HashMap<>();
        //Subtract price from wallet total
        BigDecimal remainder = wallet.getAmount().subtract(item.getPrice());
        //Iterate through coins to get each coin amount
        for(Coins c : Coins.values()) {
            //Get coin value
            BigDecimal coinValue = c.getValue();
            //Get quantity of this coin to dispense
            BigDecimal coinQuantity = remainder.divide(coinValue, 2, RoundingMode.DOWN);
            //Get total value of this coin's incidence
            BigDecimal thisCoinTotal = coinValue.multiply(coinQuantity);
            //Assign values to map to return
            change.put(c, coinQuantity);
            //Subtract this coin's total from remainder and loop
            remainder = remainder.subtract(coinValue.multiply(coinQuantity));
        }
        return change;
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        for(Item i : stock) {
            if (i.getName().equals(name)){
                i.setQuantity(i.getQuantity() - 1);
                writeStock();
                return i;

            }
        }
        //Return null on failure to find item.
        System.out.println("TExt");
        return null;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        readStock();
        return stock;
    }

    @Override
    public Item addItem(String name, BigDecimal price, int quantity) throws VendingMachinePersistenceException {
        Item addedItem = new Item(name, price, quantity);
        stock.add(addedItem);
        writeStock();
        return addedItem;
    }

    @Override
    public Item removeItem(String name) throws VendingMachinePersistenceException {
        readStock();
        Item removedItem = null;

        for (Item item : stock) {
            if (item.getName() == name) {
                removedItem = item;
                stock.remove(item);
                writeStock();
                break;
            }
        }

        return removedItem;
    }

    @Override
    public Item unmarshallItem(String itemAsText) {
        //Split text into tokens
        String[] itemTokens = itemAsText.split(DELIMITER);

        //Pattern: name::price::quantity
        String name = itemTokens[0];
        BigDecimal price = BigDecimal.valueOf(
                Double.parseDouble(itemTokens[1]));
        int quantity = Integer.parseInt(itemTokens[2]);
        return new Item(name, price, quantity);
    }

    @Override
    public String marshallItem(Item item) {
        return item.getName() + DELIMITER
                + item.getPrice() + DELIMITER
                + item.getQuantity();
    }

    @Override
    public void writeStock() throws VendingMachinePersistenceException {
        //Try accessing file
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(STOCK_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save stock data.", e);
        }

        String itemAsText;
//        List<Item> stock = this.getAllItems();
        for (Item item : stock) {
            // turn item into a String
            itemAsText = marshallItem(item);
            // write the item object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public void readStock() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(STOCK_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load stock data into memory.", e);
        }
        //Clear in-memory list
        stock.clear();
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentItem holds the most recent student unmarshalled
        Item currentItem;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into an Item
            currentItem = unmarshallItem(currentLine);

            // Put currentStudent into the list
            stock.add(currentItem);
        }
        // close scanner
        scanner.close();
    }


}
