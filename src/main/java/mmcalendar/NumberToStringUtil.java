package mmcalendar;

/**
 * Number To Specific Language String
 * 
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @version 1.0
 *
 */
public final class NumberToStringUtil {
	
    /**
     * Don't let anyone instantiate this class.
     */
	private NumberToStringUtil() {
	}

	/**
	 * Number to String
	 * @param number Number 
	 * @param languageCatalog LanguageCatalog object
	 * @return String
	 */
	public static String convert(double number, LanguageCatalog languageCatalog) {
		int r = 0;
		String s = "", g = "";
		if (number < 0) {
			g = "-";
			number = Math.abs(number);
		}
		number = Math.floor(number);
		do {
			r = (int) (number % 10);
			number = Math.floor(number / 10);
			s = languageCatalog.translate(Integer.toString(r)) + s;
		} while (number > 0);
		return (g + s);
	}

}
