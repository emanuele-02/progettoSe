package TriggerFolder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class DayOfWeekTriggerTest {

    @Test
    void testCheckTriggerWithSameDay() {
        // Define the desired format for "day month year"

        // Get the current date in the specified format
        String currentDayFormatted = LocalDateTime.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                .toLowerCase();

        // Pass the formatted date as an argument to the DayOfWeekTrigger constructor
        DayOfWeekTrigger triggerSameDay = new DayOfWeekTrigger(currentDayFormatted);

        // Perform the assertion
        assertTrue(triggerSameDay.checkTrigger());
    }

    @Test
    void testCheckTriggerWithDifferentDay() {
        // Define the desired format for "day month year"

        // Get the current date minus one day in the specified format
        String currentDayFormatted = LocalDateTime.now().minusDays(1).getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH).toLowerCase();

        // Pass the formatted date as an argument to the DayOfWeekTrigger constructor
        DayOfWeekTrigger triggerDifferentDay = new DayOfWeekTrigger(currentDayFormatted);

        // Perform the assertion
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

}
