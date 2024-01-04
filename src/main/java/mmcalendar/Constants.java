package mmcalendar;

import java.time.ZoneId;

/**
 * Constants Value for Algorithm Calculation
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0
 */
public final class Constants {

    /**
     * solar year (365.2587565) (1577917828 / 4320000.0)
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

    /**
     * Myanmar Month Names
     */
    static final String[] EMA = {
            "First Waso",
            "Tagu",
            "Kason",
            "Nayon",
            "Waso",
            "Wagaung",
            "Tawthalin",
            "Thadingyut",
            "Tazaungmon",
            "Nadaw",
            "Pyatho",
            "Tabodwe",
            "Tabaung",
            "Late Tagu",
            "Late Kason"
    };

    /**
     * New Moon mean Dark moon
     */
    static final String[] MSA = {
            "Waxing",
            "Full Moon",
            "Waning",
            "New Moon"
    };

    /**
     * Week Days
     */
    static final String[] WDA = {
            "Saturday",
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday"
    };

    public static final ZoneId MYANMAR_ZONE_ID = ZoneId.of("Asia/Rangoon");

    /**
     * Don't let anyone instantiate this class.
     */
    private Constants() {
    }

}
