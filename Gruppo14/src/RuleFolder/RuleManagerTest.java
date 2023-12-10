package RuleFolder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ActionFolder.Action;
import ActionFolder.DialogBoxAction;
import TriggerFolder.HourOfDayTrigger;
import TriggerFolder.Trigger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RuleManagerTest {

    private RuleManager ruleManager;
    private Scanner scannerYes;
    private Scanner scannerNo;

    @Before
    public void setUp() {
        ruleManager = RuleManager.getInstance();

        // Simulated scanner for "yes"
        String simulatedInputYes = "yes";
        InputStream inYes = new ByteArrayInputStream(simulatedInputYes.getBytes());
        scannerYes = new Scanner(inYes);

        // Simulated scanner for "no"
        String simulatedInputNo = "no";
        InputStream inNo = new ByteArrayInputStream(simulatedInputNo.getBytes());
        scannerNo = new Scanner(inNo);
    }

    @After
    public void clearSetUp() {
        ruleManager.getRuleList().clear();
        ruleManager = null;

        scannerYes.close();
        scannerNo.close();
    }

    @Test
    public void testAddRule() {
        Trigger trigger = new HourOfDayTrigger(2, 3);
        Action action = new DialogBoxAction("paperino");

        Rule rule = new Rule("Pippo", trigger, action, false);
        ruleManager.addRule(rule);

        assertTrue(ruleManager.getRuleList().contains(rule));
    }

    @Test
    public void testRemoveRuleWithConfirmationYes() {

        Trigger trigger = new HourOfDayTrigger(2, 3);
        Action action = new DialogBoxAction("paperino");

        Rule rule = new Rule("Pippo2", trigger, action, false);

        ruleManager.removeRule(rule, scannerYes);

        assertFalse(ruleManager.getRuleList().contains(rule));
    }

    @Test
    public void testRemoveRuleWithCancellationNo() {
        Trigger trigger = new HourOfDayTrigger(2, 3);
        Action action = new DialogBoxAction("paperino");

        Rule rule = new Rule("Pippo", trigger, action, false);

        ruleManager.removeRule(rule, scannerNo);

        assertTrue(ruleManager.getRuleList().contains(rule));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexistentRule() {
        Trigger trigger = new HourOfDayTrigger(2, 3);
        Action action = new DialogBoxAction("paperino");

        Rule rule = new Rule("Pippo3", trigger, action, false);

        simulatedUserInput("yes");
        ruleManager.removeRule(rule, scannerYes);

        // Try to remove again (remove a rule from a list that does not contain such a
        // rule)
        ruleManager.removeRule(rule, scannerYes);
    }

    private void simulatedUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
