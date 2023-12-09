package TriggerFolder;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import CounterFolder.MapCounter;

public class TriggerCounterTest {

    MapCounter mapCounter= MapCounter.getInstance();

    @Test
    void testCheckTriggerWithTwoCounters() {

        mapCounter.createCounter("counter1", 0);
        mapCounter.createCounter("counter2", 0);
        TriggerCounter triggerCounter1= new TriggerCounter(OperationType.valueOf("EQUALTO"), "counter1", "counter2");
        assertTrue(triggerCounter1.checkTrigger());
        mapCounter.setCounterValue("counter2", 1);
        assertFalse(triggerCounter1.checkTrigger());
        mapCounter.setCounterValue("counter2", -1);
        assertFalse(triggerCounter1.checkTrigger());

        mapCounter.setCounterValue("counter1", 1);
        mapCounter.setCounterValue("counter2", 2);
        TriggerCounter triggerCounter2= new TriggerCounter(OperationType.valueOf("LESSTHAN"), "counter1", "counter2");
        assertTrue(triggerCounter2.checkTrigger());
        mapCounter.setCounterValue("counter2", 1);
        assertFalse(triggerCounter2.checkTrigger());
        mapCounter.setCounterValue("counter2", 0);
        assertFalse(triggerCounter2.checkTrigger());

        mapCounter.setCounterValue("counter1", 2);
        mapCounter.setCounterValue("counter2", 1);
        TriggerCounter triggerCounter3= new TriggerCounter(OperationType.valueOf("GREATERTHAN"), "counter1", "counter2");
        assertTrue(triggerCounter3.checkTrigger());
        mapCounter.setCounterValue("counter2", 2);
        assertFalse(triggerCounter3.checkTrigger());
        mapCounter.setCounterValue("counter2", 3);
        assertFalse(triggerCounter3.checkTrigger());

    }

    @Test
    void testCheckTriggerWithOneCounterAndOneInt(){

        mapCounter.createCounter("counter1", 0);
        TriggerCounter triggerCounter1= new TriggerCounter(OperationType.valueOf("EQUALTO"), "counter1", 0);
        assertTrue(triggerCounter1.checkTrigger());
        TriggerCounter triggerCounter2= new TriggerCounter(OperationType.valueOf("EQUALTO"), "counter1", 1);
        assertFalse(triggerCounter2.checkTrigger());
        TriggerCounter triggerCounter3= new TriggerCounter(OperationType.valueOf("EQUALTO"), "counter1", -1);
        assertFalse(triggerCounter3.checkTrigger());


        TriggerCounter triggerCounter4= new TriggerCounter(OperationType.valueOf("LESSTHAN"), "counter1", 1);
        assertTrue(triggerCounter4.checkTrigger());
        TriggerCounter triggerCounter5= new TriggerCounter(OperationType.valueOf("LESSTHAN"), "counter1", 0);
        assertFalse(triggerCounter5.checkTrigger());
        TriggerCounter triggerCounter6= new TriggerCounter(OperationType.valueOf("LESSTHAN"), "counter1", -1 );
        assertFalse(triggerCounter6.checkTrigger());

        TriggerCounter triggerCounter7= new TriggerCounter(OperationType.valueOf("GREATERTHAN"), "counter1", -1);
        assertTrue(triggerCounter7.checkTrigger());
        TriggerCounter triggerCounter8= new TriggerCounter(OperationType.valueOf("GREATERTHAN"), "counter1", 0);
        assertFalse(triggerCounter8.checkTrigger());
        TriggerCounter triggerCounter9= new TriggerCounter(OperationType.valueOf("GREATERTHAN"), "counter1", 1);
        assertFalse(triggerCounter9.checkTrigger());
    }
}
