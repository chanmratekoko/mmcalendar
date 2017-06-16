package mmcalendar;

import static org.junit.Assert.*;

import org.junit.Test;

import mmcalendar.Config;
import mmcalendar.Thingyan;
import mmcalendar.ThingyanCalculator;
import mmcalendar.WesternDate;
import mmcalendar.WesternDateConverter;

public class ThingyanCalculatorTest {

	@Test
	public void getThingyan() {
		Thingyan thingyan = ThingyanCalculator.getThingyan(1379);

		WesternDate akyaDay = WesternDateConverter.j2w(thingyan.getAkyaDay(), Config.CALENDARTYPE);
		WesternDate akyaTime = WesternDateConverter.j2w(thingyan.getAkyaTime(), Config.CALENDARTYPE);

		System.out.println(akyaDay);
		System.out.println(akyaTime);

		WesternDate ataDay = WesternDateConverter.j2w(thingyan.getAtatDay(), Config.CALENDARTYPE);
		WesternDate atatTime = WesternDateConverter.j2w(thingyan.getAtatTime(), Config.CALENDARTYPE);

		System.out.println(ataDay);
		System.out.println(atatTime);

		assertEquals(2457858.0, thingyan.getAkyaDay(), 0);
		assertEquals(2457857.7058919813, thingyan.getAkyaTime(), 0);

		assertEquals(2457860.0, thingyan.getAtatDay(), 0);
		assertEquals(2457859.875810963, thingyan.getAtatTime(), 0);

	}
}
