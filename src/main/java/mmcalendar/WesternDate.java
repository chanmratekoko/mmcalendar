package mmcalendar;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class WesternDate {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;

	WesternDate(int year, int month, int day, int hour, int minute, int second) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	@Override	
	public String toString() {
		return "WesternDate [year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", minute="
				+ minute + ", second=" + second + "]";
	}
	
}
