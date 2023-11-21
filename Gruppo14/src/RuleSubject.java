public class RuleSubject {
    private RuleObserver observer;

    public void attach(RuleObserver observer) {
        this.observer = observer;
    }

    public void detach() {
        this.observer = null;
    }

    public void updateRule(String newRule) {
        if (observer != null) {
            observer.updateRule();
        }
    }
}