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

    @Test
    public void calculatePreEra3() {
        // Era 2 (pre-1312): uses jk = ja - 2.1675 instead of 2.169918982
        Thingyan thingyan = Thingyan.of(1200);
        // Verify it computes without error and returns reasonable values
        assertEquals(true, thingyan.getAkyaDay() > 0);
        assertEquals(true, thingyan.getAtatDay() > thingyan.getAkyaDay());
    }

    @Test
    public void calculateThingyan2025() {
        Thingyan thingyan = Thingyan.of(1387);
        WesternDate akyaDay =  WesternDate.of(thingyan.getAkyaDay());
        WesternDate atatTime =  WesternDate.of(thingyan.getAtatDay());

        assertEquals(2460780.0, thingyan.getAkyaDay(), 0);
        assertEquals(2460779.775943833, thingyan.getAkyaTime(), 0);

        assertEquals(2460782.0, thingyan.getAtatDay(), 0);
        assertEquals(2460781.945862815, thingyan.getAtatTime(), 0);
    }
}
