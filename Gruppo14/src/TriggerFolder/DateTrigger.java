package TriggerFolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// DateTrigger class --> trigger based on the date that activates when the date provided by the user is equal to or before the current date
public class DateTrigger implements Trigger {
    private String targetDate;

    // Constructor
    public DateTrigger(String targetDate) {
        try {
            // Create the DateTimeFormatter object with the format dd-MM-yyyy and parse the
            // provided date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate parsedDate = LocalDate.parse(targetDate, formatter);

            // Additional check to ensure the date is valid in the calendar
            if (parsedDate.getDayOfMonth() != Integer.parseInt(targetDate.substring(0, 2))
                    || parsedDate.getMonthValue() != Integer.parseInt(targetDate.substring(3, 5))
                    || parsedDate.getYear() != Integer.parseInt(targetDate.substring(6, 10))) {
                throw new IllegalArgumentException("Invalid date in the calendar.");
            }

            this.targetDate = targetDate;
            // Catch and throw an exception in case the user provides a format different
            // from "dd-MM-yyyy"
        } catch (DateTimeParseException e) {
            // Exception in case the format is not valid
            throw new IllegalArgumentException("Invalid date format. Please use the format dd-MM-yyyy");
        }
    }

    // Method to check whether the rule should be activated
    @Override
    public boolean checkTrigger() {
        // Get the current date and convert it to local date
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = currentDateTime.toLocalDate();

        // Compare the current date with the targetDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedTargetDate = LocalDate.parse(targetDate, formatter);

        // Return true only if the date provided by the user is equal to or before the
        // current date
        return currentDate.isEqual(parsedTargetDate) || currentDate.isAfter(parsedTargetDate);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((targetDate == null) ? 0 : targetDate.hashCode());
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
        DateTrigger other = (DateTrigger) obj;
        if (targetDate == null) {
            if (other.targetDate != null)
                return false;
        } else if (!targetDate.equals(other.targetDate))
            return false;
        return true;
    }

}