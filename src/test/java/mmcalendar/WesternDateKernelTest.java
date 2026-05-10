package mmcalendar;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WesternDateKernelTest {

    @Test
    public void julianToWestern() {
        WesternDate westernDate = WesternDateKernel.julianToWestern(2459977, CalendarType.ENGLISH);
        assertThat(2023, is(westernDate.getYear()));
        assertThat(2, is(westernDate.getMonth()));
        assertThat(1, is(westernDate.getDay()));
        assertThat(12, is(westernDate.getHour()));
        assertThat(0, is(westernDate.getSecond()));
        assertThat(0, is(westernDate.getSecond()));
    }

    @Test
    public void westernToJulian() {
        double jdn = WesternDateKernel.westernToJulian(2023, 2, 1, 12, 0, 0, CalendarType.ENGLISH, 0.0);
        assertThat(2459977.0, is(jdn));
    }

    @Test
    public void timeToDayFraction() {
        // Fraction of day
        double fd = WesternDateKernel.timeToDayFractionStartFrom12Noon(12, 2, 1);
        assertThat(0.001400462962962963, is(fd));
    }

    @Test
    public void timeToDayFractionStartFrom12Noon() {
        // Time to Fraction of day
        double aspect = 0.008472222222222221;
        double result = WesternDateKernel.timeToDayFractionStartFrom12Noon(12, 12, 12);
        Assert.assertEquals(aspect, result, 0);
    }

    @Test
    public void getJulianDayNumberOfStartOfMonth() {
        assertThat(2459977, is(WesternDateKernel.getJulianDayNumberOfStartOfMonth(2023, 2)));
        assertThat(2460342, is(WesternDateKernel.getJulianDayNumberOfStartOfMonth(2024, 2)));
    }

    @Test
    public void getLengthOfMonth() {
        assertThat(28, is(WesternDateKernel.getLengthOfMonth(2023, 2, 0)));
        assertThat(29, is(WesternDateKernel.getLengthOfMonth(2024, 2, 0)));
        assertThat(19, is(WesternDateKernel.getLengthOfMonth(1752 , 9, 0)));
        assertThat(29, is(WesternDateKernel.getLengthOfMonth(1600 , 2, 0)));
    }

    @Test
    public void getJulianDayNumberOfEndOfMonth() {
        assertThat(2460004, is(WesternDateKernel.getJulianDayNumberOfEndOfMonth(2023, 2)));
        assertThat(2460370, is(WesternDateKernel.getJulianDayNumberOfEndOfMonth(2024, 2)));
    }

    // --- Julian calendar path and edge case tests ---

    @Test
    public void julianToWesternJulianCalendar() {
        // calType=2 (Julian calendar) - exercises the Julian calendar code path
        WesternDate wd = WesternDateKernel.julianToWestern(2299161, CalendarType.JULIAN);
        Assert.assertNotNull(wd);
        Assert.assertTrue(wd.getYear() > 0);
    }

    @Test
    public void julianToWesternGregorian() {
        // calType=1 (Gregorian) - exercises the Gregorian calendar code path
        WesternDate wd = WesternDateKernel.julianToWestern(2459977, CalendarType.GREGORIAN);
        assertThat(2023, is(wd.getYear()));
        assertThat(2, is(wd.getMonth()));
        assertThat(1, is(wd.getDay()));
    }

    @Test
    public void getLengthOfMonthGregorianCenturyRules() {
        // 1900 is not a leap year in Gregorian (divisible by 100 but not 400)
        assertThat(28, is(WesternDateKernel.getLengthOfMonth(1900, 2, 1)));
        // 2000 is a leap year in Gregorian (divisible by 400)
        assertThat(29, is(WesternDateKernel.getLengthOfMonth(2000, 2, 1)));
    }

    @Test
    public void getLengthOfMonthJulianCalendar() {
        // In Julian calendar (calType=2), every year divisible by 4 is leap
        assertThat(29, is(WesternDateKernel.getLengthOfMonth(1900, 2, 2)));
    }

    @Test
    public void westernToJulianGregorianGap() {
        // September 14, 1752 is the first Gregorian day in English calendar
        double jdnBefore = WesternDateKernel.westernToJulian(1752, 9, 2, CalendarType.ENGLISH, 0);
        double jdnAfter = WesternDateKernel.westernToJulian(1752, 9, 14, CalendarType.ENGLISH, 0);
        // The gap should be 1 day in JDN (Sep 2 -> Sep 14 = 1 day jump)
        Assert.assertEquals(1.0, jdnAfter - jdnBefore, 0.001);
    }

}
