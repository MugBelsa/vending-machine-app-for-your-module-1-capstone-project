package com.techelevator.view;

import com.techelevator.Chip;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ChipTests {
    private Chip chip;

    @Before
    public void setUp() {
        chip = new Chip("A1", "Potato Crisps", new BigDecimal("3.05"), "Chip");
    }

    @Test
    public void constuctorSetsSlotIdCorrectly() {
        Assert.assertEquals("A1", chip.getSlotId());
    }

    @Test
    public void constructorSetsNameCorrectly() {
        Assert.assertEquals("Potato Crisps", chip.getName());
    }

    @Test
    public void constructorSetsPriceCorrectly() {
        Assert.assertEquals(new BigDecimal("3.05"), chip.getPrice());
    }

    @Test
    public void constructorSetsItemCategoryCorrectly() {
        Assert.assertEquals("Chip", chip.getSnackType());
    }

}
