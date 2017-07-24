package mmcalendar;

/**
 * Thingyan Calculator
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public final class ThingyanCalculator {		
	
    /**
     * Don't let anyone instantiate this class.
     */
	private ThingyanCalculator() {
	}

	
	/**
	 * Calculate the Thingyan (Myanmar new year)
	 * 
	 * @param myear - Myanmar year
	 * @return {@link Thingyan} Object
	 */
	public static Thingyan getThingyan(int myear) {

		double ja = Constants.SY * myear + Constants.MO;

		double jk;

		if (myear >= Constants.SE3) {
			jk = ja - 2.169918982;
		} else {
			jk = ja - 2.1675;
		}

		double da = Math.round(ja);
		double dk = Math.round(jk);

		return new Thingyan(ja, jk, da, dk);
	}

}
