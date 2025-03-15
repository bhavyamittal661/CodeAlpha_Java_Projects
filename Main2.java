//Stock Trading Platform

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Stock class to hold stock details
class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

// User Portfolio class to manage transactions
class Portfolio {
    HashMap<String, Integer> stocksOwned = new HashMap<>();
    double balance;

    // Constructor
    public Portfolio(double initialBalance) {
        this.balance = initialBalance;
    }

    // Buy stocks
    public void buyStock(String symbol, int quantity, double price) {
        double totalCost = quantity * price;

        if (totalCost <= balance) {
            balance -= totalCost;
            stocksOwned.put(symbol, stocksOwned.getOrDefault(symbol, 0) + quantity);
            System.out.println("Successfully bought " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient balance to buy " + symbol);
        }
    }

    // Sell stocks
    public void sellStock(String symbol, int quantity, double price) {
        if (stocksOwned.containsKey(symbol) && stocksOwned.get(symbol) >= quantity) {
            balance += quantity * price;
            stocksOwned.put(symbol, stocksOwned.get(symbol) - quantity);

            if (stocksOwned.get(symbol) == 0) {
                stocksOwned.remove(symbol); // Remove stock if zero shares remain
            }
            System.out.println("Successfully sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Not enough shares to sell for " + symbol);
        }
    }

    // Display portfolio details
    public void displayPortfolio() {
        System.out.println("\n=== Portfolio Summary ===");
        System.out.println("Balance: $" + balance);
        System.out.println("Owned Stocks: " + stocksOwned);
    }
}

// Main class to handle user actions
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample stock data
        ArrayList<Stock> availableStocks = new ArrayList<>();
        availableStocks.add(new Stock("AAPL", 150.0));
        availableStocks.add(new Stock("GOOGL", 2800.0));
        availableStocks.add(new Stock("TSLA", 750.0));
        availableStocks.add(new Stock("AMZN", 3300.0));

        // Display available stocks
        System.out.println("=== Available Stocks ===");
        for (Stock stock : availableStocks) {
            System.out.println(stock.symbol + " - $" + stock.price);
        }

        // Create a user portfolio
        System.out.print("\nEnter your initial balance: $");
        double initialBalance = scanner.nextDouble();
        Portfolio portfolio = new Portfolio(initialBalance);

        // Menu-driven program for stock trading
        while (true) {
            System.out.println("\n1. Buy Stock");
            System.out.println("2. Sell Stock");
            System.out.println("3. View Portfolio");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int buyQty = scanner.nextInt();

                    for (Stock stock : availableStocks) {
                        if (stock.symbol.equals(buySymbol)) {
                            portfolio.buyStock(buySymbol, buyQty, stock.price);
                            break;
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int sellQty = scanner.nextInt();

                    for (Stock stock : availableStocks) {
                        if (stock.symbol.equals(sellSymbol)) {
                            portfolio.sellStock(sellSymbol, sellQty, stock.price);
                            break;
                        }
                    }
                    break;

                case 3:
                    portfolio.displayPortfolio();
                    break;

                case 4:
                    System.out.println("Exiting... Thank you for trading!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}