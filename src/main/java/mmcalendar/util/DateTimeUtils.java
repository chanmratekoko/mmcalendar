package mmcalendar.util;

import java.time.LocalDate;
import java.time.temporal.JulianFields;

public class DateTimeUtils {

    /**
     * Convert LocalDate to Julian Date
     *
     * @param localDate {@link LocalDate}
     * @return Julian Date
     */
    public static double toJulian(LocalDate localDate) {
        return JulianFields.JULIAN_DAY.getFrom(localDate);
    }


    /**
     * Unix epoch (or Unix time or POSIX time or Unix timestamp) to Julian day number
     *
     * @param ut Number of seconds from 1970 Jan 1 00:00:00 (UTC)
     * @return Julian day number
     */
    public static double unixTimeToJulian(long ut) {
        return 2440587.5 + ut / 86400.0; // Convert to day (/24h/60min/60sec) and to JD
    }

    // check

    /**
     * Julian day number to Unix epoch (or Unix time or
     * POSIX time or Unix timestamp)
     *
     * @param jd Julian day number
     * @return unix Timestamp
     */
    public static long julianToUnixTime(double jd) {
        return (long) ((jd - 2440587.5) * 86400.0 + 0.5);
    }

    /**
     * Add Julian day number (utc time to myanmar time) to Julian day number
     * @param jd UTC Julian day number
     * @return Julian day number
     */
    public static double utcToConvertMyanmarTime(double jd) {
        // add myanmar time
        return jd + (6.5 / 24.0);
    }

    /**
     * Don't let anyone instantiate this class.
     */
    private DateTimeUtils() {
    }
}
