package com.vendingmachine.enums;

import java.math.BigDecimal;

public enum Coins {
    Quarters(new BigDecimal("0.25")),
    Dimes(new BigDecimal("0.10")),
    Nickels(new BigDecimal("0.05")),
    Pennies(new BigDecimal("0.01"));

    private final BigDecimal value;

    private Coins(BigDecimal v) {
        this.value = v;
    }

    public BigDecimal getValue() {
        return value;
    }
}
