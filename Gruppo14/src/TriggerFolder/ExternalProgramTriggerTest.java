package TriggerFolder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class ExternalProgramTriggerTest {

    @Test
    void testCheckTrigger() {
        ExternalProgramTrigger externalProgramTrigger= new ExternalProgramTrigger(0, "python", "D:/progettoSe/ExternalProgramActionExample.py", "Hello, World");

        assertTrue(externalProgramTrigger.checkTrigger());

        ExternalProgramTrigger externalProgramTrigger2= new ExternalProgramTrigger(1, "python", "D:/progettoSe/ExternalProgramActionExample.py", "Hello, World");

        assertFalse(externalProgramTrigger2.checkTrigger());
    }
    
}
