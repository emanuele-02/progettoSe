import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import ActionFolder.*;
import TriggerFolder.*;

public class RuleTest {

    private Trigger t= new HourOfDayTrigger(3, 4);
    private Action a= new DialogBoxAction("Hello, World");
    private Rule r= new Rule("Prova", t, a);


    @Test
    void testActivate() {

        r.deactivate();;
        r.activate();

        assertTrue(r.isActive());

    }
    @Test
    void testDeactivate() {

        r.activate();
        r.deactivate();
        assertFalse(r.isActive());
        
    }
    


    
}
