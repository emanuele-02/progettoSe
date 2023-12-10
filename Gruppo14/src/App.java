import ActionFolder.*;
import CounterFolder.*;
import RuleFolder.Rule;
import RuleFolder.RuleManager;
import TriggerFolder.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RuleManager rules = RuleManager.getInstance();
        Map<String, Trigger> triggers = new HashMap<>();
        Map<String, Action> actions = new HashMap<>();
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
                scanner.nextLine(); // Consumes the newline left in the buffer

                switch (choice) {
                    case 1:
                        if(triggers.isEmpty() || actions.isEmpty()){
                            System.out.println("There are no trigger or action, impossible to create a Rule");
                            break;
                        }
                        Rule rule = UtilityRule.createRule(triggers, actions, rules, scanner);
                        break;
                    case 2:
                        if(triggers.isEmpty() || actions.isEmpty()){
                            System.out.println("There are no trigger or action, impossible to create PeriodicRule");
                            break;
                        }
                        Rule rule2 = UtilityRule.createPeriodicRule(triggers, actions, rules, scanner);
                        break;
                    case 3:
                        UtilityTrigger.createTrigger(triggers, scanner);
                        break;
                    case 4:
                        UtilityAction.createAction(actions,scanner);
                        break;
                    case 5:
                        UtilityRule.displayRules(rules);
                        break;
                    case 6:
                        if(rules.getRuleList().isEmpty()){
                            System.out.println("There are no rules, impossible to do this.");
                            break;
                        }
                        UtilityRule.removeRule(rules, scanner);
                        break;
                    case 7:
                        if(rules.getRuleList().isEmpty()){
                            System.out.println("There are no rules, impossible to do this.");
                            break;
                        }
                        UtilityRule.modifyRule(rules, actions, triggers, scanner);
                        break;
                    case 8:
                        rules.saveRulesToFile();
                        break;
                    case 9:
                        UtilityRule.createCounter(scanner, mapCounter);
                        break;

                    case 10:
                        if(actions.isEmpty()){
                            System.out.println("There are no actions, impossible to do this.");
                            break;
                        }
                        UtilityAction.createSetOfAction(actions, scanner);
                        break;

                    case 11:
                        if(triggers.isEmpty()){
                            System.out.println("There are no triggers, impossible to do this.");
                            break;
                        }
                        UtilityTrigger.createLogicalTrigger(triggers, scanner);
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
                scanner.nextLine(); // Consumes the invalid input to avoid an infinite loop
            }
        }
    }
}
