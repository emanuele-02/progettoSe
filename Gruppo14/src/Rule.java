import java.lang.Thread.State;
import java.util.LinkedList;
import java.util.List;

public class Rule {
    
    private String ruleName;
    private Trigger trigger;
    private List<Action> action;
    private boolean isActive;
    private Time triggerTime;
    private State ruleState;

    public Rule(Trigger trigger, List<Action> action) {

        this.trigger = trigger;
        this.action = action;
        this.action= new LinkedList<>();

    }

    public void modifyRule(Trigger trigger, List<Action> actions){

    }

    public void modifyName(String newName){

        this.ruleName=newName;
    }

    public void addAction(Action action){

        this.action.add(action);

    }

    public void removeAction(Action action){

        this.action.remove(action);

    }

     /* */
    public void setTriggeredOnce(boolean isTriggeredOnce){

    }

    public void setTimeDelay(int timeDelay){

        

    }

    public boolean isTriggeredOnce(){


    }

    public State getState(){

        return this.ruleState;

    }

    public void setState(State s){

        this.ruleState=s;

    }


    


    



}
