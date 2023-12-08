package TriggerFolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FileSizeTriggerTest {
    @Test
    void testCheckTrigger() {
        // Specify the path to a valid file for testing
        String validFilePath = "SPRINT1_Review_Retrospective.docx";

        // Create an instance of FileSizeTrigger for testing
        FileSizeTrigger trigger = new FileSizeTrigger(validFilePath, 70); // Assuming 70 KB for testing

        // Perform the test
        boolean isTriggered = trigger.checkTrigger();

        // Assert that the trigger condition is met (you may adjust this based on your
        // specific use case)
        assertTrue(isTriggered, "Trigger condition should be met for a valid file with size > 70 KB");
    }

    @Test
    void testInvalidFilePath() {
        // Specify the path to an invalid file for testing
        String invalidFilePath = "fileNotExisting.txt";

        // Perform the test by creating an instance with an invalid file path
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new FileSizeTrigger(invalidFilePath, 1); // Assuming 1 KB for testing
        });

        // Assert that the expected exception is thrown with the correct message
        assertEquals("File does not exist or is not a regular file: " + invalidFilePath, exception.getMessage());
    }

}
