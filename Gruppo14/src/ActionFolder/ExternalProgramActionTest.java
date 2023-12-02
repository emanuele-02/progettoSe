package ActionFolder;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ExternalProgramActionTest {

    @Test
    public void ValidExecution() {
        //Catch the output
        ByteArrayOutputStream outputStreamCatched = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCatched));

        ExternalProgramAction externalProgramAction = new ExternalProgramAction("python", "ExternalProgramActionExample.py", "Hello, World");
        externalProgramAction.execute();

        System.setOut(System.out);

        String attendedOutput = "['Hello, World']\n"+ "External program exited with \n code: " + "0";// Sostituisci con l'output atteso
        assertEquals(attendedOutput, outputStreamCatched.toString());
    }

}

