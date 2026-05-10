package mmcalendar;

import org.junit.Test;

import java.time.ZoneId;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConstantsTest {

    @Test
    public void solarYearConstant() {
        assertEquals(1577917828.0 / 4320000.0, Constants.SY, 1e-10);
    }

    @Test
    public void lunarMonthConstant() {
        assertEquals(1577917828.0 / 53433336.0, Constants.LM, 1e-10);
    }

    @Test
    public void beginningOfMyanmarEra() {
        assertEquals(1954168.050623, Constants.MO, 1e-6);
    }

    @Test
    public void eraAndYearBoundaries() {
        assertEquals(1312, Constants.SE3);
        assertEquals(640, Constants.BY);
        assertEquals(2140, Constants.EY);
        assertEquals(2, Constants.MBY);
        assertEquals(1500, Constants.MEY);
    }

    @Test
    public void accuracyBoundaries() {
        assertEquals(1700, Constants.LT);
        assertEquals(2018, Constants.UT);
        assertEquals(1062, Constants.MLT);
        assertEquals(1379, Constants.MUT);
    }

    @Test
    public void gregorianStart() {
        assertEquals(2361222.0, Constants.SG, 0.0);
    }

    @Test
    public void myanmarMonthNames() {
        assertEquals(15, Constants.EMA.length);
        assertEquals("First Waso", Constants.EMA[0]);
        assertEquals("Tagu", Constants.EMA[1]);
        assertEquals("Tabaung", Constants.EMA[12]);
        assertEquals("Late Tagu", Constants.EMA[13]);
        assertEquals("Late Kason", Constants.EMA[14]);
    }

    @Test
    public void moonPhaseNames() {
        assertEquals(4, Constants.MSA.length);
        assertEquals("Waxing", Constants.MSA[0]);
        assertEquals("Full Moon", Constants.MSA[1]);
        assertEquals("Waning", Constants.MSA[2]);
        assertEquals("New Moon", Constants.MSA[3]);
    }

    @Test
    public void weekDayNames() {
        assertEquals(7, Constants.WDA.length);
        assertEquals("Saturday", Constants.WDA[0]);
        assertEquals("Sunday", Constants.WDA[1]);
        assertEquals("Friday", Constants.WDA[6]);
    }

    @Test
    public void myanmarZoneId() {
        assertNotNull(Constants.MYANMAR_ZONE_ID);
        assertEquals(ZoneId.of("Asia/Rangoon"), Constants.MYANMAR_ZONE_ID);
    }
}
