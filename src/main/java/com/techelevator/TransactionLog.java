package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionLog {

    public List<String> transactions;

    // constructor
    public TransactionLog() {
        this.transactions = new ArrayList<>();
    }

    // Writes given transaction to Log.txt using FileWriter
    public void writeToFile(String transaction) {
        try {
            File logFile = new File("Log.txt");
            // appends new transaction to end of the existing file
            FileWriter writer = new FileWriter(logFile, true);
            writer.write(transaction + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to transaction log file");
        }
    }

    // adds FEED MONEY transaction details to transaction ArrayList and writes those details to log.txt
    public void logTransaction(BigDecimal amount, BigDecimal newBalance, String description) {
        String transaction = getTimestamp() + " " + description + ": $" + amount + " $" + newBalance;
        transactions.add(transaction);
        writeToFile(transaction);
    }

    // Item and transaction details are added to transaction ArrayList and writes those details to log.txt
    public void purchaseLogTransaction(String itemName, String slotID, BigDecimal cost, BigDecimal newBalance) {
        String purchaseTransaction = getTimestamp() + " " + itemName + " " + slotID + " " + cost + " " + newBalance;
        transactions.add(purchaseTransaction);
        writeToFile(purchaseTransaction);
    }
    // adds GIVE CHANGE transaction details to transaction ArrayList and writes those details to log.txt
    public void giveChange(String itemName, BigDecimal amount, BigDecimal newBalance) {
        String changeTransaction = getTimestamp() + " " + itemName + ": $" + amount + " $" + newBalance;
        writeToFile(changeTransaction);
    }

    // method returns formatted timestamp in the form of a String
    public String getTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        // new Date method returns Date object with current date and time
        return dateFormat.format(new Date());
    }
}