package TriggerFolder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DayOfMonthTriggerTest {

    @Test
    void testCheckTrigger() {
<<<<<<< HEAD
        // Test with the current day as the target - 1
=======
        // Test with the current day as the target +1
>>>>>>> ffe0cf2d6a421e8bbe6642942f67de29f53cfea7
        DayOfMonthTrigger triggerSingleDay = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth() + 1);
        assertFalse(triggerSingleDay.checkTrigger());
    }

    @Test
    void testCheckTrigger2() {
        // Test with the current day as the target
        DayOfMonthTrigger triggerSingleDay = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth());
        assertTrue(triggerSingleDay.checkTrigger());
    }

    @Test
    void testCheckTrigger3() {
        // Test with an invalid day as the target, expecting an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            new DayOfMonthTrigger(34);
        });
    }

    @Test
    void testCheckTrigger4() {
        // Test with an invalid day as the target, expecting an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            new DayOfMonthTrigger(0);
        });
    }
}
