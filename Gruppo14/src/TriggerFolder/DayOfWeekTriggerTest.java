package TriggerFolder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DayOfWeekTriggerTest {

    @Test
    void testCheckTriggerWithValidDay() {
        // Test when the current day is equal to the target day
        DayOfWeekTrigger triggerSameDay = new DayOfWeekTrigger("Tuesday");
        assertTrue(triggerSameDay.checkTrigger());

        // Test when the current day is different from the target day
        DayOfWeekTrigger triggerDifferentDay = new DayOfWeekTrigger("monday");
        assertFalse(triggerDifferentDay.checkTrigger());
    }

    @Test
    void testCheckTriggerWithInvalidDay() {
        // Test with an invalid day of the week
        assertThrows(IllegalArgumentException.class, () -> {
            new DayOfWeekTrigger("fridei");
        });
    }

       @Test
    void testCheckTriggerWithInvalidDay2() {
        // Test with an invalid day of the week
        assertThrows(IllegalArgumentException.class, () -> {
            new DayOfWeekTrigger("tusdei");
        });
    }

    @Test
    void testCheckTriggerWithValidDayDoesNotThrow() {
        // Test that the constructor does not throw an exception for a valid day
        DayOfWeekTrigger triggerValidDay = new DayOfWeekTrigger("Monday");
        assertDoesNotThrow(triggerValidDay::checkTrigger);
        }


    @Test
    void testCheckTriggerWithSunday() {
        // Test when the current day is Sunday
        DayOfWeekTrigger triggerSunday = new DayOfWeekTrigger("Sunday");
        assertFalse(triggerSunday.checkTrigger());
    }

    @Test
    void testCheckTriggerWithThursday() {
        // Test when the current day is Thursday
        DayOfWeekTrigger triggerThursday = new DayOfWeekTrigger("Tuesday");
        assertTrue(triggerThursday.checkTrigger());
    }
}


