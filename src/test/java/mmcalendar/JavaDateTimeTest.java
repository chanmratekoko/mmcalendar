package mmcalendar;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class JavaDateTimeTest {

    @Test
    public void checkCurrentCalendarDateInMyanmar() {
        MyanmarDate myanmarDate = MyanmarDate.now();
        Assert.assertTrue(myanmarDate.getYearValue() >= 1387);
    }

    @Test
    public void checkWithSystemTimeCalendar() {
        LocalDateTime localDateTime = LocalDateTime.now();
        MyanmarDate myanmarDate = MyanmarDate.of(localDateTime);
        Assert.assertTrue(myanmarDate.getYearValue() >= 1387);

        WesternDate westernDate = WesternDate.of(myanmarDate.getJulianDayNumber(), CalendarType.ENGLISH);
        Assert.assertTrue(westernDate.getYear() >= 2025);
    }

}
