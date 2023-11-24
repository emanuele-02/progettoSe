package TriggerFolder;

import java.time.LocalTime;


public class HourOfDayTrigger implements Trigger {
    private LocalTime targetTime;


    public HourOfDayTrigger(int hour, int minute) {
        if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
            this.targetTime = LocalTime.of(hour, minute);
        } else {
            throw new IllegalArgumentException("Invalid hour or minute.");
        }
    }


    @Override
    public boolean checkTrigger() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(targetTime) || currentTime.equals(targetTime);
    }

}
