package mmcalendar;


/**
 * Core Calculation and Algorithm for Western Date
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0.2
 */
public final class WesternDateKernel {

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
    public static WesternDate julianToWestern(double julianDate, CalendarType calendarType) {
        return julianToWestern(julianDate, calendarType.getNumber(), 0);
    }

    /**
     * Julian date to Western date Credit4 Gregorian date: (j2w)
     * <a href="http://pmyers.pcug.org.au/General/JulianDates.htm">Credit4 Julian</a>
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Calendar: </a>
     *
     * @param julianDate julian date
     * @param calType    calendar type [Optional argument: 0=english (default),
     *                   1=Gregorian, 2=Julian]
     * @param sg         Beginning of Gregorian calendar in JDN [Optional argument:
     *                   (default=2361222) ]
     * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
     * {@link WesternDate} object
     */
    public static WesternDate julianToWestern(double julianDate, int calType, double sg) {

        calType = Math.max(0, calType);

        // Gregorian start in English calendar (1752/Sep/14)
        sg = sg <= 0 ? 2361222 : sg;

        double j;
        double jf;
        double y;
        double m;
        double d;
        double h;
        double n;
        double s;

        if (calType == 2 || (calType == 0 && (julianDate < sg))) {
            double b;
            double c;
            double f;
            double e;
            j = Math.floor(julianDate + 0.5);
            jf = julianDate + 0.5 - j;
            b = j + 1524;
            c = Math.floor((b - 122.1) / 365.25);
            f = Math.floor(365.25 * c);
            e = Math.floor((b - f) / 30.6001);
            m = (e > 13) ? (e - 13) : (e - 1);
            d = b - f - Math.floor(30.6001 * e);
            y = m < 3 ? (c - 4715) : (c - 4716);
        } else {
            j = Math.floor(julianDate + 0.5);
            jf = julianDate + 0.5 - j;
            j -= 1721119;
            y = Math.floor((4 * j - 1) / 146097.0);
            j = 4 * j - 1 - 146097 * y;
            d = Math.floor(j / 4);
            j = Math.floor((4 * d + 3) / 1461.0);
            d = 4 * d + 3 - 1461 * j;
            d = Math.floor((d + 4) / 4.0);
            m = Math.floor((5 * d - 3) / 153.0);
            d = 5 * d - 3 - 153 * m;
            d = Math.floor((d + 5) / 5.0);
            y = 100 * y + j;
            if (m < 10) {
                m += 3;
            } else {
                m -= 9;
                y = y + 1;
            }
        }

        jf *= 24;
        h = Math.floor(jf);
        jf = (jf - h) * 60;
        n = Math.floor(jf);
        s = Math.round((jf - n) * 60);

        return new WesternDate((int) y, (int) m, (int) d, (int) h, (int) n, (int) s);
    }

    /**
     * Western date to Julian day number. Credit4 Gregorian 2 JD: (w2j)
     *
     * @param year    western year
     * @param month   month [Jan=1, ... , Dec=12]
     * @param day     day [0-31]
     * @param calType [0-English, 1-Gregorian, 2-Julian]
     *                calendar type [Optional argument: 0=english (default),
     *                1=Gregorian, 2=Julian]
     * @param sg      SG: Beginning of Gregorian calendar in JDN [Optional argument:
     *                (default=2361222)]
     * @return Julian day number
     */
    public static double westernToJulian(int year, int month, int day, int calType, double sg) {

        calType = Math.max(0, calType);

        // Gregorian start in English calendar (1752/Sep/14)
        sg = sg <= 0 ? 2361222 : sg;

        int a = (int) Math.floor((14 - month) / 12.0);
        year = year + 4800 - a;
        month = month + (12 * a) - 3;

        double jd = day + Math.floor((153 * month + 2) / 5.0) + (365 * year) +  Math.floor(year / 4.0);

        if (calType == 1) {
            jd = jd - (int) Math.floor(year / 100.0) + (int) Math.floor(year / 400.0) - 32045;
        } else if (calType == 2) {
            jd = jd - 32083;
        } else {
            jd = jd - Math.floor(year / 100.0) + Math.floor(year / 400.0) - 32045;
            if (jd < sg) {
                jd = day + Math.floor((153.0 * month + 2) / 5) + (365 * year) + Math.floor(year / 4.0) - 32083;
                if (jd > sg) {
                    jd = sg;
                }
            }
        }

        return jd;
    }

    /**
     * Get Julian day number (w2j)
     *
     * @param year         Year
     * @param month        Month
     * @param day          Day
     * @param calendarType CalendarType Enum [0-English, 1-Gregorian, 2-Julian]
     *                     calendar type [Optional argument: 0=english (default),
     *                     1=Gregorian, 2=Julian]
     * @param sg           SG: Beginning of Gregorian calendar in JDN [Optional argument:
     *                     (default = 2361222)]
     * @return Julian day number
     */
    public static double westernToJulian(int year, int month, int day, CalendarType calendarType, double sg) {
        return westernToJulian(year, month, day, calendarType.getNumber(), sg);
    }

    /**
     * Get Julian day number (w2j)
     *
     * @param year         Year
     * @param month        Month
     * @param day          Day
     * @param hour         Hour
     * @param minute       Minute
     * @param second       Second
     * @param calendarType CalendarType enum
     *                     [0-English, 1-Gregorian, 2-Julian]
     *                     calendar type [Optional argument: 0=english (default),
     *                     1=Gregorian, 2=Julian]
     * @param sg           SG: Beginning of Gregorian calendar in JDN [Optional argument:
     *                     (default = 2361222)]
     * @return Julian day number
     */
    public static double westernToJulian(int year, int month, int day, int hour, int minute, int second, CalendarType calendarType, double sg) {
        double fractionOfDay = timeToDayFractionStartFrom12Noon(hour, minute, second);
        return westernToJulian(year, month, day, calendarType.getNumber(), sg) + fractionOfDay;
    }

    /**
     * Time to Fraction of day starting from 12 noon (t2d)
     *
     * @param hour hour
     * @param minute minute
     * @param second second
     * @return fraction of day
     */
    public static double timeToDayFractionStartFrom12Noon(double hour, double minute, double second) {
        return ((hour - 12) / 24 + minute / 1440 + second / 86400);
    }

    /**
     * @param westernDate  WesternDate object
     * @param calendarType CalendarType Enum
     * @param sg           SG: Beginning of Gregorian calendar in JDN [Optional argument:
     *                     (default=2361222)]
     * @return Julian number
     */
    public static double westernToJulian(WesternDate westernDate, CalendarType calendarType, double sg) {
        return westernToJulian(westernDate.getYear(), westernDate.getMonth(), westernDate.getDay(), westernDate.getHour(),
                westernDate.getMinute(), westernDate.getSecond(), calendarType, sg);
    }

    /**
     * The month according to calendar type
     *
     * @param year  Western Year
     * @param month Western Month
     *              [Jan=1, ... , Dec=12]
     * @return julian day number of start of month
     */
    public static int getJulianDayNumberOfStartOfMonth(int year, int month) {
        return (int) westernToJulian(year, month, 1, Config.getInstance().getCalendarType().getNumber(), 0);
    }

    /**
     * Julian day number of end of the month
     *
     * @param year  Year
     * @param month Month
     * @return Julian day number of end of the month
     */
    public static int getJulianDayNumberOfEndOfMonth(int year, int month) {
        int js = getJulianDayNumberOfStartOfMonth(year, month);
        int eml = getLengthOfMonth(year, month, Config.getInstance().getCalendarType().getNumber());
        return js + eml - 1;
    }

    /**
     * find the length of a month (emLen, wml)
     *
     * @param year         Year
     * @param month        Month
     *                     [Jan=1, ... , Dec=12]
     * @param calenderType [0=English, 1=Gregorian, 2=Julian]
     * @return the length of a month
     */
    public static int getLengthOfMonth(int year, int month, int calenderType) {
        int leap = 0;
        // length of the current month
        int mLen = (int) (30 + (month + Math.floor(month / 8.0)) % 2);

        // if february
        if (month == 2) {
            if (calenderType == 1 || (calenderType == 0 && year > 1752)) {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    leap = 1;
                }
            } else if (year % 4 == 0)
                leap = 1;
            mLen += leap - 2;
        }

        if (year == 1752 && month == 9 && calenderType == 0) {
            mLen = 19;
        }

        return mLen;
    }

    /**
     * Don't let anyone instantiate this class.
     */
    private WesternDateKernel() {
    }

}
