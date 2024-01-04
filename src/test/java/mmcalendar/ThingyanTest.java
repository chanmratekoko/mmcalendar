package mmcalendar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThingyanTest {

	@Test
	public void calculate() {
		Thingyan thingyan = Thingyan.of(1379);
		
		assertEquals(2457858.0, thingyan.getAkyaDay(), 0);
		assertEquals(2457857.7058919813, thingyan.getAkyaTime(), 0);

		assertEquals(2457860.0, thingyan.getAtatDay(), 0);
		assertEquals(2457859.875810963, thingyan.getAtatTime(), 0);
	}
}
