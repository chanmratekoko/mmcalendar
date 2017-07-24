package mmcalendar;

import java.io.Serializable;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class Thingyan implements Serializable, Cloneable {

	private static final long serialVersionUID = 7874420766164176538L;
	
	/**
	 * Atat Time
	 */
	private double ja;
	/**
	 * Akya Time
	 */
	private double jk;
	/**
	 * Atat Day
	 */
	private double da;

	/**
	 * Akya Day
	 */
	private double dk;

	/**
	 * 
	 * @param ja	Atat Time Julian date time
	 * @param jk	Akya Time Julian date time
	 * @param da	Atat Day
	 * @param dk	Akya Day
	 */
	Thingyan(double ja, double jk, double da, double dk) {
		this.ja = ja;
		this.jk = jk;
		this.da = da;
		this.dk = dk;
	}

	/**
	 * Atat Time
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
	 * @return Julian dates Double Arrays 
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
	 * @return Julian date
	 */
	public double getMyanmarNewYearDay() {
		return da + 1;
	}

	@Override
	public String toString() {
		return "Thingyan [Atat Time =" + ja + ", Akya Time =" + jk + ", Atat Day =" + da + ", Akya Day =" + dk + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(da);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(dk);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ja);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(jk);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thingyan other = (Thingyan) obj;
		if (Double.doubleToLongBits(da) != Double.doubleToLongBits(other.da))
			return false;
		if (Double.doubleToLongBits(dk) != Double.doubleToLongBits(other.dk))
			return false;
		if (Double.doubleToLongBits(ja) != Double.doubleToLongBits(other.ja))
			return false;
		if (Double.doubleToLongBits(jk) != Double.doubleToLongBits(other.jk))
			return false;
		return true;
	}
	
}
