import java.time.Duration;
import ActionFolder.Action;
import TriggerFolder.Trigger;

public class Rule extends RuleSubject implements Runnable{
    
    private String ruleName;
    private Trigger trigger;
    private Action action;
    private boolean isOneTimeTrigger;
    private Duration timeDuration;
    
    /*costructor in case timeDuration is not specified. It will be set to a number of days pair to the maximum value of long */
    public Rule(String ruleName, Trigger trigger, Action action, boolean isOneTimeTrigger) {

        this.ruleName=ruleName;
        this.trigger = trigger;
        this.action = action;
        this.isOneTimeTrigger=isOneTimeTrigger;
        this.timeDuration=Duration.ofDays(Long.MAX_VALUE);

    }

    /*costructor in case timeDuration is specified. It will be set to a specified number of second*/
    public Rule(String ruleName, Trigger trigger, Action action, boolean isOneTimeTrigger, Duration timeDuration) {

        this.ruleName=ruleName;
        this.trigger = trigger;
        this.action = action;
        this.timeDuration= timeDuration;
        this.isOneTimeTrigger=isOneTimeTrigger;

    }

    /*method to activate a Rule */
    public void activate(RuleObserver observer) {
    
        super.attach(observer); 

    }

    /*method to deactivate a Rule */
    public void deactivate() {

        super.detach();

    }

    
    @Override
    public void run() {

        
        
    }

}
