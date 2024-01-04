package mmcalendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigTest {

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
}
