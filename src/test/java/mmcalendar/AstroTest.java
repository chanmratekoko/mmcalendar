package mmcalendar;

import org.junit.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

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
    public void convertTest() {
        Astro astro = Astro.of(myanmarDate);
        assertEquals("Thamanyo", astro.getThamanyo());
        assertEquals("Nagapor", astro.getNagapor());
        assertThat(5, is(astro.getMahaboteValue()));
        assertEquals("Thike", astro.getMahabote());
        assertEquals("Mahayatkyan", astro.getMahayatkyan());
        assertThat(2, is(astro.getNakhatValue()));
        assertEquals("Human", astro.getNakhat());
        assertEquals("Mrigasiras", astro.getYearName());
        assertThat(1, is(astro.getNagahleValue()));
        assertEquals("North", astro.getNagahle());

        assertEquals("", astro.getYatyaza());
        assertEquals("", astro.getPyathada());
        assertEquals("", astro.getSabbath());
        assertEquals("", astro.getAmyeittasote());
        assertEquals("", astro.getWarameittugyi());
        assertEquals("", astro.getWarameittunge());
        assertEquals("", astro.getYatpote());
        assertEquals("", astro.getThamaphyu());
        assertEquals("", astro.getYatyotema());
        assertEquals("", astro.getShanyat());

    }

    @Test
    public void assertBooleanTest() {
        Astro astro = Astro.of(myanmarDate);

        Assert.assertFalse(astro.isAmyeittasote());
        Assert.assertTrue(astro.isMahayatkyan());
        Assert.assertTrue(astro.isNagapor());
        Assert.assertFalse(astro.isShanyat());
        Assert.assertTrue(astro.isThamanyo());
        Assert.assertFalse(astro.isThamaphyu());
        Assert.assertFalse(astro.isWarameittugyi());
        Assert.assertFalse(astro.isWarameittunge());
        Assert.assertFalse(astro.isYatpote());
        Assert.assertFalse(astro.isYatyotema());
        Assert.assertFalse(astro.isYatyaza());
        Assert.assertFalse(astro.isPyathada());
        Assert.assertFalse(astro.isSabbath());
    }

    @Test
    public void astroTestTwo() {
        MyanmarDate myanmarDate = MyanmarDate.of(LocalDate.of(2024, 1, 4));
        Astro astro = Astro.of(myanmarDate);

        assertEquals("", astro.getThamanyo());
        assertEquals("", astro.getNagapor());
        assertThat(1, is(astro.getMahaboteValue()));
        assertEquals("Atun", astro.getMahabote());
        assertEquals("", astro.getMahayatkyan());
        assertThat(2, is(astro.getNakhatValue()));
        assertEquals("Human", astro.getNakhat());
        assertEquals("Jyeshtha", astro.getYearName());

        Assert.assertFalse(astro.isAmyeittasote());
        Assert.assertFalse(astro.isMahayatkyan());
        Assert.assertFalse(astro.isNagapor());
        Assert.assertFalse(astro.isShanyat());
        Assert.assertFalse(astro.isThamanyo());
        Assert.assertFalse(astro.isThamaphyu());
        Assert.assertFalse(astro.isWarameittugyi());
        Assert.assertTrue(astro.isWarameittunge());
        Assert.assertTrue(astro.isYatpote());
        Assert.assertFalse(astro.isYatyotema());
        Assert.assertFalse(astro.isYatyaza());
        Assert.assertTrue(astro.isPyathada());
        Assert.assertTrue(astro.isSabbath());
    }

    @Test
    public void testAfternoonPyathada(){
        MyanmarDate myanmarDate = MyanmarDate.of(2024, 3, 13);
        Astro astro = Astro.of(myanmarDate);
        assertEquals("ရက်ရာဇာ၊ မွန်းလွဲ ပြဿဒါး", astro.getAstrologicalDay(Language.MYANMAR));
    }

    @Test
    public void equalsTest() {
        MyanmarDate myanmarDate = MyanmarDate.of(LocalDate.of(2024, 1, 4));
        Astro astro = Astro.of(myanmarDate);
        MyanmarDate myanmarDateTwo = MyanmarDate.of(LocalDateTime.of(2024, 1, 4, 0, 0, 0));
        Astro astroResult = Astro.of(myanmarDateTwo);
        assertEquals(astro, astroResult);
    }

    @Test
    public void hashCodeTest() {
        MyanmarDate myanmarDate = MyanmarDate.of(LocalDate.of(2024, 1, 4));
        Astro astro = Astro.of(myanmarDate);
        assertThat(-463661218, is(astro.hashCode()));
    }

}
