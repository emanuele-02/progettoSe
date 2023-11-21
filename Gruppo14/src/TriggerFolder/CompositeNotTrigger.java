package TriggerFolder;

public class CompositeNotTrigger implements Trigger {
    private Trigger trigger;

    public CompositeNotTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public boolean checkTrigger() {
        return !trigger.checkTrigger();
    }
}