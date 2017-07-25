package mmcalendar;

/**
 * 
 * Astrological information Converter
 * 
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @version 1.0.2
 * @since 1.0
 *
 */
public class AstroConverter {
	
    /**
     * Don't let anyone instantiate this class.
     */
	private AstroConverter() {
	}

	/**
	 * Myanmar Date to Astro
	 * @param myanmarDate {@link MyanmarDate} Object
	 * @return {@link Astro} Object (Astrological informations)
	 */
	public static Astro convert(MyanmarDate myanmarDate) {
		return AstroKernel.astro(myanmarDate.mmonth, myanmarDate.monthLength, myanmarDate.monthDay, myanmarDate.weekDay, myanmarDate.myear);
	}
	
}
