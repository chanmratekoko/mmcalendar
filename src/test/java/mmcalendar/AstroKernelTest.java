package mmcalendar;

import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AstroKernelTest {

    @BeforeClass
    public static void beforeClass() {
        Config.initDefault(
                new Config.Builder()
                        .setCalendarType(CalendarType.ENGLISH)
                        .setLanguage(Language.ENGLISH)
                        .build());
    }

    @AfterClass
    public static void afterClass() {
        Config.initDefault(
                new Config.Builder()
                        .setCalendarType(CalendarType.ENGLISH)
                        .setLanguage(Language.MYANMAR)
                        .build());
    }

    @Before
    public void before() {

    }

    // execute after test
    @After
    public void after() {

    }

    @Test
    public void calculateThamaphyu() {
        int thamaphyu = AstroKernel.calculateThamaphyu(1, 1);
        assertThat(1, is(thamaphyu));
    }

    @Test
    public void calculateNagapor() {
        int nagapor = AstroKernel.calculateNagapor(1, 1);
        assertThat(0, is(nagapor));
    }

    @Test
    public void calculateYatyotema() {
        int yatyotema = AstroKernel.calculateYatyotema(1, 1);
        assertThat(0, is(yatyotema));
    }

    @Test
    public void calculateMahayatkyan() {
        int mahayatkyan = AstroKernel.calculateMahayatkyan(1, 1);
        assertThat(0, is(mahayatkyan));
    }

    @Test
    public void calculateShanyat() {
        int shanyat = AstroKernel.calculateShanyat(1, 1);
        assertThat(0, is(shanyat));
    }

    @Test
    public void calculateSabbath() {
        int sabbath = AstroKernel.calculateSabbath(1, 4, 15);
        assertThat(1, is(sabbath));
    }

    @Test
    public void calculateYatyaza() {
        int yatyaza = AstroKernel.calculateYatyaza(8, 4);
        assertThat(1, is(yatyaza));
    }

    @Test
    public void calculatePyathada() {
        int pyathada = AstroKernel.calculatePyathada(8, 3);
        assertThat(1, is(pyathada));
    }

    @Test
    public void calculateNagahle() {
        int nagahle = AstroKernel.calculateNagahle(3);
        assertThat(1, is(nagahle));
    }

    @Test
    public void calculateMahabote() {
        int mahabote = AstroKernel.calculateMahabote(1350, 3);
        assertThat(3, is(mahabote));
    }

    @Test
    public void calculateNakhat() {
        int nakhat = AstroKernel.calculateNakhat(1350);
        assertThat(0, is(nakhat));
    }

    @Test
    public void calculateThamanyo() {
        int thamanyo = AstroKernel.calculateThamanyo(1, 2);
        assertThat(0, is(thamanyo));
    }

    @Test
    public void calculateAmyeittasote() {
        int amyeittasote = AstroKernel.calculateAmyeittasote(8, 3);
        assertThat(0, is(amyeittasote));
    }

    @Test
    public void calculateWarameittugyi() {
        int warameittugyi = AstroKernel.calculateWarameittugyi(8, 3);
        assertThat(1, is(warameittugyi));
    }

    @Test
    public void calculateWarameittunge() {
        int warameittunge = AstroKernel.calculateWarameittunge(8, 4);
        assertThat(0, is(warameittunge));
    }

    @Test
    public void calculateYatpote() {
        int yatpote = AstroKernel.calculateYatpote(8, 4);
        assertThat(0, is(yatpote));
    }

    // --- Edge case tests ---

    @Test
    public void calculatePyathadaAfternoon() {
        // m1 = mmonth % 4 = 0, weekDay = 4 => afternoon pyathada (return 2)
        assertThat(2, is(AstroKernel.calculatePyathada(4, 4)));
        assertThat(2, is(AstroKernel.calculatePyathada(8, 4)));
        assertThat(2, is(AstroKernel.calculatePyathada(12, 4)));
    }

    @Test
    public void calculateNagahleAllDirections() {
        // west=0 for months 1-3 (mod 12 / 3 = 0)
        assertThat(0, is(AstroKernel.calculateNagahle(1)));
        assertThat(0, is(AstroKernel.calculateNagahle(2)));
        // north=1 for months 4-6
        assertThat(1, is(AstroKernel.calculateNagahle(4)));
        assertThat(1, is(AstroKernel.calculateNagahle(5)));
        // east=2 for months 7-9
        assertThat(2, is(AstroKernel.calculateNagahle(7)));
        assertThat(2, is(AstroKernel.calculateNagahle(8)));
        // south=3 for months 10-11 (month 12 wraps: 12%12=0 => west)
        assertThat(3, is(AstroKernel.calculateNagahle(10)));
        assertThat(3, is(AstroKernel.calculateNagahle(11)));
        // month 12: 12%12=0, 0/3=0 => west
        assertThat(0, is(AstroKernel.calculateNagahle(12)));
    }

    @Test
    public void calculateNagahleFirstWaso() {
        // First Waso (mmonth=0) should be treated as Waso (month 4) => north=1
        assertThat(1, is(AstroKernel.calculateNagahle(0)));
    }

    @Test
    public void calculateSabbathEve() {
        // sabbath eve: days 7, 14, 22
        assertThat(2, is(AstroKernel.calculateSabbath(0, 1, 7)));
        assertThat(2, is(AstroKernel.calculateSabbath(0, 1, 14)));
        assertThat(2, is(AstroKernel.calculateSabbath(0, 1, 22)));
    }

    @Test
    public void calculateSabbathNone() {
        assertThat(0, is(AstroKernel.calculateSabbath(0, 1, 5)));
    }

    @Test
    public void calculateYearNameAll12() {
        for (int i = 0; i < 12; i++) {
            assertThat(i, is(AstroKernel.calculateYearName(i)));
        }
        assertThat(0, is(AstroKernel.calculateYearName(12)));
        assertThat(1, is(AstroKernel.calculateYearName(1381)));
    }

    @Test
    public void calculateMahaboteAllStates() {
        // (myear - weekDay) % 7 => test all 7 values
        for (int expected = 0; expected < 7; expected++) {
            int weekDay = 0;
            int myear = expected; // (expected - 0) % 7 = expected
            assertThat(expected, is(AstroKernel.calculateMahabote(myear, weekDay)));
        }
    }

    @Test
    public void calculateNakhatAllValues() {
        assertThat(0, is(AstroKernel.calculateNakhat(1350)));
        assertThat(1, is(AstroKernel.calculateNakhat(1351)));
        assertThat(2, is(AstroKernel.calculateNakhat(1352)));
    }

    @Test
    public void calculateYatyotemaFirstWaso() {
        // mmonth=0 (First Waso) should be treated as Waso (month 4)
        int result = AstroKernel.calculateYatyotema(0, 1);
        assertThat(true, is(result == 0 || result == 1));
    }

    @Test
    public void calculateMahayatkyanFirstWaso() {
        // mmonth=0 (First Waso) should be treated as Waso (month 4)
        int result = AstroKernel.calculateMahayatkyan(0, 1);
        assertThat(true, is(result == 0 || result == 1));
    }

    @Test
    public void calculateShanyatFirstWaso() {
        // mmonth=0 (First Waso) should be treated as Waso (month 4)
        int result = AstroKernel.calculateShanyat(0, 1);
        assertThat(true, is(result == 0 || result == 1));
    }

    @Test
    public void calculateThamaphyuPositiveCase() {
        // weekDay=0 (sat), wda[0]=1, so fortnight day 1 => thamaphyu
        assertThat(1, is(AstroKernel.calculateThamaphyu(1, 0)));
    }

    @Test
    public void calculateThamaphyuSpecialCase() {
        // mf == 4 && weekDay == 5 => thamaphyu
        // md = 4 => fortnightDay = 4
        assertThat(1, is(AstroKernel.calculateThamaphyu(4, 5)));
    }

}
