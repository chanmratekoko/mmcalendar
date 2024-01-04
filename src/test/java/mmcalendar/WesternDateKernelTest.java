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
    }

    @Test
    public void getJulianDayNumberOfEndOfMonth() {
        assertThat(2460004, is(WesternDateKernel.getJulianDayNumberOfEndOfMonth(2023, 2)));
        assertThat(2460370, is(WesternDateKernel.getJulianDayNumberOfEndOfMonth(2024, 2)));
    }

}
