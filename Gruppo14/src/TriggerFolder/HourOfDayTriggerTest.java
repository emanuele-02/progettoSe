package TriggerFolder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;

public class HourOfDayTriggerTest {

    @Test
    public void testCheckTriggerBeforeTargetTime() {
        LocalTime currentTime = LocalTime.now();

        // Extract the hour and minutes.
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();

        // targetTime is greater than currentdate because of +1.
        HourOfDayTrigger trigger = new HourOfDayTrigger(currentHour, (currentMinute + 1));
        assertFalse(trigger.checkTrigger(), "CheckTrigger should be false before target time");
    }

    @Test
    public void testCheckTriggerEqualTime() {
        // Get the current time
        LocalTime currentTime = LocalTime.now();

        // Extract the hour and minutes
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();

        // targetTime is equal to currentdate.
        HourOfDayTrigger trigger = new HourOfDayTrigger(currentHour, currentMinute);
        assertTrue(trigger.checkTrigger(), "CheckTrigger should be false before target time");
    }

    @Test
    public void testCheckTriggerAfterTargetTime() {
        // Get the current time.
        LocalTime currentTime = LocalTime.now();

        // Extract the hour and minutes.
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();

        // Ensure currentMinute is not negative
        if (currentMinute == 0) {
            currentMinute = 59;
        } else {
            currentMinute--;
        }

        // targetTime is less than current date because of -1.
        HourOfDayTrigger trigger = new HourOfDayTrigger(currentHour, currentMinute);
        assertTrue(trigger.checkTrigger(), "CheckTrigger should be true after target time");
    }

    // Invalid hour or minute
    @Test
    public void testIllegalTime() {
        assertThrows(IllegalArgumentException.class, () -> new HourOfDayTrigger(24, 00),
                "Creating trigger with invalid values should throw IllegalArgumentException");
    }
}