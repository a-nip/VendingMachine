package com.vendingmachine.dto;

import java.math.BigDecimal;

public class Item {
    private String name;
    private BigDecimal price;
    private int quantity; //TODO: change code to use quantity variable here instead of map quantity integer

    //Must be instantiated with a name and price.
    public Item(String n, BigDecimal p) {
        name = n;
        price = p;
        quantity = 0;
    }

    public Item(String n, BigDecimal p, int q) {
        name = n;
        price = p;
        quantity = q;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
