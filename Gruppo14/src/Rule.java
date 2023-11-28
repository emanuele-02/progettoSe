import java.time.Duration;

import ActionFolder.Action;
import TriggerFolder.Trigger;

public class Rule {

    private String ruleName;
    private Trigger trigger;
    private Action action;
    private boolean isActive;
    private boolean triggeredOnce;
    private boolean alreadyTriggered;
    

    private int day;
    private int hour;
    private int minute;
    private Duration period;
   

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

    public Rule(String ruleName, Trigger trigger, Action action, int day, int hour, int minute) {
        this.ruleName = ruleName;
        this.trigger = trigger;
        this.action = action;
        this.isActive = true;
        this.triggeredOnce = false; 
        this.alreadyTriggered = false;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        updatePeriod();
        RuleManager ruleManager = RuleManager.getInstance();
        ruleManager.addRule(this);
    }

    public boolean isActive(){
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
        long totalMinutes = day * 24 * 60 + hour * 60 + minute;
        this.period = Duration.ofMinutes(totalMinutes);
    }
    

    public Duration getDelay() {
        return period;
    }
    
    // Set new delay time
    public void setDelay(int newDay, int newHour, int newMinute) {
        this.day = newDay;
        this.hour = newHour;
        this.minute = newMinute;
        updatePeriod();
    }
}
