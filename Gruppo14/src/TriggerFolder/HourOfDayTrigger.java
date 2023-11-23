package TriggerFolder;

import java.time.LocalTime;


public class HourOfDayTrigger implements Trigger {
    private LocalTime targetTime;


    public HourOfDayTrigger(int hour, int minute) {
        this.targetTime = LocalTime.of(hour, minute);
    }


    @Override
    public boolean checkTrigger() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(targetTime) || currentTime.equals(targetTime);
    }

}
