package RuleFolder;

import java.io.Serializable;
import java.time.Duration;

import ActionFolder.Action;
import TriggerFolder.Trigger;

public class Rule implements Serializable {

    private String ruleName;
    private Trigger trigger;
    private Action action;
    private boolean isActive;
    private boolean triggeredOnce;
    private boolean alreadyTriggered;

    private int days;
    private int hours;
    private int minutes;
    private Duration period;
    private long lastExecutionTime;

    public Rule(String ruleName, Trigger trigger, Action action, Boolean triggeredOnce) {
        this.ruleName = ruleName;
        this.trigger = trigger;
        this.action = action;
        this.isActive = true;
        this.triggeredOnce = triggeredOnce;
        this.alreadyTriggered = false;
        RuleManager ruleManager = RuleManager.getInstance();
        ruleManager.addRule(this);
    }

    public Rule(String ruleName, Trigger trigger, Action action, int days, int hours, int minutes) {
        this(ruleName, trigger, action, false);

        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        updatePeriod();
        this.lastExecutionTime = 0;
       
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void activate() {
        isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String newName) {
        this.ruleName = newName;
    }

    public Trigger getTrigger() {
        return this.trigger;
    }

    public Action getAction() {
        return this.action;
    }

    public boolean isTriggeredOnce() {
        return triggeredOnce;
    }

    public void setTriggeredOnce(boolean triggeredOnce) {
        this.triggeredOnce = triggeredOnce;
    }

    public boolean isAlreadyTriggered() {
        return alreadyTriggered;
    }

    public void setAlreadyTriggered(boolean alreadyTriggered) {
        this.alreadyTriggered = alreadyTriggered;
    }

    // Calculating and setting the duration
    private void updatePeriod() {
        long totalminutes = days * 24 * 60 + hours * 60 + minutes;
        this.period = Duration.ofMinutes(totalminutes);
    }

    public Duration getPeriod() {
        return period;
    }

    // Set new period time
    public void setPeriod(int newDays, int newHours, int newMinutes) {
        this.days = newDays;
        this.hours = newHours;
        this.minutes = newMinutes;
        updatePeriod();
    }

    public long getLastExecutionTime() {
        return lastExecutionTime;
    }

    public void setLastExecutionTime(long lastExecutionTime) {
        this.lastExecutionTime = lastExecutionTime;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ruleName == null) ? 0 : ruleName.hashCode());
        result = prime * result + ((trigger == null) ? 0 : trigger.hashCode());
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + (isActive ? 1231 : 1237);
        result = prime * result + (triggeredOnce ? 1231 : 1237);
        result = prime * result + (alreadyTriggered ? 1231 : 1237);
        result = prime * result + days;
        result = prime * result + hours;
        result = prime * result + minutes;
        result = prime * result + ((period == null) ? 0 : period.hashCode());
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
        Rule other = (Rule) obj;
        if (ruleName == null) {
            if (other.ruleName != null)
                return false;
        } else if (!ruleName.equals(other.ruleName))
            return false;
        if (trigger == null) {
            if (other.trigger != null)
                return false;
        } else if (!trigger.equals(other.trigger))
            return false;
        if (action == null) {
            if (other.action != null)
                return false;
        } else if (!action.equals(other.action))
            return false;
        if (isActive != other.isActive)
            return false;
        if (triggeredOnce != other.triggeredOnce)
            return false;
        if (alreadyTriggered != other.alreadyTriggered)
            return false;
        if (days != other.days)
            return false;
        if (hours != other.hours)
            return false;
        if (minutes != other.minutes)
            return false;
        if (period == null) {
            if (other.period != null)
                return false;
        } else if (!period.equals(other.period))
            return false;
        return true;
    }

}
