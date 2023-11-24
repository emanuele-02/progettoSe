import ActionFolder.Action;
import TriggerFolder.Trigger;

public class Rule {

    private String ruleName;
    private Trigger trigger;
    private Action action;
    private boolean isActive;

    public Rule(String ruleName, Trigger trigger, Action action) {
        this.ruleName = ruleName;
        this.trigger = trigger;
        this.action = action;
        this.isActive = true;
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

}
