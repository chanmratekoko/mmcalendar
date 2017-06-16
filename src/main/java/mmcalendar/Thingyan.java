package mmcalendar;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class Thingyan {

	/**
	 * atat time
	 */
	double ja;
	/**
	 * akya time
	 */
	double jk;
	/**
	 * atat day
	 */
	double da;

	/**
	 * akya day
	 */
	double dk;

	protected Thingyan() {
	}

	/**
	 * 
	 * @return Julian date time
	 */
	public double getAtatTime() {
		return ja;
	}

	/**
	 * Akya time
	 * 
	 * @return Julian date time
	 */
	public double getAkyaTime() {
		return jk;
	}

	/**
	 * Atat day
	 * 
	 * @return Julian date
	 */
	public double getAtatDay() {
		return da;
	}

	/**
	 * Akya day
	 * 
	 * @return Julian date
	 */
	public double getAkyaDay() {
		return dk;
	}

	/**
	 * Thingyan Akyo day
	 * 
	 * @return Julian date
	 */
	public double getAkyoDay() {
		return dk - 1;
	}

	/**
	 * Thingyan Akyat day
	 * 
	 * @return
	 */
	public double[] getAkyatDay() {
		if ((da - dk) > 2) {
			return new double[] { dk + 1, dk + 2 };
		}
		return new double[] { dk + 1 };
	}

	/**
	 * Myanmar new year's day
	 * 
	 * @return
	 */
	public double getMyanmarNewYearDay() {
		return da + 1;
	}
}
