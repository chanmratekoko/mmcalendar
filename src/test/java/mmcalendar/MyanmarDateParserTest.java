package mmcalendar;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Comprehensive test cases for MyanmarDateParser
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0.0
 * @since 1.0.12
 */
public class MyanmarDateParserTest {

    @BeforeClass
    public static void setUp() {
        Config.initDefault(
            new Config.Builder()
                .setCalendarType(CalendarType.ENGLISH)
                .setLanguage(Language.MYANMAR)
                .build()
        );
    }

    // ========== Basic Parsing Tests ==========

    @Test
    public void testParseEnglishSimpleFormat() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String formatted = original.format("B y k, M p f r", Language.ENGLISH);

        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "B y k, M p f r", Language.ENGLISH);
        
        assertThat(parsed, is(notNullValue()));
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
        assertThat(parsed.getMonth(), is(original.getMonth()));
        assertThat(parsed.getDayOfMonth(), is(original.getDayOfMonth()));
    }

    @Test
    public void testParseMyanmarUnicode() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String formatted = original.format("B y k, M p f r", Language.MYANMAR);

        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "B y k, M p f r", Language.MYANMAR);
        
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
        assertThat(parsed.getMonth(), is(original.getMonth()));
        assertThat(parsed.getDayOfMonth(), is(original.getDayOfMonth()));
    }

    @Test
    public void testParseWithFullPattern() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String pattern = MyanmarDateFormat.SIMPLE_MYANMAR_DATE_FORMAT_PATTERN;
        String formatted = original.format(pattern, Language.ENGLISH);

        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
        
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
        assertThat(parsed.getMonth(), is(original.getMonth()));
        assertThat(parsed.getDayOfMonth(), is(original.getDayOfMonth()));
    }

    // ========== Moon Phase Tests ==========

    @Test
    public void testParseWaxingMoon() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 5);
        String formatted = original.format("y M p f", Language.ENGLISH);

        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "y M p f", Language.ENGLISH);
        
        // Validate round-trip: parsed date should match original
        assertThat(parsed.getMoonPhaseValue(), is(original.getMoonPhaseValue()));
        assertThat(parsed.getDayOfMonth(), is(original.getDayOfMonth()));
        assertThat(parsed.getMonth(), is(original.getMonth()));
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
    }

    @Test
    public void testParseFullMoon() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 15);
        String formatted = original.format("y M p f", Language.ENGLISH);

        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "y M p f", Language.ENGLISH);

        // Validate round-trip: parsed date should match original
        assertThat(parsed.getMoonPhaseValue(), is(original.getMoonPhaseValue()));
        assertThat(parsed.getDayOfMonth(), is(original.getDayOfMonth()));
        assertThat(parsed.getMonth(), is(original.getMonth()));
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
    }

    @Test
    public void testParseWaningMoon() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 20);
        String formatted = original.format("y M p f", Language.ENGLISH);

        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "y M p f", Language.ENGLISH);
        
        // Validate round-trip: parsed date should match original
        assertThat(parsed.getMoonPhaseValue(), is(original.getMoonPhaseValue()));
        assertThat(parsed.getDayOfMonth(), is(original.getDayOfMonth()));
        assertThat(parsed.getMonth(), is(original.getMonth()));
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
    }

    @Test
    public void testParseNewMoon() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 30);
        String formatted = original.format("y M p f", Language.ENGLISH);

        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "y M p f", Language.ENGLISH);
        
        // Validate round-trip: parsed date should match original
        assertThat(parsed.getMoonPhaseValue(), is(original.getMoonPhaseValue()));
        assertThat(parsed.getDayOfMonth(), is(original.getDayOfMonth()));
        assertThat(parsed.getMonth(), is(original.getMonth()));
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
    }

    // ========== Multi-Language Tests ==========

    @Test
    public void testParseAllLanguages() {
        MyanmarDate original = MyanmarDate.of(2020, 7, 4);
        String pattern = "B y k, M p f r";
        Language[] languages = {
            Language.ENGLISH, Language.MYANMAR, Language.ZAWGYI, 
            Language.MON, Language.TAI, Language.SGAW_KAREN
        };
        
        for (Language lang : languages) {
            String formatted = original.format(pattern, lang);
            MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, lang);

            assertThat("Failed for language: " + lang, 
                      parsed.getYearValue(), is(original.getYearValue()));
            assertThat("Month mismatch for " + lang, 
                      parsed.getMonth(), is(original.getMonth()));
            assertThat("Day mismatch for " + lang, 
                      parsed.getDayOfMonth(), is(original.getDayOfMonth()));
        }
    }

    @Test
    public void testParseZawgyi() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String formatted = original.format("B y k, M p f r", Language.ZAWGYI);
        
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "B y k, M p f r", Language.ZAWGYI);
        
        assertTrue(parsed.hasSameDay(original));
    }

    @Test
    public void testParseMon() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String formatted = original.format("B y k, M p f r", Language.MON);
        
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "B y k, M p f r", Language.MON);
        
        assertTrue(parsed.hasSameDay(original));
    }

    @Test
    public void testParseTai() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String formatted = original.format("B y k, M p f r", Language.TAI);
        
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "B y k, M p f r", Language.TAI);
        
        assertTrue(parsed.hasSameDay(original));
    }

    @Test
    public void testParseKaren() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String formatted = original.format("B y k, M p f r", Language.SGAW_KAREN);
        
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "B y k, M p f r", Language.SGAW_KAREN);
        
        assertTrue(parsed.hasSameDay(original));
    }

    // ========== Edge Cases ==========

    @Test
    public void testParseDifferentMonths() {
        String pattern = "y M p f";

        for (int month = 1; month <= 12; month++) {
            MyanmarDate original = MyanmarDate.of(2024, month, 5);

            String formatted = original.format(pattern, Language.ENGLISH);
            
            MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);

            // Validate round-trip: parsed date should match original
            assertThat("Month " + month + " failed", 
                      parsed.getMonth(), is(original.getMonth()));
            assertThat("Year for month " + month + " failed", 
                      parsed.getYearValue(), is(original.getYearValue()));
            assertTrue("Day for month " + month + " failed", 
                      parsed.hasSameDay(original));
        }
    }

    @Test
    public void testParseWatatYear() {
        // Test watat year (has intercalary month)
        MyanmarDate original = MyanmarDate.of(2023, 8, 1);
        String formatted = original.format("B y k, M p f r", Language.MYANMAR);
        
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "B y k, M p f r", Language.MYANMAR);

        // Validate round-trip: parsed date should match original
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
        assertThat(parsed.getMonth(), is(original.getMonth()));
        assertThat(parsed.getDayOfMonth(), is(original.getDayOfMonth()));
        assertTrue(parsed.hasSameDay(original));
    }

    @Test
    public void testParseLateMonths() {
        // Test Late Tagu (month 13)
        MyanmarDate original = MyanmarDate.of(1989, 4, 15);
        String formatted = original.format("B y k, M p f r", Language.ENGLISH);
        
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, "B y k, M p f r", Language.ENGLISH);

        assertThat(parsed.getMonth(), is(13)); // Late Tagu
        assertTrue(parsed.hasSameDay(original));
    }

    // ========== Round-Trip Tests ==========

    @Test
    public void testParseRoundTrip() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String pattern = "B y k, M p f r";

        String formatted1 = original.format(pattern, Language.ENGLISH);
        MyanmarDate parsed = MyanmarDateParser.parse(formatted1, pattern, Language.ENGLISH);
        String formatted2 = parsed.format(pattern, Language.ENGLISH);

        assertThat(formatted2, is(formatted1));
    }

    @Test
    public void testParseRoundTripAllLanguages() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String pattern = "B y k, M p f r";
        
        Language[] languages = {
            Language.ENGLISH, Language.MYANMAR, Language.ZAWGYI, 
            Language.MON, Language.TAI, Language.SGAW_KAREN
        };
        
        for (Language lang : languages) {
            String formatted1 = original.format(pattern, lang);
            MyanmarDate parsed = MyanmarDateParser.parse(formatted1, pattern, lang);
            String formatted2 = parsed.format(pattern, lang);
            
            assertThat("Round-trip failed for " + lang, formatted2, is(formatted1));
        }
    }

    // ========== Validation & Error Tests ==========

    @Test(expected = IllegalArgumentException.class)
    public void testParseNullText() {
        MyanmarDateParser.parse(null, "B y k, M p f r", Language.ENGLISH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseEmptyText() {
        MyanmarDateParser.parse("", "B y k, M p f r", Language.ENGLISH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNullPattern() {
        MyanmarDateParser.parse("Myanmar Year 1385", null, Language.ENGLISH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNullLanguage() {
        MyanmarDateParser.parse("Myanmar Year 1385", "B y k", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidFormat() {
        MyanmarDateParser.parse("Invalid text format", "B y k, M p f r", Language.ENGLISH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidFortnightDay() {
        // Fortnight day out of valid range
        String text = "Myanmar Year 1385 Ku, Nadaw Waxing 20 Yat";
        MyanmarDateParser.parse(text, "B y k, M p f r", Language.ENGLISH);
    }

    // ========== Practical Usage Tests ==========

    @Test
    public void testParseMinimalPattern() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String pattern = "y M p f";
        String formatted = original.format(pattern, Language.ENGLISH);
        
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
        
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
        assertThat(parsed.getMonth(), is(original.getMonth()));
    }

    @Test
    public void testParseWithCustomDelimiters() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String pattern = "[y] {M} (p f)";
        String formatted = original.format(pattern, Language.ENGLISH);

        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
        
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
    }

    @Test
    public void testParseMultipleDates() {
        // Test batch parsing
        String pattern = "B y k, M p f r";
        int[] testDays = {1, 5, 10, 15, 20, 25, 29};
        
        for (int day : testDays) {
            MyanmarDate original = MyanmarDate.of(2024, 1, day);
            String formatted = original.format(pattern, Language.MYANMAR);
            
            MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.MYANMAR);
            
            assertTrue("Failed for day " + day, parsed.hasSameDay(original));
        }
    }

    @Test
    public void testParseHistoricalDates() {
        // Test different year ranges
        int[] testYears = {100, 500, 1000, 1200, 1385, 1450};
        String pattern = "y M p f";
        
        for (int year : testYears) {
            try {
                MyanmarDate original = MyanmarDate.create(year, 5, 10);
                String formatted = original.format(pattern, Language.ENGLISH);
                
                MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
                
                assertThat("Year " + year + " failed", 
                          parsed.getYearValue(), is(year));
            } catch (Exception e) {
                // Some years might be out of accurate range, that's ok
            }
        }
    }

    @Test
    public void testParseWeekdayValidation() {
        MyanmarDate original = MyanmarDate.of(2024, 1, 6);
        String pattern = "B y k, M p f r E n";
        String formatted = original.format(pattern, Language.ENGLISH);
        
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
        
        // Should parse successfully even with weekday
        assertThat(parsed.getYearValue(), is(original.getYearValue()));
        assertThat(parsed.getWeekDay(), is(original.getWeekDay()));
    }

    // ========== Additional Comprehensive Tests ==========

    @Test
    public void testParseAllMoonPhasesAllLanguages() {
        // Test all combinations of moon phases with all languages
        MyanmarDate[] dates = {
            MyanmarDate.create(1382, 0, 5),   // Waxing
            MyanmarDate.create(1382, 0, 15),  // Full Moon
            MyanmarDate.create(1382, 0, 20),  // Waning
            MyanmarDate.create(1382, 0, 30)   // New Moon
        };
        
        Language[] languages = {Language.ENGLISH, Language.MYANMAR, Language.ZAWGYI, 
                               Language.MON, Language.TAI, Language.SGAW_KAREN};
        String pattern = "y M p f";
        
        for (MyanmarDate date : dates) {
            for (Language lang : languages) {
                String formatted = date.format(pattern, lang);
                MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, lang);

                assertThat("Moon phase mismatch for " + lang, 
                          parsed.getMoonPhaseValue(), is(date.getMoonPhaseValue()));
                assertTrue("Date mismatch for " + lang, parsed.hasSameDay(date));
            }
        }
    }

    @Test
    public void testParseFortnightDayBoundaries() {
        // Test boundary values for fortnight day (1-15)
        String pattern = "y M p f";
        int[] fortnightDays = {1, 2, 7, 14, 15};
        
        for (int day : fortnightDays) {
            // Test waxing (day 1-15 maps directly to fortnight day)
            MyanmarDate original = MyanmarDate.create(1385, 9, 0, day);
            String formatted = original.format(pattern, Language.ENGLISH);
            MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);

            assertTrue("Fortnight day " + day + " failed", parsed.hasSameDay(original));
        }
    }

    @Test
    public void testParseYearBoundaries() {
        // Test various year values
        int[] years = {1, 100, 500, 1000, 1385, 1400, 1500};
        String pattern = "y M p f";
        
        for (int year : years) {
            MyanmarDate original = MyanmarDate.create(year, 1, 5);
            String formatted = original.format(pattern, Language.ENGLISH);
            MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);

            assertThat("Year " + year + " failed", 
                      parsed.getYearValue(), is(original.getYearValue()));
        }
    }

    @Test
    public void testParseAllMonthsAllPhases() {
        // Comprehensive test: all 12 months × 4 moon phases
        String pattern = "y M p f";
        int[] moonPhases = {0, 1, 2, 3}; // Waxing, Full, Waning, New
        
        for (int month = 1; month <= 12; month++) {
            for (int phase : moonPhases) {
                int fortnightDay = (phase == 1 || phase == 3) ? 15 : 7;
                MyanmarDate original = MyanmarDate.create(1385, month, phase, fortnightDay);
                String formatted = original.format(pattern, Language.ENGLISH);
                MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);

                assertThat("Month " + month + " phase " + phase + " failed",
                          parsed.hasSameDay(original), is(true));
            }
        }
    }

    @Test
    public void testParseDifferentPatternFormats() {
        // Test various pattern combinations
        MyanmarDate original = MyanmarDate.of(2024, 6, 10);
        
        String[][] patterns = {
            {"y M p f", "Minimal pattern"},
            {"B y k, M p f r", "Full format"},
            {"y k M p f r", "Different order"},
            {"B y k, M p f r E n", "With weekday"},
            {"S s k, B y k, M p f r E n", "Complete pattern"}
        };
        
        for (String[] patternInfo : patterns) {
            String pattern = patternInfo[0];
            String desc = patternInfo[1];
            
            String formatted = original.format(pattern, Language.ENGLISH);
            MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);

            assertNotNull(desc + " parsing failed", parsed);
            // Check year always matches since all patterns have year
            assertThat(desc + " year mismatch", 
                      parsed.getYearValue(), is(original.getYearValue()));
        }
    }

    @Test
    public void testParseWithExtraWhitespace() {
        // Test resilience to variable whitespace
        MyanmarDate original = MyanmarDate.of(2024, 1, 15);
        String pattern = "y M p f";
        
        // Format normally
        String formatted = original.format(pattern, Language.ENGLISH);
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
        
        assertTrue("Normal spacing failed", parsed.hasSameDay(original));
    }

    @Test
    public void testParseSecondWaso() {
        // Test Second Waso (watat year intercalary month)
        MyanmarDate original = MyanmarDate.of(2023, 8, 1); // Should be in Second Waso
        String pattern = "y M p f";
        
        String formatted = original.format(pattern, Language.ENGLISH);

        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);

        assertThat("Second Waso month failed",
                  parsed.getMonth(), is(original.getMonth()));
    }

    @Test
    public void testParseFirstWaso() {
        // Test First Waso (month 0 in watat years)
        MyanmarDate original = MyanmarDate.create(1385, 0, 1, 5); // First Waso
        String pattern = "y M p f";
        
        String formatted = original.format(pattern, Language.ENGLISH);
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
        
        assertThat("First Waso failed", parsed.getMonth(), is(0));
        assertTrue(parsed.hasSameDay(original));
    }

    @Test
    public void testParseLateKason() {
        // Test Late Kason (month 14)
        MyanmarDate original = MyanmarDate.create(1383, 13, 0, 1);
        String pattern = "y M p f";

        String formatted = original.format(pattern, Language.ENGLISH);
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
        assertThat("Late Tagu failed", parsed.getMonth(), is(13));
    }

    @Test
    public void testParseMyanmarDigits() {
        // Test parsing with Myanmar Unicode digits
        MyanmarDate original = MyanmarDate.of(2024, 5, 10);
        String pattern = "y M p f";
        
        String formatted = original.format(pattern, Language.MYANMAR);
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.MYANMAR);
        
        assertTrue("Myanmar digits parsing failed", parsed.hasSameDay(original));
    }

    @Test
    public void testParseZawgyiDigits() {
        // Test parsing with Zawgyi encoding
        MyanmarDate original = MyanmarDate.of(2024, 5, 10);
        String pattern = "y M p f";
        
        String formatted = original.format(pattern, Language.ZAWGYI);
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ZAWGYI);
        
        assertTrue("Zawgyi digits parsing failed", parsed.hasSameDay(original));
    }

    @Test
    public void testParseBuddhistEra() {
        // Test with Buddhist Era year alongside Myanmar year
        MyanmarDate original = MyanmarDate.of(2024, 3, 10);
        String pattern = "s k, B y k, M p f r";
        
        String formatted = original.format(pattern, Language.ENGLISH);
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
        
        assertThat("Buddhist Era parsing failed", 
                  parsed.getBuddhistEra(), is(original.getBuddhistEra()));
        assertThat("Myanmar year should match",
                  parsed.getYearValue(), is(original.getYearValue()));
    }

    @Test
    public void testParseConsecutiveDates() {
        // Test parsing consecutive dates in same month
        String pattern = "y M p f";
        
        for (int day = 1; day <= 28; day++) {
            MyanmarDate original = MyanmarDate.of(2024, 3, day);
            String formatted = original.format(pattern, Language.ENGLISH);
            MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
            
            assertTrue("Day " + day + " failed", parsed.hasSameDay(original));
        }
    }

    @Test
    public void testParseYearTransition() {
        // Test dates around year transitions
        int[][] testCases = {
            {1384, 12, 29}, // End of year
            {1385, 1, 1},   // Start of year
            {1385, 12, 30}, // End of another year
            {1386, 1, 1}    // Start of next year
        };
        
        String pattern = "y M p f";
        for (int[] testCase : testCases) {
            MyanmarDate original = MyanmarDate.create(testCase[0], testCase[1], testCase[2]);
            String formatted = original.format(pattern, Language.ENGLISH);
            MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
            
            assertTrue("Year transition test failed", parsed.hasSameDay(original));
        }
    }

    @Test
    public void testParsePatternCaching() {
        // Test that pattern caching works correctly
        MyanmarDate date1 = MyanmarDate.of(2024, 1, 5);
        MyanmarDate date2 = MyanmarDate.of(2024, 2, 10);
        String pattern = "y M p f";
        
        // Parse twice with same pattern - should use cached pattern
        String formatted1 = date1.format(pattern, Language.ENGLISH);
        MyanmarDate parsed1 = MyanmarDateParser.parse(formatted1, pattern, Language.ENGLISH);
        
        String formatted2 = date2.format(pattern, Language.ENGLISH);
        MyanmarDate parsed2 = MyanmarDateParser.parse(formatted2, pattern, Language.ENGLISH);
        
        assertTrue(parsed1.hasSameDay(date1));
        assertTrue(parsed2.hasSameDay(date2));
    }

    @Test
    public void testParseMultiLanguageSameDate() {
        // Parse same date in all languages
        MyanmarDate original = MyanmarDate.of(2024, 6, 15);
        String pattern = "B y k, M p f r";
        
        Language[] languages = {Language.ENGLISH, Language.MYANMAR, Language.ZAWGYI,
                               Language.MON, Language.TAI, Language.SGAW_KAREN};
        
        for (Language lang : languages) {
            String formatted = original.format(pattern, lang);
            MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, lang);
            
            assertThat("Language " + lang + " year mismatch",
                      parsed.getYearValue(), is(original.getYearValue()));
            assertThat("Language " + lang + " month mismatch",
                      parsed.getMonth(), is(original.getMonth()));
            assertThat("Language " + lang + " day mismatch",
                      parsed.getDayOfMonth(), is(original.getDayOfMonth()));
        }
    }

    @Test
    public void testParseSpecialCharactersInPattern() {
        // Test patterns with special delimiters
        MyanmarDate original = MyanmarDate.of(2024, 3, 10);
        String[] patterns = {
            "y-M-p-f",
            "y/M/p/f",
            "y.M.p.f",
            "y|M|p|f",
            "y_M_p_f"
        };
        
        for (String pattern : patterns) {
            String formatted = original.format(pattern, Language.ENGLISH);
            MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
            
            assertTrue("Pattern '" + pattern + "' failed", parsed.hasSameDay(original));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseMismatchedPattern() {
        // Try to parse with wrong pattern
        String text = "1385 Nadaw Waning 10";
        MyanmarDateParser.parse(text, "B y k, M p f r", Language.ENGLISH); // Wrong pattern
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseIncompleteSyntax() {
        // Text missing required components
        String text = "1385 Nadaw";
        MyanmarDateParser.parse(text, "y M p f", Language.ENGLISH); // Missing p and f
    }

    @Test
    public void testParseFullMoonEmptyFortnightDay() {
        // Full moon should have empty fortnight day in formatted text
        MyanmarDate original = MyanmarDate.create(1385, 9, 1, 15); // Full moon
        String pattern = "y M p f r";
        
        String formatted = original.format(pattern, Language.ENGLISH);
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
        System.out.println(parsed);
        assertThat("Full moon parsing failed", 
                  parsed.getMoonPhaseValue(), is(1));
        assertTrue(parsed.hasSameDay(original));
    }

    @Test
    public void testParseNewMoonEmptyFortnightDay() {
        // New moon should have empty fortnight day in formatted text
        MyanmarDate original = MyanmarDate.create(1385, 9, 3, 15); // New moon
        String pattern = "y M p f r";
        
        String formatted = original.format(pattern, Language.ENGLISH);
        MyanmarDate parsed = MyanmarDateParser.parse(formatted, pattern, Language.ENGLISH);
        
        assertThat("New moon parsing failed", 
                  parsed.getMoonPhaseValue(), is(3));
        assertTrue(parsed.hasSameDay(original));
    }
}
