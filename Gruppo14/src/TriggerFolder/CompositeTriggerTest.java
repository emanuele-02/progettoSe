package TriggerFolder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class CompositeTriggerTest {

    @Test
    public void testInvalidOperation1() {
        int current = LocalDate.now().getDayOfMonth();
        DayOfMonthTrigger t = new DayOfMonthTrigger(current);
        DayOfMonthTrigger t2 = new DayOfMonthTrigger(current);

        assertThrows(IllegalArgumentException.class, () -> {
            new CompositeTrigger(t, t2, LogicalOperation.NOT);
        });
    }

    @Test
    public void testInvalidOperation2() {
        int current = LocalDate.now().getDayOfMonth();
        DayOfMonthTrigger t = new DayOfMonthTrigger(current);

        assertThrows(IllegalArgumentException.class, () -> {
            new CompositeTrigger(t, LogicalOperation.AND);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CompositeTrigger(t, LogicalOperation.OR);
        });
    }

    @Test
    public void testAndTrigger() {

        int current = LocalDate.now().getDayOfMonth();
        LocalDate date = LocalDate.now().plusDays(1);
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // true
        DayOfMonthTrigger t = new DayOfMonthTrigger(current);
        DayOfMonthTrigger t2 = new DayOfMonthTrigger(current);
        // false
        DateTrigger t3 = new DateTrigger(date.format(formatDate));
        // true,true=true
        CompositeTrigger ct = new CompositeTrigger(t, t2, LogicalOperation.AND);
        // true,false=false
        CompositeTrigger ct2 = new CompositeTrigger(t, t3, LogicalOperation.AND);
        // false,false=false
        CompositeTrigger ct3 = new CompositeTrigger(t3, ct2, LogicalOperation.AND);

        assertTrue(ct.checkTrigger());
        assertFalse(ct2.checkTrigger());
        assertFalse(ct3.checkTrigger());

    }

    @Test
    public void testOrTrigger() {

        int current = LocalDate.now().getDayOfMonth();
        LocalDate date = LocalDate.now().plusDays(1);
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // true
        DayOfMonthTrigger t = new DayOfMonthTrigger(current);
        DayOfMonthTrigger t2 = new DayOfMonthTrigger(current);
        // false
        DateTrigger t3 = new DateTrigger(date.format(formatDate));

        // true,true=true
        CompositeTrigger ct = new CompositeTrigger(t, t2, LogicalOperation.OR);
        // true,false=true
        CompositeTrigger ct2 = new CompositeTrigger(t, t3, LogicalOperation.OR);
        // false,false=false
        CompositeTrigger ct3 = new CompositeTrigger(t3, t3, LogicalOperation.OR);

        assertTrue(ct.checkTrigger());
        assertTrue(ct2.checkTrigger());
        assertFalse(ct3.checkTrigger());
    }

    @Test
    public void testNotTrigger() {
        int current = LocalDate.now().getDayOfMonth();
        // true
        DayOfMonthTrigger t = new DayOfMonthTrigger(current);

        // true->false
        CompositeTrigger ct = new CompositeTrigger(t, LogicalOperation.NOT);
        // false->true
        CompositeTrigger ct2 = new CompositeTrigger(ct, LogicalOperation.NOT);

        assertFalse(ct.checkTrigger());
        assertTrue(ct2.checkTrigger());
    }

}
