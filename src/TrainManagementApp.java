import java.util.*;
import java.util.stream.Collectors;

public class TrainManagementApp {

    // ----------- UC14: Custom Exception -----------
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    // ----------- Bogie Class -----------
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.name = name;
            this.capacity = capacity;
        }

        public String toString() {
            return name + " (Capacity: " + capacity + ")";
        }
    }

    // ----------- UC11 Regex -----------
    public static boolean validateTrainID(String trainId) {
        return trainId.matches("TRN-\\d{4}");
    }

    public static boolean validateCargoCode(String cargoCode) {
        return cargoCode.matches("PET-[A-Z]{2}");
    }

    // ----------- Helper Method -----------
    public static Bogie createBogie(String name, int capacity) {
        try {
            return new Bogie(name, capacity);
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // ----------- UC1–UC3 -----------
        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        System.out.println("Passenger Bogies: " + passengerBogies);

        passengerBogies.remove("AC Chair");

        if (passengerBogies.contains("Sleeper")) {
            System.out.println("Sleeper bogie is present.");
        }

        // ----------- UC4 -----------
        Set<String> bogieIDs = new HashSet<>();
        bogieIDs.add("BG101");
        bogieIDs.add("BG102");
        bogieIDs.add("BG101");

        System.out.println("Unique Bogie IDs: " + bogieIDs);

        // ----------- UC5 -----------
        LinkedList<String> orderedTrain = new LinkedList<>();
        orderedTrain.add("Engine");
        orderedTrain.add("Sleeper");
        orderedTrain.add("Cargo");

        orderedTrain.add(2, "Pantry Car");
        orderedTrain.removeFirst();
        orderedTrain.removeLast();

        System.out.println("Ordered Train: " + orderedTrain);

        // ----------- UC6 -----------
        Set<String> trainFormation = new LinkedHashSet<>();
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Sleeper");

        System.out.println("Train Formation: " + trainFormation);

        // ----------- UC7 -----------
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);

        capacityMap.forEach((k, v) ->
                System.out.println(k + " -> " + v)
        );

        // ----------- UC8–UC10 -----------
        List<Bogie> bogieList = new ArrayList<>();

        bogieList.add(createBogie("Sleeper", 72));
        bogieList.add(createBogie("AC Chair", 60));
        bogieList.add(createBogie("First Class", 40));

        bogieList = bogieList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("Sorted Bogies:");
        bogieList.forEach(System.out::println);

        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        Map<String, List<Bogie>> grouped =
                bogieList.stream().collect(Collectors.groupingBy(b -> b.name));

        int totalCapacity = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Capacity: " + totalCapacity);

        // ----------- UC11 -----------
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Train ID: ");
        String trainId = sc.nextLine();

        System.out.print("Enter Cargo Code: ");
        String cargoCode = sc.nextLine();

        System.out.println("Train ID Valid: " + validateTrainID(trainId));
        System.out.println("Cargo Code Valid: " + validateCargoCode(cargoCode));

        // ----------- UC12 -----------
        System.out.println("\n--- UC12 Safety Check ---");

        List<Map<String, String>> goods = new ArrayList<>();
        goods.add(Map.of("Cylindrical", "Petroleum"));
        goods.add(Map.of("Open", "Coal"));

        boolean safe = goods.stream()
                .allMatch(g ->
                        !g.containsKey("Cylindrical") ||
                                g.get("Cylindrical").equalsIgnoreCase("Petroleum")
                );

        System.out.println("Safety Status: " + safe);

        // ----------- UC13 -----------
        System.out.println("\n--- UC13 Performance ---");

        List<Bogie> largeList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeList.add(createBogie("Sleeper", 72));
        }

        largeList = largeList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        long start1 = System.nanoTime();
        List<Bogie> loopRes = new ArrayList<>();
        for (Bogie b : largeList) {
            if (b.capacity > 60) loopRes.add(b);
        }
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        List<Bogie> streamRes = largeList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long end2 = System.nanoTime();

        System.out.println("Loop Time: " + (end1 - start1));
        System.out.println("Stream Time: " + (end2 - start2));

        // ----------- UC14 -----------
        System.out.println("\n--- UC14 Exception Handling ---");

        List<Bogie> safeBogies = new ArrayList<>();

        try {
            Bogie b1 = new Bogie("Sleeper", 72);
            Bogie b2 = new Bogie("AC Chair", 60);
            Bogie b3 = new Bogie("Invalid", -5); // ❌

            safeBogies.add(b1);
            safeBogies.add(b2);
            safeBogies.add(b3);

        } catch (InvalidCapacityException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("Valid Bogies:");
        safeBogies.forEach(System.out::println);

        sc.close();
    }
}