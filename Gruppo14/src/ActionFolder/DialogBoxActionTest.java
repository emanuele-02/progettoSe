package ActionFolder;
import org.junit.jupiter.api.Test;



import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class DialogBoxActionTest {

    @Test
    void testExecute() {
        // Cattura l'output del sistema per il test
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Esegui l'azione passando come parametro un messaggio
            DialogBoxAction dialogBoxAction = new DialogBoxAction("Hello, this is a message!");
            dialogBoxAction.execute();
        } finally {
           
            System.setOut(System.out);
         
        }

      
    }
}
