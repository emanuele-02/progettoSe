package ActionFolder;
import org.junit.jupiter.api.Test;

import CounterFolder.MapCounter;

import java.io.ByteArrayOutputStream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DialogBoxActionTest {
   
    @Test
    void testValidExecute() {
       
         MapCounter counter = MapCounter.getInstance();
        counter.createCounter("name", 42);

        // Execute the action by passing a valid message as parameter 
        DialogBoxAction validDialogBoxAction = new DialogBoxAction("Hello! $name");
        validDialogBoxAction.execute();
       

    
    

        }
    
 
     @Test
    void testStringToLongExecute() {
      
            // Execute the action by passing a message exceeding the maximum length
            String longMessage = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz\r\n" + //
                    "";
            assertThrows(IllegalArgumentException.class, () -> new DialogBoxAction(longMessage));

        } 
    }


    

