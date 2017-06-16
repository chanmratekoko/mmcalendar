package mmcalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import mmcalendar.Config;
import mmcalendar.WesternDate;
import mmcalendar.WesternDateConverter;

public class WesternDateConverterTest {

	@Test
	public void j2w() {

		double jd = 2457857.7058919813;
		WesternDate westernDate = WesternDateConverter.j2w(jd, Config.CALENDARTYPE);

		assertThat(2017, is(westernDate.getYear()));
		assertThat(4, is(westernDate.getMonth()));
		assertThat(14, is(westernDate.getDay()));
		assertThat(4, is(westernDate.getHour()));
		assertThat(56, is(westernDate.getMinute()));
		assertThat(29, is(westernDate.getSecond()));

		//double result = WesternDateConverter.w2j(westernDate, Config.CALENDARTYPE, 0);

		//assertThat(jd, is(result));

	}
}
