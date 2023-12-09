package ActionFolder;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import CounterFolder.MapCounter;

public class CounterActionTest {

    MapCounter mapCounter=MapCounter.getInstance();

    @Test
    void testSetExecute() {

        mapCounter.createCounter("counter1", 0);
        assertEquals(mapCounter.getCounterValue("counter1"), 0);
        CounterAction counterAction= new CounterAction(CounterActionType.valueOf("SET"),  "counter1", 1);
        counterAction.execute();
        assertEquals(mapCounter.getCounterValue("counter1"), 1);

        
    }

    @Test
    void testAddExecute() {

        mapCounter.createCounter("counter1", 1);
        assertEquals(mapCounter.getCounterValue("counter1"), 1);
        CounterAction counterAction= new CounterAction(CounterActionType.valueOf("ADD"),  "counter1", 3);
        counterAction.execute();
        assertEquals(mapCounter.getCounterValue("counter1"), 4);

    }

    @Test
    void testAddFromAnotherCounterExecute() {

        mapCounter.createCounter("counter1", 1);
        mapCounter.createCounter("counter2", 5);
        assertEquals(mapCounter.getCounterValue("counter1"), 1);
        CounterAction counterAction= new CounterAction("counter1", "counter2");
        counterAction.execute();
        assertEquals(mapCounter.getCounterValue("counter1"), 6);

        
    }
    
}
