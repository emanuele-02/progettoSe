package TriggerFolder;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;


public class DayOfWeekTrigger implements Trigger{

    private String targetDayOfWeek;

    public DayOfWeekTrigger(String targetDayOfWeek) {
        this.targetDayOfWeek = targetDayOfWeek;
    }

    //verifico se la condizione del trigger è verificata
    public boolean checkTrigger(){
        //converte la data attuale in giorno della settimana (inglese)
        String currentDayOfWeek=LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        if(targetDayOfWeek.equalsIgnoreCase(currentDayOfWeek))
            return true;

        else
            return false;
    }
    
    //Per i test
    public void setTargetdayOfWeek(String day){
        this.targetDayOfWeek=day;
    }

    public String getTargetDayOfWeek() {
        return targetDayOfWeek;
    }
}
