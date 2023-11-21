package TriggerFolder;
import static org.junit.Assert.assertEquals;
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

    @Test
    void testGetTargetDayOfWeek() {

        DayOfWeekTrigger trigger=  new DayOfWeekTrigger("Monday");

        if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Monday")){
            assertEquals("Monday", trigger.getTargetDayOfWeek());
        }

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Tuesday")){
            trigger.setTargetdayOfWeek("Tuesday");
            assertEquals("Tuesday", trigger.getTargetDayOfWeek());
        }

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Wednesday")){
            trigger.setTargetdayOfWeek("Wednesday");
            assertEquals("Wednesday", trigger.getTargetDayOfWeek());
        }

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Thursday")){
            trigger.setTargetdayOfWeek("Thursday");
            assertEquals("Thursday", trigger.getTargetDayOfWeek());
        }

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Friday")){
            trigger.setTargetdayOfWeek("Friday");
            assertEquals("Friday", trigger.getTargetDayOfWeek());
        }

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Saturday")){
            trigger.setTargetdayOfWeek("Saturday");
            assertEquals("Saturday", trigger.getTargetDayOfWeek());
        }

        else{
            trigger.setTargetdayOfWeek("Sunday");
            assertEquals("Sunday", trigger.getTargetDayOfWeek());
        }
    }

    @Test
    void testSetTargetdayOfWeek() {
        DayOfWeekTrigger trigger=  new DayOfWeekTrigger("Monday");

        if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Monday")){
            assertEquals("Monday", trigger.getTargetDayOfWeek());
        }

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Tuesday")){
            trigger.setTargetdayOfWeek("Tuesday");
            assertEquals("Tuesday", trigger.getTargetDayOfWeek());
        }

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Wednesday")){
            trigger.setTargetdayOfWeek("Wednesday");
            assertEquals("Wednesday", trigger.getTargetDayOfWeek());
        }

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Thursday")){
            trigger.setTargetdayOfWeek("Thursday");
            assertEquals("Thursday", trigger.getTargetDayOfWeek());
        }

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Friday")){
            trigger.setTargetdayOfWeek("Friday");
            assertEquals("Friday", trigger.getTargetDayOfWeek());
        }

        else if(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase("Saturday")){
            trigger.setTargetdayOfWeek("Saturday");
            assertEquals("Saturday", trigger.getTargetDayOfWeek());
        }

        else{
            trigger.setTargetdayOfWeek("Sunday");
            assertEquals("Sunday", trigger.getTargetDayOfWeek());
        }

    }
}
