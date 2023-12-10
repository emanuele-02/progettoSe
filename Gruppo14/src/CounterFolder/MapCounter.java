package CounterFolder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MapCounter {

    private static MapCounter instance;
    private Map<String, Integer> counters;

    private MapCounter() {
        // Initialize the map to store counters
        this.counters = new HashMap<>();
    }

    public static MapCounter getInstance() {
        // Singleton pattern: Create a single instance of MapCounter
        if (instance == null) {
            instance = new MapCounter();
        }
        return instance;
    }

    public void createCounter(String name, int initialValue) {
        // Create a new counter with the specified name and initial value
        counters.put(name, initialValue);
    }

    public int getCounterValue(String name) {
        // Get the current value of the counter with the specified name
        if (counters.containsKey(name)) {
            return counters.get(name);
        } else {
            throw new IllegalArgumentException("Counter with name '" + name + "' does not exist.");

        }
    }

    public void setCounterValue(String name, int value) {
        // Set the value of the counter with the specified name
        if (counters.containsKey(name)) {
            counters.put(name, value);
        } else {
            throw new IllegalArgumentException("Counter with name '" + name + "' does not exist.");
        }
    }

    public String substituteVariables(String inputString) {
        // Use regular expressions to find variables of the form $variableName
        Pattern pattern = Pattern.compile("\\$([a-zA-Z0-9_]+)");
        Matcher matcher = pattern.matcher(inputString);

        // Replace each match with the current value of the corresponding counter
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String variableName = matcher.group(1);
            String variableReplacement = getVariableReplacement(variableName);
            matcher.appendReplacement(result, variableReplacement);
        }
        matcher.appendTail(result);

        // Return the string with variable substitutions
        return result.toString();
    }

    // Private method to get the value of a counter variable based on the specified
    // name
    private String getVariableReplacement(String name) {
        // Get the current value of the counter with the specified name
        if (counters.containsKey(name)) {
            int counterValue = counters.get(name);
            return Integer.toString(counterValue);
        } else {
            throw new IllegalArgumentException("Counter with name '" + name + "' does not exist.");
        }
    }

    public boolean containsCounter(String name) {
        // Check if the counter with the specified name is present in the map
        return counters.containsKey(name);
    }

    public Set<String> keySet() {
        // Implement custom keySet by manually adding each key to a new set
        Set<String> keySet = new HashSet<>();

        for (String counterName : counters.keySet()) {
            keySet.add(counterName);
        }

        return keySet;
    }

}
