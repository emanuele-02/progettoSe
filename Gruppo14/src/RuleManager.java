import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RuleManager implements RuleObserver {
    private static RuleManager instance;
    private List<Rule> ruleList;

    // costrutture della classe
     private RuleManager() {
        ruleList = new ArrayList<>();
    }
    // metoto get istance
    public static synchronized RuleManager getInstance() {
        if (instance == null) {
            instance = new RuleManager();
        }
        return instance;
    }
    protected void addRule(Rule rule) {
        ruleList.add(rule);
    }

    public void removeRule(Rule rule) {
        // Ask for confirmation before removing the rule
        if (confirmRemoval()) {
            ruleList.remove(rule);
            System.out.println("Rule removed successfully.");
        } else {
            System.out.println("Removal canceled.");
        }
    }

    // Method to get confirmation from the user(no case sensitive)
    private boolean confirmRemoval() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure you want to remove the rule? (Yes/No): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    //for testing might come in handy in the future
    public List<Rule> getRuleList() {
        return new ArrayList<>(ruleList); // Return a copy to prevent external modification
    }


    @Override
    public void updateRule() {
       
        throw new UnsupportedOperationException("Unimplemented method 'updateRule'");
    }
}
