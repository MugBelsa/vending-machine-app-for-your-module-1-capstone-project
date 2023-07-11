package com.techelevator.view;

import com.techelevator.VendingMachine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineTests {
  private VendingMachine vendingMachine;

  @Before
    public void setUp() {
      vendingMachine = new VendingMachine();
  }

  @Test
    public void shouldAddMoneyToCurrentBalance() {
      BigDecimal expectedBalance = BigDecimal.valueOf(2.50);
      vendingMachine.addMoney(BigDecimal.valueOf(2.50));
      Assert.assertEquals(expectedBalance, vendingMachine.getCurrentBalance());
  }

  @Test
    public void shouldReturnCorrectChange() {
      vendingMachine.addMoney(BigDecimal.valueOf(2.35));
      Map<String, Integer> expectedCoins = Map.of(
              "quarters", 9,
              "dimes", 1,
              "nickels", 0
      );
      Assert.assertEquals(expectedCoins, vendingMachine.returnChange());
  }

  @Test
    public void shouldHandleInsufficientFunds() {
      vendingMachine.addMoney(BigDecimal.valueOf(0.50));
      BigDecimal expectedBalance = BigDecimal.valueOf(0.50);
      String userInput = "A1";
      BigDecimal actualBalance = vendingMachine.purchase(userInput);
      Assert.assertEquals(expectedBalance, actualBalance);
  }

  @Test
    public void shouldDisplayItems() {
      vendingMachine.displayItems();
      Assert.assertEquals(16, vendingMachine.itemArray.size());
  }

  @Test
    public void shouldFillInventory() {
      vendingMachine.fillInventory();
      Assert.assertEquals(16, vendingMachine.inventory.size());
  }
}
