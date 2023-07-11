package com.techelevator;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachine {
    public List<Product> itemArray = new ArrayList<>();
    public Map<String, Product> inventory = new HashMap<>();
    private BigDecimal currentBalance;

    public VendingMachine() {
        this.currentBalance = BigDecimal.ZERO;
        this.fillInventory();
    }

    public void fillInventory() {
        String fileName = "vendingmachine.csv";
        Map<String, Product> keymap = new HashMap<>();
      /*
      Creates a new buffered reader to read through vendingmachine.csv named "fileName"
      Splits text inside vendingmachine.csv with the "|" character.
      creates a string array using the [0] as the key in the keyMap.
      joins the rest of the parts ([1],[2],[3]) and sets them in the value part of the keymap.
       */
        String key = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                BigDecimal price = new BigDecimal(parts[2]);
                key = parts[0];
                String snackType = parts[3];
                Product value = new Product(key, parts[1], price, snackType);
                keymap.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        inventory = keymap;
    }


    public void displayItems() {
        /*
        This method reads data from a CSV file named "vendingmachine.csv", parses each line of data,
        creates an object based on the type of product (Chip, Candy, Drink, or Gum),
        and adds it to an ArrayList called "itemArray".
        The method also clears the ArrayList before adding new items to it.
        Any errors while reading the file are caught and displayed as a message.
        Finally, the method displays the contents of the CSV file on the console.
        */
        itemArray.clear();
        // read file
        File inputFile = new File("vendingmachine.csv");
        try (Scanner fileScanner = new Scanner(inputFile.getAbsoluteFile())) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
                String[] itemData = line.split("\\|");
                String slotId = itemData[0];
                String name = itemData[1];
                BigDecimal price = new BigDecimal(itemData[2]);
                String product = itemData[3];

                if (product.equals("Chip")) {
                    itemArray.add(new Chip(slotId, name, price, product));
                } else if (product.equals("Candy")) {
                    itemArray.add(new Candy(slotId, name, price, product));
                } else if (product.equals("Drink")) {
                    itemArray.add(new Drink(slotId, name, price, product));
                } else if (product.equals("Gum")) {
                    itemArray.add(new Gum(slotId, name, price, product));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String purchaseHelper(){
        /*
        This method prompts the user to enter a slot code, converts the input to uppercase,
        and checks if it exists in a keymap. If the input matches a keymap value,
        the method prompts the user to confirm the selection by entering 'Y' or 'N'.
        If the user confirms the selection, the method calls the purchase() method
        with the selected keymap value as a parameter. If the input doesn't match any keymap value,
        the method outputs "INVALID SLOT ID". Finally, the method returns the case-insensitive version of the user's input.
        */
        Scanner input = new Scanner(System.in);
        printCenteredWord("Enter Slot Code", 30);
        System.out.print("Slot Id: ");
        String Input = input.nextLine();
        // Change user input to uppercase
        String caseInsensitiveInput = Input.toUpperCase();

        // Uses the users input to select the corresponding keymap value

        if (inventory.containsKey(caseInsensitiveInput)) {
            System.out.println("You have selected: " + caseInsensitiveInput + ". Is this correct? (Y\\N)");
            Scanner scanner = new Scanner(System.in);
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("Y")) {
                this.purchase(caseInsensitiveInput);
            }
        } else {
            System.out.println("INVALID SLOT ID");
        }
        return caseInsensitiveInput;
    }
    // Adds a specified amount of money to the current balance of the vending machine
    public void addMoney(BigDecimal amount) {
        currentBalance = currentBalance.add(amount);
    }

    public BigDecimal purchase(String userInput) {
        Product item = inventory.get(userInput);
        /*
        Takes a user input and uses it to get a corresponding Product object from a keymap.
        The method then checks if the current balance is sufficient to purchase the selected item.
        If the balance is insufficient, it displays "INSUFFICIENT FUNDS" and returns the current balance.
        If the balance is sufficient, it checks if the selected item is in stock,
        and if so, it displays the name, price, and message associated with the selected item,
        decrements the item quantity, and updates the current balance by subtracting the item price.
        Finally, it adds a new transaction record to a transaction log object. The method returns the current balance.
        */

        //check if balance is greater than price
                if (currentBalance.compareTo(item.getPrice()) < 0) {
                    //subtract price from balance
                    System.out.println("INSUFFICIENT FUNDS");
                    return currentBalance;
                } else {
                    if (item.getQuantity() <= 0) {
                        System.out.println("OUT OF STOCK");
                    } else {
                        System.out.println("------------------------------");
                        currentBalance = currentBalance.subtract(item.getPrice());
                        System.out.println("Remaining Balance: $" + currentBalance);
                        System.out.println(item.getName() + " | Item price: $" + item.getPrice());
                        System.out.println(item.getMessage());
                        System.out.println("------------------------------");
                        item.decrement();

                        TransactionLog purchaseLog = new TransactionLog();
                        purchaseLog.purchaseLogTransaction(item.getName(), item.getSlotId(), item.getPrice(), currentBalance);

                    }
                }
        return currentBalance;
    }

    public Map<String, Integer> returnChange() {
        BigDecimal balance = currentBalance;
        /*
        This method calculates the change to be returned to the customer
        and returns the value in the form of a map that shows the number of
        quarters, dimes, and nickels to be returned.
        It initializes a new map with default values of zero for the three coin types,
        and then uses a loop to subtract the value of each coin from the balance until the balance reaches zero.
        At each iteration of the loop, the method increments the value of the corresponding coin type in the map.
        The method sets the current balance to zero and returns the map.
        */
        Map<String, Integer> coins = new HashMap<>();
        coins.put("quarters", 0);
        coins.put("dimes", 0);
        coins.put("nickels", 0);

        BigDecimal quarterValue = new BigDecimal("0.25");
        BigDecimal dimeValue = new BigDecimal("0.10");
        BigDecimal nickelValue = new BigDecimal("0.05");

        while (balance.compareTo(BigDecimal.ZERO) > 0) {
            if (balance.compareTo(quarterValue) >= 0) {
                balance = balance.subtract(quarterValue);
                coins.put("quarters", coins.get("quarters") + 1);
            } else if (balance.compareTo(dimeValue) >= 0) {
                balance = balance.subtract(dimeValue);
                coins.put("dimes", coins.get("dimes") + 1);
            } else {
                balance = balance.subtract(nickelValue);
                coins.put("nickels", coins.get("nickels") + 1);
            }
        }

        currentBalance = BigDecimal.ZERO;
        return coins;
    }
    // getter currentBalance
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    // formatting methods for output/display
    /*
    calculates the necessary padding on either side of the string to center it
    by subtracting the string length from the container width, dividing it by 2, and rounding down.
    It then uses the printf method to print the string with the calculated padding using the %s format specifier.
    The "%n" in the format string adds a newline character to the output (counts total of characters printed).
    */
    public void printCenteredPhrase(String format, int containerWidth) {
        String word = String.format(format);
        int padding = (containerWidth - word.length()) / 2;

        System.out.printf("%" + (padding + word.length()) + "s%n", word);
    }

    public void printCenteredWord(String word, int containerWidth) {
        int padding = (containerWidth - word.length()) / 2;
        String dashes = "-".repeat(containerWidth);

        System.out.println(dashes);
        System.out.printf("%" + (padding + word.length()) + "s%n", word);
        System.out.println(dashes);
    }

    public void printCenteredClosing(String word, int containerWidth) {
        int padding = (containerWidth - word.length()) / 2;
        String dashes = "-".repeat(containerWidth);

        System.out.printf("%" + (padding + word.length()) + "s%n", word);
        System.out.println(dashes);
    }
}