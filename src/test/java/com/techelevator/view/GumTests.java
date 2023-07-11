package com.techelevator.view;

import com.techelevator.Gum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class GumTests {
    private Gum gum;

    @Before
    public void setUp() {
        gum = new Gum("D1", "U-Chews", new BigDecimal("0.85"), "Gum");
    }

    @Test
    public void constuctorSetsSlotIdCorrectly() {
        Assert.assertEquals("D1", gum.getSlotId());
    }

    @Test
    public void constructorSetsNameCorrectly() {
        Assert.assertEquals("U-Chews", gum.getName());
    }

    @Test
    public void constructorSetsPriceCorrectly() {
        Assert.assertEquals(new BigDecimal("0.85"), gum.getPrice());
    }

    @Test
    public void constructorSetsItemCategoryCorrectly() {
        Assert.assertEquals("Gum", gum.getSnackType());
    }
}
