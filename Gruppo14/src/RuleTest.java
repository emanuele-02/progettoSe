import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import ActionFolder.*;
import TriggerFolder.*;


public class RuleTest {

    private Trigger t= new HourOfDayTrigger(3, 4);
    private Action a= new DialogBoxAction("Hello, World");
    private Rule r= new Rule("Prova", t, a, 0, 0, 100);
    
    @Test
    void testActivate() {

        r.activate();

        assertTrue(r.isActive());

    }


    @Test
    void testDeactivate() {

        r.deactivate();

        assertFalse(r.isActive());


    }

    @Test
    void testIsActive() {

        r.activate();
        assertTrue(r.isActive());

        r.deactivate();
        assertFalse(r.isActive());
        

    }

    @Test
    void testRun() throws InterruptedException {

        Thread thread= new Thread(r);
        thread.start();

        Thread.sleep(1000);

        assertTrue(t.checkTrigger());

        r.deactivate();

        thread.join();
    }
}
