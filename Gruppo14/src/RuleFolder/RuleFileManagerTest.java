package RuleFolder;

import org.junit.jupiter.api.Test;

import ActionFolder.Action;
import ActionFolder.DialogBoxAction;
import TriggerFolder.HourOfDayTrigger;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RuleFileManagerTest {

    @Test
    public void testSaveAndLoadRules() {
        // Create a RuleManager instance
        RuleManager ruleManager = RuleManager.getInstance();

        // Save rules to the file using RuleManager
        ruleManager.saveRulesToFile();

        // Load rules from the file using RuleManager
        ruleManager.loadRulesFromFile();

        // Create a list of rules to save
        List<Rule> originalRules = createTestRules();

        // Get the loaded rules from RuleManager
        List<Rule> loadedRules = ruleManager.getRuleList();

        // Verify that the loaded rules contain the original rules
        for (Rule originalRule : originalRules) {
            assertTrue(loadedRules.contains(originalRule),
                    "Rule not found: " + originalRule.toString());
        }

        for (Rule originalRule : originalRules) {
            ruleManager.getRuleList().remove(originalRule);
        }
    }

    private List<Rule> createTestRules() {
        LocalTime currentTime = LocalTime.now();
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();

        // Rule creation (automatically entered in rule manager)
        HourOfDayTrigger trigger = new HourOfDayTrigger(currentHour, currentMinute);
        Action action = new DialogBoxAction("Pippo");
        HourOfDayTrigger trigger2 = new HourOfDayTrigger(currentHour, currentMinute);
        Action action2 = new DialogBoxAction("Pluto");
        Rule rule = new Rule("TestRule", trigger, action, false);
        Rule rule2 = new Rule("TestRule2", trigger2, action2, false);

        return Arrays.asList(rule, rule2);
    }
}
