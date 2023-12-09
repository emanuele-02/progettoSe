package TriggerFolder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTriggerTest {

    @Test
    void testCheckTriggerWithValidDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Test where the date provided by the user is equal to the current date, so I
        // expect TRUE
        DateTrigger trigger1 = new DateTrigger(LocalDate.now().format(formatter));
        assertTrue(trigger1.checkTrigger());

        // Test where the date provided by the user is before the current date, so I
        // expect TRUE
        DateTrigger trigger2 = new DateTrigger(LocalDate.now().minusDays(1).format(formatter));
        assertTrue(trigger2.checkTrigger());

        // Test with a date greater than the current
        DateTrigger trigger3 = new DateTrigger(LocalDate.now().plusDays(1).format(formatter));
        assertFalse(trigger3.checkTrigger());
    }

    @Test
    void testCheckTriggerWithInvalidDate() {
        // Test where the date provided by the user is in an incorrect format (uses a
        // comma instead of a hyphen as a separator), so I expect an exception to be
        // thrown
        assertThrows(IllegalArgumentException.class, () -> {
            new DateTrigger("24,11,2023");
        });

        // Test where the date provided by the user is technically correct but does not
        // represent a valid date in the calendar, so I expect an exception to be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            new DateTrigger("31-02-2023");
        });

        // Test where the date provided by the user is technically correct but not valid
        // in the calendar, so I expect an exception to be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            new DateTrigger("31-11-2023");
        });

    }
}