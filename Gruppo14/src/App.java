import ActionFolder.*;
import TriggerFolder.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RuleManager rules= RuleManager.getInstance();
        Map<String,Trigger> triggers= new HashMap<>();
        Map<String,Action> actions= new HashMap<>();
        
        while (true) {
            System.out.println("1. Create a Rule (remember that you have to create a trigger and an action for this rule before creating it)");
            System.out.println("2. Create a Trigger");
            System.out.println("3. Create an Action");
            System.out.println("4. View existing rules");
            System.out.println("5. Remove a Rule");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    Rule rule = createRule(triggers, actions, scanner);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    rule.deactivate();
                    break;

                case 2:
                    createTrigger(triggers, scanner);
                    break;

                case 3:
                    createAction(actions, scanner);
                    break;

                case 4:
                    displayRules(rules);
                    break;

                case 5:
                    removeRule(rules, scanner);
                    break;

                case 6:
                    System.out.println("Bye!");
                    rules.getRuleList().clear();
                    rules.shutdown();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice, retry");
            }
        }
    }

    private static Rule createRule(Map<String,Trigger> triggers, Map<String,Action> actions, Scanner scanner) {

        System.out.println("Enter rule's name");
        String ruleName=scanner.nextLine();

        System.out.println("Enter the name of the trigger you want to trigger the rule");
        String triggerName = scanner.nextLine();
        Trigger t= triggers.get(triggerName);

        System.out.println("Enter the name of the action you want to be performed");
        String actionName = scanner.nextLine();
        Action a= actions.get(actionName);
    
        return new Rule(ruleName, t, a);
    }

    private static void createTrigger(Map<String, Trigger> triggers, Scanner scanner){

        System.out.println("Insert trigger's name");
        String name= scanner.nextLine();

        System.out.println("Insert trigger's hour");
        int hour= scanner.nextInt();
        scanner.nextLine();

        System.out.println("Insert trigger's minute");
        int minute= scanner.nextInt();
        scanner.nextLine();

        Trigger t= new HourOfDayTrigger(hour, minute);

        triggers.putIfAbsent(name, t);

    }

    private static void createAction (Map<String, Action> actions, Scanner scanner){

        System.out.println("Insert action's name");
        String name= scanner.nextLine();
        System.out.println("Which type of action do you want create?");
        System.out.println("1. AudioAction");
        System.out.println("2. DialogBoxAction");

        int choice= scanner.nextInt();
        scanner.nextLine();

        switch(choice){

            case 1:
                System.out.println("Insert the audio's path");
                String path= scanner.nextLine();
                Action audioAction= new AudioAction(path);
                actions.putIfAbsent(name, audioAction);
                break;

            case 2:
                System.out.println("Insert the message");
                String message= scanner.nextLine();
                Action dialogAction= new DialogBoxAction(message);
                actions.putIfAbsent(name, dialogAction);
                break;

            default:
                System.out.println("Invalid choice, retry");

        }

    }

    private static void displayRules(RuleManager rules) {

        List<Rule> ruleList= rules.getRuleList();

        if (ruleList.isEmpty()) 
            System.out.println("No rule present");
        
        else {

            System.out.println("Existing rules");
            for (Rule rule : ruleList) {
                System.out.println(rule.getRuleName());
            }

        }
    }

    private static void removeRule(RuleManager rules, Scanner scanner){

        System.out.println("Enter the name of the rule you wish to delete");
        String name=scanner.nextLine();

        for(Rule r : rules.getRuleList()){

            if(name.equalsIgnoreCase(r.getRuleName()))
                rules.removeRule(r, scanner);
                
        }

    }

    private static Rule createRule(Map<String,Trigger> triggers, Map<String,Action> actions, Scanner scanner) {

        System.out.println("Enter rule's name");
        String ruleName=scanner.nextLine();

        System.out.println("Enter the name of the trigger you want to trigger the rule");
        String triggerName = scanner.nextLine();
        Trigger t= triggers.get(triggerName);

        System.out.println("Enter the name of the action you want to be performed");
        String actionName = scanner.nextLine();
        Action a= actions.get(actionName);
    
        return new Rule(ruleName, t, a);
    }

    private static void createTrigger(Map<String, Trigger> triggers, Scanner scanner){

        System.out.println("Insert trigger's name");
        String name= scanner.nextLine();

        System.out.println("Insert trigger's hour");
        int hour= scanner.nextInt();
        scanner.nextLine();

        System.out.println("Insert trigger's minute");
        int minute= scanner.nextInt();
        scanner.nextLine();

        Trigger t= new HourOfDayTrigger(hour, minute);

        triggers.putIfAbsent(name, t);

    }

    private static void createAction (Map<String, Action> actions, Scanner scanner){

        System.out.println("Insert action's name");
        String name= scanner.nextLine();
        System.out.println("Which type of action do you want create?");
        System.out.println("1. AudioAction");
        System.out.println("2. DialogBoxAction");

        int choice= scanner.nextInt();
        scanner.nextLine();

        switch(choice){

            case 1:
                System.out.println("Insert the audio's path");
                String path= scanner.nextLine();
                Action audioAction= new AudioAction(path);
                actions.putIfAbsent(name, audioAction);
                break;

            case 2:
                System.out.println("Insert the message");
                String message= scanner.nextLine();
                Action dialogAction= new DialogBoxAction(message);
                actions.putIfAbsent(name, dialogAction);
                break;

            default:
                System.out.println("Invalid choice, retry");

        }

    }

    private static void displayRules(RuleManager rules) {

        List<Rule> ruleList= rules.getRuleList();

        if (ruleList.isEmpty()) 
            System.out.println("No rule present");
        
        else {

            System.out.println("Existing rules");
            for (Rule rule : ruleList) {
                System.out.println(rule.getRuleName());
            }

        }
    }

    private static void removeRule(RuleManager rules, Scanner scanner){

        System.out.println("Enter the name of the rule you wish to delete");
        String name=scanner.nextLine();

        for(Rule r : rules.getRuleList()){

            if(name.equalsIgnoreCase(r.getRuleName()))
                rules.removeRule(r, scanner);
                
        }

    }
}