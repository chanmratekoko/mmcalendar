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

}
