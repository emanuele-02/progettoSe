package TriggerFolder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateTriggerTest {

  @Test
void testCheckTriggerWithValidDate() {
    // Test where the date provided by the user is equal to the current date, so I expect TRUE
    DateTrigger trigger1 = new DateTrigger("29-11-2023");
    assertTrue(trigger1.checkTrigger());

    // Test where the date provided by the user is before the current date, so I expect TRUE
    DateTrigger trigger2 = new DateTrigger("28-11-2023");
    assertTrue(trigger2.checkTrigger());

    // Test with a date greater than the current
    DateTrigger trigger3 = new DateTrigger("30-11-2023");
    assertFalse(trigger3.checkTrigger());
}

@Test
void testCheckTriggerWithInvalidDate() {
    // Test where the date provided by the user is in an incorrect format (uses a comma instead of a hyphen as a separator), so I expect an exception to be thrown
    assertThrows(IllegalArgumentException.class, () -> {
        new DateTrigger("24,11,2023");
    });

    // Test where the date provided by the user is technically correct but does not represent a valid date in the calendar, so I expect an exception to be thrown
    assertThrows(IllegalArgumentException.class, () -> {
        new DateTrigger("31-02-2023");
    });

    // Test where the date provided by the user is technically correct but not valid in the calendar, so I expect an exception to be thrown
    assertThrows(IllegalArgumentException.class, () -> {
        new DateTrigger("31-11-2023");
    });

    
    
}}