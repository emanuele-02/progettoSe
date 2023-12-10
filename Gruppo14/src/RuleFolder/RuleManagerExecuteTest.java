package RuleFolder;

import ActionFolder.Action;
import ActionFolder.*;
import TriggerFolder.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class RuleManagerExecuteTest {

    // Reference to the RuleManager instance to be tested
    private RuleManager ruleManager;

    // run before each test method
    @Before
    public void setUp() {
        // Initialize the ruleManager with a new instance before each test
        ruleManager = RuleManager.getInstance();
    }

    // run after each test method
    @After
    public void clearSetUp() {
        // Clear the ruleList and set the instance to null after each test
        ruleManager.getRuleList().clear();
        ruleManager.shutdown();
        ruleManager = null;
    }

    // Test method for scheduling rule evaluation
    @Test
    public void testScheduledRuleEvaluation() {
        // Create a rule with a trigger and action
        LocalTime currentTime = LocalTime.now();

        // Extract the hour and minutes
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();

        // rule creation that is always activated
        HourOfDayTrigger trigger = new HourOfDayTrigger(currentHour, currentMinute);
        Action action = new DialogBoxAction("Pippo");
        HourOfDayTrigger trigger2 = new HourOfDayTrigger(currentHour, currentMinute);
        Action action2 = new AudioAction("TestAudioAction.wav");
        Rule rule = new Rule("TestRule", trigger, action, false);
        Rule rule2 = new Rule("TestRule2", trigger2, action2, false);
        // Simulate the passage of time to allow scheduled evaluation
        simulateTimePassing(20);

    }

    // Test for deactivation

    @Test
    public void testScheduledRuleEvaluationActivationDeactivation() {
        // Create a rule with a trigger and action
        LocalTime currentTime = LocalTime.now();

        // Extract the hour and minutes
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();

        // rule creation that is always activated
        HourOfDayTrigger trigger = new HourOfDayTrigger(currentHour, currentMinute);
        Action action = new DialogBoxAction("Pippo");
        Rule rule = new Rule("TestRule", trigger, action, false);
        // 2 message Pippo
        simulateTimePassing(10);
        // no message for 10 seconds
        rule.deactivate();
        simulateTimePassing(10);
        // 2 message pippo and finish test
        rule.activate();
        simulateTimePassing(10);
    }

    @Test
    public void testTriggerOnce() {

        LocalTime currentTime = LocalTime.now();

        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();

        HourOfDayTrigger trigger = new HourOfDayTrigger(currentHour, currentMinute);
        Action action = new DialogBoxAction("Pippo");
        Rule rule = new Rule("TestRule", trigger, action, true);

        // only one message Pippo
        simulateTimePassing(13);

        rule.setTriggeredOnce(false);
        // 2 messagge Pippo
        simulateTimePassing(11);
    }

    @Test
    public void testTriggerPeriod() {

        LocalTime currentTime = LocalTime.now();

        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();

        HourOfDayTrigger trigger = new HourOfDayTrigger(currentHour, currentMinute);
        Action action = new DialogBoxAction("Pippo");
        Rule rule = new Rule("TestRule", trigger, action, 0, 0, 1);
        simulateTimePassing(8);
        rule.deactivate();
        rule.activate();
        // 2 message Pippo
        simulateTimePassing(65);
    }

    // Helper method to simulate the passage of time
    private void simulateTimePassing(int seconds) {
        try {
            // Sleep for the specified number of seconds
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
