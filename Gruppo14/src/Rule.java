import java.lang.Thread.State;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

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
