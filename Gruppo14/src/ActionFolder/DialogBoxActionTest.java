package ActionFolder;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DialogBoxActionTest {
   
    @Test
    void testValidExecute() {
        // Captures system output for testing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        
        // Execute the action by passing a valid message as parameter
        DialogBoxAction validDialogBoxAction = new DialogBoxAction("Hello!");
        validDialogBoxAction.execute();

        }
    

     @Test
    void testStringToLongExecute() {
        // Captures system output for testing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

            
            // Execute the action by passing a message exceeding the maximum length
            String longMessage = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz\r\n" + //
                    "";
            assertThrows(IllegalArgumentException.class, () -> new DialogBoxAction(longMessage));

        } 
    }


    

