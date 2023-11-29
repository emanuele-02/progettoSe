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
            System.out.println("6. Activate Rule");
            System.out.println("7. Deactivate Rule");
            System.out.println("8. Exit");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    Rule rule = createRule(triggers, actions, scanner);
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
                    activateRuleByName(rules, scanner);
                    break;

                case 7:
                    deactivateRuleByName(rules, scanner);
                    break;

                case 8:
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

        while(!triggers.containsKey(triggerName)){

            System.out.println("Invalid name. Insert a correct trigger name or create a new trigger. Press 0 to come back to the main menu");
            triggerName=scanner.nextLine();

            try{
                if(Integer.parseInt(triggerName)==0)
                return null;
            }catch(NumberFormatException e){
                
            }
        }

        Trigger t = triggers.get(triggerName);
        

        System.out.println("Enter the name of the action you want to be performed");
        String actionName = scanner.nextLine();

        while(!actions.containsKey(actionName)){

            System.out.println("Invalid name. Insert a correct action name or create a new action. Press 0 to come back to the main menu");
            actionName=scanner.nextLine();
            
            try{
                if(Integer.parseInt(actionName)==0)
                return null;
            }catch(NumberFormatException e){
                
            }

        }
        
        Action a = actions.get(actionName);
    
        return new Rule(ruleName, t, a);
    }
    
    private static void createTrigger(Map<String, Trigger> triggers, Scanner scanner) {
        System.out.println("Insert trigger's name");
        String name = scanner.nextLine();
    
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Insert trigger's hour");
                int hour = scanner.nextInt();
                scanner.nextLine();
    
                System.out.println("Insert trigger's minute");
                int minute = scanner.nextInt();
                scanner.nextLine();
    
    
                Trigger t = new HourOfDayTrigger(hour, minute);
                triggers.putIfAbsent(name, t);
                validInput = true;
            } catch (Exception e) {
                System.out.println("Error creating trigger: " + e.getMessage()+"Press enter to try again or 0 to come back to the main menu");
                String s= scanner.nextLine();
                try{
                    if(Integer.parseInt(s)==0)
                    return ;
                }catch(NumberFormatException exc){
                    
                }
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
        boolean validInput=false;
    
        while(!validInput){
            try {
                switch (choice) {
                    case 1:
                        System.out.println("Insert the audio's path");
                        String path = scanner.nextLine();
                        createdAction = new AudioAction(path);
                        validInput=true;
                        break;
        
                    case 2:
                        System.out.println("Insert the message");
                        String message = scanner.nextLine();
                        createdAction = new DialogBoxAction(message);
                        validInput=true;
                        break;
        
                    default:
                        System.out.println("Invalid choice, retry");
                }
        
                if (createdAction != null) {
                    actions.putIfAbsent(name, createdAction);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage()+"Press enter to try again or 0 to come back to the main menu");
                String s=scanner.nextLine(); 

                try{
                    if(Integer.parseInt(s)==0)
                    return ;
                }catch(NumberFormatException exc){
                    
                }
            }
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
        boolean isPresent=false;

        while(!isPresent){

            for(Rule r : rules.getRuleList()){

                if(name.equalsIgnoreCase(r.getRuleName())){
                    rules.removeRule(r, scanner);
                    isPresent=true;
                }
            }

            if(!isPresent){
                System.out.println("Rule not present, please insert a valid rule name or insert 0 to come back to the main menu");
                name=scanner.nextLine();

                try{
                    if(Integer.parseInt(name)==0)
                    return ;
                }catch(NumberFormatException e){
                    
                }
            }
        }
    
    }

    private static void activateRuleByName(RuleManager rules, Scanner scanner) {
        System.out.println("Enter the name of the rule you want to activate:");
        String name = scanner.nextLine();
        boolean isPresent=false;

        while(!isPresent){
            for(Rule r : rules.getRuleList()){
                if(name.equalsIgnoreCase(r.getRuleName())){
                    r.activate();  
                    isPresent=true;
                }
                
            }
            if(!isPresent){
                System.out.println("Rule not present, plese insert a valid rule name or press 0 to come back to the main menu");
                name=scanner.nextLine();

                try{
                    if(Integer.parseInt(name)==0)
                    return ;
                }catch(NumberFormatException e){
                    
                }
            }
        }
        
    }

    private static void deactivateRuleByName(RuleManager rules, Scanner scanner) {
        System.out.println("Enter the name of the rule you want to deactivate:");
        String name = scanner.nextLine();
        boolean isPresent= false;

        while(!isPresent){
            for(Rule r : rules.getRuleList()){
                if(name.equalsIgnoreCase(r.getRuleName())){
                    r.deactivate();  
                    isPresent=true;
                }
                
            }
            if(!isPresent){
                System.out.println("Rule not present, plese insert a valid rule name or press 0 to come back to the main menu");
                name=scanner.nextLine();

                try{
                    if(Integer.parseInt(name)==0)
                    return ;
                }catch(NumberFormatException e){
                    
                }
            }
        }
    }

}