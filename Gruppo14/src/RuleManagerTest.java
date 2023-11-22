
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;


public class RuleManagerTest {

    // Reference to the RuleManager instance to be tested
    private RuleManager ruleManager;

    // run before each test method
    @Before
    public void setUp() {
        // Initialize the ruleManager with a new instance before each test
        ruleManager = RuleManager.getInstance();
    }

    //  run after each test method
    @After
    public void clearSetUp() {
        // Clear the ruleList and set the instance to null after each test
        ruleManager.getRuleList().clear();
        ruleManager = null;
    }

    // Test method for adding a rule to the ruleManager
    @Test
    public void testAddRule() {
        // Create a new rule
        Rule rule = new Rule();
        // Add the rule to the ruleManager
        ruleManager.addRule(rule);
        // Check if the ruleManager's ruleList contains the added rule
        assertTrue(ruleManager.getRuleList().contains(rule));
    }

    // Test method for removing a rule from the ruleManager
    @Test
    public void testRemoveRule() {
        // Create a new rule
        Rule rule = new Rule();
        // Add the rule to the ruleManager
        ruleManager.addRule(rule);
        // Remove the rule from the ruleManager
        ruleManager.removeRule(rule);
        // Check if the ruleManager's ruleList no longer contains the removed rule
        assertFalse(ruleManager.getRuleList().contains(rule));
    }

    // Test method for ensuring that getInstance returns the same instance
    @Test
    public void testGetInstance() {
        // Get two instances using getInstance method
        RuleManager instance1 = RuleManager.getInstance();
        RuleManager instance2 = RuleManager.getInstance();
        // Check if the two instances are the same (i.e., pointing to the same object)
        assertSame("Instances should be the same", instance1, instance2);
    }
}