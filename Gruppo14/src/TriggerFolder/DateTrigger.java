
package TriggerFolder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// classe DateTrigger --> trigger sulla data che si attiva quando la data passata dall'utente è uguale o precedente a quella corrente
public class DateTrigger {
    private String targetDate;

    // Costruttore
    public DateTrigger(String targetDate) {
        try {
            // creo l'oggetto datetimeFormatter con il formato dd-mm-yyyy e faccio il parse della data passata
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(targetDate, formatter);
            this.targetDate = targetDate;
            // catturo e lancio l'eccezione nel caso in cui l'utente inserisca un formato diverso da "dd-MM-yyyy"
        } catch (DateTimeParseException e) {
            // Eccezione nel caso in cui il formato non sia valido
            throw new IllegalArgumentException("Formato della data non valido. Utilizzare il formato dd-MM-yyyy");
        }
    }

    // Metodo per verificare se la regola deve essere attivata
    public boolean checkTrigger() {
        // Prendo la data corrente e la converto in local date
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = currentDateTime.toLocalDate();

        // Confronta la data corrente con la targetDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedTargetDate = LocalDate.parse(targetDate, formatter);
        // ritorno vero solo se la data passata dall'utente è uguale o precedente a quella odierna
        return currentDate.isEqual(parsedTargetDate) || currentDate.isAfter(parsedTargetDate);
    }
}
