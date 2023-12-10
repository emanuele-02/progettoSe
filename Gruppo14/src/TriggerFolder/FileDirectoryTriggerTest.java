package TriggerFolder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileDirectoryTriggerTest {

    @Test
    void testCheckTriggerFileExists() {
        // Specify the file name and directory for testing
        String fileName = "ExistingFile.txt";
        String directory = "DirectoryProvaExistsFile";

        FileDirectoryTrigger trigger = new FileDirectoryTrigger(fileName, directory);

        boolean isTriggered = trigger.checkTrigger();

        assertTrue(isTriggered, "Trigger condition should be met for an existing file in the specified directory");
    }

    @Test
    void testCheckTriggerFileDoesNotExist() {
        // Specify a non-existent file name and directory for testing
        String nonExistentFileName = "nonexistentfile.txt";
        String directory = "DirectoryProvaExistsFile";

        FileDirectoryTrigger trigger = new FileDirectoryTrigger(nonExistentFileName, directory);

        boolean isTriggered = trigger.checkTrigger();

        assertFalse(isTriggered,
                "Trigger condition should not be met for a non-existent file in the specified directory");
    }
}