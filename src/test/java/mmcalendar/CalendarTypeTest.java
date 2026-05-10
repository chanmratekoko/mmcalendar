package mmcalendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalendarTypeTest {

    @Test
    public void englishCalendarType() {
        assertEquals(0, CalendarType.ENGLISH.getNumber());
        assertEquals("English", CalendarType.ENGLISH.getLabel());
    }

    @Test
    public void gregorianCalendarType() {
        assertEquals(1, CalendarType.GREGORIAN.getNumber());
        assertEquals("Gregorian", CalendarType.GREGORIAN.getLabel());
    }

    @Test
    public void julianCalendarType() {
        assertEquals(2, CalendarType.JULIAN.getNumber());
        assertEquals("Julian", CalendarType.JULIAN.getLabel());
    }

    @Test
    public void valuesContainsAllTypes() {
        CalendarType[] values = CalendarType.values();
        assertEquals(3, values.length);
    }

    @Test
    public void valueOfReturnsCorrectEnum() {
        assertEquals(CalendarType.ENGLISH, CalendarType.valueOf("ENGLISH"));
        assertEquals(CalendarType.GREGORIAN, CalendarType.valueOf("GREGORIAN"));
        assertEquals(CalendarType.JULIAN, CalendarType.valueOf("JULIAN"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void valueOfThrowsForInvalidName() {
        CalendarType.valueOf("INVALID");
    }
}
