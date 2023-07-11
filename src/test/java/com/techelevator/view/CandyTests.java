package com.techelevator.view;

import com.techelevator.Candy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CandyTests {
    private Candy candy;

    @Before
    public void setUp() {
        candy = new Candy("B1","Moonpie",new BigDecimal("1.80"), "Candy");
    }

    @Test
    public void constructorSetsSlotIdCorrectly() {
        Assert.assertEquals("B1", candy.getSlotId());
    }

    @Test
    public void constructorSetsNameCorrectly() {
        Assert.assertEquals("Moonpie", candy.getName());
    }

    @Test
    public void constructorSetsPriceCorrectly() {
        Assert.assertEquals(new BigDecimal("1.80"), candy.getPrice());
    }

    @Test
    public void constructorSetsItemCategoryCorrectly() {
        Assert.assertEquals("Candy", candy.getSnackType());
    }
}
