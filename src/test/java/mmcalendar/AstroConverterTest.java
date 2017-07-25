package mmcalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AstroConverterTest {
	
	private static MyanmarDate myanmarDate;

	// execute before class
	@BeforeClass
	public static void beforeClass() {
		Config.init(CalendarType.ENGLISH, Language.ENGLISH);
		myanmarDate = MyanmarDateConverter.convert(2017, 7, 24);
		System.out.println("========================= Start =========================");
		System.out.println("AstroConverterTest");
	}

	// execute after class
	@AfterClass
	public static void afterClass() {
		myanmarDate = null;
		Config.init(CalendarType.ENGLISH, Language.MYANMAR);
		System.out.println("========================= End =========================");
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
		Astro astro = AstroConverter.convert(myanmarDate);
		
		Assert.assertEquals("Thamanyo", astro.getThamanyo());
		Assert.assertEquals("Nagapor", astro.getNagapor());
		
		assertThat(5, is(astro.getMahaboteInt()));		
		Assert.assertEquals("Thike", astro.getMahabote());
		Assert.assertEquals("Mahayatkyan", astro.getMahayatkyan());
		
		assertThat(2, is(astro.getNakhatInt()));
		
		Assert.assertEquals("Human", astro.getNakhat());
		
		Assert.assertEquals("\u1019\u103C\u102D\u1000\u1039\u1000\u101E\u102D\u102F\u101D\u103A\u1014\u103E\u1005\u103A", astro.getYearName());
		
	}

}
