package TriggerFolder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileDirectoryTriggerTest {

    @Test
    void testCheckTriggerFileExists() {
        // Specify the file name and directory for testing
        String fileName = "ProvaMove.txt";
        String directory = "DirectoryProvaMove1";

        // Create an instance of FileDirectoryTrigger for testing
        FileDirectoryTrigger trigger = new FileDirectoryTrigger(fileName, directory);

        // Perform the test
        boolean isTriggered = trigger.checkTrigger();

        // Assert that the trigger condition is met
        assertTrue(isTriggered, "Trigger condition should be met for an existing file in the specified directory");
    }

    @Test
    void testCheckTriggerFileDoesNotExist() {
        // Specify a non-existent file name and directory for testing
        String nonExistentFileName = "nonexistentfile.txt";
        String directory = "DirectoryProvaMove1";

        // Create an instance of FileDirectoryTrigger for testing
        FileDirectoryTrigger trigger = new FileDirectoryTrigger(nonExistentFileName, directory);

        // Perform the test
        boolean isTriggered = trigger.checkTrigger();

        // Assert that the trigger condition is not met
        assertFalse(isTriggered, "Trigger condition should not be met for a non-existent file in the specified directory");
    }

    // You can add more tests as needed to cover different scenarios
}