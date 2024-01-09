package mmcalendar;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class JavaDateTimeTest {

    @Test
    public void checkCurrentCalendarDateInMyanmar() {
        MyanmarDate myanmarDate = MyanmarDate.now();
        Assert.assertEquals(1385, myanmarDate.getYearValue());
    }

    @Test
    public void checkWithSystemTimeCalendar() {
        LocalDateTime localDateTime = LocalDateTime.now();
        MyanmarDate myanmarDate = MyanmarDate.of(localDateTime);
        Assert.assertEquals(1385, myanmarDate.getYearValue());

        WesternDate westernDate = WesternDate.of(myanmarDate.getJulianDayNumber(), CalendarType.ENGLISH);
        Assert.assertEquals(2024, westernDate.getYear());
    }

}
