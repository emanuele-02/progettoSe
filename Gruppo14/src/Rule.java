import java.time.Duration;
import ActionFolder.Action;
import TriggerFolder.Trigger;

public class Rule implements Runnable{
    
    private String ruleName;
    private Trigger trigger;
    private Action action;
    private Duration timeDuration;
    private boolean isActive;

    
    /*costructor in case timeDuration is not specified. It will be set to a number of days pair to the maximum value of long. When the rule is created, it will be added within the rule list */
    public Rule(String ruleName, Trigger trigger, Action action) {

        this.ruleName=ruleName;
        this.trigger = trigger;
        this.action = action;
        this.isActive=true;
        //this.timeDuration=Duration.ofDays(Long.MAX_VALUE);
        RuleManager ruleManager=RuleManager.getInstance();
        ruleManager.addRule(this);

    }

    /*costructor in case timeDuration is specified. It will be set to a specified number of second. When the rule is created, it will be added within the rule list*/
    public Rule(String ruleName, Trigger trigger, Action action, Duration timeDuration) {

        this.ruleName=ruleName;
        this.trigger = trigger;
        this.action = action;
        this.isActive= true;
        //this.timeDuration= timeDuration;
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

    @Override
    public void run() {
        
        long startTime=System.currentTimeMillis();

        long finishTime=startTime+ timeDuration.toMillis();

        while(System.currentTimeMillis()<finishTime){
            while(isActive){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    
                    System.out.println("Errore nell'esecuzione della regola:\t");
                    e.printStackTrace();
                }

                if(trigger.checkTrigger()){

                    action.execute();
                }

            }

        }
    }

}