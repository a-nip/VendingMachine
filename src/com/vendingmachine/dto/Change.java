package com.vendingmachine.dto;

import java.math.BigDecimal;

public class Change {
    private BigDecimal amount = BigDecimal.valueOf(0);

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
