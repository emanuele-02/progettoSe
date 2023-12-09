package RuleFolder;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuleManager implements Serializable {
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
        ruleList = ruleFileManager.loadRulesFromFile();
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

    private boolean shouldExecuteRule(Rule rule) {
        return rule.getTrigger().checkTrigger() && rule.isActive()
                && !(rule.isTriggeredOnce() && rule.isAlreadyTriggered());
    }

    /*
     * Executes the specified rule based on its configuration, including periodic
     * execution,
     * one-time execution, and scheduling future executions.
     */
    private void executeRule(Rule rule) {
        if (rule.getPeriod() != null) {
            long elapsedTime = System.currentTimeMillis() - rule.getLastExecutionTime();

            // Check if a periodic rule has already been triggered and if enough time has
            // passed
            if (rule.isAlreadyTriggered() && elapsedTime >= rule.getPeriod().toMillis()) {
                // Execute, recalculate last execution time and reschedule execution
                executorService.submit(() -> rule.getAction().execute());
                rule.setLastExecutionTime(System.currentTimeMillis());
                scheduleRuleExecution(rule);
            }
            // Check if a periodic rule has not been triggered yet
            else if (!rule.isAlreadyTriggered()) {
                // Executes, labels the rule as already executed and recalculates next execution
                // time
                executorService.submit(() -> rule.getAction().execute());
                rule.setAlreadyTriggered(true);
                rule.setLastExecutionTime(System.currentTimeMillis());
                scheduleRuleExecution(rule);
            }
        } else {
            // Execute the rule directly if it's not periodic
            executorService.submit(() -> rule.getAction().execute());
            rule.setAlreadyTriggered(true);
        }
    }

    // Schedules the execution of the specified rule based on its periodicity +
    // check
    private void scheduleRuleExecution(Rule rule) {
        scheduler.schedule(() -> {
            // Verifica prima di eseguire l'azione
            if (shouldExecuteRule(rule)) {
                executeRule(rule);
            }
        }, rule.getPeriod().toMillis(), TimeUnit.MILLISECONDS);
    }

    // scheduleAtFixedRate schedules the periodic execution of a task
    private void scheduleRuleEvaluation() {
        // this::evaluateRules is a lambda expression representing the task to be
        // executed
        // param 0: initial Period time param 1: time interval between executions
        scheduler.scheduleAtFixedRate(this::evaluateRules, 0, 5, TimeUnit.SECONDS);
    }

    // close scheduler
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