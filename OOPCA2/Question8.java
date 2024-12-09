package OOPCA2;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * Name: Harris Teh Kai Ze
 * Class Group: SD2B
 */
public class Question8 { // Multi-Company Stock Shares Tax Calculation (Queue)
    public static void main(String[] args) {
        double totalGains = 0;
        String command = "";

        Map<String, Queue<Share>> companyShares = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("buy, sell or quit?");
            command = sc.next();
            
            if (command.equalsIgnoreCase("buy")) {
                try {
                    System.out.println("Enter company symbol: (e.g. AAPL)");
                    String symbol = sc.next().toUpperCase();
                    System.out.println("Enter quantity: ");
                    int qty = sc.nextInt();
                    System.out.println("Enter price: ");
                    double price = sc.nextDouble();

                    // get or create queue for this company
                    Queue<Share> companyQueue = companyShares.getOrDefault(symbol, new LinkedList<>());
                    companyQueue.add(new Share(qty, price));

                    // add company and the share object
                    companyShares.put(symbol, companyQueue);

                    System.out.println("Company " + symbol + " bought " + qty + " shares at $"
                            + String.format("%.2f", price) + " per share");

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, please enter a valid number!");
                    sc.next();
                }
            } else if (command.equals("sell")) {
                try {
                    System.out.println("Enter company symbol: (e.g. AAPL)");
                    String symbol = sc.next().toUpperCase();

                    // Check if company exists in our records
                    if (!companyShares.containsKey(symbol)) {
                        System.out.println("Error: No shares found for company " + symbol);
                        continue;
                    }

                    System.out.println("Enter quantity: ");
                    int qty = sc.nextInt();

                    // calculate the total shares for this company
                    Queue<Share> companyQueue = companyShares.get(symbol);
                    int totalBuyQuantity = 0;
                    for (Share share : companyQueue) {
                        totalBuyQuantity += share.getQuantity();
                    }

                    // if sell quantity exceeds total buy quantity
                    if (qty > totalBuyQuantity) {
                        System.out.println("Error: Cannot sell company " + symbol + " for " + qty + " shares. Only " +
                                totalBuyQuantity + " shares available.");
                        continue;
                    }

                    System.out.println("Enter price: ");
                    double price = sc.nextDouble();

                    Queue<Share> updatedQueue = new LinkedList<>();

                    while (qty > 0 && !companyQueue.isEmpty()) {
                        Share firstShare = companyQueue.poll();
                        // if buy quantity less than or equal to sell quantity
                        if (firstShare.getQuantity() <= qty) {
                            // calculate the gain for this block
                            totalGains += (price - firstShare.getPrice()) * firstShare.getQuantity();
                            qty -= firstShare.getQuantity();
                        } else {
                            totalGains += (price - firstShare.getPrice()) * qty;
                            firstShare.setQuantity(firstShare.getQuantity() - qty);
                            updatedQueue.add(firstShare);
                            // exit the loop because sell order is completed
                            qty = 0;
                        }
                    }

                    // Add any remaining shares back to the queue
                    while (!companyQueue.isEmpty()) {
                        updatedQueue.add(companyQueue.poll());
                    }

                    // Update the company's queue
                    if (!updatedQueue.isEmpty()) {
                        companyShares.put(symbol, updatedQueue);
                    } else {
                        companyShares.remove(symbol);
                    }

                    System.out.println("Total gains: $" + totalGains);

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, please try again!");
                    sc.next();
                }
            }
        } while (!command.equalsIgnoreCase("quit"));

        sc.close();
    }
}
