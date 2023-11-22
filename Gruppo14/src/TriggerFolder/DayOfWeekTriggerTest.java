package TriggerFolder;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class DayOfWeekTriggerTest {

    @Test
    void testCheckTrigger() {

        DayOfWeekTrigger trigger=  new DayOfWeekTrigger("Monday");

        if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Monday")){}
        
        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Tuesday"))
            trigger.setTargetdayOfWeek("Tuesday");
        
        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Wednesday")){
            trigger.setTargetdayOfWeek("Wednesday");
        }
        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Thursday")){
            trigger.setTargetdayOfWeek("Thursday");
        }
        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Friday"))
            trigger.setTargetdayOfWeek("Friday");

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Saturday"))
            trigger.setTargetdayOfWeek("Saturday");

        else 
            trigger.setTargetdayOfWeek("Sunday");

        assertTrue(trigger.checkTrigger());

    }
}
