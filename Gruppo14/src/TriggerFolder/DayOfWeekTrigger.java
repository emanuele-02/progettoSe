package TriggerFolder;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

// DayOfWeekTrigger class implementing the Trigger interface
public class DayOfWeekTrigger implements Trigger {
    private String targetDayOfWeek;

    // Constructor
    public DayOfWeekTrigger(String targetDayOfWeek) {
        // Convert the target day to lowercase
        this.targetDayOfWeek = targetDayOfWeek.toLowerCase();

        // Check if the provided day is valid
        try {
            DayOfWeek.valueOf(targetDayOfWeek.toUpperCase());
        } catch (IllegalArgumentException e) {
            // If an exception occurs, the day is not valid
            throw new IllegalArgumentException("Invalid day of the week: " + targetDayOfWeek);
        }
    }

    // Method to check if the rule should be triggered
    @Override
    public boolean checkTrigger() {
        // Get the current day as a string (in lowercase)
        String currentDayOfWeek = LocalDateTime.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toLowerCase();

        // Compare the current day with the target day
        return currentDayOfWeek.equals(targetDayOfWeek);
    }
}