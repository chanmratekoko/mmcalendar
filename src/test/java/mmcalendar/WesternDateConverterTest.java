package mmcalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class WesternDateConverterTest {

	@Test
	public void julianToWestern() {

		double jd = 2457857.7058912036;		
		WesternDate westernDate = WesternDateKernel.julianToWestern(jd, Config.getInstance().getCalendarType());

		assertThat(2017, is(westernDate.getYear()));
		assertThat(4, is(westernDate.getMonth()));
		assertThat(14, is(westernDate.getDay()));
		assertThat(4, is(westernDate.getHour()));
		assertThat(56, is(westernDate.getMinute()));
		assertThat(29, is(westernDate.getSecond()));

		double result = WesternDateKernel.westernToJulian(westernDate, Config.getInstance().getCalendarType(), 0);

		assertThat(jd, is(result));

	}
}
