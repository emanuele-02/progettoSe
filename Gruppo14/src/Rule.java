import java.time.Duration;
import ActionFolder.Action;
import TriggerFolder.Trigger;

<<<<<<< HEAD
public class Rule implements Runnable{
=======
public class Rule extends RuleSubject implements Runnable{
>>>>>>> f8dd2d94e07cdb07c78e8295f96e62b8ab1c2838
    
    private String ruleName;
    private Trigger trigger;
    private Action action;
<<<<<<< HEAD
    private Duration timeDuration;
    private boolean isActive;

    
    /*costructor in case timeDuration is not specified. It will be set to a number of days pair to the maximum value of long. When the rule is created, it will be added within the rule list */
    public Rule(String ruleName, Trigger trigger, Action action) {
=======
    private boolean isOneTimeTrigger;
    private Duration timeDuration;
    
    /*costructor in case timeDuration is not specified. It will be set to a number of days pair to the maximum value of long */
    public Rule(String ruleName, Trigger trigger, Action action, boolean isOneTimeTrigger) {
>>>>>>> f8dd2d94e07cdb07c78e8295f96e62b8ab1c2838

        this.ruleName=ruleName;
        this.trigger = trigger;
        this.action = action;
<<<<<<< HEAD
        this.isActive=true;
        //this.timeDuration=Duration.ofDays(Long.MAX_VALUE);
        RuleManager ruleManager=RuleManager.getInstance();
        ruleManager.addRule(this);

    }

    /*costructor in case timeDuration is specified. It will be set to a specified number of second. When the rule is created, it will be added within the rule list*/
    public Rule(String ruleName, Trigger trigger, Action action, Duration timeDuration) {
=======
        this.isOneTimeTrigger=isOneTimeTrigger;
        this.timeDuration=Duration.ofDays(Long.MAX_VALUE);

    }

    /*costructor in case timeDuration is specified. It will be set to a specified number of second*/
    public Rule(String ruleName, Trigger trigger, Action action, boolean isOneTimeTrigger, Duration timeDuration) {
>>>>>>> f8dd2d94e07cdb07c78e8295f96e62b8ab1c2838

        this.ruleName=ruleName;
        this.trigger = trigger;
        this.action = action;
<<<<<<< HEAD
        this.isActive= true;
        //this.timeDuration= timeDuration;
        RuleManager ruleManager=RuleManager.getInstance();
        ruleManager.addRule(this);

=======
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
    //Costruttore vuoto per i test
    
>>>>>>> f8dd2d94e07cdb07c78e8295f96e62b8ab1c2838

        
        
    }

    /*method get on the rule's state */
    public boolean isActive(){

<<<<<<< HEAD
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
=======
}
>>>>>>> f8dd2d94e07cdb07c78e8295f96e62b8ab1c2838
