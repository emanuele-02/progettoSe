package ActionFolder;
import org.junit.jupiter.api.Test;



import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class DialogBoxActionTest {

    @Test
    void testExecute() {
        // Captures system output for testing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Execute the action by passing a message as parameter
            DialogBoxAction dialogBoxAction = new DialogBoxAction("Hello, this is a message!");
            dialogBoxAction.execute();
        } finally {
           
            System.setOut(System.out);
         
        }

      
    }
}
