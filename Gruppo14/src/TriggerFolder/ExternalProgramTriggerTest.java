package TriggerFolder;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExternalProgramTriggerTest {

    @Test
    public void testCheckTrigger() throws InterruptedException {
        ExternalProgramTrigger trigger = new ExternalProgramTrigger(0, "echo", "Hello, World!");

        // Call checkTrigger before the thread is active
        boolean resultBeforeExecution = trigger.checkTrigger();

        // Verify that the result before execution is false
        assertFalse(resultBeforeExecution);

        // Create a Thread object to simulate execution
        Thread mockExecutionThread = new Thread(() -> {
            // Simulate a successful execution by setting the result using the public method
            trigger.lastExecutionResult = true;
        });

        // Start the simulated execution thread
        mockExecutionThread.start();

        // Wait for the simulated execution thread to complete
        mockExecutionThread.join();

        // Call checkTrigger after the execution
        boolean resultAfterExecution = trigger.checkTrigger();

        // Verify that the result after execution is true
        assertTrue(resultAfterExecution);
    }
}
