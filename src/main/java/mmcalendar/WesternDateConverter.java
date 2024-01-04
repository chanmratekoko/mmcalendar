package mmcalendar;

/**
 * Western Date Converter
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0.2
 * @since 1.0
 */
public class WesternDateConverter {

    /**
     * Don't let anyone instantiate this class.
     */
    private WesternDateConverter() {
    }


    /**
     * Myanmar Date to Western Date
     *
     * @param myanmarDate {@link MyanmarDate} object
     * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
     * {@link WesternDate} object
     */
    public static WesternDate convert(MyanmarDate myanmarDate) {
        return convert(myanmarDate.getJulianDayNumber());
    }

    /**
     * Julian date to Western date
     *
     * @param julianDate Julian date
     * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
     * {@link WesternDate} object
     */
    public static WesternDate convert(double julianDate) {
        return WesternDateKernel.julianToWestern(julianDate, Config.getInstance().getCalendarType());
    }

    /**
     * Julian date to Western date Credit4 Gregorian date:
     * <a href="http://pmyers.pcug.org.au/General/JulianDates.htm">Credit4 Julian</a>
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Calendar:</a>
     *
     * @param julianDate   julian date
     * @param calendarType CalendarType Enum
     * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
     * {@link WesternDate} object
     */
    public static WesternDate convert(double julianDate, CalendarType calendarType) {
        return WesternDateKernel.julianToWestern(julianDate, calendarType.getNumber(), 0);
    }
}
