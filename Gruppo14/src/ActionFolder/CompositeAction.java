package ActionFolder;

import java.util.ArrayList;
import java.util.List;

public class CompositeAction implements Action{

    private List<Action> actions;

    public CompositeAction() {
        actions = new ArrayList<>();
    }

    public void addAction(Action a){
        actions.add(a);
    }

    public void removeAction(Action a){
        if(!actions.isEmpty()){
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
    
    
}
