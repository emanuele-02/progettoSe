package TriggerFolder;

import CounterFolder.MapCounter;

public class TriggerCounter implements Trigger {

    private OperationType operation;
    private String counterName1;
    private String counterName2;
    private Integer valueNumber;
    private MapCounter mapCounter;

    public TriggerCounter(OperationType operation, String counterName1, String counterName2) {
        this.operation = operation;
        this.counterName1 = counterName1;
        this.counterName2 = counterName2;
        this.valueNumber = null;
        this.mapCounter = MapCounter.getInstance();
        //checks if the  counters entered are valid
        if (!mapCounter.containsCounter(this.counterName1) ||
                (this.counterName2 != null && !mapCounter.containsCounter(this.counterName2))) {
            throw new IllegalArgumentException("Error: Invalid Counters ");
        }
    }

    public TriggerCounter(OperationType operation, String counterName1, int valueNumber) {
        this.operation = operation;
        this.counterName1 = counterName1;
        this.valueNumber = valueNumber;
        this.counterName2 = null;
        this.mapCounter = MapCounter.getInstance();
        //checks if the  counters entered are valid
        if (!mapCounter.containsCounter(this.counterName1) || this.counterName1 == null) {
            throw new IllegalArgumentException("Error: Invalid Counter ");
        }
    }

    @Override
    public boolean checkTrigger() {

        Integer counter1;
        Integer counter2;

        // case of user passes 2 counters
        if (valueNumber == null) {

            // choose the operations to be performed based on the operation passed by the
            // user
            switch (operation) {

                case EQUALTO:

                    counter1 = this.mapCounter.getCounterValue(counterName1);
                    counter2 = this.mapCounter.getCounterValue(counterName2);

                    if (counter1 == counter2)
                        return true;

                    else
                        return false;

                case LESSTHAN:

                    counter1 = this.mapCounter.getCounterValue(counterName1);
                    counter2 = this.mapCounter.getCounterValue(counterName2);

                    if (counter1 < counter2)
                        return true;

                    else
                        return false;

                case GREATERTHAN:

                    counter1 = this.mapCounter.getCounterValue(counterName1);
                    counter2 = this.mapCounter.getCounterValue(counterName2);

                    if (counter1 > counter2)
                        return true;

                    else
                        return false;
            }
        }

        // case of user passes a counter and a value
        else {

            // choose the operations to be performed based on the operation passed by the
            // user
            switch (operation) {

                case EQUALTO:

                    counter1 = this.mapCounter.getCounterValue(counterName1);

                    if (counter1 == this.valueNumber)
                        return true;

                    else
                        return false;

                case LESSTHAN:

                    counter1 = this.mapCounter.getCounterValue(counterName1);

                    if (counter1 < this.valueNumber)
                        return true;

                    else
                        return false;

                case GREATERTHAN:

                    counter1 = this.mapCounter.getCounterValue(counterName1);

                    if (counter1 > this.valueNumber)
                        return true;

                    else
                        return false;

            }
        }
        return false;
    }
}