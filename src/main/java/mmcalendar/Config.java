package mmcalendar;


/**
 * Configuration For Calendar
 * 
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @version 1.0
 *
 */
public final class Config {
	
	/**
	 * beginning and end of the calendar
	 */
	public static final int BY = 640;
	public static final int EY = 2140;
	
	public static final int MBY = 2;
	public static final int MEY = 1500;

	/**
	 * lower and upper threshold for accurate years 
	 */
	public static final int LT = 1700;
	public static final int UT = 2018;	
	
	public static final int MLT = 1062;
	public static final int MUT = 1379;

	private static CalendarType CALENDARTYPE = CalendarType.ENGLISH;
	
	private static Language lANGUAGE = Language.MYANMAR;	
	
	/**
	 * Gregorian start in English calendar (1752/Sep/14)
	 */
	public static final double SG = 2361222; 
	
	public static void init(CalendarType calendarType, Language language){
		
		if(calendarType == null || language == null){
			throw new IllegalArgumentException("CalendarType or Language cannot be null");
		}
		
		CALENDARTYPE = calendarType;
		lANGUAGE = language;
	}

	public static CalendarType getCalendarType() {
		return CALENDARTYPE;
	}

	public static Language getLanguage() {
		return lANGUAGE;
	}	
	
}
