package TriggerFolder;

public class CompositeOrTrigger implements Trigger {
    private Trigger trigger1;
    private Trigger trigger2;

    public CompositeOrTrigger(Trigger trigger1, Trigger trigger2) {
        this.trigger1 = trigger1;
        this.trigger2 = trigger2;
    }

    @Override
    public boolean checkTrigger() {
        return trigger1.checkTrigger() || trigger2.checkTrigger();
    }
}