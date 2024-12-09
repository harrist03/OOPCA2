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

        Queue<Share> tracker = new LinkedList<>();
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
                    // Add the trade to a Share object
                    tracker.add(new Share(qty, price));
                    System.out.println("Bought " + qty + " shares at $" + String.format("%.2f", price) + " per share");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, please enter a valid number!");
                    sc.next();
                }
            } else if (command.equalsIgnoreCase("sell")) {
                System.out.println("Enter sell quantity: ");
                int qty = sc.nextInt();
                int totalBuyQuantity = 0;
                for (Share share : tracker) {
                    totalBuyQuantity += share.getQuantity();
                }
                // If sell quantity exceeds total buy quantity
                if (qty > totalBuyQuantity) {
                    System.out.println("Error: Cannot sell " + qty + " shares. Only " +
                            totalBuyQuantity + " shares available.");
                    continue;
                }
                System.out.println("Enter price: ");
                double price = sc.nextDouble();

                while (qty > 0 && !tracker.isEmpty()) {
                    Share firstShare = tracker.poll();
                    // If buy quantity is less than or equal to sell quantity
                    if (firstShare.getQuantity() <= qty) {
                        // Calculate gains for the current share
                        totalGains += (price - firstShare.getPrice()) * firstShare.getQuantity();
                        // Deduct sold quantity from the sell quantity
                        qty -= firstShare.getQuantity();
                    } else {
                        totalGains += (price - firstShare.getPrice()) * qty;
                        firstShare.setQuantity(firstShare.getQuantity() - qty);
                        tracker.add(firstShare);
                        // Exit the loop because sell order is completed
                        qty = 0;
                    }
                }
                System.out.println("Total gains: $" + String.format("%.2f", totalGains));

            }
        } while (!command.equalsIgnoreCase("quit"));
        sc.close();
    }
}
