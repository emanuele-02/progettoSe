import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

import ActionFolder.*;
import CounterFolder.MapCounter;
import RuleFolder.*;
import TriggerFolder.*;

public class UtilityRule {

    // Create a rule with check for correct argument
    public static Rule createRule(Map<String, Trigger> triggers, Map<String, Action> actions, RuleManager rules,
            Scanner scanner) {
        displayRules(rules);
        System.out.println("Enter rule's name");
        String ruleName = scanner.nextLine();

        while (ruleName.trim().isEmpty()) {
            System.out.println(
                    "You didn't insert a rule name. Please insert a correct one or press 0 to come back to the main menu ");
            ruleName = scanner.nextLine();

            try {
                if (Integer.parseInt(ruleName) == 0)
                    return null;
            } catch (NumberFormatException e) {

            }

        }

        for (Rule r : rules.getRuleList()) {

            while (r.getRuleName().equals(ruleName)) {
                System.out.println(
                        "This rulename already exists, insert a different one or press 0 to come back to the main menu");
                ruleName = scanner.nextLine();

                try {
                    if (Integer.parseInt(ruleName) == 0)
                        return null;
                } catch (NumberFormatException e) {

                }
            }

        }

        // print of the avaible triggers
        UtilityTrigger.displayTriggers(triggers);

        System.out.println("Enter the name of the trigger you want to trigger the rule");
        String triggerName = scanner.nextLine();

        triggerName = UtilityTrigger.checkTriggerPresent(triggers, scanner, triggerName);
        if (triggerName == null)
            return null;
        Trigger t = triggers.get(triggerName);

        // print of the avaible action
        UtilityAction.displayActions(actions);
        System.out.println("Enter the name of the action you want to be performed");
        String actionName = scanner.nextLine();
        actionName = UtilityAction.checkActionPresent(actions, scanner, actionName);
        if (actionName == null)
            return null;

        Action a = actions.get(actionName);

        System.out.println("Should the rule be executed only once? (y/n)");
        String response = scanner.nextLine().toLowerCase();
        boolean triggeredOnce = response.equals("y") || response.equals("yes");

        Rule r = null;
        try {
            r = new Rule(ruleName, t, a, triggeredOnce);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Please insert again");
            scanner.nextLine(); // Clear the buffer
        }
        return r;
    }

    // Create a periodic rule with check for correct argument
    public static Rule createPeriodicRule(Map<String, Trigger> triggers, Map<String, Action> actions, RuleManager rules,
            Scanner scanner) {
        displayRules(rules);
        System.out.println("Enter rule's name");
        String ruleName = scanner.nextLine();

        while (ruleName.trim().isEmpty()) {

            System.out.println(
                    "You didn't insert a correct rule name. Please insert a correct one or press 0 to come back to the main menu ");
            ruleName = scanner.nextLine();

            try {
                if (Integer.parseInt(ruleName) == 0)
                    return null;
            } catch (NumberFormatException e) {

            }

        }

        for (Rule r : rules.getRuleList()) {

            while (r.getRuleName().equals(ruleName)) {

                System.out.println(
                        "This rulename already exists, insert a different one or press 0 to come back to the main menu");
                ruleName = scanner.nextLine();

                try {
                    if (Integer.parseInt(ruleName) == 0)
                        return null;
                } catch (NumberFormatException e) {

                }
            }
        }
        UtilityTrigger.displayTriggers(triggers);
        System.out.println("Enter the name of the trigger you want to trigger the rule");
        String triggerName = scanner.nextLine();

        triggerName = UtilityTrigger.checkTriggerPresent(triggers, scanner, triggerName);
        if (triggerName == null)
            return null;
        Trigger t = triggers.get(triggerName);

        UtilityAction.displayActions(actions);
        System.out.println("Enter the name of the action you want to be performed");
        String actionName = scanner.nextLine();
        actionName = UtilityAction.checkActionPresent(actions, scanner, actionName);
        if (actionName == null)
            return null;
        Action a = actions.get(actionName);

        System.out.println("Enter the delay in days:");

        while (!scanner.hasNextInt()) {
            if (!scanner.hasNext())
                System.out.println("You didn't insert anything. Please insert a valid delay");

            else
                System.out.println(scanner.nextLine() + " is an incorrect delay format. Please retry");
        }

        int days = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the delay in hours:");

        while (!scanner.hasNextInt()) {
            if (!scanner.hasNext())
                System.out.println("You didn't insert anything. Please insert a valid delay");

            else
                System.out.println(scanner.nextLine() + " is an incorrect delay format. Please retry");
        }

        int hours = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the delay in minutes:");

        while (!scanner.hasNextInt()) {
            if (!scanner.hasNext())
                System.out.println("You didn't insert anything. Please insert a valid delay");

            else
                System.out.println(scanner.nextLine() + " is an incorrect delay format. Please retry");
        }

        int minutes = scanner.nextInt();
        scanner.nextLine();

        Rule r = null;
        try {
            r = new Rule(ruleName, t, a, days, hours, minutes);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Please insert again");
            scanner.nextLine(); // Clear the buffer
        }
        return r;
    }

    // Modify rule action or trigger , activate/deactivate rule
    public static void modifyRule(RuleManager rules, Map<String, Action> actions, Map<String, Trigger> triggers,
            Scanner scanner) {

        String ruleName;
        while (true) {
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
            if (scanner.hasNextInt()) {

                choice = scanner.nextInt();
                scanner.nextLine();

            } else {
                System.out.println("Error: invalid input");
                scanner.nextLine();
                choice = 6;
            }

            switch (choice) {
                case 1:
                    activateRuleByName(rules, scanner);
                    break;
                case 2:
                    deactivateRuleByName(rules, scanner);
                    break;
                case 3:
                    displayRules(rules);
                    System.out.println("Which rule do you want to change?");
                    ruleName = scanner.nextLine();
                    checkRulePresent(rules, scanner, ruleName);

                    UtilityAction.displayActions(actions);
                    String actionName;
                    System.out.println("Which action do you want to use for this rule?");
                    actionName = scanner.nextLine();
                    UtilityAction.checkActionPresent(actions, scanner, actionName);

                    for (Rule r : rules.getRuleList()) {
                        if (ruleName.equals(r.getRuleName()))
                            r.setAction(actions.get(actionName));
                    }
                    System.out.println("Rule successfully modified");
                    break;
                case 4:
                    displayRules(rules);
                    System.out.println("Which rule do you want to change?");
                    ruleName = scanner.nextLine();
                    checkRulePresent(rules, scanner, ruleName);

                    UtilityTrigger.displayTriggers(triggers);
                    String triggerName;
                    System.out.println("Which trigger do you want to use for this rule?");
                    triggerName = scanner.nextLine();
                    UtilityTrigger.checkTriggerPresent(triggers, scanner, triggerName);
                    for (Rule r : rules.getRuleList()) {
                        if (ruleName.equals(r.getRuleName()))
                            r.setTrigger(triggers.get(triggerName));
                    }
                     System.out.println("Rule successfully modified");
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice, please retry");

            }
        }
    }

    // Print the set of rule
    public static void displayRules(RuleManager rules) {

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

    // Remove rule with specified name
    public static void removeRule(RuleManager rules, Scanner scanner) {
        while (true) {
            displayRules(rules);
            System.out.println("Enter the name of the rule you wish to delete (enter 0 to return to the menu):");
            String name = scanner.nextLine();

            Rule rule = checkRulePresent(rules, scanner, name);
            if (rule == null) {
                return; // User wants to go back to the menu
            } else {
                rules.removeRule(rule, scanner);
                return;
            }
        }
    }

    // Activate rule with specified name + print inactive rules
    private static void activateRuleByName(RuleManager rules, Scanner scanner) {
        List<Rule> ruleList = rules.getRuleList();

        if (ruleList.isEmpty()) {
            System.out.println("No rule present");
        } else {
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
        Rule rule = checkRulePresent(rules, scanner, name);
        if (rule == null) {
            return;
        } else {
            rule.activate();
        }
    }

    // Deactivate rule with specifide name + print active rules
    private static void deactivateRuleByName(RuleManager rules, Scanner scanner) {
        List<Rule> ruleList = rules.getRuleList();

        if (ruleList.isEmpty()) {
            System.out.println("No rule present");
        } else {
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
        Rule rule = checkRulePresent(rules, scanner, name);
        if (rule == null) {
            return;
        } else {
            rule.deactivate();
        }
    }

    // create a counter + check input
    public static void createCounter(Scanner scanner, MapCounter mapCounter) {
        UtilityTrigger.displayCounters();
        while (true) {
            System.out.println("Insert counter name:");
            String name = scanner.nextLine().trim();
    
            // Validate counter name
            if (name.isEmpty()) {
                System.out.println("Error: Counter name cannot be empty. Please retry.");
                continue;
            }
    
            // Check if the counter name already exists
            try {
                mapCounter.getCounterValue(name);
                System.out.println(
                        "Error: Counter with the name '" + name + "' already exists. Please choose a different name.");
                continue;
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
                continue;
            }
    
            // Chiamata al metodo createCounter della tua istanza di MapCounter
            mapCounter.createCounter(name, initialValue);
    
            System.out.println("Counter created successfully.");
            break;  // Break out of the loop if everything is successful
        }
    }
    

    // check if the rule is present
    public static Rule checkRulePresent(RuleManager rules, Scanner scanner, String ruleName) {
        while (true) {
            if (ruleName.equals("0")) {
                System.out.println("Returning to the menu...");
                return null; // User wants to go back to the modify rule menu
            }

            for (Rule r : rules.getRuleList()) {
                if (ruleName.equals(r.getRuleName())) {
                    return r; // Rule found, return it
                }
            }

            System.out.println(
                    "Not a valid rule name. Please insert a different one or press 0 to go back to the modify rule menu");
            ruleName = scanner.nextLine();
        }
    }


}
