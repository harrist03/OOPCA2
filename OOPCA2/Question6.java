package OOPCA2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Name: Harris Teh Kai Ze
 * Class Group: SD2B
 */

public class Question6 // Flight take-off (Queue)
{
    private final Queue<String> landingQueue;
    private final Queue<String> takeOffQueue;

    public Question6() {
        landingQueue = new LinkedList<>();
        takeOffQueue = new LinkedList<>();
    }

    public void takeOff(String flightCode) {
        takeOffQueue.add(flightCode);
        System.out.println(flightCode + " is queued for take off.");
    }

    public void land(String flightCode) {
        landingQueue.add(flightCode);
        System.out.println(flightCode + " is queued for landing.");
    }

    public void next() {
        if (!landingQueue.isEmpty()) {
            String flightCode = landingQueue.poll();
            System.out.println(flightCode + " has landed.");
        } else if (!takeOffQueue.isEmpty()) {
            String flightCode = takeOffQueue.poll();
            System.out.println(flightCode + " has taken off.");
        } else {
            System.out.println("No flights waiting to take off or land.");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command with Flight number (e.g., takeoff, land, next, exit): ");
            String command = scanner.nextLine().trim();
            if (command.startsWith("takeoff")) {
                String flightCode = command.split(" ", 2)[1];
                takeOff(flightCode);
            } else if (command.startsWith("land")) {
                String flightCode = command.split(" ", 2)[1];
                land(flightCode);
            } else if (command.startsWith("next")) {
                next();
            } else if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting simulation.");
                break;
            } else {
                System.out.println(
                        "Invalid command. Please use 'take off flight code', 'land flight code', 'next',or 'quit'.");
            }

        }
        scanner.close();
    }

    public static void main(String[] args) {
        Question6 q6 = new Question6();
        q6.run();

    }
}
