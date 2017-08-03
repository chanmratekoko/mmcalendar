package mmcalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class WesternDateConverterTest {

	@Test
	public void j2w() {

		double jd = 2457857.7058912036;		
		WesternDate westernDate = WesternDateKernel.j2w(jd, Config.get().getCalendarType());

		assertThat(2017, is(westernDate.getYear()));
		assertThat(4, is(westernDate.getMonth()));
		assertThat(14, is(westernDate.getDay()));
		assertThat(4, is(westernDate.getHour()));
		assertThat(56, is(westernDate.getMinute()));
		assertThat(29, is(westernDate.getSecond()));

		double result = WesternDateKernel.w2j(westernDate, Config.get().getCalendarType(), 0);

		assertThat(jd, is(result));

	}
}
