package mmcalendar;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

import static mmcalendar.Constants.MYANMAR_ZONE_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MyanmarDateTest {

    @BeforeClass
    public static void beforeClass() {

        Config.initDefault(
                new Config.Builder()
                        .setCalendarType(CalendarType.ENGLISH)
                        .setLanguage(Language.MYANMAR)
                        .build());

    }

    // execute after class
    @AfterClass
    public static void afterClass() {
        Config.initDefault(
                new Config.Builder()
                        .setCalendarType(CalendarType.ENGLISH)
                        .setLanguage(Language.MYANMAR)
                        .build());
    }

    @Test
    public void outputsInEnglish() {
        MyanmarDate myanmarDate = MyanmarDate.of(1989, 4, 15);
        final String format = "S s k, B y k, M p f r E n";
        Language language = Language.ENGLISH;
        String aspectMyanmarResult = "Sasana Year 2532 Ku, Myanmar Year 1350 Ku, Late Tagu Waxing 10 Yat Saturday Nay";
        Assert.assertEquals(aspectMyanmarResult, myanmarDate.format(format, language));
    }

    @Test
    public void outputsInMyanmarBurmese() {
        MyanmarDate myanmarDate = MyanmarDate.of(1989, 4, 15);
        final String format = "S s k, B y k, M p f r En";
        Language language = Language.MYANMAR;
        String aspectMyanmarResult = "သာသနာနှစ် ၂၅၃၂ ခု, မြန်မာနှစ် ၁၃၅၀ ခု, နှောင်းတန်ခူး လဆန်း ၁၀ ရက် စနေနေ့";
        Assert.assertEquals(aspectMyanmarResult, myanmarDate.format(format, language));
        assertTrue(myanmarDate.isWeekend());
        assertEquals("Late", myanmarDate.getMnt(Language.ENGLISH));
    }

    @Test
    public void objectCreation() {
        LocalDateTime localDateTime = LocalDateTime.now(MYANMAR_ZONE_ID);
        MyanmarDate myanmarDate = MyanmarDate.of(localDateTime);
        MyanmarDate myanmarDate2 = MyanmarDate.create(myanmarDate.getYearValue(), myanmarDate.getMonthName(Language.ENGLISH), myanmarDate.getMoonPhase(Language.ENGLISH), myanmarDate.getFortnightDayValue());
        assertTrue(myanmarDate.hasSameDay(myanmarDate2));
        MyanmarDate myanmarDate3 = MyanmarDate.of(myanmarDate.getJulianDayNumber());
        assertTrue(myanmarDate.hasSameDay(myanmarDate3));

        MyanmarDate myanmarDate4 = MyanmarDate.ofEpochSecond(localDateTime.toEpochSecond(ZoneOffset.ofTotalSeconds(0)), MYANMAR_ZONE_ID);
        assertTrue(myanmarDate.hasSameDay(myanmarDate4));

        Calendar calendar = Calendar.getInstance();
        MyanmarDate myanmarDate5 = MyanmarDate.of(calendar);
        assertTrue(myanmarDate.hasSameDay(myanmarDate5));

        MyanmarDate myanmarDateFromJavaDate = MyanmarDate.of(new Date());
        assertTrue(myanmarDate.hasSameDay(myanmarDateFromJavaDate));

        LocalDateTime returnMyanmarLocalDate = myanmarDate.toMyanmarLocalDateTime();

        assertThat(localDateTime.getYear(), is(returnMyanmarLocalDate.getYear()));
        assertThat(localDateTime.getMonthValue(), is(returnMyanmarLocalDate.getMonthValue()));
        assertThat(localDateTime.getDayOfMonth(), is(returnMyanmarLocalDate.getDayOfMonth()));
        assertThat(localDateTime.getHour(), is(returnMyanmarLocalDate.getHour()));
        assertThat(localDateTime.getMinute(), is(returnMyanmarLocalDate.getMinute()));
        assertThat(localDateTime.getSecond(), is(returnMyanmarLocalDate.getSecond()));
    }

    @Test
    public void toLocalDateTimeTest() {
        LocalDateTime localDateTime = LocalDateTime.now(MYANMAR_ZONE_ID);
        MyanmarDate myanmarDate = MyanmarDate.of(localDateTime);

        LocalDateTime returnMyanmarLocalDateTime = myanmarDate.toMyanmarLocalDateTime();

        assertThat(localDateTime.getYear(), is(returnMyanmarLocalDateTime.getYear()));
        assertThat(localDateTime.getMonthValue(), is(returnMyanmarLocalDateTime.getMonthValue()));
        assertThat(localDateTime.getDayOfMonth(), is(returnMyanmarLocalDateTime.getDayOfMonth()));
        assertThat(localDateTime.getHour(), is(returnMyanmarLocalDateTime.getHour()));
        assertThat(localDateTime.getMinute(), is(returnMyanmarLocalDateTime.getMinute()));
        assertThat(localDateTime.getSecond(), is(returnMyanmarLocalDateTime.getSecond()));

        LocalDateTime localDateTimeMM = myanmarDate.toLocalDateTime(MYANMAR_ZONE_ID);
        assertEquals(returnMyanmarLocalDateTime, localDateTimeMM);

        LocalDate returnMyanmarLocalDate = myanmarDate.toMyanmarLocalDate();
        LocalDate localDate = myanmarDate.toLocalDate(MYANMAR_ZONE_ID);

        assertEquals(returnMyanmarLocalDate, localDate);
    }

    @Test
    public void timeTest() {
        LocalDateTime localDateTime = LocalDateTime.now(MYANMAR_ZONE_ID);
        MyanmarDate myanmarDate = MyanmarDate.of(localDateTime, MYANMAR_ZONE_ID);

        assertThat(localDateTime.getHour(), is(myanmarDate.getHour()));
        assertThat(localDateTime.getMinute(), is(myanmarDate.getMinute()));
        assertThat(localDateTime.getSecond(), is(myanmarDate.getSecond()));
    }

    @Test
    public void fieldTest() {
        // 2023-August-01
        MyanmarDate myanmarDate = MyanmarDate.of(2460158);
        assertThat(2567, is(myanmarDate.getBuddhistEraValue()));
        assertEquals("၂၅၆၇", myanmarDate.getBuddhistEra());
        assertEquals("၁၃၈၅", myanmarDate.getYear());
        assertEquals("ဒု", myanmarDate.getMnt());
        assertEquals("ဒု", myanmarDate.getMnt(Language.MYANMAR));
        assertEquals("ဒု ဝါဆို", myanmarDate.getMonthName(Language.MYANMAR));
        assertThat(385, is(myanmarDate.lengthOfYear()));
        assertEquals("လပြည့်", myanmarDate.getMoonPhase());
        assertEquals("", myanmarDate.getFortnightDay());
        assertEquals("အင်္ဂါ", myanmarDate.getWeekDay());
        assertFalse(myanmarDate.isWeekend());
    }

    @Test
    public void formatTest() {
        MyanmarDate myanmarDate = MyanmarDate.of(2024, 1, 6);
        String format = "B y k၊ M p f r E n";
        String expected = "မြန်မာနှစ် ၁၃၈၅ ခု၊ နတ်တော် လဆုတ် ၁၀ ရက် စနေ နေ့";
        assertEquals(expected, myanmarDate.format(format, Language.MYANMAR));
        assertEquals(expected, myanmarDate.format(format));
    }

    @Test
    public void formatExceptionTest() {
        MyanmarDate myanmarDate = MyanmarDate.of(2024, 1, 6);
        assertThrows(IllegalArgumentException.class, () -> {
            myanmarDate.format(null, Language.MYANMAR);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            myanmarDate.format("s", null);
        });
    }

    @Test
    public void hasSameDay(){
        MyanmarDate myanmarDate1 = MyanmarDate.of(2024, 1, 6);
        MyanmarDate myanmarDate2 = MyanmarDate.of(2024, 1, 7);
        assertFalse(myanmarDate1.hasSameDay(myanmarDate2));

        MyanmarDate myanmarDate3 = MyanmarDate.of(2024, 1, 6);
        MyanmarDate myanmarDate4 = MyanmarDate.of(2024, 2, 6);
        assertFalse(myanmarDate3.hasSameDay(myanmarDate4));

        MyanmarDate myanmarDate5 = MyanmarDate.of(2023, 1, 6);
        MyanmarDate myanmarDate6 = MyanmarDate.of(2024, 1, 6);
        assertFalse(myanmarDate5.hasSameDay(myanmarDate6));
    }

    @Test
    public void hashEqual(){
        MyanmarDate myanmarDate1 = MyanmarDate.of(2024, 1, 6);
        assertThat(806872610, is(myanmarDate1.hashCode()));
        MyanmarDate myanmarDate2 = MyanmarDate.of(2460316);
        assertEquals(myanmarDate1, myanmarDate2);
    }

    @Test
    public void startMyanmarDate() {
        MyanmarDate myanmarDate = MyanmarDate.of(1954900.0);
        String expected = "သာသနာနှစ် ၁၁၈၄ ခု, မြန်မာနှစ် ၂ ခု, တန်ခူး လဆုတ် ၁၁ ရက် ကြာသပတေး နေ့";
        assertEquals(expected, myanmarDate.toString());
        assertEquals("0640-03-23", myanmarDate.toMyanmarLocalDate().toString());

    }

    @Test
    public void beforeSkipDay() {
        LocalDateTime startMyanmarLocalDate = LocalDateTime.of(640, 3, 23, 12, 0);

        LocalDateTime localDateTime = LocalDateTime.of(1752, 9, 2, 12, 0);

        while (startMyanmarLocalDate.isBefore(localDateTime)) {

            startMyanmarLocalDate = startMyanmarLocalDate.plusDays(1);
            MyanmarDate aspectedMyanmarDate = MyanmarDate.of(startMyanmarLocalDate);
            WesternDate wd = WesternDate.of(aspectedMyanmarDate.getJulianDayNumber());
            assertThat(startMyanmarLocalDate.getYear(), is(wd.getYear()));
            assertThat(startMyanmarLocalDate.getMonthValue(), is(wd.getMonth()));
            assertThat(startMyanmarLocalDate.getDayOfMonth(), is(wd.getDay()));
            assertThat(startMyanmarLocalDate.getHour(), is(wd.getHour()));
            assertThat(startMyanmarLocalDate.getMinute(), is(wd.getMinute()));
            assertThat(startMyanmarLocalDate.getSecond(), is(wd.getSecond()));
        }
    }

    @Test
    public void afterSkipDay() {
        // To correct this, the Gregorian calendar was introduced by Pope Gregory XIII in 1582.
        // The adjustment involved skipping 11 days to bring the calendar back in line with the astronomical year.
        // In September 1752, the dates were adjusted by omitting the period from September 3rd to September 13th.
        // So, in that year, September 2nd was followed by September 14th, effectively aligning the calendar with the new Gregorian system.
        LocalDateTime startMyanmarLocalDate = LocalDateTime.of(1752, 9, 14, 12, 0);

        LocalDateTime localDateTime = LocalDateTime.now(MYANMAR_ZONE_ID);

        while (startMyanmarLocalDate.isBefore(localDateTime)) {

            startMyanmarLocalDate = startMyanmarLocalDate.plusDays(1);
            MyanmarDate aspectedMyanmarDate = MyanmarDate.of(startMyanmarLocalDate);
            WesternDate wd = WesternDate.of(aspectedMyanmarDate.getJulianDayNumber());

            assertThat(startMyanmarLocalDate.getYear(), is(wd.getYear()));
            assertThat(startMyanmarLocalDate.getMonthValue(), is(wd.getMonth()));
            assertThat(startMyanmarLocalDate.getDayOfMonth(), is(wd.getDay()));
            assertThat(startMyanmarLocalDate.getHour(), is(wd.getHour()));
            assertThat(startMyanmarLocalDate.getMinute(), is(wd.getMinute()));
            assertThat(startMyanmarLocalDate.getSecond(), is(wd.getSecond()));
        }
    }
}
