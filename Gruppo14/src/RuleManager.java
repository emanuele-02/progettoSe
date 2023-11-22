import java.util.ArrayList;
import java.util.List;

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
    public void addRule(Rule rule) {
        ruleList.add(rule);
    }
    public void removeRule(Rule rule) {
        ruleList.remove(rule);
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
