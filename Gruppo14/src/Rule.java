import java.time.Duration;
import ActionFolder.Action;
import TriggerFolder.Trigger;

public class Rule implements Runnable{
    
    private String ruleName;
    private Trigger trigger;
    private Action action;
    private Duration timeDelay;
    private boolean isActive;

    
    /*costructor in case timeDuration is not specified. It will be set to a number of days pair to the maximum value of long. When the rule is created, it will be added within the rule list */
    public Rule(String ruleName, Trigger trigger, Action action) {

        this.ruleName=ruleName;
        this.trigger = trigger;
        this.action = action;
        this.isActive=true;
        this.timeDelay=Duration.ZERO;
        RuleManager ruleManager=RuleManager.getInstance();
        ruleManager.addRule(this);

    }

    /*costructor in case timeDuration is specified. It will be set to a specified number of second. When the rule is created, it will be added within the rule list*/
    public Rule(String ruleName, Trigger trigger, Action action, long daysOfDelay, long hoursOfDelay, long minutesOfDelay) {

        this.ruleName=ruleName;
        this.trigger = trigger;
        this.action = action;
        this.isActive= true;
        this.timeDelay= Duration.ofDays(daysOfDelay).plusHours(hoursOfDelay).plusMinutes(minutesOfDelay);
        RuleManager ruleManager=RuleManager.getInstance();
        ruleManager.addRule(this);


    }

    /*method get on the rule's state */
    public boolean isActive(){

        return this.isActive;

    }

    /*method to activate a Rule */
    public void activate() {
           
        isActive=true;

    }

    /*method to deactivate a Rule */
    public void deactivate() {

        isActive=false;
  
    }

    public String getRuleName(){

        return this.ruleName;
    }

    public void setRuleName(String newName){

        this.ruleName=newName;
    }

    @Override
    public void run() {
        
        while(isActive){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                
                System.err.println("Error in rule's execution\t");
                e.printStackTrace();
            }

            if(trigger.checkTrigger()){

                action.execute();

                try {
                    Thread.sleep(this.timeDelay.toMillis());
                } catch (InterruptedException e) {
                    System.err.println("Error in rule's execution\t");
                    e.printStackTrace();
                }
            }

        }
    }

}