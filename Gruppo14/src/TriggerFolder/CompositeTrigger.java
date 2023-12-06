package TriggerFolder;

public class CompositeTrigger implements Trigger {

    private Trigger trigger1;
    private Trigger trigger2;
    private LogicalOperation operationType;

    public CompositeTrigger(Trigger trigger1, Trigger trigger2, LogicalOperation operationType) {
        if (operationType == LogicalOperation.NOT) {
            throw new IllegalArgumentException("When passing two triggers, only AND and OR operations are allowed.");
        }
        this.trigger1 = trigger1;
        this.trigger2 = trigger2;
        this.operationType = operationType;
    }

    public CompositeTrigger(Trigger trigger1, LogicalOperation operationType) {
        if (operationType != LogicalOperation.NOT) {
            throw new IllegalArgumentException("When passing a single trigger, only NOT operation is allowed.");
        }
        this.trigger1 = trigger1;
        this.operationType = operationType;
    }

    @Override
    public boolean checkTrigger() {
        if (operationType == LogicalOperation.AND) {
            return trigger1.checkTrigger() && trigger2.checkTrigger();
        } else if (operationType == LogicalOperation.OR) {
            return trigger1.checkTrigger() || trigger2.checkTrigger();
        } else {
            return !trigger1.checkTrigger();
        } 
    }
}