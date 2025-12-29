package mmcalendar;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

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
    public void thingyanHoliday() {
        for (int day = 11; day < 20; day++) {
            MyanmarDate myanmarDate = MyanmarDate.of(2026, 4, day);
            List<String> holiday = HolidayCalculator.thingyan(myanmarDate.getJulianDayNumber(), myanmarDate.getYearValue(), myanmarDate.getMonthType());
            assertFalse(holiday.isEmpty());
        }
    }

    @Test
    public void getHoliday() {
        MyanmarDate myanmarDate = MyanmarDate.of(2460052);
        List<String> thingyan = HolidayCalculator.getHoliday(myanmarDate);
        List<String> actualList = Collections.singletonList("မြန်မာ နှစ်ဆန်း နေ့");
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

        MyanmarDate aungSanBDDate = MyanmarDate.of(2026, 2, 13);
        String aungSanBD = HolidayCalculator.getAnniversary(aungSanBDDate, Language.ENGLISH).get(0);
        assertEquals("G. Aung San BD", aungSanBD);
    }

    @Test
    public void continuousHolidayTest() {
        List<String> actualList = Collections.singletonList("Holiday");
        assertEquals(actualList, HolidayCalculator.continuousHoliday(2024, 12, 31));
        assertEquals(actualList, HolidayCalculator.continuousHoliday(2025, 3, 12));
        assertEquals(actualList, HolidayCalculator.continuousHoliday(2025, 3, 14));
        assertEquals(actualList, HolidayCalculator.continuousHoliday(2025, 11, 3));
        assertEquals(actualList, HolidayCalculator.continuousHoliday(2025, 12, 26));
        assertEquals(actualList, HolidayCalculator.continuousHoliday(2026, 2, 13));
    }

    @Test
    public void isNewYearHoliday() {
        MyanmarDate myanmarDate = MyanmarDate.of(2026, 1, 2);
        boolean isHoliday = HolidayCalculator.isHoliday(myanmarDate);
        Assert.assertTrue("holiday", isHoliday);
    }


    @Test
    public void isChineseNewYearHoliday() {
        Assert.assertTrue("holiday", HolidayCalculator.isHoliday(MyanmarDate.of(2026, 2, 16)));
        Assert.assertTrue("holiday", HolidayCalculator.isHoliday(MyanmarDate.of(2026, 2, 17)));
    }

    @Test
    public void holidayIn2026() {
        // raw input
        Set<String> holidayList = Stream.of(
                "2026-01-01", "2026-01-02", "2026-01-04",
                "2026-02-12", "2026-02-13", "2026-02-16", "2026-02-17",
                "2026-03-02", "2026-03-27",
                "2026-04-11", "2026-04-12", "2026-04-13", "2026-04-14",
                "2026-04-15", "2026-04-16", "2026-04-17", "2026-04-18",
                "2026-04-19", "2026-04-30",
                "2026-05-01",
                "2026-07-19", "2026-07-29",
                "2026-10-25", "2026-10-26", "2026-10-27",
                "2026-11-23", "2026-11-24",
                "2026-12-04", "2026-12-25"
        ).collect(Collectors.toSet());

        // parse once into LocalDate set for fast contains checks
        Set<LocalDate> holidayDates = holidayList.stream()
                .map(LocalDate::parse) // uses ISO_LOCAL_DATE ("yyyy-MM-dd")
                .collect(Collectors.toSet());

        final int year = 2026;
        LocalDate yearStart = LocalDate.of(year, 1, 1);
        LocalDate yearEnd = LocalDate.of(year, 12, 31);

        // 1) Every listed holiday MUST be detected as holiday
        for (LocalDate hd : holidayDates) {
            MyanmarDate md = MyanmarDate.of(hd);
            boolean isHoliday = HolidayCalculator.isHoliday(md);
            Assert.assertTrue("Expected holiday for date: " + hd, isHoliday);
        }

        // 2) Every other day in the year that is NOT in the list MUST NOT be a holiday
        for (LocalDate date = yearStart; !date.isAfter(yearEnd); date = date.plusDays(1)) {
            if (!holidayDates.contains(date)) {
                MyanmarDate md = MyanmarDate.of(date);
                boolean isHoliday = HolidayCalculator.isHoliday(md);
                Assert.assertFalse("Unexpected holiday for date: " + date, isHoliday);
            }
        }
    }
}
