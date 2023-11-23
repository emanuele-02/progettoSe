import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class RuleManager implements RuleObserver {
    private static RuleManager instance;
    private List<Rule> ruleList;

    private RuleManager() {
        ruleList = new ArrayList<>();
    }

    public static synchronized RuleManager getInstance() {
        if (instance == null) {
            instance = new RuleManager();
        }
        return instance;
    }


    protected void addRule(Rule rule) {
        if (rule == null) {
            throw new IllegalArgumentException("Rule cannot be null");
        }
        ruleList.add(rule);
    }

    public void removeRule(Rule rule) {
        if (rule == null) {
            throw new IllegalArgumentException("Rule cannot be null");
        }

        if (!ruleList.contains(rule)) {
            throw new IllegalArgumentException("Rule not found in the rule list");
        }

        // Ask for confirmation before removing the rule
        if (confirmRemoval()) {
            ruleList.remove(rule);
            System.out.println("Rule removed successfully.");
        } else {
            System.out.println("Removal canceled.");
        }
    }

    private boolean confirmRemoval() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure you want to remove the rule? (Yes/No): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    public List<Rule> getRuleList() {
        return new ArrayList<>(ruleList);
    }

    @Override
    public void updateRule() {
        throw new UnsupportedOperationException("Unimplemented method 'updateRule'");
    }
}
