package mmcalendar;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class ThingyanCalculator {		
	
	/**
	 * Calculate the Thingyan (Myanmar new year)
	 * 
	 * @param my -myanmar year
	 * @return Thingyan
	 */
	public static Thingyan getThingyan(int my) {

		double ja = Constants.SY * my + Constants.MO;

		double jk;

		if (my >= Constants.SE3) {
			jk = ja - 2.169918982;
		}
		else {
			jk = ja - 2.1675;
		}

		double da = Math.round(ja);
		double dk = Math.round(jk);

		Thingyan thingyan = new Thingyan();
		thingyan.ja = ja;
		thingyan.jk = jk;
		thingyan.da = da;
		thingyan.dk = dk;

		return thingyan;
	}

}
