package ActionFolder;

import org.junit.jupiter.api.Test;

import CounterFolder.MapCounter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

class DialogBoxActionTest {

    @Test
    void testValidExecute() {

        MapCounter counter = MapCounter.getInstance();
        counter.createCounter("name", 42);

        // Redirect standard output to capture the message displayed in JOptionPane
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the action by passing a valid message as parameter
        DialogBoxAction validDialogBoxAction = new DialogBoxAction("Hello! $name");
        validDialogBoxAction.execute();

        // Check if the last message displayed is equal to the string
        assertEquals("Hello! 42", DialogBoxAction.getLastDisplayedMessage());

    }

    @Test
    void testStringToLongExecute() {

        int maxLength= 300;
        // Execute the action by passing a message exceeding the maximum length
        String longMessage = String.join("", Collections.nCopies(maxLength + 1, "a"));
                
        assertThrows(IllegalArgumentException.class, () -> new DialogBoxAction(longMessage));

    }
}
