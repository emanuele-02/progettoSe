import java.time.LocalTime;

import ActionFolder.*;
import TriggerFolder.*;

public class App {

    public static void main(String[] args) {
        // Creation of 2 actions
        ActionFolder.Action action = new DialogBoxAction("Pippo");
        ActionFolder.Action action2 = new AudioAction("TestAudioAction.wav");

        // Trigger creation (always True)
        LocalTime currentTime = LocalTime.now();
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();
        TriggerFolder.Trigger trigger = new HourOfDayTrigger(currentHour, currentMinute);
        
        // Creation of 2 Rules
        Rule rule = new Rule("Reminder", trigger, action);
        Rule rule2 = new Rule("Reminder2", trigger, action2);
        
        RuleManager ruleManager = RuleManager.getInstance();

        // View all set of rules
        System.out.println("Set of Rule:");
        ruleManager.getRuleList().forEach(r -> System.out.println(r.getRuleName()));

        // Wait 11s to allow the rules to be executed periodically.
        try {
            Thread.sleep(8000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Rules disactivation
        System.out.println("Rules deactivated......");
        rule.deactivate();
        rule2.deactivate();
        
        try {
            Thread.sleep(10000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Reactivating first rule
        System.out.println("Reactivation rule Reminder(Pippo)...");
        rule.activate();

        // 1 execution of rule 1
        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Rule deactivated again
        System.out.println("Deactivation rule Reminder(Pippo)...");
        rule.deactivate();

        //Remove rule 
        System.out.println("Remove rule Reminder(Pippo)...");
        ruleManager.removeRule(rule);


        // Set of rule after remove
        System.out.println("Set of rules after removal:");
        ruleManager.getRuleList().forEach(r -> System.out.println(r.getRuleName()));

        
        ruleManager.getRuleList().clear();
        ruleManager.shutdown();
    }
}
