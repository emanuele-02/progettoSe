import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuleManager implements Serializable{
    private static RuleManager instance;
    private List<Rule> ruleList;
    private ScheduledExecutorService scheduler;
    private RuleFileManager ruleFileManager;
    private ExecutorService executorService;

    private RuleManager() {
        ruleList = new ArrayList<>();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduleRuleEvaluation();
        ruleFileManager = new RuleFileManager("rules.ser");
        ruleFileManager.loadRulesFromFile();
        executorService = Executors.newFixedThreadPool(10);
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

    public void removeRule(Rule rule, Scanner scanner) {
        if (rule == null) {
            throw new IllegalArgumentException("Rule cannot be null");
        }

        if (!ruleList.contains(rule)) {
            throw new IllegalArgumentException("Rule not found in the rule list");
        }

        // Ask for confirmation before removing the rule
        if (confirmRemoval(scanner)) {
            ruleList.remove(rule);
            System.out.println("Rule removed successfully.");
        } else {
            System.out.println("Removal canceled.");
        }
    }

    private boolean confirmRemoval(Scanner scanner) {
        
        System.out.print("Are you sure you want to remove the rule? (Yes/No): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
        
    }

    public List<Rule> getRuleList() {
        return new ArrayList<>(ruleList);
    }


    // Logic for evaluating and executing rules
    private void evaluateRules() {
        for (Rule rule : ruleList) {
            // Check trigger condition and rule activation status
            if (shouldExecuteRule(rule)) {
                executeRule(rule);
            }
        }
    }

    // Check if the rule should be executed based on trigger and activation status
    private boolean shouldExecuteRule(Rule rule) {
        return rule.getTrigger().checkTrigger() && rule.isActive() && 
            !(rule.isTriggeredOnce() && rule.isAlreadyTriggered());
    }

    private void executeRule(Rule rule) {
        if (rule.getPeriod() != null) {
            if (rule.isAlreadyTriggered()) {
                // Calculate the time elapsed since the last execution
                long lastExecutionTime = rule.getLastExecutionTime();
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - lastExecutionTime;

                if (elapsedTime >= rule.getPeriod().toMillis()) {
                    // Time elapsed is greater or equal to the specified period, execute the rule
                    rule.getAction().execute();
                    rule.setLastExecutionTime(currentTime);
                    // Schedule future executions
                    scheduleRuleExecution(rule);
                } else {
                    // Time elapsed is less than the period, schedule the next execution
                    scheduleRuleExecution(rule, rule.getPeriod().toMillis() - elapsedTime);
                }
            } else {
                // Execute the rule directly and set as already triggered
                executorService.submit(() -> rule.getAction().execute());
                rule.setAlreadyTriggered(true);
                rule.setLastExecutionTime(System.currentTimeMillis());
                // Schedule future executions
                scheduleRuleExecution(rule);
            }
        } else {
            // Execute the rule directly without Period
            executorService.submit(() -> rule.getAction().execute());
            rule.setAlreadyTriggered(true);
        }
    }

    private void scheduleRuleExecution(Rule rule) {
        scheduler.schedule(() -> {
            executeRule(rule);
        }, rule.getPeriod().toMillis(), TimeUnit.MILLISECONDS);
    }

    // Schedule the rule for re-evaluation after the specified delay
    private void scheduleRuleExecution(Rule rule, long delayMillis) {
        scheduler.schedule(() -> {
            executeRule(rule);
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    //scheduleAtFixedRate schedules the periodic execution of a task
    private void scheduleRuleEvaluation() {
        //this::evaluateRules is a lambda expression representing the task to be executed
        //param 0: initial Period time param 1: time interval between executions
        scheduler.scheduleAtFixedRate(this::evaluateRules, 0, 5, TimeUnit.SECONDS);
    }

    //close scheduler
    public void shutdown() {
        scheduler.shutdown();
    }

    // saves the rule set in a file
    public void saveRulesToFile() {
        ruleFileManager.saveRulesToFile(ruleList);
    }

    // loads the rule set from file and inserts it in the list
    public void loadRulesFromFile() {
        List<Rule> loadedRules = ruleFileManager.loadRulesFromFile();
        if (loadedRules != null) {
            ruleList = loadedRules;
        }
    }
}