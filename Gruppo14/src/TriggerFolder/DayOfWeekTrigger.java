package TriggerFolder;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;


public class DayOfWeekTrigger implements Trigger{

    private String targetDayOfWeek;

    public DayOfWeekTrigger(String targetDayOfWeek) {
        this.targetDayOfWeek = targetDayOfWeek;
    }

    /*verify if the trigger condition is true */
    public boolean checkTrigger(){

        String currentDayOfWeek=LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);/*takes the current day of week in english */
        if(targetDayOfWeek.equalsIgnoreCase(currentDayOfWeek))
            return true;

        else
            return false;
    }
    
    public void setTargetdayOfWeek(String day){

        this.targetDayOfWeek=day;
    }

    public String getTargetDayOfWeek() {
        return targetDayOfWeek;
    }
}
