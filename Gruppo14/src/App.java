import ActionFolder.*;
import CounterFolder.*;
import RuleFolder.Rule;
import RuleFolder.RuleManager;
import TriggerFolder.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RuleManager rules= RuleManager.getInstance();
        Map<String,Trigger> triggers= new HashMap<>();
        Map<String,Action> actions= new HashMap<>();
        MapCounter mapCounter = MapCounter.getInstance(); 
        Map<String, CompositeAction> actionSets = new HashMap<>();
        while (true) {
        
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║                Rule Management System            ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║ 1. Create a Rule                                 ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 2. Create a Periodical Rule                      ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 3. Create a Trigger                              ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 4. Create an Action                              ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 5. View existing rules                           ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 6. Remove a Rule                                 ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 7. Modify Rule                                   ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 8. Save set of Rules to File                     ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 9. Create a Counter                              ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 10. Work on set of Action                        ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 11. Create logical triggers                      ║");
            System.out.println("║                                                  ║");
            System.out.println("║ 12. Exit                                         ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.println("\n╔═════════════════════════════════════════════════════════════════╗");
            System.out.print("║  Enter the number corresponding to your choice and press Enter  ║");
            System.out.println("\n╚═════════════════════════════════════════════════════════════════╝");
            
           try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consumes the newline left in the buffer

                switch (choice) {
                    case 1:
                        Rule rule = createRule(triggers, actions, rules, scanner);
                        break;
                    case 2:
                        Rule rule2 = createPeriodicRule(triggers, actions, rules, scanner);
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
                        modifyRule(rules,actions,triggers, scanner);
                        
                    case 8:
                        rules.saveRulesToFile();
                        break;
                    case 9:
                        createCounter(scanner, mapCounter);
                        break;

                    case 10:
                        createSetOfAction(actions, scanner);
                        break;

                    case 11:
                        createLogicalTrigger(triggers, scanner);
                        break;

                    case 12:
                        System.out.println("Bye!");
                        rules.getRuleList().clear();
                        rules.shutdown();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice, please retry");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  // Consumes the invalid input to avoid an infinite loop
            }
        }
    }


    private static void modifyRule(RuleManager rules, Map<String,Action> actions, Map<String, Trigger> triggers, Scanner scanner) {

            boolean isRulePresent=false;
            String ruleName;
            while(true){
                System.out.println("\n╔════════════════════════════════════════════════════╗");
                System.out.println("║                     Modify Rule Menu               ║");
                System.out.println("╠════════════════════════════════════════════════════╣");
                System.out.println("║ 1. Activate a rule                                 ║");
                System.out.println("║ 2. Deactivate a rule                               ║");
                System.out.println("║ 3. Change the action                               ║");
                System.out.println("║ 4. Change the trigger                              ║");
                System.out.println("║ 5. Exit                                            ║");
                System.out.println("╚════════════════════════════════════════════════════╝");
                int choice;
                if(scanner.hasNextInt()){

                    choice=scanner.nextInt();
                    scanner.nextLine();

                } 
                else{
                    System.out.println("Error: invalid input");
                    scanner.nextLine();
                    choice=6;
                }

                switch(choice){
                    case 1:
                        activateRuleByName(rules, scanner);
                        break;
                    case 2:
                        deactivateRuleByName(rules, scanner);
                        break;
                    case 3:
                        displayRules(rules);
                        System.out.println("Which rule do you want to change?");
                        ruleName=scanner.nextLine();
                        while(!isRulePresent){
                            for(Rule r : rules.getRuleList()){
                                if(ruleName.equals(r.getRuleName()))
                                    isRulePresent=true;
                            }

                            if(!isRulePresent){
                                System.out.println("not valid rule name, please insert a different one or press 0 to come back to the modify rule menu");
                                ruleName=scanner.nextLine();

                                try{
                                    if(Integer.parseInt(ruleName)==0){
                                        break;
                                    }
                                }catch(NumberFormatException e){

                                }
                            }
                        }

                        displayActions(actions);
                        boolean isActionPresent=false;
                        String actionName;
                        System.out.println("Which action do you want to use for this rule?");
                        actionName=scanner.nextLine();
                        while(!isActionPresent){
                            if(actions.get(actionName)==null){
                                System.out.println("Not valid action name, please insert a different one or press 0 to come back to the modify rule menu");
                                actionName=scanner.nextLine();
                            
                                try{
                                    if(Integer.parseInt(actionName)==0){
                                        break;
                                    }
                                }catch(NumberFormatException e){

                                }
                            }

                            else
                                isActionPresent=true;

                        }

                        for(Rule r : rules.getRuleList()){

                            if(ruleName.equals(r.getRuleName()))
                                r.setAction(actions.get(actionName));
                        }

                        break;
                    case 4:
                        displayRules(rules);
                        System.out.println("Which rule do you want to change?");
                        ruleName=scanner.nextLine();
                        while(!isRulePresent){
                            for(Rule r : rules.getRuleList()){
                                if(ruleName.equals(r.getRuleName()))
                                    isRulePresent=true;
                            }

                            if(!isRulePresent){
                                System.out.println("not valid rule name, please insert a different one or press 0 to come back to the modify rule menu");
                                ruleName=scanner.nextLine();

                                try{
                                    if(Integer.parseInt(ruleName)==0){
                                        break;
                                    }
                                }catch(NumberFormatException e){

                                }
                            }
                        }

                        displayTriggers(triggers);
                        boolean isTriggerPresent=false;
                        String triggerName;
                        System.out.println("Which trigger do you want to use for this rule?");
                        triggerName=scanner.nextLine();
                        while(!isTriggerPresent){
                            if(triggers.get(triggerName)==null){
                                System.out.println("Not valid trigger name, please insert a different one or press 0 to come back to the modify rule menu");
                                triggerName=scanner.nextLine();
                            
                                try{
                                    if(Integer.parseInt(triggerName)==0){
                                        break;
                                    }
                                }catch(NumberFormatException e){

                                }
                            }

                            else
                                isTriggerPresent=true;

                        }

                        for(Rule r : rules.getRuleList()){

                            if(ruleName.equals(r.getRuleName()))
                                r.setTrigger(triggers.get(triggerName));
                        }
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Invalid choice, please retry");

                }
            }
    }


    private static Rule createRule(Map<String, Trigger> triggers, Map<String, Action> actions, RuleManager rules, Scanner scanner) {
        
        System.out.println("Enter rule's name");
        String ruleName = scanner.nextLine();

        while(ruleName.trim().isEmpty()){
            
            System.out.println("You didn't insert a rule name. Please insert a correct one or press 0 to come back to the main menu ");
            ruleName= scanner.nextLine();

            try{
                if(Integer.parseInt(ruleName)==0)
                return null;
            }catch(NumberFormatException e){
                
            }

        }

        for(Rule r: rules.getRuleList()){

            while(r.getRuleName().equals(ruleName)){
            
                System.out.println("This rulename already exists, insert a different one or press ' to come back to the main menu");
                ruleName=scanner.nextLine();
            
                try{
                    if(Integer.parseInt(ruleName)==0)
                    return null;
                }catch(NumberFormatException e){
                    
                }
            }


        }

        // print of the avaible triggers
        Set<String> setTriggerKey = triggers.keySet();
        StringBuilder triggerStringSet = new StringBuilder();

        
        for (String chiave : setTriggerKey) {
            triggerStringSet.append(chiave).append(", ");
        }

        // Rimuovere l'ultima virgola e spazio in eccesso
        if (triggerStringSet.length() > 0) {
            triggerStringSet.setLength(triggerStringSet.length() - 2);
        }

        // Stampare le chiavi concatenate
        System.out.println("Avaible Triggers: " + triggerStringSet.toString());
    
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
        
        // print of the avaible action
          Set<String> setActionKey = actions.keySet();
          StringBuilder actionStringSet  = new StringBuilder();

        // Iterare sul set delle chiavi e concatenarle nella stringa
        for (String chiave : setActionKey) {
            actionStringSet.append(chiave).append(", ");
        }

        // Rimuovere l'ultima virgola e spazio in eccesso
        if (actionStringSet.length() > 0) {
            actionStringSet.setLength(actionStringSet.length() - 2);
        }
        System.out.println("Avaible Actions: " + actionStringSet.toString());
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

    
    private static Rule createPeriodicRule(Map<String, Trigger> triggers, Map<String, Action> actions, RuleManager rules, Scanner scanner) {
        System.out.println("Enter rule's name");
        String ruleName = scanner.nextLine();
        
        while(ruleName.trim().isEmpty()){
            
            System.out.println("You didn't insert a correct rule name. Please insert a correct one or press 0 to come back to the main menu ");
            ruleName= scanner.nextLine();

            try{
                if(Integer.parseInt(ruleName)==0)
                return null;
            }catch(NumberFormatException e){
                
            }

        }

        for(Rule r: rules.getRuleList()){

            while(r.getRuleName().equals(ruleName)){
                
                System.out.println("This rulename already exists, insert a different one or press 0 to come back to the main menu");
                ruleName=scanner.nextLine();
            
                try{
                    if(Integer.parseInt(ruleName)==0)
                    return null;
                }catch(NumberFormatException e){
                    
                }
            }
        }
    
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
        Trigger t=null;

        while(name.trim().isEmpty()){
            
            System.out.println("You didn't insert a trigger name. Please insert a correct one or press 0 to come back to the main menu ");
            name= scanner.nextLine();

            try{
                if(Integer.parseInt(name)==0)
                return ;
            }catch(NumberFormatException e){
                
            }
        }
    
        boolean validInput = false;
        while(triggers.containsKey(name)){

            System.out.println("This trigger name already exists, please insert a different one or press 0 to come back to the main menu");
            name=scanner.nextLine();

            try{
                if(Integer.parseInt(name)==0)
                return ;
            }catch(NumberFormatException e){
                
            }

        }

        while (!validInput && !triggers.containsKey(name)) {
                System.out.println("Which type of trigger do you want to create?");
                System.out.println(" ");
                System.out.println("1. HourOfDayTrigger");
                System.out.println(" ");
                System.out.println("2. DateTrigger");
                System.out.println(" ");
                System.out.println("3. DayOfMonthTrigger");
                System.out.println(" ");
                System.out.println("4. DayOfWeekTrigger");
                System.out.println(" ");
                System.out.println("5. ExternalProgramTrigger");
                System.out.println(" ");
                System.out.println("6. FileSizeTrigger");
                System.out.println(" ");
                System.out.println("7. FileDirecotryTrigger");
                System.out.println("---------------------------------------");
                System.out.print("Please enter the number of your choice: ");
           
            int choice= scanner.nextInt();
            scanner.nextLine();
            try{
                switch(choice){
                
                    case 1:
                        System.out.println("Insert trigger's hour");
                        while(!scanner.hasNextInt()){
                            
                            if(!scanner.hasNext())
                            System.out.println("You didn't insert anything. Please insert a valid hour");

                            else
                            System.out.println(scanner.nextLine()+" is an incorrect hour format. Please retry");
                        }
                        
                        int hour= scanner.nextInt();
                        scanner.nextLine();
                        
                
            
                        System.out.println("Insert trigger's minute");
                        while(!scanner.hasNextInt()){
                            if(!scanner.hasNext())
                            System.out.println("You didn't insert anything. Please insert a valid minute");

                            else
                            System.out.println(scanner.nextLine()+" is an incorrect minute format. Please retry");
                        }

                        int minute= scanner.nextInt();
                        scanner.nextLine();
            
                        t = new HourOfDayTrigger(hour, minute);
                        validInput = true;
                        System.out.println("Trigger successfully created");
                        break;

                    case 2:

                        System.out.println("Insert a date in the format dd-mm-yyyy");
                        String date=scanner.nextLine();

                        t= new DateTrigger(date);
                        validInput=true;
                        System.out.println("Trigger successfully created");
                        break;

                    case 3:

                        System.out.println("Insert a day of month");
                        int targetDayOfmonth= scanner.nextInt();
                        scanner.nextLine();

                        t= new DayOfMonthTrigger(targetDayOfmonth);
                        validInput=true;
                        System.out.println("Trigger successfully created");
                        break;

                    case 4:

                        System.out.println("Insert a day of week");
                        String targetDayOfWeek= scanner.nextLine();

                        t= new DayOfWeekTrigger(targetDayOfWeek);
                        validInput=true;
                        System.out.println("Trigger successfully created");
                        break;

                    case 5:
                            System.out.println("Insert the command to do to run the program:");
                            String command = scanner.nextLine();
                            System.out.println("Insert the path of the program that you want to run");
                            String path= scanner.nextLine();
                             System.out.println("How many command line arguments do you want to pass?");
                        while(!scanner.hasNextInt()){
                            scanner.nextLine();
                            System.out.println("Error: You have to insert a number!");
                        }
                        int n= scanner.nextInt();
                        scanner.nextLine();
                        if ( n != 0){
                        System.out.println("Insert your command line arguments");}
                        String[] args= new String[n];
                        for(int i=0; i<n; i++){
                            args[i]=scanner.nextLine();
                        }
                        System.out.println("Enter the output value of the expected external program:");
                            int targetExitValue= scanner.nextInt();
                        t= new ExternalProgramTrigger(targetExitValue, command, path, args );
                        validInput= true;
                        System.out.println("Trigger successfully created");
                        break;

                        case 6:
                            System.out.println("Insert the file path for size control:");
                            String filePath = scanner.nextLine();
                            System.out.println("Insert the target size in KB of the fike:");
                            int targetSiize = scanner.nextInt();
                            t = new FileSizeTrigger(filePath, targetSiize);
                            validInput=true;
                            System.out.println("Trigger successfully created");
                            break;

                            case 7: 
                            System.out.println("Insert the file name to check if it exists in a direcotry:");
                            String fileName = scanner.nextLine();
                            System.out.println("Insert the path of the directory:");
                            String targetDirectory = scanner.nextLine();
                            t = new FileDirectoryTrigger(fileName, targetDirectory);
                            validInput=true;
                            System.out.println("Trigger successfully created");
                            break;

                    default:
                         System.out.println("Invalid choice, retry");
                }

                if (t != null) {
                    triggers.putIfAbsent(name, t);
                }
            }catch (Exception e) {
                System.out.println("Error creating trigger: " + e.getMessage()+" Press enter to try again or 0 to come back to the main menu");
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
        while(name.trim().isEmpty()){
            
            System.out.println("You didn't insert an action name. Please insert a correct one or press 0 to come back to the main menu ");
            name= scanner.nextLine();

            try{
                if(Integer.parseInt(name)==0)
                return ;
            }catch(NumberFormatException e){
                
            }

        }
    
        System.out.println("Which type of action do you want to create?");
        System.out.println(" ");
        System.out.println("1. AudioAction");
        System.out.println(" ");
        System.out.println("2. DialogBoxAction");
        System.out.println(" ");
        System.out.println("3. ExternalProgramAction");
        System.out.println(" ");
        System.out.println("4. WriteStringOnFileAction");
        System.out.println(" ");
        System.out.println("5. MoveCopyFileAction");
        System.out.println(" ");
        System.out.println("6. DeleteFileAction");
        System.out.println("---------------------------------------");
        System.out.print("Please enter the number of your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
    
        Action createdAction = null;
        boolean validInput=false;

        while(actions.containsKey(name)){

            System.out.println("This action name already exists, please insert a different one or press 0 to come back to the main menu");
            name=scanner.nextLine();

            try{
                if(Integer.parseInt(name)==0)
                return ;
            }catch(NumberFormatException e){
                
            }

        }
    
        while(!validInput && !actions.containsKey(name)){
            try {
                switch (choice) {
                    case 1:
                        System.out.println("Insert the audio's path");
                        String path = scanner.nextLine();
                        createdAction = new AudioAction(path);
                        validInput=true;
                        System.out.println("Action  successfully created");
                        break;
        
                    case 2:
                        System.out.println("Insert the message");
                        String message = scanner.nextLine();
                        createdAction = new DialogBoxAction(message);
                        validInput=true;
                        System.out.println("Action  successfully created");
                        break;
                    case 3:
                        System.out.println("Insert the path of the program that you want to run");
                        String path1= scanner.nextLine();    
                    System.out.println("Insert the command to do to run the program");
                        String command= scanner.nextLine();
                        

                        System.out.println("How many command line arguments do you want to pass?");
                        while(!scanner.hasNextInt()){
                            scanner.nextLine();
                            System.out.println("Error: You have to insert a number!");
                        }
                        int n= scanner.nextInt();
                        scanner.nextLine();
                        if (n != 0)
                            System.out.println("Insert your command line arguments");
                        String[] args= new String[n];
                        for(int i=0; i<n; i++){
                            args[i]=scanner.nextLine();
                        }
                        createdAction= new ExternalProgramAction(command, path1, args);
                        validInput=true;
                        System.out.println("Action  successfully created");
                        break;
                        
                        case 4:
                         System.out.println("Insert the string you want to write");
                         String stringToWrite = scanner.nextLine();
                         System.out.println("Insert the path to the file you want the string to be written to");
                         String targetFilePath = scanner.nextLine();
                         createdAction = new WriteStringOnFileAction(targetFilePath,stringToWrite);
                         validInput=true;
                         System.out.println("Action  successfully created");
                         break;

                         case 5:
                       
                          System.out.println("Insert the operation you want to perform (Move or Copy)");
                          String operation = scanner.nextLine();
                          System.out.println("Insert the name of the file on which the operation is to be performed");
                          String targetFileName = scanner.nextLine();
                          System.out.println("Insert the path of the source directory");
                          String targetDirectory = scanner.nextLine();
                          System.out.println("Insert the path of the destination directory");
                          String destinationDirectory = scanner.nextLine();
                      
                            if (operation.equalsIgnoreCase("MOVE")) {
                                createdAction = new MoveCopyFileAction(OperationFileType.MOVE, targetFileName, targetDirectory, destinationDirectory);
                            } else if (operation.equalsIgnoreCase("COPY")) {
                                createdAction = new MoveCopyFileAction(OperationFileType.COPY, targetFileName, targetDirectory, destinationDirectory);
                            } else {
                                throw new IllegalArgumentException("Error: You have to insert Move or Copy");
                            }
              
                    validInput = true;
                    System.out.println("Action  successfully created");
                    break;

                        case 6:
                        System.out.println("Insert the path of the source directory");
                        String targetDirectoryForDelete = scanner.nextLine();
                        System.out.println("Insert the name of the file you want to delete");
                        String targetFileNameToDelete = scanner.nextLine();
                        createdAction = new DeleteFileAction( targetDirectoryForDelete, targetFileNameToDelete);
                        
                        validInput=true;
                        System.out.println("Action  successfully created");
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


    private static void createSetOfAction(Map<String, Action> actions, Scanner scanner) {
        System.out.println("Enter the name for the Composite Action:");
        String compositeActionName = scanner.nextLine();
        CompositeAction compositeAction = (CompositeAction) actions.get(compositeActionName);
    
        // If the set does not exist, create a new one
        if (compositeAction == null) {
            compositeAction = new CompositeAction();
            System.out.println("Composite Action with the given name does not exist. Creating a new one.");
        }
    
        boolean validInput = false;
      
        while (!validInput) {
            try {
                System.out.println("\n╔════════════════════════════════════════════════════╗");
                System.out.println("║              Create a Set of Actions               ║");
                System.out.println("╠════════════════════════════════════════════════════╣");
                System.out.println("║ 1. Add Action to Set                               ║");
                System.out.println("║ 2. Finish and Save                                 ║");
                System.out.println("║ 3. Exit                                            ║");
                System.out.println("╚════════════════════════════════════════════════════╝");
    
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consumes the newline left in the buffer
    
                switch (choice) {
                    case 1:
                    // Ask how many actions to add
                    System.out.println("How many actions do you want to add to the set?");
                    int numActionsToAdd = scanner.nextInt();
                    scanner.nextLine();  // Consumes the newline left in the buffer
                    displayActions(actions);
                    // Add the specified number of actions
                    for (int i = 0; i < numActionsToAdd; i++) {
                        System.out.println("Enter the name of the existing action to add to the set:");
                        String existingActionName = scanner.nextLine();
                
                        Action existingAction = actions.get(existingActionName);
                
                        if (existingAction != null) {
                            compositeAction.addAction(existingAction);
                            System.out.println("Existing action added to the set.");
                        } else {
                            // Action with the given name does not exist
                            System.out.println("Action with the given name does not exist.");
                            
                            // Ask the user to enter the action name again
                            i--; // Decrement i so that the same index is processed again
                        }
                    }
                    break;
                  
                    case 2:
                        // Exit the set of actions creation and save to the actions map
                        actions.put(compositeActionName, compositeAction);
                        System.out.println("Set of actions '" + compositeActionName + "' successfully created and saved.");
                        validInput = true;
                        break;

                    case 3:
                     validInput= true;
                    break;

                    default:
                        System.out.println("Invalid choice, please retry");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  // Consumes the invalid input to avoid an infinite loop
            }
        }
    }
     
    
    private static void displayRules(RuleManager rules) {

        List<Rule> ruleList = rules.getRuleList();

        if (ruleList.isEmpty()) {
            System.out.println("No rule present");
        } else {
            System.out.print("Existing rules:");
            StringJoiner ruleNames = new StringJoiner(", ");
            for (Rule rule : ruleList) {
                ruleNames.add(rule.getRuleName());
            }
            System.out.println(ruleNames);
        }
    }

    
    private static void removeRule(RuleManager rules, Scanner scanner){
        displayRules(rules);
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
        List<Rule> ruleList = rules.getRuleList();

        if (ruleList.isEmpty()) {
            System.out.println("No rule present");
        } 
        else {
            System.out.print("Rules Inactive: ");
            StringJoiner ruleNames = new StringJoiner(", ");
        for (Rule rule : ruleList) {
            if (!rule.isActive())
                ruleNames.add(rule.getRuleName());
        }
        System.out.println(ruleNames);
        }  
       
       
       
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
          List<Rule> ruleList = rules.getRuleList();

        if (ruleList.isEmpty()) {
            System.out.println("No rule present");
        } 
        else {
            System.out.print("Rules Active: ");
            StringJoiner ruleNames = new StringJoiner(", ");
        for (Rule rule : ruleList) {
            if (rule.isActive())
                ruleNames.add(rule.getRuleName());
        }
        System.out.println(ruleNames);
        }  

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


    private static void createCounter(Scanner scanner, MapCounter mapCounter) {
        System.out.println("Insert counter name:");
        String name = scanner.nextLine();
         // Validate counter name
    if (name.trim().isEmpty()) {
        System.out.println("Error: Counter name cannot be empty. Please retry.");
        return;
    }

    // Check if the counter name already exists
    try {
        mapCounter.getCounterValue(name);
        System.out.println("Error: Counter with the name '" + name + "' already exists. Please choose a different name.");
        return;
    } catch (IllegalArgumentException e) {
        // Counter does not exist, continue
    }

        System.out.println("Insert " + name + " value:");

        // Validate initial value
        int initialValue;
        try {
            initialValue = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid initial value. Please enter a valid integer.");
            return;
        }
    
        // Chiamata al metodo createCounter della tua istanza di MapCounter
        mapCounter.createCounter(name, initialValue);
    
        System.out.println("Counter created successfully.");
    }

    
    private static void displayActions(Map<String, Action> actions){

        Set<String> setActionKey= actions.keySet();
        StringBuilder actionStringSet  = new StringBuilder();
        for (String chiave : setActionKey) {
            actionStringSet.append(chiave).append(", ");
        }

        // Rimuovere l'ultima virgola e spazio in eccesso
        if (actionStringSet.length() > 0) {
            actionStringSet.setLength(actionStringSet.length() - 2);
        }
        System.out.println("Avaible Actions: " + actionStringSet.toString());
    }
    
    
    private static void displayTriggers(Map<String, Trigger> triggers){

        Set<String> setTriggerKey= triggers.keySet();
        StringBuilder triggerStringSet  = new StringBuilder();
        for (String chiave : setTriggerKey) {
            triggerStringSet.append(chiave).append(", ");
        }

        // Rimuovere l'ultima virgola e spazio in eccesso
        if (triggerStringSet.length() > 0) {
            triggerStringSet.setLength(triggerStringSet.length() - 2);
        }
        System.out.println("Avaible Triggers: " + triggerStringSet.toString());
    }
    

    private static void createLogicalTrigger(Map<String, Trigger> triggers, Scanner scanner){
        System.out.println("Enter the name for the Logical Trigger:");
        String compositeTriggerName = scanner.nextLine();
      
        while (true) {
            try {
                System.out.println("\n╔════════════════════════════════════════════════════╗");
                System.out.println("║              Create a Logical Trigger              ║");
                System.out.println("╠════════════════════════════════════════════════════╣");
                System.out.println("║ 1. Create an \"or\" trigger                          ║");
                System.out.println("║ 2. Create an \"and\" trigger                         ║");
                System.out.println("║ 3. Create a \"not\" trigger                          ║");
                System.out.println("║ 4. Save and exit                                     ║");
                System.out.println("╚════════════════════════════════════════════════════╝");
                
                boolean isTrigger1Present=false;
                boolean isTrigger2Present=false;
                String triggerName1;
                Trigger trigger1=null;
                String triggerName2;
                Trigger trigger2=null;
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consumes the newline left in the buffer
    
                switch (choice) {
                    case 1:
                    displayTriggers(triggers);
                    
                    while(!isTrigger1Present && !isTrigger2Present){
                        System.out.println("Insert the first trigger name");
                        triggerName1=scanner.nextLine();
                        trigger1=triggers.get(triggerName1);

                        if(trigger1==null){
                            while(trigger1==null){
                                System.out.println("Invalid trigger name, please retry or press 0 to come back to the menu");
                                triggerName1=scanner.nextLine();
                                trigger1=triggers.get(triggerName1);
                                
                                try{
                                    if(Integer.parseInt(triggerName1)==0){
                                        break;
                                    }
                                }catch(NumberFormatException e){

                                }
                            }
                        }
                        else{
                            System.out.println("Insert the second trigger name");
                            triggerName2=scanner.nextLine();
                            trigger2=triggers.get(triggerName2);
                            while(trigger2==null){
                                System.out.println("Invalid trigger name, please retry or press 0 to come back to the menu");
                                triggerName2=scanner.nextLine();
                                try{
                                    if(Integer.parseInt(triggerName2)==0){
                                        break;
                                    }
                                }catch(NumberFormatException e){
    
                                }
                            }
                        }
                    }

                    triggers.putIfAbsent(compositeTriggerName,new CompositeTrigger(trigger1, trigger2, LogicalOperation.valueOf("OR")));
                    break;
                
                    case 2:
                        displayTriggers(triggers);
                        
                        while(!isTrigger1Present && !isTrigger2Present){
                            System.out.println("Insert the first trigger name");
                            triggerName1=scanner.nextLine();
                            trigger1=triggers.get(triggerName1);

                            if(trigger1==null){
                                while(trigger1==null){
                                    System.out.println("Invalid trigger name, please retry or press 0 to come back to the menu");
                                    triggerName1=scanner.nextLine();
                                    trigger1=triggers.get(triggerName1);
                                    
                                    try{
                                        if(Integer.parseInt(triggerName1)==0){
                                            break;
                                        }
                                    }catch(NumberFormatException e){

                                    }
                                }
                            }
                            else{
                                System.out.println("Insert the second trigger name");
                                triggerName2=scanner.nextLine();
                                trigger2=triggers.get(triggerName2);
                                while(trigger2==null){
                                    System.out.println("Invalid trigger name, please retry or press 0 to come back to the menu");
                                    triggerName2=scanner.nextLine();
                                    try{
                                        if(Integer.parseInt(triggerName2)==0){
                                            break;
                                        }
                                    }catch(NumberFormatException e){
        
                                    }
                                }
                            }
                        }

                        triggers.putIfAbsent(compositeTriggerName,new CompositeTrigger(trigger1, trigger2, LogicalOperation.valueOf("AND")));
                        break;
                        

                    case 3:

                        displayTriggers(triggers);
                        System.out.println("On which of these triggers do you want to create the not trigger?");
                        triggerName1=scanner.nextLine();
                        trigger1=triggers.get(triggerName1);

                        while(trigger1==null){
                            System.out.println("Invalid trigger name, please retry or press 0 to come back to the menu");
                            triggerName1=scanner.nextLine();
                            trigger1=triggers.get(triggerName1);
                            
                            try{
                                if(Integer.parseInt(triggerName1)==0){
                                    break;
                                }
                            }catch(NumberFormatException e){

                            }

                        }
                        
                        triggers.putIfAbsent(compositeTriggerName,new CompositeTrigger(trigger1,LogicalOperation.valueOf("NOT")));
                        break;

                    default:
                        System.out.println("Invalid choice, please retry");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  // Consumes the invalid input to avoid an infinite loop
            }
        }
    }


}
