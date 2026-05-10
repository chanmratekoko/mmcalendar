package mmcalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class ConfigTest {

    @BeforeClass
    public static void beforeClass() {
        Config.getInstance();
    }

    @AfterClass
    public static void afterClass() {
        Config.initDefault(
                new Config.Builder()
                        .setCalendarType(CalendarType.ENGLISH)
                        .setLanguage(Language.MYANMAR)
                        .build()
        );
    }

    @Test
    public void initTest() {
        Config.initDefault(
                new Config.Builder()
                        .setCalendarType(CalendarType.GREGORIAN)
                        .setLanguage(Language.MON)
                        .build()
        );

        CalendarType calendarType = Config.getInstance().getCalendarType();
        Language language = Config.getInstance().getLanguage();

        assertEquals(CalendarType.GREGORIAN, calendarType);
        assertEquals("Gregorian", calendarType.getLabel());
        assertThat(1, is(calendarType.getNumber()));
        assertEquals(Language.MON, language);
    }

    @Test
    public void initFunctional() {
        Config.initDefault(
                it ->
                        it.setCalendarType(CalendarType.JULIAN)
                                .setLanguage(Language.TAI)

        );

        CalendarType calendarType = Config.getInstance().getCalendarType();
        Language language = Config.getInstance().getLanguage();

        assertEquals(CalendarType.JULIAN, calendarType);
        assertEquals(Language.TAI, language);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildExpection() {
        Config.initDefault(
                it ->
                        it.setCalendarType(null)
                                .setLanguage(null)

        );
    }
}
