package com.techelevator.view;

import com.techelevator.Product;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductTests {

    @Test
    public void getProductMessageReturnsCorrectMessage() {
        // Arrange
        Product product = new Product("D4", "Triplemint", BigDecimal.valueOf(0.75), "Gum");
        // Act
        String message = product.getMessage();
        // Assert
        Assert.assertEquals("Chew Chew,Yum!", message);
    }

    @Test
    public void getProductQuantityReturnsCorrectQuantity() {
        // Arrange
        Product product = new Product("D4", "Triplemint", BigDecimal.valueOf(0.75), "Gum");
        //Act
        int quantity = product.getQuantity();
        // Assert
        Assert.assertEquals(5, quantity);
    }

    @Test
    public void decrementProductQuantityDecrementsCorrectly() {
        // Arrange
        Product product = new Product("D4", "Triplemint", BigDecimal.valueOf(0.75), "Gum");
        // Act
        int initialQuantity = product.getQuantity();
        int decrementedQuantity = product.decrement();
        // Assert
        Assert.assertEquals(initialQuantity - 1, decrementedQuantity);
    }

    public void getProductMessageReturnsErrorMessageForNullProduct() {
        // Arrange
        Product product = null;
        //Act
        String message = "";
        try {
            message = product.getMessage();
        } catch (NullPointerException e) {
            message = "Error: Product is null";
        }
        // Assert
        Assert.assertEquals("Error: product is null", message);
    }

    @Test
    public void getProductQuantityReturnsErrorMessageForNullProduct() {
        // Arrange
        Product product = null;
        // Act
        int quantity = -1;
        try {
            quantity = product.getQuantity();
        } catch (NullPointerException e) {
            quantity = -2;
        }
        // Assert
        Assert.assertEquals(-2, quantity);
    }

    @Test
    public void decrementProductQuantityDoesNotDecrementQuantityIfZero() {
        // Arrange
        Product product = new Product("D4", "Triplemint", BigDecimal.valueOf(0.75), "Gum");
        product.quantity = 0;
        // Act
        int initialQuantity = product.getQuantity();
        int decrementQuantity = product.decrement();
        // Assert
        Assert.assertEquals(initialQuantity, decrementQuantity);
    }
}
