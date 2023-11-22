import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assignment3BDemo {
    public static void main(String[] args) {
        // Create AaryamanHashMap to store state populations
        AaryamanHashMap<String, Integer> statePopulations = new AaryamanHashMap<>();

        // Create AaryamanHashSet to store state names
        AaryamanHashSet<String> states = new AaryamanHashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Assignment3BData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String stateName = parts[0];
                    int population = Integer.parseInt(parts[1]);

                    // Populate AaryamanHashMap with state names and populations
                    statePopulations.put(stateName, population);

                    // Populate AaryamanHashSet with state names
                    states.add(stateName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Demonstrate AaryamanHashMap functionality
        System.out.println("AaryamanHashMap Demonstrations:");
        System.out.println("Population of California: " + statePopulations.get("California"));
        System.out.println("Size of AaryamanHashMap: " + statePopulations.size());
        System.out.println("Is Texas in AaryamanHashMap? " + statePopulations.containsKey("Texas"));
        System.out.println("State with maximum population: " + findMaxPopulationState(statePopulations));
        System.out.println();

        // Demonstrate AaryamanHashSet functionality
        System.out.println("AaryamanHashSet Demonstrations:");
        System.out.println("Number of states in AaryamanHashSet: " + states.size());
        System.out.println("Is Florida in AaryamanHashSet? " + states.contains("Florida"));
        System.out.println("Is Puerto Rico in AaryamanHashSet? " + states.contains("Puerto Rico"));
        System.out.println();

        // Demonstrate AaryamanOutput methods
        System.out.println("AaryamanHashMap Contents:");
        statePopulations.AaryamanOutput();

        System.out.println("AaryamanHashSet Contents:");
        states.AaryamanOutput();
    }

    private static String findMaxPopulationState(AaryamanHashMap<String, Integer> map) {
        String maxState = null;
        int maxPopulation = Integer.MIN_VALUE;
        for (String state : map.keySet()) {
            int population = map.get(state);
            if (population > maxPopulation) {
                maxPopulation = population;
                maxState = state;
            }
        }
        return maxState;
    }
}
