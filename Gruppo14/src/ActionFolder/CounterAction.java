package ActionFolder;

import CounterFolder.MapCounter;

public class CounterAction implements Action {

    private CounterActionType operation;
    private Integer valueNumber;
    private String counterName1;
    private String counterName2;
    private MapCounter mapCounter;
    

    public CounterAction(String counterName1, String counterName2) {
        this.operation=CounterActionType.valueOf("ADD");
        this.counterName1 = counterName1;
        this.counterName2 = counterName2;
        this.valueNumber=null;
        this.mapCounter=MapCounter.getInstance();

    }


    public CounterAction(CounterActionType operation,  String counterName1, int valueNumber) {
        this.operation = operation;
        this.valueNumber = valueNumber;
        this.counterName1 = counterName1;
        this.counterName2= null;
        this.mapCounter=MapCounter.getInstance();
    }



    @Override
    public void execute() {
        
        //Verify that the user passed 2 counters
        if(valueNumber==null)
            //adding the counter2's value to counter1
                mapCounter.setCounterValue(this.counterName1, mapCounter.getCounterValue(this.counterName1)+mapCounter.getCounterValue(this.counterName2));

        else{
            switch(operation){

                case SET:

                    mapCounter.setCounterValue(this.counterName1, valueNumber);
                    break;

                case ADD: 

                    mapCounter.setCounterValue(this.counterName1, mapCounter.getCounterValue(this.counterName1)+valueNumber);
                    break;
            }
        }
    }
}
