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
	 * Beginning of English Calendar
	 */
	public static final int BY = 640;
	
	/**
	 * End of English Calendar
	 */
	public static final int EY = 2140;

	/**
	 * Beginning of Myanmar Calendar
	 */
	public static final int MBY = 2;
	
	/**
	 * End of Myanmar Calendar
	 */
	public static final int MEY = 1500;

	/**
	 * Minimum accurate English Year
	 */
	public static final int LT = 1700;
	
	/**
	 * Maximum accurate English Year
	 */
	public static final int UT = 2018;

	/**
	 * Minimum accurate Myanmar Year
	 */
	public static final int MLT = 1062;
	
	/**
	 * Maximum accurate Myanmar Year
	 */
	public static final int MUT = 1379;

	/**
	 * Gregorian start in English calendar (1752/Sep/14)
	 */
	public static final double SG = 2361222;
	
	public static final String SIMPLE_MYANMAR_DATE_FORMAT_PATTERN = "S s k, B y k, M p f r En";
	
	private CalendarType CALENDARTYPE = CalendarType.ENGLISH;

	private Language lANGUAGE = Language.MYANMAR;
	
    private static Config instance;

    /**
     * Set the default Calendar Config
     *
     * @param config the config build using the builder.
     * @see mmcalendar.Config.Builder
     */
	public static void initDefault(Config config){
		instance = config;
	}
	
    /**
     * The current Calendar Config.
     * If not set it will create a default config.
     */
	public static Config get(){
		if (instance == null) {
			instance = new Config(new Builder());
		}
		return instance;
	}
	
	private Config(Builder builder){
		CALENDARTYPE = builder.getCalendarType();
		lANGUAGE = builder.getLanguage();
	}

	public CalendarType getCalendarType() {
		return CALENDARTYPE;
	}

	public Language getLanguage() {
		return lANGUAGE;
	}

	public static class Builder {

		private CalendarType calendarType = CalendarType.ENGLISH;

		private Language language = Language.MYANMAR;

		public Builder() {
		}
		
		public CalendarType getCalendarType() {
			return calendarType;
		}

		public Builder setCalendarType(CalendarType calendarType) {
			this.calendarType = calendarType;
			return this;
		}
		
		public Language getLanguage() {
			return language;
		}

		public Builder setLanguage(Language language) {
			this.language = language;
			return this;
		}

		public Config build(){
			if (calendarType == null || language == null) {
				throw new IllegalArgumentException("CalendarType or Language cannot be null");
			}
			return new Config(this);
		}
	}

}
