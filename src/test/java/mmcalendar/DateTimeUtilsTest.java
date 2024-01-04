package mmcalendar;

import mmcalendar.util.DateTimeUtils;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class DateTimeUtilsTest {

    @Test
    public void toJulian() {
        LocalDate localDate = LocalDate.of(2023, 12, 30);
        double jd = DateTimeUtils.toJulian(localDate);
        assertThat(2460309.0, is(jd));
    }

    @Test
    public void unixTimeToJulian() {
        long unix = 1703895000;
        double jd = DateTimeUtils.unixTimeToJulian(unix);
        assertThat(2460308.5069444445, is(jd));
    }

    @Test
    public void julianToUnixTime() {
        double jd = 2460308.5069444445;
        long unix = DateTimeUtils.julianToUnixTime(jd);
        assertThat(1703895000L, is(unix));
    }

    @Test
    public void u2jVsj2u() {
        long unix = 1703895000;
        double jd = DateTimeUtils.unixTimeToJulian(unix);
        long reverse = DateTimeUtils.julianToUnixTime(jd);
        assertEquals(unix, reverse);
    }

}
