package ActionFolder;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ExternalProgramActionTest {

    @Test
    public void ValidExecution() {
        // Cattura l'output
        ByteArrayOutputStream outputStreamCatched = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCatched));

        ExternalProgramAction externalProgramAction = new ExternalProgramAction("python",
                "ExternalProgramActionExample.py", "Hello, World");
        externalProgramAction.execute();

        System.setOut(System.out);

        // Output atteso
        String expectedOutput = "Hello, World";

        // Output reale
        String actualOutput = outputStreamCatched.toString().trim(); // Rimuovi eventuali spazi aggiuntivi alla fine

        // Verifica che l'output contenga "Hello, World"
        assertTrue("L'output non contiene 'Hello, World'", actualOutput.contains(expectedOutput));
    }
}