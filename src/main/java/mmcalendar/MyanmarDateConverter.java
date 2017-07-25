package mmcalendar;

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
	 * Western day, month, year to Myanmar Date
	 * @param year
	 *            Western Year
	 * @param month
	 *            Western Month [1 = Jan, ... , 12 = Dec]
	 * @param day
	 *            Western Day [0-31]
	 * @return {@link MyanmarDate} Object
	 */
	public static MyanmarDate convert(int year, int month, int day) {
		return convert(year, month, day, CalendarType.ENGLISH, 0);
	}

	/**
	 * 
	 * @param year
	 *            Western Year
	 * @param month
	 *            Western Month [1 = Jan, ... , 12 = Dec]
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
		return convert(year, month, day, hour, minute, second, Config.getCalendarType(), 0);
	}	

	/**
	 * 
	 * @param year
	 *            Western Year
	 * @param month
	 *            Western Month [1 = Jan, ... , 12 = Dec]
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
	 * @param julianDayNumber
	 * @return {@link MyanmarDate} Object
	 */
	public static MyanmarDate convert(double julianDayNumber) {
		return MyanmarDateKernel.j2m(julianDayNumber);
	}
}
