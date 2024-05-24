package mmcalendar;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HolidayCalculatorTest {

    @Test
    public void englishHoliday() {
        List<String> holiday = HolidayCalculator.englishHoliday(2017, 1, 4);
        List<String> actualList = Collections.singletonList("Independence Day");
        assertEquals(actualList, holiday);
    }

    @Test
    public void myanmarHoliday() {
        List<String> holiday = HolidayCalculator.myanmarHoliday(1385, 4, 15, 1);
        List<String> actualList = Collections.singletonList("Start of Buddhist Lent");
        assertEquals(actualList, holiday);
    }

    @Test
    public void unionDay() {
        MyanmarDate unionDayDate = MyanmarDate.of(2024, 2, 12);
        String unionDay = HolidayCalculator.getHoliday(unionDayDate, Language.MYANMAR).get(0);
        assertEquals("ပြည်ထောင်စု နေ့", unionDay);
    }

    @Test
    public void thingyan() {
        List<String> thingyan = HolidayCalculator.thingyan(2460052, 1385, 0);
        List<String> actualList = Collections.singletonList("Myanmar New Year's Day");
        assertEquals(actualList, thingyan);
    }

    @Test
    public void getHoliday() {
        MyanmarDate myanmarDate = MyanmarDate.of(2460052);
        List<String> thingyan = HolidayCalculator.getHoliday(myanmarDate);
        List<String> actualList = Collections.singletonList("Myanmar နှစ်ဆန်း နေ့");
        assertEquals(actualList, thingyan);
    }

    @Test
    public void isHoliday() {
        MyanmarDate myanmarDate = MyanmarDate.of(2460052);
        boolean isHoliday = HolidayCalculator.isHoliday(myanmarDate);
        Assert.assertTrue("holiday", isHoliday);
    }

    @Test
    public void getAnniversary() {
        MyanmarDate newYearDate = MyanmarDate.of(2017, 1, 1);
        String newYear = HolidayCalculator.getAnniversary(newYearDate, Language.ENGLISH).get(0);
        assertEquals("New Year Day", newYear);

        MyanmarDate aungSanBDDate = MyanmarDate.of(2024, 2, 13);
        String aungSanBD = HolidayCalculator.getAnniversary(aungSanBDDate, Language.ENGLISH).get(0);
        assertEquals("G. Aung San BD", aungSanBD);
    }
}
