package com.techelevator;

import java.math.BigDecimal;

public interface Sellable {

    int getQuantity();

    BigDecimal getPrice();

    String getName();

    String getSlotId();
}
