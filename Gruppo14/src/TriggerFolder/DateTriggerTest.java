package TriggerFolder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTriggerTest {
    // TEST DEL METODO CHECKTRIGGER()
    @Test
    void testCheckTrigger() {
        // Test  in cui la data passata dall'utente è uguale alla data corrente e quindi mi aspetto TRUE
        DateTrigger trigger1 = new DateTrigger("22-11-2023");
        assertTrue(trigger1.checkTrigger());

        // Test caso in cui la data passata dall'utente è successiva rispetto alla data corrente e mi aspetto FALSE
        DateTrigger trigger2 = new DateTrigger("24-11-2023");
        assertFalse(trigger2.checkTrigger());

        // Test caso in cui la data passata dall'utente  è precedente a quella corrente e mi aspetto TRUE
        DateTrigger trigger3 = new DateTrigger("21-11-2023");
        assertTrue(trigger3.checkTrigger());

        // Test in cui la data passata dall'utente è in un formato errato ( usa la virgola invece del trattino - come separatore) e quindi mi aspetto che venga lancaita l'eccezione
        assertThrows(IllegalArgumentException.class, () -> {
        DateTrigger triggerInvalidFormat = new DateTrigger("24,11,2023");  });

    }
}
