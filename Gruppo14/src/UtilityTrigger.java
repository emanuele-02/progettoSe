import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import CounterFolder.MapCounter;
import TriggerFolder.*;

public class UtilityTrigger {

    public static void createTrigger(Map<String, Trigger> triggers, Scanner scanner) {
        displayTriggers(triggers);
        System.out.println("Insert trigger's name");
        String name = scanner.nextLine();
        Trigger t = null;

        while (name.trim().isEmpty()) {

            System.out.println(
                    "You didn't insert a trigger name. Please insert a correct one or press 0 to come back to the main menu ");
            name = scanner.nextLine();

            try {
                if (Integer.parseInt(name) == 0)
                    return;
            } catch (NumberFormatException e) {

            }
        }

        boolean validInput = false;
        while (triggers.containsKey(name)) {

            System.out.println(
                    "This trigger name already exists, please insert a different one or press 0 to come back to the main menu");
            name = scanner.nextLine();

            try {
                if (Integer.parseInt(name) == 0)
                    return;
            } catch (NumberFormatException e) {

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
            System.out.println(" ");
            System.out.println("8. TriggerCounter");
            System.out.println(" ");
            System.out.println("9. Return to menu");
            System.out.println("---------------------------------------");
            System.out.print("Please enter the number of your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            while (!validInput && !triggers.containsKey(name) && choice <= 9 && choice > 0) {
                try {
                    switch (choice) {

                        case 1:
                            System.out.println("Insert trigger's hour");
                            while (!scanner.hasNextInt()) {

                                if (!scanner.hasNext())
                                    System.out.println("You didn't insert anything. Please insert a valid hour");

                                else
                                    System.out
                                            .println(scanner.nextLine() + " is an incorrect hour format. Please retry");
                            }

                            int hour = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("Insert trigger's minute");
                            while (!scanner.hasNextInt()) {
                                if (!scanner.hasNext())
                                    System.out.println("You didn't insert anything. Please insert a valid minute");

                                else
                                    System.out.println(
                                            scanner.nextLine() + " is an incorrect minute format. Please retry");
                            }

                            int minute = scanner.nextInt();
                            scanner.nextLine();

                            t = new HourOfDayTrigger(hour, minute);
                            validInput = true;
                            System.out.println("Trigger successfully created");
                            break;

                        case 2:

                            System.out.println("Insert a date in the format dd-mm-yyyy");
                            String date = scanner.nextLine();

                            t = new DateTrigger(date);
                            validInput = true;
                            System.out.println("Trigger successfully created");
                            break;

                        case 3:

                            System.out.println("Insert a day of month");
                            int targetDayOfmonth = scanner.nextInt();
                            scanner.nextLine();

                            t = new DayOfMonthTrigger(targetDayOfmonth);
                            validInput = true;
                            System.out.println("Trigger successfully created");
                            break;

                        case 4:

                            System.out.println("Insert a day of week");
                            String targetDayOfWeek = scanner.nextLine();

                            t = new DayOfWeekTrigger(targetDayOfWeek);
                            validInput = true;
                            System.out.println("Trigger successfully created");
                            break;

                        case 5:
                            
                            System.out.println("Insert the command to do to run the program:");
                            String command = scanner.nextLine();
                            System.out.println("Insert the path of the program that you want to run");
                            String path = scanner.nextLine();
                            System.out.println("How many command line arguments do you want to pass?");
                            while (!scanner.hasNextInt()) {
                                scanner.nextLine();
                                System.out.println("Error: You have to insert a number!");
                            }
                            int n = scanner.nextInt();
                            scanner.nextLine();
                            if (n != 0) {
                                System.out.println("Insert your command line arguments");
                            }
                            String[] args = new String[n];
                            for (int i = 0; i < n; i++) {
                                args[i] = scanner.nextLine();
                            }
                            System.out.println("Enter the output value of the expected external program:");
                              while (!scanner.hasNextInt()) {
                                scanner.nextLine();
                                System.out.println("Error: You have to insert a number!");
                            }
                            int targetExitValue = scanner.nextInt();
                             scanner.nextLine();
                            t = new ExternalProgramTrigger(targetExitValue, command, path, args);
                            validInput = true;
                            System.out.println("Trigger successfully created");
                            
                            break;

                        case 6:
                            System.out.println("Insert the file path for size control:");
                            String filePath = scanner.nextLine();
                            System.out.println("Insert the target size in KB of the fike:");
                            int targetSiize = scanner.nextInt();
                            t = new FileSizeTrigger(filePath, targetSiize);
                            validInput = true;
                            System.out.println("Trigger successfully created");
                            break;

                        case 7:
                            System.out.println("Insert the file name to check if it exists in a direcotry:");
                            String fileName = scanner.nextLine();
                            System.out.println("Insert the path of the directory:");
                            String targetDirectory = scanner.nextLine();
                            t = new FileDirectoryTrigger(fileName, targetDirectory);
                            validInput = true;
                            System.out.println("Trigger successfully created");
                            break;

                        case 8:
                            createTriggerCounter(triggers, name, scanner); // Add this line
                            System.out.println("TriggerCounter successfully created"); // Add this line
                            validInput = true; // Add this line
                            break;

                        case 9:
                            validInput = true;
                            return;
                        default:
                            System.out.println("Invalid choice, retry");

                    }

                    if (t != null) {
                        triggers.putIfAbsent(name, t);
                    }
                } catch (Exception e) {
                    System.out.println("Error creating trigger: " + e.getMessage()
                            + " Press enter to try again or 0 to come back to the main menu");
                    String s = scanner.nextLine();
                    try {
                        if (Integer.parseInt(s) == 0)
                            return;
                    } catch (NumberFormatException exc) {

                    }
                }

            }
        }
    }

    // Create a logical trigger by choice
    public static void createLogicalTrigger(Map<String, Trigger> triggers, Scanner scanner) {
        System.out.println("Enter the name for the Logical Trigger:");
        String compositeTriggerName = scanner.nextLine();

        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("╔════════════════════════════════════════════════════╗");
                System.out.println("║              Create a Logical Trigger              ║");
                System.out.println("╠════════════════════════════════════════════════════╣");
                System.out.println("║ 1. Create an \"or\" trigger                          ║");
                System.out.println("║ 2. Create an \"and\" trigger                         ║");
                System.out.println("║ 3. Create a \"not\" trigger                          ║");
                System.out.println("║ 4. Save and exit                                   ║");
                System.out.println("╚════════════════════════════════════════════════════╝");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consumes the newline left in the buffer

                switch (choice) {
                    case 1:
                        createLogicalOperationTrigger(triggers, compositeTriggerName, LogicalOperation.OR, scanner);
                        break;
                    case 2:
                        createLogicalOperationTrigger(triggers, compositeTriggerName, LogicalOperation.AND, scanner);
                        break;
                    case 3:
                        createNotTrigger(triggers, compositeTriggerName, scanner);
                        break;
                    case 4:
                        validInput = true;
                        return;
                    default:
                        System.out.println("Invalid choice, retry");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consumes the invalid input to avoid an infinite loop

            }
        }
    }

    // method too create a LogicalTrigger ( and/or )
    private static void createLogicalOperationTrigger(Map<String, Trigger> triggers, String compositeTriggerName,
            LogicalOperation operation, Scanner scanner) {
        displayTriggers(triggers);

        System.out.println("Insert the first trigger name");
        String triggerName1 = scanner.nextLine();
        Trigger trigger1 = getValidTrigger(triggers, triggerName1, scanner);
        if (trigger1 == null)
            return;
        System.out.println("Insert the second trigger name");
        String triggerName2 = scanner.nextLine();
        Trigger trigger2 = getValidTrigger(triggers, triggerName2, scanner);
        if (trigger2 == null)
            return;
        triggers.putIfAbsent(compositeTriggerName, new CompositeTrigger(trigger1, trigger2, operation));
        System.out.println("Logical Trigger successfully created");
    }

    // method too create a LogicalTrigger ( not )
    private static void createNotTrigger(Map<String, Trigger> triggers, String compositeTriggerName, Scanner scanner) {
        displayTriggers(triggers);

        System.out.println("Insert the trigger name to negate");
        String triggerName = scanner.nextLine();
        Trigger triggerToNegate = getValidTrigger(triggers, triggerName, scanner);
        if (triggerToNegate == null)
            return;
        triggers.putIfAbsent(compositeTriggerName, new CompositeTrigger(triggerToNegate, LogicalOperation.NOT));
        System.out.println("Logical Trigger successfully created");
    }

    // Method to create Counter Trigger
    private static void createTriggerCounter(Map<String, Trigger> triggers, String name, Scanner scanner) {
        System.out.println("Choose the operation for TriggerCounter:");
        System.out.println("");
        System.out.println("1. EQUAL TO");
        System.out.println("");
        System.out.println("2. LESS THAN");
        System.out.println("");
        System.out.println("3. GREATE RTHAN");
        System.out.println("-----------------------------------------");
        System.out.print("Please enter the number of your choice: ");

        int operationChoice = scanner.nextInt();
        scanner.nextLine();

        OperationType operation = null;
        switch (operationChoice) {
            case 1:
                operation = OperationType.EQUALTO;
                break;
            case 2:
                operation = OperationType.LESSTHAN;
                break;
            case 3:
                operation = OperationType.GREATERTHAN;
                break;
            default:
                System.out.println("Invalid choice for operation. TriggerCounter creation failed.");
                return;
        }
        displayCounters();
        System.out.println("Enter the name of the first counter: ");
        String counterName1 = scanner.nextLine();

        System.out.println("Do you want to compare with another counter? (yes/no): ");
        String compareOption = scanner.nextLine().toLowerCase();

        while (!compareOption.equals("yes") && !compareOption.equals("no") && !compareOption.equals("y")
                && !compareOption.equals("n")) {
            System.out.println("Invalid choice. Please enter 'yes' or 'no': ");
            compareOption = scanner.nextLine().toLowerCase();
        }

        TriggerCounter triggerCounter;

        if (compareOption.equals("yes") || compareOption.equals("y")) {
            System.out.println("Enter the name of the second counter: ");
            String counterName2 = scanner.nextLine();
            triggerCounter = new TriggerCounter(operation, counterName1, counterName2);
        } else {
            System.out.println("Enter the value to compare with: ");
            int valueNumber;
            while (!scanner.hasNextInt()) {
                System.out.println("You didn't insert a valid number. Please insert a valid number for the value.");
                scanner.nextLine();
            }
            valueNumber = scanner.nextInt();
            scanner.nextLine();
            triggerCounter = new TriggerCounter(operation, counterName1, valueNumber);
        }

        triggers.put(name, triggerCounter);
        return;
    }

    // check if the trigger is valid ( present in the triggers Map)
    private static Trigger getValidTrigger(Map<String, Trigger> triggers, String triggerName, Scanner scanner) {
        Trigger trigger = triggers.get(triggerName);

        while (trigger == null) {
            System.out.println("Invalid trigger name, please retry or press 0 to come back to the menu");
            triggerName = scanner.nextLine();

            try {
                if (Integer.parseInt(triggerName) == 0)
                    return null;
            } catch (NumberFormatException exc) {

            }

            trigger = triggers.get(triggerName);
        }

        return trigger;
    }

    // Trigger existence check
    public static String checkTriggerPresent(Map<String, Trigger> triggers, Scanner scanner, String triggerName) {

        boolean isTriggerPresent = false;
        while (!isTriggerPresent) {
            if (triggers.get(triggerName) == null) {
                System.out.println(
                        "Not valid trigger name, please insert a different one or press 0 to come back to the modify rule menu");
                triggerName = scanner.nextLine();

                try {
                    if (Integer.parseInt(triggerName) == 0) {
                        return null;
                    }
                } catch (NumberFormatException e) {

                }
            }

            else
                isTriggerPresent = true;

        }
        return triggerName;
    }

    // Method to print the Triggers
    public static void displayTriggers(Map<String, Trigger> triggers) {

        Set<String> setTriggerKey = triggers.keySet();
        StringBuilder triggerStringSet = new StringBuilder();
        for (String key : setTriggerKey) {
            triggerStringSet.append(key).append(", ");
        }

        if (triggerStringSet.length() > 0) {
            triggerStringSet.setLength(triggerStringSet.length() - 2);
        }
        if (triggerStringSet.length() == 0)
            System.out.println("No existing Triggers");
        else
            System.out.println("Existing Triggers: " + triggerStringSet.toString());
    }

    public static void displayCounters() {
        MapCounter counters = MapCounter.getInstance();
        // Printing the counters in the map
        System.out.println("Existing Counters:");
        StringBuilder result = new StringBuilder();
        for (String counterName : counters.keySet()) {
            int counterValue = counters.getCounterValue(counterName);
            result.append(counterName).append(": ").append(counterValue).append(", ");
        }

        // Removing the trailing comma and space
        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }

        System.out.println(result.toString());
    }
}
