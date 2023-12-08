package ActionFolder;

import java.util.ArrayList;
import java.util.List;

public class CompositeAction implements Action {

    private List<Action> actions;

    public CompositeAction() {
        actions = new ArrayList<>();
    }

    public void addAction(Action a) {
        actions.add(a);
    }

    public void removeAction(Action a) {
        if (!actions.isEmpty()) {
            actions.remove(a);
        } else {
            throw new IllegalStateException("Cannot remove action from an empty CompositeAction");
        }
    }

    @Override
    public void execute() {
        for (Action action : actions) {
            action.execute();
        }
    }

    public List<Action> getActions() {
        return new ArrayList<>(actions);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actions == null) ? 0 : actions.hashCode());
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
        CompositeAction other = (CompositeAction) obj;
        if (actions == null) {
            if (other.actions != null)
                return false;
        } else if (!actions.equals(other.actions))
            return false;
        return true;
    }

}
