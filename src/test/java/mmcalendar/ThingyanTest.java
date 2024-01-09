package mmcalendar;

import org.junit.Test;

import java.time.DateTimeException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ThingyanTest {

    @Test(expected = DateTimeException.class)
    public void catchException() {
        Thingyan.of(1099);
    }

    @Test
    public void calculate() {
        Thingyan thingyan = Thingyan.of(1379);

        assertEquals(2457858.0, thingyan.getAkyaDay(), 0);
        assertEquals(2457857.7058919813, thingyan.getAkyaTime(), 0);

        assertEquals(2457860.0, thingyan.getAtatDay(), 0);
        assertEquals(2457859.875810963, thingyan.getAtatTime(), 0);
    }

    @Test
    public void calculateDoubleAkyatDay() {
        Thingyan thingyan = Thingyan.of(1382);

        assertEquals(2458953.0, thingyan.getAkyaDay(), 0);
        assertEquals(2458953.4821614255, thingyan.getAkyaTime(), 0);

        assertEquals(2458956.0, thingyan.getAtatDay(), 0);
        assertEquals(2458955.6520804074, thingyan.getAtatTime(), 0);

        double[] akyatDays = {2458954.0, 2458955.0};

        assertArrayEquals(akyatDays, thingyan.getAkyatDay(), 0);
    }
}
