package OOPCA2;

/**
 * Name: Harris Teh Kai Ze
 * Class Group: SD2B
 */

import java.io.*;
import java.util.*;

public class Question11 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file name: ");
        String fileName = scanner.nextLine();

        Map<String, TreeSet<DistanceTo>> connections = new HashMap<>();
        String startingCity = null; // The first city in the file will be used as the starting city

        try (Scanner fileScanner = new Scanner(new FileReader(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty())
                    continue;

                // to extract city1, city2, and the distance between them
                String[] parts = line.split(" ");
                String city1 = parts[0];
                String city2 = parts[1];
                int distance = Integer.parseInt(parts[2]);

                if (startingCity == null)
                    startingCity = city1;

                // Update to record bidirectional connection
                connections.putIfAbsent(city1, new TreeSet<>());
                connections.putIfAbsent(city2, new TreeSet<>());
                connections.get(city1).add(new DistanceTo(city2, distance));
                connections.get(city2).add(new DistanceTo(city1, distance));
            }
        } catch (IOException e) {
            System.out.println("Error: file not found or unreadable");
            scanner.close();
            return;
        }

        Map<String, Integer> shortestDist = new HashMap<>();
        PriorityQueue<DistanceTo> priorityQueue = new PriorityQueue<>();
        Set<String> finalized = new HashSet<>();
        Map<String, String> initialCity = new HashMap<>();

        priorityQueue.add(new DistanceTo(startingCity, 0));

        // process cities until all reachable cities have been visited (Dijkstra's
        // Algorithm)
        while (!priorityQueue.isEmpty()) {
            DistanceTo current = priorityQueue.poll();

            if (finalized.contains(current.getTarget())) {
                continue;
            }

            finalized.add(current.getTarget());
            shortestDist.put(current.getTarget(), current.getDistance());

            // Add neighboring cities to the priority queue with updated distances
            for (DistanceTo neighbor : connections.getOrDefault(current.getTarget(), new TreeSet<>())) {
                if (!finalized.contains(neighbor.getTarget())) {
                    int updatedDistance = current.getDistance() + neighbor.getDistance();
                    priorityQueue.add(new DistanceTo(neighbor.getTarget(), updatedDistance));

                    if (!initialCity.containsKey(neighbor.getTarget())) {
                        initialCity.put(neighbor.getTarget(), current.getTarget());
                    }
                }
            }
        }

        System.out.println("Shortest distances and routes from " + startingCity + ":");
        for (String city : finalized) {
            if (!city.equals(startingCity)) {
                List<String> route = new ArrayList<>();
                String currentCity = city;
                while (currentCity != null) {
                    route.add(currentCity);
                    currentCity = initialCity.get(currentCity);
                }
                Collections.reverse(route); // Reverse the route to start from the starting city

                System.out
                        .println(city + " " + shortestDist.get(city) + " (Route: " + String.join(" -> ", route) + ")");
            }
        }
        scanner.close();
    }
}
