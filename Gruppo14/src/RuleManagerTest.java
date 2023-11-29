import org.junit.Before;
import org.junit.Test;

import TriggerFolder.*;
import ActionFolder.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;
import org.junit.After;

public class RuleManagerTest {

    // Reference to the RuleManager instance to be tested
    private RuleManager ruleManager;
    private Scanner scanner = new Scanner(System.in);
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
        ruleManager = null;
    }

    // Test method for adding a rule to the ruleManager
    @Test
    public void testAddRule() {
        Trigger trigger = new HourOfDayTrigger(2, 3);
        Action action = new DialogBoxAction("paperino");

        Rule rule = new Rule("pippo", trigger, action,false);
        // I do not add() because it is done automatically when the rule is created
        // Check if the ruleManager's ruleList contains the added rule
        assertTrue(ruleManager.getRuleList().contains(rule));
    }

    // Test method for removing a rule from the ruleManager with confirmation (Yes)
    @Test
    public void testRemoveRuleWithConfirmationYes() {
        Trigger trigger = new HourOfDayTrigger(2, 3);
        Action action = new DialogBoxAction("paperino");

        Rule rule = new Rule("Pippo", trigger, action,false);

        // simulated user input for confirmation (response: Yes)
        simulatedUserInput("yes");

        ruleManager.removeRule(rule,scanner);

        // Check if the ruleManager's ruleList no longer contains the removed rule
        assertFalse(ruleManager.getRuleList().contains(rule));
    }

    // Test method for removing a rule from the ruleManager with cancellation (No)
    @Test
    public void testRemoveRuleWithCancellationNo() {
        Trigger trigger = new HourOfDayTrigger(2, 3);
        Action action = new DialogBoxAction("paperino");

        Rule rule = new Rule("Pippo", trigger, action,false);

        // simuleted user input for confirmation (response: No)
        simulatedUserInput("no");

        ruleManager.removeRule(rule,scanner);

        // Check if the ruleManager's ruleList still contains the rule since removal is canceled
        assertTrue(ruleManager.getRuleList().contains(rule));
    }

    // Test method for ensuring that getInstance returns the same instance
    @Test
    public void testGetInstance() {
        RuleManager instance1 = RuleManager.getInstance();
        RuleManager instance2 = RuleManager.getInstance();
        // Check if the two instances are the same (i.e., pointing to the same object)
        assertSame("Instances should be the same", instance1, instance2);
    }
    
    //Add null rule
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullRule() {
        ruleManager.addRule(null);
    }
    //Remove null rule
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullRule() {
        ruleManager.removeRule(null,scanner);
    }

    //Remove a rule from a list that does not contain such a rule
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexistentRule() {
        Trigger trigger = new HourOfDayTrigger(2, 3);
        Action action = new DialogBoxAction("paperino");

        Rule rule = new Rule("Pippo", trigger, action,false);

        simulatedUserInput("yes");
        // Try to remove the rule from the ruleManager OK
        ruleManager.removeRule(rule,scanner);
        // Try to remove again (remove a rule from a list that does not contain such a rule)
        ruleManager.removeRule(rule,scanner);
    }

    // Helper method to simulate user input for confirmation
    private void simulatedUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
