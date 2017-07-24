package mmcalendar;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class WesternDateConverter {

	/**
	 * Myanmar Date to Western Date
	 * 
	 * @param myanmarDate
	 *            {@link MyanmarDate} object
	 * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
	 *         {@link WesternDate} object
	 */
	public static WesternDate convert(MyanmarDate myanmarDate) {
		return convert(myanmarDate.getJulianDayNumber());
	}

	/**
	 * Julian date to Western date
	 * @param juliandate
	 *            Julian date
	 * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
	 *         {@link WesternDate} object
	 */
	public static WesternDate convert(double juliandate) {
		return WesternDateKernel.j2w(juliandate, CalendarType.ENGLISH);
	}
	
	/**
	 * Julian date to Western date Credit4 Gregorian date:
	 * http://pmyers.pcug.org.au/General/JulianDates.htm Credit4 Julian
	 * Calendar: http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html
	 * 
	 * @param juliandate
	 *            julian date
	 * @param calendarType
	 *            CalendarType Enum
	 * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
	 *         {@link WesternDate} object
	 */
	public static WesternDate convert(double juliandate, CalendarType calendarType) {
		return WesternDateKernel.j2w(juliandate, calendarType.getNumber(), 0);
	}
}
