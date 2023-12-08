package ActionFolder;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeActionTest {

    private CompositeAction actions;
    private DialogBoxAction a, a1;

    @BeforeEach
    public void setUp() {
        actions = new CompositeAction();
        a = new DialogBoxAction("pippo");
        a1 = new DialogBoxAction("pluto");
    }

    @Test
    void addExecuteMultipleTest() {
        // Expected messagge pippo,pluto
        actions.addAction(a);
        actions.addAction(a1);
        actions.execute();
    }

    @Test
    void removeTest() {
        // Expected message pluto
        actions.addAction(a);
        actions.addAction(a1);
        actions.removeAction(a);
        actions.execute();
    }

    @Test
    void removeEmptyTest() {
        assertThrows(IllegalStateException.class, () -> actions.removeAction(a));
    }

}
