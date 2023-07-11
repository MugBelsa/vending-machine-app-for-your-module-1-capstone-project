package com.techelevator.view;

import com.techelevator.TransactionLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionLogTests {

    private final String ITEM_NAME = "Test item";
    private final String DESCRIPTION = "Test transaction";
    private final String SLOT_ID = "A1";
    private final BigDecimal AMOUNT = new BigDecimal(2.50);
    private final BigDecimal NEW_BALANCE = new BigDecimal(7.50);
    private TransactionLog log;
    private String logFilePath = "Log.txt";

    @Before
    public void setUp() {
        // Create new TransactionLog object before each test
        log = new TransactionLog();
    }

    @After
    public void delete() {
        File logFile = new File(logFilePath);
        if (logFile.exists()) {
            // Delete the test log file after each test
            logFile.delete();
        }
    }

    @Test
    public void logTransactionAddsTransactionToList() throws IOException {
        // Arrange
        String expectedTransaction = log.getTimestamp() + " " + DESCRIPTION + ": $" + AMOUNT + " $" + NEW_BALANCE;
        // Act
        log.logTransaction(AMOUNT, NEW_BALANCE, DESCRIPTION);
        // Assert
        List<String> transactions = log.transactions;
        assertEquals(1, transactions.size());
        assertEquals(expectedTransaction, transactions.get(0));
    }

    @Test
    public void purchaseLogTransactionAddsTransactionToList() {
        // Arrange
        String expectedTransaction = log.getTimestamp() + " " + ITEM_NAME + " " + SLOT_ID + " " + AMOUNT + " " + NEW_BALANCE;
        // Act
        log.purchaseLogTransaction(ITEM_NAME, SLOT_ID, AMOUNT, NEW_BALANCE);
        // Assert
        List<String> transactions = log.transactions;
        assertEquals(1, transactions.size());
    }

    @Test
    public void giveChangeWritesTransactionToFile() throws IOException {
        // Arrange
        String expectedTransaction = log.getTimestamp() + " " + ITEM_NAME + ": $" + AMOUNT + " $" + NEW_BALANCE;
        // Act
        log.giveChange(ITEM_NAME, AMOUNT, NEW_BALANCE);
        List<String> lines = Files.readAllLines(Paths.get(logFilePath));
        // Assert
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).endsWith(expectedTransaction));
    }

    @Test
    public void writeToFileWritesTransactionToFile() throws IOException {
        log.writeToFile(DESCRIPTION);

        List<String> lines = Files.readAllLines(Paths.get(logFilePath));
        assertEquals(1, lines.size());
        assertEquals(DESCRIPTION, lines.get(0));
    }
}
