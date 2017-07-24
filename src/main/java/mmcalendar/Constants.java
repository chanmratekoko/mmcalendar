package mmcalendar;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public final class Constants {

	/**
	 * solar year (365.2587565) (1577917828 / 4320000.0)
	 * 
	 */
	public static final double SY = 365.2587564814815; 
	
	/**
	 * lunar month (29.53058795) (1577917828 / 53433336.0)
	 */
	public static final double LM = 29.53058794607172;
	
	/**
	 * beginning of 0 ME
	 * Starting year of Myanmar era (year 0)
	 */
	public static final double MO = 1954168.050623;	
	
	/**
	 * beginning of 3rd Era
	 */
	public static final int SE3 = 1312;
	
    /**
     * Don't let anyone instantiate this class.
     */
	private Constants() {
	}
	
}
