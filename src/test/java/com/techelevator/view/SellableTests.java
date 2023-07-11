package com.techelevator.view;

import com.techelevator.Product;
import com.techelevator.Sellable;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SellableTests {
    private Sellable sellable;

    @Before
    public void setUp() {
        sellable = new Product("A1", "Sample Product", BigDecimal.valueOf(1.99), "Sample Snack Type");
    }

    @Test
    public void testGetQuantity() {
        assertEquals(5, sellable.getQuantity());
    }

    @Test
    public void testGetPrice() {
        assertEquals(BigDecimal.valueOf(1.99), sellable.getPrice());
    }

    @Test
    public void testGetName() {
        assertEquals("Sample Product", sellable.getName());
    }

    @Test
    public void testGetSlotId() {
        assertEquals("A1", sellable.getSlotId());
    }
}
