package mmcalendar;

import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AstroTest {

    private static MyanmarDate myanmarDate;

    // execute before class
    @BeforeClass
    public static void beforeClass() {

        Config.initDefault(
                new Config.Builder()
                        .setCalendarType(CalendarType.ENGLISH)
                        .setLanguage(Language.ENGLISH)
                        .build());

        myanmarDate = MyanmarDate.of(2017, 7, 24);
    }

    // execute after class
    @AfterClass
    public static void afterClass() {
        myanmarDate = null;
        Config.initDefault(
                new Config.Builder()
                        .setCalendarType(CalendarType.ENGLISH)
                        .setLanguage(Language.MYANMAR)
                        .build());
    }

    // execute before test
    @Before
    public void before() {

    }

    // execute after test
    @After
    public void after() {

    }

    @Test
    public void convert() {
        Astro astro = Astro.of(myanmarDate);
        Assert.assertEquals("Thamanyo", astro.getThamanyo());
        Assert.assertEquals("Nagapor", astro.getNagapor());
        assertThat(5, is(astro.getMahaboteValue()));
        Assert.assertEquals("Thike", astro.getMahabote());
        Assert.assertEquals("Mahayatkyan", astro.getMahayatkyan());
        assertThat(2, is(astro.getNakhatValue()));
        Assert.assertEquals("Human", astro.getNakhat());
        Assert.assertEquals("Mrigasiras", astro.getYearName());
    }

}
