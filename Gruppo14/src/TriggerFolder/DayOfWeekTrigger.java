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
        String currentDayOfWeek = LocalDateTime.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                .toLowerCase();

        // Compare the current day with the target day
        return currentDayOfWeek.equals(targetDayOfWeek);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((targetDayOfWeek == null) ? 0 : targetDayOfWeek.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DayOfWeekTrigger other = (DayOfWeekTrigger) obj;
        if (targetDayOfWeek == null) {
            if (other.targetDayOfWeek != null)
                return false;
        } else if (!targetDayOfWeek.equals(other.targetDayOfWeek))
            return false;
        return true;
    }

}