package mmcalendar;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class WesternDateTest {

    @Test
    public void ofMyanmarDate() {
        MyanmarDate myanmarDate = MyanmarDate.of(2024, 1, 1);
        WesternDate westernDate = WesternDate.of(myanmarDate);
        assertThat(2024, is(westernDate.getYear()));
        assertThat(1, is(westernDate.getMonth()));
        assertThat(1, is(westernDate.getDay()));
        assertThat(12, is(westernDate.getHour()));
        assertThat(0, is(westernDate.getMinute()));
        assertThat(0, is(westernDate.getSecond()));

        WesternDate westernDate1 = WesternDate.of(myanmarDate, CalendarType.ENGLISH);
        assertEquals(westernDate, westernDate1);
    }

    @Test
    public void of() {

        double jd = 2457857.7058912036;
        WesternDate westernDate = WesternDate.of(jd, Config.getInstance().getCalendarType());

        assertThat(2017, is(westernDate.getYear()));
        assertThat(4, is(westernDate.getMonth()));
        assertThat(14, is(westernDate.getDay()));
        assertThat(4, is(westernDate.getHour()));
        assertThat(56, is(westernDate.getMinute()));
        assertThat(29, is(westernDate.getSecond()));

        double result = westernDate.toJulian(Config.getInstance().getCalendarType(), 0);

        assertThat(jd, is(result));
    }

    @Test
    public void to() {

        double jd = 2457857.7058912036;
        WesternDate westernDate = WesternDate.of(jd);

        assertThat(2017, is(westernDate.getYear()));
        assertThat(4, is(westernDate.getMonth()));
        assertThat(14, is(westernDate.getDay()));
        assertThat(4, is(westernDate.getHour()));
        assertThat(56, is(westernDate.getMinute()));
        assertThat(29, is(westernDate.getSecond()));

        double result = westernDate.toJulian();

        assertThat(jd, is(result));

    }
}
