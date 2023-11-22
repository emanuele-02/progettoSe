package TriggerFolder;
import java.util.ArrayList;
import java.util.List;

class CompositeTrigger implements Trigger {
    private LogicalOperation operator;
    private List<Trigger> triggers;

    public CompositeTrigger(LogicalOperation operator, List<Trigger> triggers) {
        this.operator = operator;
        this.triggers = new ArrayList<>();
    }

    public void addTrigger(Trigger trigger) {
        triggers.add(trigger);
    }

    public void removeTrigger(Trigger trigger) {
        triggers.remove(trigger);
    }

    public boolean checkTrigger() {
        switch (operator) {
            case AND:
                return checkAndOperator();
            case OR:
                return checkOrOperator();
            case NOT:
                return checkNotOperator();
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + operator);
        }
    }

    private boolean checkAndOperator() {
        // Restituisci true solo se tutti i trigger restituiscono true
        for (Trigger trigger : triggers) {
            if (!trigger.checkTrigger()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkOrOperator() {
        // Restituisci true se almeno uno dei trigger restituisce true
        for (Trigger trigger : triggers) {
            if (trigger.checkTrigger()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkNotOperator() {
        // Restituisci true se l'unico trigger restituisce false
        if (triggers.size() == 1) {
            return !triggers.get(0).checkTrigger();
        } else {
            throw new UnsupportedOperationException("NOT operator deve avere esattamente un trigger.");
        }
    }
}
