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
    

    private int days;
    private int hours;
    private int minutes;
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

    public Rule(String ruleName, Trigger trigger, Action action, int days, int hours, int minutes) {
        this.ruleName = ruleName;
        this.trigger = trigger;
        this.action = action;
        this.isActive = true;
        this.triggeredOnce = false; 
        this.alreadyTriggered = false;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
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
}
