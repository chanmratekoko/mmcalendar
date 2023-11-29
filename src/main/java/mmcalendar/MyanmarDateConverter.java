package mmcalendar;

import java.util.Calendar;

/**
 * Myanmar Date Converter
 * 
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @version 1.0.2
 * 
 * @since 1.0
 *
 */
public class MyanmarDateConverter {
	
	/**
	 * Calendar to Myanmar Date
	 * 
	 * @param calendar
	 * @see Calendar
	 * @return {@link MyanmarDate} Object
	 */
	public static MyanmarDate convert(Calendar calendar){		
		return convert(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), Config.get().getCalendarType(), 0);
	}	

	/**
	 * Western day, month, year to Myanmar Date
	 * @param year
	 *            Western Year
	 * @param month
	 *            Western Month [1 = Jan, ... , 12 = Dec]
	 *            Month value is 1-based. e.g., 1 for January.
	 * @param day
	 *            Western Day [0-31]
	 * @return {@link MyanmarDate} Object
	 */
	public static MyanmarDate convert(int year, int month, int day) {
		return convert(year, month, day, Config.get().getCalendarType(), 0);
	}

	/**
	 * 
	 * @param year
	 *            Western Year
	 * @param month
	 *            Western Month [1 = Jan, ... , 12 = Dec]
	 *            Month value is 1-based. e.g., 1 for January.
	 * @param day
	 *            Western Day [0-31]
	 * @param calendarType CalendarType Enum
	 * @param SG
	 *            : Beginning of Gregorian calendar in JDN [Optional argument:
	 *            default=2361222)])
	 * @return {@link MyanmarDate} Object
	 */
	public static MyanmarDate convert(int year, int month, int day, CalendarType calendarType, double SG) {
		double julianDayNumber = WesternDateKernel.w2j(year, month, day, calendarType, SG);
		return convert(julianDayNumber);
	}
	
	/**
	 * Western day, month, year to Myanmar Date
	 * @param year
	 *            Western Year
	 * @param month
	 *            Western Month [1 = Jan, ... , 12 = Dec]
	 *            Month value is 1-based. e.g., 1 for January.
	 * @param day
	 *            Western Day [0-31]
	 * @param hour
	 *            Hour
	 * @param minute
	 *            Minute
	 * @param second
	 *            Second
	 * @return {@link MyanmarDate} Object
	 */
	public static MyanmarDate convert(int year, int month, int day, int hour, int minute, int second) {		
		return convert(year, month, day, hour, minute, second, Config.get().getCalendarType(), 0);
	}	

	/**
	 * 
	 * @param year
	 *            Western Year
	 * @param month
	 *            Western Month [1 = Jan, ... , 12 = Dec]
	 *            Month value is 1-based. e.g., 1 for January.
	 * @param day
	 *            Western Day [0-31]
	 * @param hour
	 *            Hour
	 * @param minute
	 *            Minute
	 * @param second
	 *            Second
	 * @param calendarType CalendarType Enum
	 * @param SG
	 *            : Beginning of Gregorian calendar in JDN [Optional argument:
	 *            default=2361222)])
	 * @return {@link MyanmarDate} Object
	 */
	public static MyanmarDate convert(int year, int month, int day, int hour, int minute, int second,
			CalendarType calendarType, double SG) {
		double julianDayNumber = WesternDateKernel.w2j(year, month, day, hour, minute, second, calendarType, SG);
		return convert(julianDayNumber);
	}	

	/**
	 * Julian date to Myanmar Date
	 * @return {@link MyanmarDate} Object
	 */
	public static MyanmarDate convert(double julianDayNumber) {
		return MyanmarDateKernel.j2m(julianDayNumber);
	}
}
