package TriggerFolder;

import java.time.LocalDate;

// DayOfMonthTrigger class

public class DayOfMonthTrigger implements Trigger {

    private int targetDayOfMonth;

    // Constructor
    public DayOfMonthTrigger(int targetDayOfMonth) {

        if (targetDayOfMonth < 1 || targetDayOfMonth > 31) {
            throw new IllegalArgumentException("Day of the month must be between 1 and 31");
        }

        this.targetDayOfMonth = targetDayOfMonth;
    }

    // Method to check whether the rule should be activated
    @Override
    public boolean checkTrigger() {
        // Get the current day of the month
        int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        // Check if the current day is among those specified as targets

        if (currentDayOfMonth == targetDayOfMonth) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + targetDayOfMonth;
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
        DayOfMonthTrigger other = (DayOfMonthTrigger) obj;
        if (targetDayOfMonth != other.targetDayOfMonth)
            return false;
        return true;
    }

}