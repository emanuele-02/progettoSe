import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import ActionFolder.*;

public class UtilityAction {

    // Create different type of action with choise and check correct argument
    public static void createAction(Map<String, Action> actions, Scanner scanner) {
        displayActions(actions);
        System.out.println("Insert action's name");
        String name = scanner.nextLine();
        while (name.trim().isEmpty()) {

            System.out.println(
                    "You didn't insert an action name. Please insert a correct one or press 0 to come back to the main menu ");
            name = scanner.nextLine();

            try {
                if (Integer.parseInt(name) == 0)
                    return;
            } catch (NumberFormatException e) {

            }

        }

        Action createdAction = null;
        boolean validInput = false;

        while (actions.containsKey(name)) {

            System.out.println(
                    "This action name already exists, please insert a different one or press 0 to come back to the main menu");
            name = scanner.nextLine();

            try {
                if (Integer.parseInt(name) == 0)
                    return;
            } catch (NumberFormatException e) {

            }

        }

        while (!validInput && !actions.containsKey(name)) {
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
            System.out.println(" ");
            System.out.println("7. CounterAction");
            System.out.println(" ");
            System.out.println("8. Return to menu");
            System.out.println("---------------------------------------");
            System.out.print("Please enter the number of your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            while (!validInput && !actions.containsKey(name) && choice <= 8 && choice > 0) {
                try {
                    switch (choice) {
                        case 1:
                            System.out.println("Insert the audio's path");
                            String path = scanner.nextLine();
                            createdAction = new AudioAction(path);
                            validInput = true;
                            System.out.println("Action  successfully created");
                            break;

                        case 2:
                            System.out.println("Insert the message");
                            String message = scanner.nextLine();
                            createdAction = new DialogBoxAction(message);
                            validInput = true;
                            System.out.println("Action  successfully created");
                            break;
                        case 3:
                            System.out.println("Insert the path of the program that you want to run");
                            String path1 = scanner.nextLine();
                            System.out.println("Insert the command to do to run the program");
                            String command = scanner.nextLine();

                            System.out.println("How many command line arguments do you want to pass?");
                            while (!scanner.hasNextInt()) {
                                scanner.nextLine();
                                System.out.println("Error: You have to insert a number!");
                            }
                            int n = scanner.nextInt();
                            scanner.nextLine();
                            if (n != 0)
                                System.out.println("Insert your command line arguments");
                            String[] args = new String[n];
                            for (int i = 0; i < n; i++) {
                                args[i] = scanner.nextLine();
                            }
                            createdAction = new ExternalProgramAction(command, path1, args);
                            validInput = true;
                            System.out.println("Action  successfully created");
                            break;

                        case 4:
                            System.out.println("Insert the string you want to write");
                            String stringToWrite = scanner.nextLine();
                            System.out.println("Insert the path to the file you want the string to be written to");
                            String targetFilePath = scanner.nextLine();
                            createdAction = new WriteStringOnFileAction(targetFilePath, stringToWrite);
                            validInput = true;
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
                                createdAction = new MoveCopyFileAction(OperationFileType.MOVE, targetFileName,
                                        targetDirectory, destinationDirectory);
                            } else if (operation.equalsIgnoreCase("COPY")) {
                                createdAction = new MoveCopyFileAction(OperationFileType.COPY, targetFileName,
                                        targetDirectory, destinationDirectory);
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
                            createdAction = new DeleteFileAction(targetDirectoryForDelete, targetFileNameToDelete);

                            validInput = true;
                            System.out.println("Action  successfully created");
                            break;

                        case 7:
                            UtilityTrigger.displayCounters();
                            System.out.println(
                                    "Do you want to create an action with two counters or a counter and an integer?");
                            System.out.println("1. Two Counters");
                            System.out.println("2. Counter and Integer");
                            int counterChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (counterChoice) {
                                case 1:

                                    System.out.println("Insert the names of the first counter:");
                                    String counterName1 = scanner.nextLine();
                                    System.out.println("Insert the names of the second counter:");
                                    String counterName2 = scanner.nextLine();

                                    createdAction = new CounterAction(counterName1, counterName2);
                                    break;

                                case 2:
                                    System.out.println("Insert the name of the counter");
                                    String counterName = scanner.nextLine();
                                    System.out.println("Insert the integer value");
                                    int intValue = scanner.nextInt();
                                    scanner.nextLine();
                                    createdAction = new CounterAction(CounterActionType.ADD, counterName, intValue);
                                    break;

                                

                                default:
                                    System.out.println("Invalid choice, retry");
                                    return;
                            }

                            validInput = true;
                            System.out.println("CounterAction successfully created");
                            break;

                            case 8:
                            validInput= true;
                            return;

                        default:
                            System.out.println("Invalid choice, retry");
                    }

                    if (createdAction != null) {
                        actions.putIfAbsent(name, createdAction);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "Press enter to try again or 0 to come back to the main menu");
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

    // Create a composite action with option of add/sava and controls
    public static void createSetOfAction(Map<String, Action> actions, Scanner scanner) {
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
                scanner.nextLine(); // Consumes the newline left in the buffer

                switch (choice) {
                    case 1:
                        // Ask how many actions to add
                        // Ask how many actions to add
                        System.out.println("How many actions do you want to add to the set?");
                        int numActionsToAdd = scanner.nextInt();
                        scanner.nextLine(); // Consumes the newline left in the buffer
                        displayActions(actions);

                        // Add the specified number of actions
                        for (int i = 0; i < numActionsToAdd; i++) {
                            System.out.println(
                                    "Enter the name of the existing action to add to the set (enter 0 to go back to the previous menu):");
                            String existingActionName = scanner.nextLine();

                            if (existingActionName.equals("0")) {
                                // User wants to go back to the previous menu
                                System.out.println("Returning to the previous menu.");
                                break;
                            }

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
                        System.out.println(
                                "Set of actions '" + compositeActionName + "' successfully created and saved.");
                        validInput = true;
                        break;

                    case 3:
                        validInput = true;
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

    public static String checkActionPresent(Map<String, Action> actions, Scanner scanner, String actionName) {
        boolean isActionPresent = false;
        while (!isActionPresent) {
            if (actions.get(actionName) == null) {
                System.out.println(
                        "Not valid action name, please insert a different one or press 0 to come back to the modify rule menu");
                actionName = scanner.nextLine();

                try {
                    if (Integer.parseInt(actionName) == 0) {
                        return null;
                    }
                } catch (NumberFormatException e) {

                }
            }

            else
                isActionPresent = true;

        }
        return actionName;
    }

    // Method to print the Actions
    public static void displayActions(Map<String, Action> actions) {

        Set<String> setActionKey = actions.keySet();
        StringBuilder actionStringSet = new StringBuilder();
        for (String key : setActionKey) {
            actionStringSet.append(key).append(", ");
        }

        if (actionStringSet.length() > 0) {
            actionStringSet.setLength(actionStringSet.length() - 2);
        }
        if (actionStringSet.length() == 0)
            System.out.println("No existing Actions");
        else
            System.out.println("Existing Actions: " + actionStringSet.toString());
    }

  
}
