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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((trigger1 == null) ? 0 : trigger1.hashCode());
        result = prime * result + ((trigger2 == null) ? 0 : trigger2.hashCode());
        result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
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
        CompositeTrigger other = (CompositeTrigger) obj;
        if (trigger1 == null) {
            if (other.trigger1 != null)
                return false;
        } else if (!trigger1.equals(other.trigger1))
            return false;
        if (trigger2 == null) {
            if (other.trigger2 != null)
                return false;
        } else if (!trigger2.equals(other.trigger2))
            return false;
        if (operationType != other.operationType)
            return false;
        return true;
    }
    
}