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
            System.out.println("2. Create a Periodical Rule (remember that you have to create a trigger and an action for this rule before creating it)");
            System.out.println("3. Create a Trigger");
            System.out.println("4. Create an Action");
            System.out.println("5. View existing rules");
            System.out.println("6. Remove a Rule");
            System.out.println("7. Activate Rule");
            System.out.println("8. Deactivate Rule");
            System.out.println("9. Save set of Rule");
            System.out.println("10. Exit");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    Rule rule = createRule(triggers, actions, scanner);
                    break;
                case 2:
                    Rule rule2 = createPeriodicRule(triggers, actions, scanner);
                    break;
                case 3:
                    createTrigger(triggers, scanner);
                    break;

                case 4:
                    createAction(actions, scanner);
                    break;

                case 5:
                    displayRules(rules);
                    break;

                case 6:
                    removeRule(rules, scanner);
                    break;

                case 7:
                    activateRuleByName(rules, scanner);
                    break;

                case 8:
                    deactivateRuleByName(rules, scanner);
                    break;
                case 9:
                    rules.saveRulesToFile();
                    break;
                case 10:
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

    private static Rule createRule(Map<String, Trigger> triggers, Map<String, Action> actions, Scanner scanner) {
        System.out.println("Enter rule's name");
        String ruleName = scanner.nextLine();
    
        System.out.println("Enter the name of the trigger you want to trigger the rule");
        String triggerName = scanner.nextLine();
        Trigger t = triggers.get(triggerName);
    
        System.out.println("Enter the name of the action you want to be performed");
        String actionName = scanner.nextLine();
        Action a = actions.get(actionName);

        System.out.println("Should the rule be executed only once? (y/n)");
        String response = scanner.nextLine().toLowerCase();
        boolean triggeredOnce = response.equals("y") || response.equals("yes");
    
        Rule r = null;
        try {
            r = new Rule(ruleName, t, a,triggeredOnce);
        } catch (Exception e) {
            System.out.println(e.getMessage()+"Please insert again");
            scanner.nextLine(); // Clear the buffer
        }
        return r;
    }

    private static Rule createPeriodicRule(Map<String, Trigger> triggers, Map<String, Action> actions, Scanner scanner) {
        System.out.println("Enter rule's name");
        String ruleName = scanner.nextLine();
    
        System.out.println("Enter the name of the trigger you want to trigger the rule");
        String triggerName = scanner.nextLine();
        Trigger t = triggers.get(triggerName);
    
        System.out.println("Enter the name of the action you want to be performed");
        String actionName = scanner.nextLine();
        Action a = actions.get(actionName);
    
        System.out.println("Enter the delay in days:");
        int days = scanner.nextInt();
    
        System.out.println("Enter the delay in hours:");
        int hours = scanner.nextInt();
    
        System.out.println("Enter the delay in minutes:");
        int minutes = scanner.nextInt();
    
        Rule r = null;
        try {
            r = new Rule(ruleName, t, a, days, hours, minutes);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Please insert again");
            scanner.nextLine(); // Clear the buffer
        }
        return r;
    }
    
    
    private static void createTrigger(Map<String, Trigger> triggers, Scanner scanner) {
        System.out.println("Insert trigger's name");
        String name = scanner.nextLine();
    
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Insert trigger's hour");
                int hour = scanner.nextInt();
    
                System.out.println("Insert trigger's minute");
                int minute = scanner.nextInt();
    
                scanner.nextLine(); // Consuma il carattere di nuova riga residuo
    
                Trigger t = new HourOfDayTrigger(hour, minute);
                triggers.putIfAbsent(name, t);
                validInput = true;
            } catch (Exception e) {
                System.out.println("Error creating trigger: " + e.getMessage()+"Press enter to try again.");
                scanner.nextLine(); // Pulisce il buffer
            }
        }
    }
    


    
    private static void createAction(Map<String, Action> actions, Scanner scanner) {
        System.out.println("Insert action's name");
        String name = scanner.nextLine();
    
        System.out.println("Which type of action do you want to create?");
        System.out.println("1. AudioAction");
        System.out.println("2. DialogBoxAction");
    
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
    
        Action createdAction = null;
    
        try {
            switch (choice) {
                case 1:
                    System.out.println("Insert the audio's path");
                    String path = scanner.nextLine();
                    createdAction = new AudioAction(path);
                    break;
    
                case 2:
                    System.out.println("Insert the message");
                    String message = scanner.nextLine();
                    createdAction = new DialogBoxAction(message);
                    break;
    
                default:
                    System.out.println("Invalid choice, retry");
            }
    
            if (createdAction != null) {
                actions.putIfAbsent(name, createdAction);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()+"Please insert again");
            scanner.nextLine(); 
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

    private static void activateRuleByName(RuleManager rules, Scanner scanner) {
        System.out.println("Enter the name of the rule you want to activate:");
        String name = scanner.nextLine();
        for(Rule r : rules.getRuleList()){
            if(name.equalsIgnoreCase(r.getRuleName()))
                r.activate();      
        }
    }

    private static void deactivateRuleByName(RuleManager rules, Scanner scanner) {
        System.out.println("Enter the name of the rule you want to deactivate:");
        String name = scanner.nextLine();
        for(Rule r : rules.getRuleList()){
            if(name.equalsIgnoreCase(r.getRuleName()))
                r.deactivate();      
        }
    }

}