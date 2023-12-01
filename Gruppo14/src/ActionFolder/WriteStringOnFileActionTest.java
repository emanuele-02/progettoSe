package ActionFolder;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

// WriteStringOnFileActionTest class
public class WriteStringOnFileActionTest {
    
    @Test
    void testExecute()  {
        // Define the file path and content to write
        String targetFilePath = "testWriteOnFIle.txt";
        String stringToWrite = "Hello, World! :)";

        // Action creation and execution
        WriteStringOnFileAction writeAction = new WriteStringOnFileAction(targetFilePath, stringToWrite);
        // Ensure that no exception is thrown during execution
        assertDoesNotThrow(writeAction::execute);
    }

    @Test
    void testExecuteNonValid()  {
        // Define the file path and content to write
        String targetFilePath = "FileNotExisting.ret";
        String stringToWrite = "useless";

        // // Ensure that an IllegalArgumentException is thrown 
        assertThrows(IllegalArgumentException.class,() -> new WriteStringOnFileAction (targetFilePath, stringToWrite));
       
        
        
    }
}