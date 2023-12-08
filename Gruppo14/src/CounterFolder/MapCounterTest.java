package CounterFolder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapCounterTest {

    @Test
    void testCreateCounter() {
        // Create an instance of MapCounter for testing
        MapCounter mapCounter = MapCounter.getInstance();

        // Perform the test
        mapCounter.createCounter("testCounter", 10);

        // Assert that the counter has been created
        assertEquals(10, mapCounter.getCounterValue("testCounter"), "Counter value should be 10");
    }

    @Test
    void testGetCounterValue() {
        // Create an instance of MapCounter for testing
        MapCounter mapCounter = MapCounter.getInstance();

        // Create a counter for testing
        mapCounter.createCounter("testCounter", 15);

        // Perform the test
        int counterValue = mapCounter.getCounterValue("testCounter");

        // Assert that the correct counter value is returned
        assertEquals(15, counterValue, "Counter value should be 15");
    }

    @Test
    void testSetCounterValue() {
        // Create an instance of MapCounter for testing
        MapCounter mapCounter = MapCounter.getInstance();

        // Create a counter for testing
        mapCounter.createCounter("testCounter", 20);

        // Perform the test
        mapCounter.setCounterValue("testCounter", 25);

        // Assert that the counter value has been updated
        assertEquals(25, mapCounter.getCounterValue("testCounter"), "Counter value should be 25");

    }

    @Test
    void testGetInstance() {
        // Create instances of MapCounter for testing
        MapCounter instance1 = MapCounter.getInstance();
        MapCounter instance2 = MapCounter.getInstance();

        // Assert that both instances are the same
        assertSame(instance1, instance2, "Instances should be the same");
    }

    @Test
    void testSetCounterValueNonValid() {
        // Create an instance of MapCounter for testing
        MapCounter mapCounter = MapCounter.getInstance();

        // Create a counter for testing
        mapCounter.createCounter("testCounter", 20);
        assertThrows(IllegalArgumentException.class, () -> mapCounter.setCounterValue("nonExistentCounter", 30),
                "Setting the value of a non-existent counter should throw an exception");
    }

    @Test
    void testSubstituteVariables() {
        // Create an instance of MapCounter for testing
        MapCounter mapCounter = MapCounter.getInstance();

        // Create counters for testing
        mapCounter.createCounter("counter1", 10);
        mapCounter.createCounter("counter2", 30);

        // Perform the test
        String inputString = "Value of counter 1: $counter1, value of counter 2: $counter2.";
        String substitutedString = mapCounter.substituteVariables(inputString);

        // Assert that variables are substituted correctly
        assertEquals("Value of counter 1: 10, value of counter 2: 30.", substitutedString,
                "Variable substitution should be correct");
    }

}
