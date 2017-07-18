package mmcalendar;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class WesternDate {

	private double year;
	private double month;
	private double day;
	private double hour;
	private double minute;
	private double second;

	WesternDate(double year, double month, double day, double hour, double minute, double second) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public int getYear() {
		return (int) year;
	}

	public int getMonth() {
		return (int) month;
	}

	public int getDay() {
		return (int) day;
	}

	public int getHour() {
		return (int) hour;
	}

	public int getMinute() {
		return (int) minute;
	}

	public int getSecond() {
		return (int) second;
	}

	@Override	
	public String toString() {
		return "WesternDate [year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", minute="
				+ minute + ", second=" + second + "]";
	}
	
}
