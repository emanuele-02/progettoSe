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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((targetTime == null) ? 0 : targetTime.hashCode());
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
        HourOfDayTrigger other = (HourOfDayTrigger) obj;
        if (targetTime == null) {
            if (other.targetTime != null)
                return false;
        } else if (!targetTime.equals(other.targetTime))
            return false;
        return true;
    }

}
