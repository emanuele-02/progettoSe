package TriggerFolder;

import java.time.LocalTime;

// Implementazione della classe HourOfDayTrigger che implementa l'interfaccia Trigger
public class HourOfDayTrigger implements Trigger {
    private LocalTime targetTime;

    // Costruttore
    public HourOfDayTrigger(int hour, int minute) {
        this.targetTime = LocalTime.of(hour, minute);
    }

    // Metodo per verificare se la regola deve essere attivata
    @Override
    public boolean checkTrigger() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(targetTime) || currentTime.equals(targetTime);
    }

}
