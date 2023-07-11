package com.techelevator;

import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private VendingMachine vendingMachine;
	private Menu menu;
	private Scanner scanner;
	private BigDecimal currentBalance;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.vendingMachine = new VendingMachine();
		this.currentBalance = BigDecimal.ZERO;
		this.scanner = new Scanner(System.in);
	}

	public void run() {
		vendingMachine.printCenteredWord("Welcome to the Vendo-Matic 800", 30);

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				vendingMachine.printCenteredWord("Items", 30);
				vendingMachine.displayItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				while (true) {
					System.out.println(String.format("Current Money Provided: $%.2f", vendingMachine.getCurrentBalance()));
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {

						// feed money
						vendingMachine.printCenteredWord("Enter Amount ($1, $2, $5, $10) ", 30);
						System.out.print("\n$ Amount: ");
						String moneyInput = scanner.nextLine();
						BigDecimal moneyToAdd = new BigDecimal(moneyInput);
						vendingMachine.addMoney(moneyToAdd);

						// Writes FEED MONEY to transactionLog
						TransactionLog transactionLog = new TransactionLog();
						transactionLog.logTransaction(moneyToAdd, vendingMachine.getCurrentBalance(), "FEED MONEY");

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						// Displays items by calling displayItems() and selects item using purchaseHelper() method to get user input (SlotId)
						vendingMachine.printCenteredWord("Items", 30);
						vendingMachine.displayItems();
						vendingMachine.purchaseHelper();

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						vendingMachine.printCenteredWord("Transaction Completed", 30);
						// Method call here to complete transaction
						TransactionLog changeAmount = new TransactionLog();
						changeAmount.giveChange("GIVE CHANGE", vendingMachine.getCurrentBalance(), BigDecimal.ZERO);
						Map<String, Integer> change = vendingMachine.returnChange();
						vendingMachine.printCenteredPhrase("Here's Your Change: ", 30);
						for (Map.Entry<String, Integer> entry : change.entrySet()) {
							vendingMachine.printCenteredPhrase((entry.getValue() + " " + entry.getKey()), 30);
						}
						System.out.println("------------------------------");

						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("------------------------------");
				vendingMachine.printCenteredPhrase("Thank you For using the Vendo-Matic 800",30);
				vendingMachine.printCenteredClosing("Have a nice day :)", 30);
				// Reset current balance to zero
				currentBalance = BigDecimal.ZERO;
				// Break and return to the Main Menu
				break;
			}
		}
	}

	public static void main (String[]args){
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}