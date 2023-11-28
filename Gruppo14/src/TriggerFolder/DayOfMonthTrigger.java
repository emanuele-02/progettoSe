package TriggerFolder;
import java.time.LocalDate;

// DayOfMonthTrigger class
public class DayOfMonthTrigger {
    private int targetDayOfMonth;

    // Constructor
    public DayOfMonthTrigger(int targetDayOfMonth) {
       
            if (targetDayOfMonth < 1 || targetDayOfMonth > 31) {
                throw new IllegalArgumentException("Day of the month must be between 1 and 31");
            }
        
        this.targetDayOfMonth = targetDayOfMonth;
    }

    // Method to check whether the rule should be activated
    public boolean checkTrigger() {
        // Get the current day of the month
        int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        // Check if the current day is among those specified as targets
       
            if (currentDayOfMonth == targetDayOfMonth) {
                return true;
            }
        
        return false;
    }
}
