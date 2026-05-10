package mmcalendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyanmarDateFormatTest {

    @Test
    public void formatCharConstants() {
        assertEquals('S', MyanmarDateFormat.SASANA_YEAR);
        assertEquals('s', MyanmarDateFormat.BUDDHIST_ERA);
        assertEquals('B', MyanmarDateFormat.BURMESE_YEAR);
        assertEquals('y', MyanmarDateFormat.MYANMAR_YEAR);
        assertEquals('k', MyanmarDateFormat.KU);
        assertEquals('M', MyanmarDateFormat.MONTH_IN_YEAR);
        assertEquals('p', MyanmarDateFormat.MOON_PHASE);
        assertEquals('f', MyanmarDateFormat.FORTNIGHT_DAY);
        assertEquals('E', MyanmarDateFormat.DAY_NAME_IN_WEEK);
        assertEquals('n', MyanmarDateFormat.NAY);
        assertEquals('r', MyanmarDateFormat.YAT);
    }

    @Test
    public void simpleFormatPatternContainsAllTokens() {
        String pattern = MyanmarDateFormat.SIMPLE_MYANMAR_DATE_FORMAT_PATTERN;
        assertEquals("S s k, B y k, M p f r E n", pattern);
        assertTrue(pattern.contains(String.valueOf(MyanmarDateFormat.SASANA_YEAR)));
        assertTrue(pattern.contains(String.valueOf(MyanmarDateFormat.BUDDHIST_ERA)));
        assertTrue(pattern.contains(String.valueOf(MyanmarDateFormat.BURMESE_YEAR)));
        assertTrue(pattern.contains(String.valueOf(MyanmarDateFormat.MYANMAR_YEAR)));
        assertTrue(pattern.contains(String.valueOf(MyanmarDateFormat.MONTH_IN_YEAR)));
        assertTrue(pattern.contains(String.valueOf(MyanmarDateFormat.MOON_PHASE)));
        assertTrue(pattern.contains(String.valueOf(MyanmarDateFormat.FORTNIGHT_DAY)));
        assertTrue(pattern.contains(String.valueOf(MyanmarDateFormat.DAY_NAME_IN_WEEK)));
        assertTrue(pattern.contains(String.valueOf(MyanmarDateFormat.NAY)));
        assertTrue(pattern.contains(String.valueOf(MyanmarDateFormat.YAT)));
    }

    @Test
    public void allFormatCharsAreDistinct() {
        char[] chars = {
            MyanmarDateFormat.SASANA_YEAR,
            MyanmarDateFormat.BUDDHIST_ERA,
            MyanmarDateFormat.BURMESE_YEAR,
            MyanmarDateFormat.MYANMAR_YEAR,
            MyanmarDateFormat.KU,
            MyanmarDateFormat.MONTH_IN_YEAR,
            MyanmarDateFormat.MOON_PHASE,
            MyanmarDateFormat.FORTNIGHT_DAY,
            MyanmarDateFormat.DAY_NAME_IN_WEEK,
            MyanmarDateFormat.NAY,
            MyanmarDateFormat.YAT
        };
        java.util.Set<Character> unique = new java.util.HashSet<>();
        for (char c : chars) {
            assertTrue("Duplicate format char: " + c, unique.add(c));
        }
    }
}
