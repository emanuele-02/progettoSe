package TriggerFolder;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExternalProgramTriggerTest {

    @Test
    public void testCheckTrigger() throws InterruptedException {
        ExternalProgramTrigger trigger = new ExternalProgramTrigger(0, "python", "ExternalProgramActionExample.py","Hello world");
        boolean resultBeforeExecution = trigger.checkTrigger();
        // Call checkTrigger before the thread is active
        simulateTimePassing(5);
        resultBeforeExecution = trigger.checkTrigger();
        assertTrue(resultBeforeExecution);

    }

    // Helper method to simulate the passage of time
    private void simulateTimePassing(int seconds) {
        try {
            // Sleep for the specified number of seconds
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        
}
