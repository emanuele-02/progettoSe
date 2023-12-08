package ActionFolder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import CounterFolder.MapCounter;

// WriteStringOnFileActionTest class
public class WriteStringOnFileActionTest {

    @Test
    void testExecute() {
        // Define the file path and content to write
        String targetFilePath = "testWriteOnFIle.txt";
        String stringToWrite = "Hello, World! :)";

        // Action creation and execution
        WriteStringOnFileAction writeAction = new WriteStringOnFileAction(targetFilePath, stringToWrite);
        // Ensure that no exception is thrown during execution
        assertDoesNotThrow(writeAction::execute);
    }

    @Test
    void testExecuteNonValid() {
        // Define the file path and content to write
        String targetFilePath = "FileNotExisting.ret";
        String stringToWrite = "useless";

        // // Ensure that an IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> new WriteStringOnFileAction(targetFilePath, stringToWrite));

    }

    @Test
    void testExecuteNonExistingFile() {
        // Define the file path and content to write
        String targetFilePath = "CiaoNonEsisto.txt";
        String stringToWrite = "useless";

        // // Ensure that an IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> new WriteStringOnFileAction(targetFilePath, stringToWrite));

    }

    @Test
    void testSubstituteVariables() {
        // Define the file path and content to write
        String targetFilePath = "testWriteOnFile.txt";
        String stringToWrite = "Hello, $name! :)";

        // Set up a counter and create a counter variable
        MapCounter counter = MapCounter.getInstance();
        counter.createCounter("name", 42);
        String substitutedString = counter.substituteVariables(stringToWrite);
        // Perform the substitution
        // Action creation
        WriteStringOnFileAction writeAction = new WriteStringOnFileAction(targetFilePath, substitutedString);
        writeAction.execute();
        // Ensure that the substitution is correct
        assertEquals("Hello, 42! :)", substitutedString);

    }

}
