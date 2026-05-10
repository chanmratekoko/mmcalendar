package mmcalendar;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.DateTimeException;
import java.util.Map;

import static mmcalendar.Constants.EMA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class MyanmarDateKernelTest {

    @BeforeClass
    public static void beforeClass() {
        Config.initDefault(
                new Config.Builder()
                        .setCalendarType(CalendarType.ENGLISH)
                        .setLanguage(Language.ENGLISH)
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
    public void convert() {

        final String format = "S s k, B y k, M p f r E n";

        MyanmarDate myanmarDate = MyanmarDate.of(2017, 7, 24);

        String aspectEnglishResult = "Sasana Year 2561 Ku, Myanmar Year 1379 Ku, Wagaung Waxing 1 Yat Monday Nay";
        Assert.assertEquals(aspectEnglishResult, myanmarDate.toString());

        Language language = Language.MYANMAR;
        String aspectMyanmarResult = "သာသနာနှစ် ၂၅၆၁ ခု, မြန်မာနှစ် ၁၃၇၉ ခု, ဝါခေါင် လဆန်း ၁ ရက် တနင်္လာ နေ့";
        Assert.assertEquals(aspectMyanmarResult, myanmarDate.format(format, language));
    }

    @Test
    public void julianToMyanmarDate() {
        double julianDate = 2458579.0;
        MyanmarDate myanmarDate = MyanmarDateKernel.julianToMyanmarDate(julianDate);
        assertThat(1380, is(myanmarDate.getYearValue()));
        assertThat(13, is(myanmarDate.getMonth()));
        assertThat(1, is(myanmarDate.getDayOfMonth()));
    }

    @Test
    public void checkMyanmarYear() {
        int myear = 1381;
        Map<String, Integer> map = MyanmarDateKernel.checkMyanmarYear(myear);
        assertThat(2458681, is(map.get("fm")));
        assertThat(0, is(map.get("myt")));
        assertThat(0, is(map.get("werr")));
        assertThat(2458579, is(map.get("tg1")));
    }

    @Test
    public void checkWatat() {
        int myear = 1381;
        Map<String, Integer> map = MyanmarDateKernel.checkWatat(myear);
        assertThat(2458711, is(map.get("fm")));
        assertThat(0, is(map.get("watat")));
    }

    @Test
    public void m2j() {
        double julianDate = MyanmarDateKernel.myanmarDateToJulian(1380, 13, 1);
        assertThat(2458579.0, is(julianDate));
    }

    @Test
    public void m2jWithThreeParameters() {
        double aspectJdn = 2458403;
        double julianDay = MyanmarDateKernel.myanmarDateToJulian(1380, 7, 2);
        Assert.assertEquals(aspectJdn, julianDay, 0);
    }

    @Test
    public void getJulianDayNumber() {

        int year = 1380;
        String myanmarMonthName = "Kason";
        int day = 29;

        double julianDayNumber = MyanmarDateKernel.getJulianDayNumber(year, myanmarMonthName, day);

        assertEquals(2458252.0, julianDayNumber, 0.0001);
    }

    @Test(expected = DateTimeException.class)
    public void getJulianDayNumberException() {
        MyanmarDateKernel.getJulianDayNumber(2024, "hello", 1);
    }

    @Test
    public void searchMyanmarMonth() {
        for (int i = 0; i < EMA.length; i++) {
            int monthIndex = MyanmarDateKernel.searchMyanmarMonthNumber(EMA[i]);
            assertThat(i, is(monthIndex));
        }

        assertThat(-1, is(MyanmarDateKernel.searchMyanmarMonthNumber("hello")));
    }

    @Test
    public void searchMoonPhase() {

        String[] moonPhase = {"waxing", "full moon", "waning", "new moon"};
        for (int i = 0; i < moonPhase.length; i++) {
            int index = MyanmarDateKernel.searchMoonPhase(moonPhase[i]);
            assertThat(i, is(index));
        }

        assertThat(-1, is(MyanmarDateKernel.searchMoonPhase("hello")));
    }

    // === checkWatat era tests ===

    @Test
    public void testCheckWatatEra1_1() {
        Map<String, Integer> map = MyanmarDateKernel.checkWatat(500);
        int watat = map.get("watat");
        Assert.assertTrue("watat should be 0 or 1", watat == 0 || watat == 1);
    }

    @Test
    public void testCheckWatatEra1_2() {
        Map<String, Integer> map = MyanmarDateKernel.checkWatat(900);
        int watat = map.get("watat");
        Assert.assertTrue("watat should be 0 or 1", watat == 0 || watat == 1);
    }

    @Test
    public void testCheckWatatEra1_3() {
        Map<String, Integer> map = MyanmarDateKernel.checkWatat(1150);
        int watat = map.get("watat");
        Assert.assertTrue("watat should be 0 or 1", watat == 0 || watat == 1);
    }

    @Test
    public void testCheckWatatEra2() {
        Map<String, Integer> map = MyanmarDateKernel.checkWatat(1250);
        int watat = map.get("watat");
        Assert.assertTrue("watat should be 0 or 1", watat == 0 || watat == 1);
    }

    @Test
    public void testCheckWatatEra3() {
        Map<String, Integer> map = MyanmarDateKernel.checkWatat(1380);
        int watat = map.get("watat");
        Assert.assertTrue("watat should be 0 or 1", watat == 0 || watat == 1);
    }

    @Test
    public void testCheckWatatExceptionYear1344() {
        Map<String, Integer> map = MyanmarDateKernel.checkWatat(1344);
        int watat = map.get("watat");
        Assert.assertTrue("watat should be 0 or 1", watat == 0 || watat == 1);
    }

    @Test
    public void testCheckWatatExceptionYear1263() {
        Map<String, Integer> map = MyanmarDateKernel.checkWatat(1263);
        int watat = map.get("watat");
        Assert.assertTrue("watat should be 0 or 1", watat == 0 || watat == 1);
    }

    @Test
    public void testCheckWatatExceptionYear1201() {
        Map<String, Integer> map = MyanmarDateKernel.checkWatat(1201);
        int watat = map.get("watat");
        Assert.assertTrue("watat should be 0 or 1", watat == 0 || watat == 1);
    }

    @Test
    public void testCheckWatatMinYear() {
        Map<String, Integer> map = MyanmarDateKernel.checkWatat(2);
        int watat = map.get("watat");
        Assert.assertTrue("watat should be 0 or 1", watat == 0 || watat == 1);
    }

    // === julianToMyanmarDate tests ===

    @Test(expected = DateTimeException.class)
    public void testJulianToMyanmarDateNegativeJdn() {
        MyanmarDateKernel.julianToMyanmarDate(-1);
    }

    @Test
    public void testJulianToMyanmarDateZeroJdn() {
        MyanmarDate myanmarDate = MyanmarDateKernel.julianToMyanmarDate(0);
        Assert.assertNotNull(myanmarDate);
    }

    // === checkMyanmarYear tests ===

    @Test
    public void testCheckMyanmarYearCommon() {
        Map<String, Integer> map = MyanmarDateKernel.checkMyanmarYear(1381);
        assertThat(0, is(map.get("myt")));
    }

    @Test
    public void testCheckMyanmarYearEra1() {
        Map<String, Integer> map = MyanmarDateKernel.checkMyanmarYear(500);
        Assert.assertNotNull(map);
        Assert.assertNotNull(map.get("myt"));
        Assert.assertNotNull(map.get("tg1"));
        Assert.assertNotNull(map.get("fm"));
        Assert.assertNotNull(map.get("werr"));
    }
}
