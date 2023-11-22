import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RuleManagerTest {
    /**
     * 
     */
   

    @Test
    void testGetInstance() {
        // Verifica che le due chiamate a getInstance restituiscano la stessa istanza
        RuleManager instance1 = RuleManager.getInstance();
        RuleManager instance2 = RuleManager.getInstance();

        assertSame(instance1, instance2);
    }
}
   