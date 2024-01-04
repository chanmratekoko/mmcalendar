package mmcalendar;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HolidayCalculatorTest {

    @Test
    public void englishHoliday() {
        List<String> holiday = HolidayCalculator.englishHoliday(2017, 1, 4);
        List<String> actualList = Collections.singletonList("Independence Day");
        Assert.assertEquals(actualList, holiday);
    }

    @Test
    public void myanmarHoliday() {
        List<String> holiday = HolidayCalculator.myanmarHoliday(1385, 4, 15, 1);
        List<String> actualList = Collections.singletonList("Start of Buddhist Lent");
        Assert.assertEquals(actualList, holiday);
    }

    @Test
    public void thingyan() {
        List<String> thingyan = HolidayCalculator.thingyan(2460052, 1385, 0);
        List<String> actualList = Collections.singletonList("Myanmar New Year Day");
        Assert.assertEquals(actualList, thingyan);
    }

    @Test
    public void getHoliday() {
        MyanmarDate myanmarDate = MyanmarDate.of(2460052);
        List<String> thingyan = HolidayCalculator.getHoliday(myanmarDate);
        List<String> actualList = Collections.singletonList("Myanmar New Year Day");
        Assert.assertEquals(actualList, thingyan);
    }

    @Test
    public void isHoliday() {
        MyanmarDate myanmarDate = MyanmarDate.of(2460052);
        boolean isHoliday = HolidayCalculator.isHoliday(myanmarDate);
        Assert.assertTrue("holiday", isHoliday);
    }

}
