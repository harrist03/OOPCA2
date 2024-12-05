package OOPCA2;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Name: Harris Teh Kai Ze
 * Class Group: SD2B
 */
public class Question7 // Shares Tax Calculations (Queue)
{
    public static void main(String[] args) {
        double totalGains = 0;
        String command = "";

        Queue<Block> tracker = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("buy, sell or quit?");
            command = sc.next();
            if (command.equalsIgnoreCase("buy")) {
                try {
                    System.out.println("Enter buy quantity: ");
                    int qty = sc.nextInt();
                    System.out.println("Enter price: ");
                    double price = sc.nextDouble();
                    // add the trade to a block object
                    tracker.add(new Block(qty, price));
                    System.out.println("Bought " + qty + " shares at $" + String.format("%.2f", price) + " per share");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, please enter a valid number!");
                    sc.next();
                }
            } else if (command.equals("sell")) {
                try {
                    System.out.println("Enter sell quantity: ");
                    int qty = sc.nextInt();
                    int totalBuyQuantity = 0;
                    for (Block block : tracker) {
                        totalBuyQuantity += block.qty;
                    }
                    // if sell quantity exceeds total buy quantity
                    if (qty > totalBuyQuantity) {
                        System.out.println("Error: Cannot sell " + qty + " shares. Only " +
                                totalBuyQuantity + " shares available.");
                        continue;
                    }
                    System.out.println("Enter price: ");
                    double price = sc.nextDouble();

                    while (qty > 0 && !tracker.isEmpty()) {
                        Block firstBlock = tracker.poll();
                        // if buy quantity less than or equal to sell quantity
                        if (firstBlock.qty <= qty) {
                            // get the difference of price times quantity times the maximum of buy quantity
                            totalGains += (price - firstBlock.price) * firstBlock.qty;
                            // deduct sell quantity from the buy quantity
                            qty -= firstBlock.qty;
                        } else {
                            totalGains += (price - firstBlock.price) * qty;
                            firstBlock.qty -= qty;
                            tracker.add(firstBlock);
                            // exit the loop because sell order is completed
                            qty = 0;
                        }
                    }
                    System.out.println("Total gains: $" + totalGains);

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, please enter a valid number!");
                    sc.next();
                }
            }
        } while (!command.equalsIgnoreCase("quit"));
        sc.close();
    }
}