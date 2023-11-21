import java.lang.Thread.State;
import java.util.LinkedList;
import java.util.List;

import TriggerFolder.Trigger;

public class Rule extends RuleSubject{
    
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


    



}
