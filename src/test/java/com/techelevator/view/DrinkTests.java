package com.techelevator.view;

import com.techelevator.Drink;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class DrinkTests {


    private Drink drink;

    @Before
    public void setUp() {
        drink = new Drink("C1", "Cola", new BigDecimal("1.25"), "Drink");
    }

    @Test
    public void constuctorSetsSlotIdCorrectly() {
        Assert.assertEquals("C1", drink.getSlotId());
    }

    @Test
    public void constructorSetsNameCorrectly() {
        Assert.assertEquals("Cola", drink.getName());
    }

    @Test
    public void constructorSetsPriceCorrectly() {
        Assert.assertEquals(new BigDecimal("1.25"), drink.getPrice());
    }

    @Test
    public void constructorSetsItemCategoryCorrectly() {
        Assert.assertEquals("Drink", drink.getSnackType());
    }
}
