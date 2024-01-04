package mmcalendar;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class MyanmarCalendarKernelTest {

    private static MyanmarDate myanmarDate;

    @BeforeClass
    public static void beforeClass() {

//        Config.initDefault(
//                new Config.Builder()
//                        .setCalendarType(CalendarType.ENGLISH)
//                        .setLanguage(Language.ENGLISH)
//                        .build());

        // သာသနာနှစ် ၂၅၆၇ ခု, မြန်မာနှစ် ၁၃၈၅ ခု, တန်ခူး လကွယ်  ရက် အင်္ဂါနေ့
        int jdn = 2460053;
        myanmarDate = MyanmarDate.of(jdn);
    }

    @AfterClass
    public static void afterClass() {
        myanmarDate = null;
        Config.initDefault(
                new Config.Builder()
                        .setCalendarType(CalendarType.ENGLISH)
                        .setLanguage(Language.MYANMAR)
                        .build());
    }

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    @Test
    public void calculateRelatedMyanmarMonths() {
        MyanmarMonths myanmarMonth = MyanmarCalendarKernel.calculateRelatedMyanmarMonths(1380, 3);

        List<Integer> index = new ArrayList<>();
        List<String> list = new ArrayList<>();
        index.add(2);
        list.add("Kason");
        index.add(3);
        list.add("Nayon");
        index.add(0);
        list.add("First Waso");
        index.add(4);
        list.add("Second Waso");
        index.add(5);
        list.add("Wagaung");
        index.add(6);
        list.add("Tawthalin");
        index.add(7);
        list.add("Thadingyut");
        index.add(8);
        list.add("Tazaungmon");
        index.add(9);
        list.add("Nadaw");
        index.add(10);
        list.add("Pyatho");
        index.add(11);
        list.add("Tabodwe");
        index.add(12);
        list.add("Tabaung");
        index.add(13);
        list.add("Late Tagu");

        assertThat(index, hasItem(13));
        Assert.assertEquals(list, myanmarMonth.getMonthNameList());
        Assert.assertEquals(index, myanmarMonth.getMonthList());
        Assert.assertEquals(3, myanmarMonth.getCalculationMonth());
        Assert.assertEquals("Nayon", myanmarMonth.getCalculationMonthName());
    }

    @Test
    public void getCalendarHeader() {
        String head = MyanmarCalendarKernel.getCalendarHeader(1380, 2, 14);
        String actual = "သာသနာနှစ် ၂၅၆၂ ခု မြန်မာနှစ် ၁၃၈၀ ခု ကဆုန် - နယုန်";
        Assert.assertEquals(actual, head);
    }

    @Test
    public void calculateMyanmarYearLength() {
        // calculate myanmar year length
        int yearLength = MyanmarCalendarKernel.calculateMyanmarYearLength(myanmarDate.getYearType());
        assertThat(385, is(yearLength));
    }

    @Test
    public void calculateLengthOfMonth() {
        // length of the month
        int monthLength = MyanmarCalendarKernel.calculateLengthOfMonth(myanmarDate.getMonth(), myanmarDate.getYearType());
        assertThat(29, is(monthLength));
    }

    @Test
    public void calculateMoonPhase() {
        // calculate moon phase
        int moonPhase = MyanmarCalendarKernel.calculateMoonPhase(myanmarDate.getYearType(), myanmarDate.getMonth(), myanmarDate.getDayOfMonth());
        assertThat(3, is(moonPhase));
    }

    @Test
    public void calculateFortnightDay() {
        // Calculates fortnight day from month day
        int fortnightDay = MyanmarCalendarKernel.calculateFortnightDay(myanmarDate.getDayOfMonth());
        assertThat(14, is(fortnightDay));
    }

    @Test
    public void calculateDayOfMonthByYearType() {
        // day of month
        int monthDay = MyanmarCalendarKernel.calculateDayOfMonthByYearType(myanmarDate.getYearType(), myanmarDate.getMonth(), myanmarDate.getMoonPhaseValue(), myanmarDate.getFortnightDayValue());
        assertThat(29, is(monthDay));
    }
}
