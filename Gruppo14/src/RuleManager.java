import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

public class RuleManager {
    private static RuleManager instance;
    private List<Rule> ruleList;
    private ScheduledExecutorService scheduler;

    private RuleManager() {
        ruleList = new ArrayList<>();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduleRuleEvaluation();
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
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Are you sure you want to remove the rule? (Yes/No): ");
            String response = scanner.nextLine().trim().toLowerCase();
            return response.equals("yes") || response.equals("y");
        }
    }

    public List<Rule> getRuleList() {
        return new ArrayList<>(ruleList);
    }

    //logic of evaluation and execution of rules
    private void evaluateRules() {
        for (Rule rule : ruleList) {
            if(rule.getTrigger().checkTrigger() && rule.isActive()){
                rule.getAction().execute();
            }
        }
    }

    //scheduleAtFixedRate schedules the periodic execution of a task
    private void scheduleRuleEvaluation() {
        //this::evaluateRules is a lambda expression representing the task to be executed
        //param 0: initial delay time param 1: time interval between executions
        scheduler.scheduleAtFixedRate(this::evaluateRules, 0, 5, TimeUnit.SECONDS);
    }

    //close scheduler
    public void shutdown() {
        scheduler.shutdown();
    }
}