package mmcalendar;

import java.io.Serializable;

/**
 * Western Date
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0.2
 * @since 1.0
 */
public class WesternDate implements Serializable {

    private static final long serialVersionUID = -198088735719287260L;

    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;
    private final int second;

    WesternDate(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static WesternDate of(MyanmarDate date) {
        return of(date.getJulianDayNumber());
    }

    public static WesternDate of(MyanmarDate date, CalendarType calendarType) {
        return of(date.getJulianDayNumber(), calendarType);
    }

    /**
     * Julian date to Western date
     *
     * @param julianDate Julian date
     * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
     * {@link WesternDate} object
     */
    public static WesternDate of(double julianDate) {
        return of(julianDate, Config.getInstance().getCalendarType());
    }

    /**
     * Julian date to Western date Credit4 Gregorian date: (j2w)
     * <a href="http://pmyers.pcug.org.au/General/JulianDates.htm">Credit4 Julian</a>
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Calendar: </a>
     *
     * @param julianDate   julian date
     * @param calendarType {@link CalendarType} Enum
     * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
     * {@link WesternDate} object
     */
    public static WesternDate of(double julianDate, CalendarType calendarType) {
        return of(julianDate, calendarType, 0);
    }


    /**
     * Julian date to Western date Credit4 Gregorian date: (j2w)
     * <a href="http://pmyers.pcug.org.au/General/JulianDates.htm">Credit4 Julian</a>
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Calendar: </a>
     *
     * @param julianDate   julian date
     * @param calendarType calendar type [Optional argument: 0=english (default),
     *                     1=Gregorian, 2=Julian]
     * @param sg           Beginning of Gregorian calendar in JDN [Optional argument:
     *                     (default=2361222) ]
     * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
     * {@link WesternDate} object
     */
    public static WesternDate of(double julianDate, CalendarType calendarType, double sg) {
        return WesternDateKernel.julianToWestern(julianDate, calendarType.getNumber(), sg);
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

    /**
     * @return hour [0-23]
     */
    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }


    /**
     * @return Julian day number
     */
    public double toJulian() {
        return toJulian(Config.getInstance().getCalendarType());
    }

    /**
     * @param calendarType CalendarType enum
     *                     [0-English, 1-Gregorian, 2-Julian]
     *                     calendar type [Optional argument: 0=english (default),
     *                     1=Gregorian, 2=Julian]
     * @return Julian day number
     */
    public double toJulian(CalendarType calendarType) {
        return toJulian(calendarType, 0);
    }

    /**
     * @param calendarType CalendarType enum
     *                     [0-English, 1-Gregorian, 2-Julian]
     *                     calendar type [Optional argument: 0=english (default),
     *                     1=Gregorian, 2=Julian]
     * @param sg           SG: Beginning of Gregorian calendar in JDN [Optional argument:
     *                     (default = 2361222)]
     * @return Julian day number
     */
    public double toJulian(CalendarType calendarType, double sg) {
        return WesternDateKernel.westernToJulian(
                this.getYear(),
                this.getMonth(),
                this.getDay(),
                this.getHour(),
                this.getMinute(),
                this.getSecond(),
                calendarType, sg);
    }

    @Override
    public String toString() {
        return "WesternDate [year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", minute="
                + minute + ", second=" + second + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + day;
        result = prime * result + hour;
        result = prime * result + minute;
        result = prime * result + month;
        result = prime * result + second;
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WesternDate other = (WesternDate) obj;
        if (day != other.day)
            return false;
        if (hour != other.hour)
            return false;
        if (minute != other.minute)
            return false;
        if (month != other.month)
            return false;
        if (second != other.second)
            return false;
        return year == other.year;
    }

}
