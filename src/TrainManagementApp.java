import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.*;
import java.util.stream.Collectors;

public class TrainManagementApp {
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public String toString() {
            return name + " (Capacity: " + capacity + ")";
        }
    }
    public static void main(String[] args) {

        // Welcome message
        System.out.println("=== Train Consist Management App ===");

        // Initialize empty train consist (list of bogies)
        List<String> trainConsist = new ArrayList<>();

        // Display initial bogie count
        System.out.println("Train consist initialized.");
        System.out.println("Initial number of bogies: " + trainConsist.size());

        System.out.println("\n--- Adding Passenger Bogies ---");

        // Create passenger bogie list
        List<String> passengerBogies = new ArrayList<>();

        // Add bogies
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        // Display after adding
        System.out.println("Passenger Bogies: " + passengerBogies);

        // Remove one bogie
        System.out.println("\nRemoving 'AC Chair'...");
        passengerBogies.remove("AC Chair");

        // Display after removal
        System.out.println("Passenger Bogies after removal: " + passengerBogies);

        // Check existence
        System.out.println("\nChecking if 'Sleeper' exists...");
        if (passengerBogies.contains("Sleeper")) {
            System.out.println("Sleeper bogie is present.");
        } else {
            System.out.println("Sleeper bogie is NOT present.");
        }

        // Final state
        System.out.println("\nFinal Passenger Bogie List: " + passengerBogies);
        System.out.println("\n--- Tracking Unique Bogie IDs ---");

        // Create HashSet for unique bogie IDs
        Set<String> bogieIDs = new HashSet<>();

        // Adding bogie IDs (with duplicates intentionally)
        bogieIDs.add("BG101");
        bogieIDs.add("BG102");
        bogieIDs.add("BG103");
        bogieIDs.add("BG101"); // duplicate
        bogieIDs.add("BG102"); // duplicate

        // Display unique bogie IDs
        System.out.println("Bogie IDs (duplicates automatically removed):");
        System.out.println(bogieIDs);
        System.out.println("\n--- Maintaining Ordered Train Consist ---");

        // Create LinkedList for ordered bogies
        LinkedList<String> orderedTrain = new LinkedList<>();

        // Add bogies
        orderedTrain.add("Engine");
        orderedTrain.add("Sleeper");
        orderedTrain.add("AC");
        orderedTrain.add("Cargo");
        orderedTrain.add("Guard");

        System.out.println("Initial Train Consist: " + orderedTrain);

        // Insert Pantry Car at position 2
        System.out.println("\nInserting 'Pantry Car' at position 2...");
        orderedTrain.add(2, "Pantry Car");

        System.out.println("After Insertion: " + orderedTrain);

        // Remove first and last bogie
        System.out.println("\nRemoving first and last bogies...");
        orderedTrain.removeFirst();
        orderedTrain.removeLast();

        // Final consist
        System.out.println("Final Ordered Train Consist: " + orderedTrain);
        System.out.println("\n--- Train Formation using LinkedHashSet ---");

        // Create LinkedHashSet
        Set<String> trainFormation = new LinkedHashSet<>();

        // Add bogies
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        // Add duplicate intentionally
        trainFormation.add("Sleeper"); // duplicate ignored

        // Display formation
        System.out.println("Train Formation (No duplicates, ordered):");
        System.out.println(trainFormation);
        System.out.println("\n--- Bogie Capacity Mapping (HashMap) ---");

        // Create HashMap
        Map<String, Integer> bogieCapacity = new HashMap<>();

        // Add bogie-capacity pairs
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 60);
        bogieCapacity.put("First Class", 40);

        // Display mapping using entrySet()
        System.out.println("Bogie Capacity Details:");
        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " -> Capacity: " + entry.getValue());
        }
        System.out.println("\n--- Sorting Bogies by Capacity ---");

        // Create List of Bogie objects
        List<Bogie> bogieList = new ArrayList<>();

        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 60));
        bogieList.add(new Bogie("First Class", 40));

        // Sort using Comparator (ascending order)
        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        // Display sorted bogies
        System.out.println("Bogies sorted by capacity (ascending):");
        for (Bogie b : bogieList) {
            System.out.println(b);
        }
        System.out.println("\n--- Filtered Bogies (capacity > 60) ---");

        List<Bogie> filteredBogies = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        for (Bogie b : filteredBogies) {
            System.out.println(b);
        }

        // Program continues...
    }
}
