package mmcalendar;

import mmcalendar.util.DateTimeUtils;

import java.io.Serializable;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

import static mmcalendar.Constants.*;

/**
 * Myanmar Date
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0.3
 * @since 1.0
 */
public class MyanmarDate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Myanmar year
     */
    private final int myear;

    /**
     * year type [0=common, 1=little watat, 2=big watat]
     */
    private final int yearType;

    /**
     * myl : year length [normal = 354, small watat = 384, or big watat = 385 days]
     */
    private final int yearLength;

    /**
     * mm : The month-of-year
     * month Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     * Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     * Tabaung=12
     */
    private final int mmonth;

    /**
     * mmt: month type [1=hnaung, 0= Oo]
     */
    private final int monthType;

    /**
     * mml: month length [29 or 30 days]
     */
    private final int monthLength;

    /**
     * md: The day-of-month [1 to 30]
     */
    private final int monthDay;

    /**
     * fd: fortnight day [1 to 15],
     */
    private final int fortnightDay;

    /**
     * mp :moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon],
     */
    private final int moonPhase;

    /**
     * wd: week day [0=sat, 1=sun, ..., 6=fri]
     */
    private final int weekDay;

    /**
     * julian day number
     */
    private final double jd;

    protected MyanmarDate(int myear, int yearType, int yearLength,
                          int mmonth, int monthType, int monthLength,
                          int monthDay, int moonPhase, int fortnightDay,
                          int weekDay, double jd) {
        this.myear = myear;
        this.yearType = yearType;
        this.yearLength = yearLength;
        this.mmonth = mmonth;
        this.monthType = monthType;
        this.monthLength = monthLength;
        this.monthDay = monthDay;
        this.moonPhase = moonPhase;
        this.fortnightDay = fortnightDay;
        this.weekDay = weekDay;
        this.jd = jd;
    }

    public MyanmarDate(MyanmarDate original) {
        this.myear = original.myear;
        this.yearType = original.yearType;
        this.yearLength = original.yearLength;
        this.mmonth = original.mmonth;
        this.monthType = original.monthType;
        this.monthLength = original.monthLength;
        this.monthDay = original.monthDay;
        this.fortnightDay = original.fortnightDay;
        this.moonPhase = original.moonPhase;
        this.weekDay = original.weekDay;
        this.jd = original.jd;
    }

    //-----------------------------------------------------------------------

    /**
     * @param myear    Myanmar Year (2 to 1500)
     * @param mmonth   Myanmar month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd)
     *                 Waso=4, Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8,
     *                 Nadaw=9, Pyatho=10, Tabodwe=11, Tabaung=12 , Late Tagu = 13, Late Kason = 14]
     * @param monthDay day of month [from 1 to 29 or 30]
     * @return Myanmar date
     */
    public static MyanmarDate create(
            int myear,
            int mmonth,
            int monthDay
    ) {
        double jd = MyanmarDateKernel.myanmarDateToJulian(myear, mmonth, monthDay);
        return MyanmarDateKernel.julianToMyanmarDate(jd);
    }

    /**
     * @param myear            Myanmar Year (2 to 1500)
     * @param myanmarMonthName Myanmar month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd)
     *                         Waso=4, Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8,
     *                         Nadaw=9, Pyatho=10, Tabodwe=11, Tabaung=12 , Late Tagu = 13, Late Kason = 14]
     * @param monthDay         day of month [from 1 to 29 or 30]
     * @return Myanmar date
     */
    public static MyanmarDate create(
            int myear,
            String myanmarMonthName,
            int monthDay
    ) {
        double jd = MyanmarDateKernel.getJulianDayNumber(myear, myanmarMonthName, monthDay);
        return MyanmarDateKernel.julianToMyanmarDate(jd);
    }


    /**
     * Create Myanmar Date from myanmar year, length of the month, moon phase and fortnight day
     *
     * @param myear        Myanmar Year
     * @param mmonth       month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *                     Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *                     Tabaung=12, Late Tagu=13, Late Kason=14 ]
     * @param moonPhase    moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon]
     * @param fortnightDay fortnight day [1 to 15]
     * @return Myanmar date
     */
    public static MyanmarDate create(
            int myear,
            int mmonth,
            int moonPhase,
            int fortnightDay
    ) {
        int monthDay = MyanmarCalendarKernel.calculateDayOfMonth(myear, mmonth, moonPhase, fortnightDay);
        return create(myear, mmonth, monthDay);
    }

    //-----------------------------------------------------------------------
    public static MyanmarDate now() {
        LocalDateTime canberraDateTime = LocalDateTime.now(MYANMAR_ZONE_ID);
        return of(canberraDateTime);
    }

    public static MyanmarDate of(Date date) {
        LocalDateTime localDateTime = date.toInstant()
                .atZone(MYANMAR_ZONE_ID)
                .toLocalDateTime();
        return of(localDateTime);
    }

    /**
     * Calendar to Myanmar Date
     *
     * @param calendar java calendar
     * @return {@link MyanmarDate} Object
     * @see Calendar
     */
    public static MyanmarDate of(Calendar calendar) {
        return of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), Config.getInstance().getCalendarType(), 0);
    }

    /**
     * LocalDateTime to Myanmar Date
     *
     * @param myanmarDateTime java {@link LocalDateTime}
     * @return the Myanmar date, not null
     * @see LocalDateTime
     * <p>
     * Note: All the calculations are based on Myanmar Standard Time (UTC+06:30)
     * which is calculated on the basis of 97° 30' longitude.
     */
    public static MyanmarDate of(LocalDateTime myanmarDateTime) {
        return of(
                myanmarDateTime.getYear(),
                myanmarDateTime.getMonthValue(),
                myanmarDateTime.getDayOfMonth(),
                myanmarDateTime.getHour(),
                myanmarDateTime.getMinute(),
                myanmarDateTime.getSecond()
        );
    }

    /**
     * @param localDateTime local date-time
     * @param zone          the time-zone, which may be an offset, not null
     * @return the Myanmar date, not null
     */
    public static MyanmarDate of(LocalDateTime localDateTime, ZoneId zone) {
        ZonedDateTime zonedDateTime = localDateTime
                .atZone(zone);
        return of(zonedDateTime);
    }

    /**
     * Obtains an instance of {@code MyanmarDate} using seconds from the
     * epoch of 1970-01-01T00:00:00Z.
     *
     * @param epochSecond the number of seconds from the epoch of 1970-01-01T00:00:00Z
     * @param zone        the time-zone, which may be an offset, not null
     * @return the Myanmar date, not null
     */
    public static MyanmarDate ofEpochSecond(long epochSecond, ZoneId zone) {
        ZonedDateTime zonedDateTime = LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.ofTotalSeconds(0))
                .atZone(zone);
        return of(zonedDateTime);
    }

    public static MyanmarDate of(ZonedDateTime zonedDateTime) {
        LocalDateTime myanmarLocalDateTime = zonedDateTime.withZoneSameInstant(MYANMAR_ZONE_ID)
                .toLocalDateTime();
        return of(myanmarLocalDateTime);
    }

    //-----------------------------------------------------------------------

    /**
     * Western day, month, year to Myanmar Date
     *
     * @param year  Western Year
     * @param month Western Month [1 = Jan, ... , 12 = Dec]
     *              Month value is 1-based. e.g., 1 for January.
     * @param day   Western Day [0-31]
     * @return {@link MyanmarDate} Object
     */
    public static MyanmarDate of(int year, int month, int day) {
        return of(year, month, day, Config.getInstance().getCalendarType(), 0);
    }

    /**
     * @param year         Western Year
     * @param month        Western Month [1 = Jan, ... , 12 = Dec]
     *                     Month value is 1-based. e.g., 1 for January.
     * @param day          Western Day [0-31]
     * @param calendarType CalendarType Enum
     * @param sg           : Beginning of Gregorian calendar in JDN [Optional argument:
     *                     default=2361222 ]
     * @return {@link MyanmarDate} Object
     */
    public static MyanmarDate of(int year, int month, int day, CalendarType calendarType, double sg) {
        double julianDayNumber = WesternDateKernel.westernToJulian(year, month, day, calendarType, sg);
        return of(julianDayNumber);
    }

    /**
     * Western day, month, year to Myanmar Date
     *
     * @param year   Western Year
     * @param month  Western Month [1 = Jan, ... , 12 = Dec]
     *               Month value is 1-based. e.g., 1 for January.
     * @param day    Western Day [0-31]
     * @param hour   Hour
     * @param minute Minute
     * @param second Second
     * @return {@link MyanmarDate} Object
     */
    public static MyanmarDate of(int year, int month, int day, int hour, int minute, int second) {
        return of(year, month, day, hour, minute, second, Config.getInstance().getCalendarType(), 0);
    }

    /**
     * @param year         Western Year
     * @param month        Western Month [1 = Jan, ... , 12 = Dec]
     *                     Month value is 1-based. e.g., 1 for January.
     * @param day          Western Day [1-31]
     * @param hour         Hour
     * @param minute       Minute
     * @param second       Second
     * @param calendarType CalendarType Enum
     * @param sg           : Beginning of Gregorian calendar in JDN [Optional argument:
     *                     default=2361222]
     * @return {@link MyanmarDate} Object
     */
    public static MyanmarDate of(int year, int month, int day, int hour, int minute, int second,
                                 CalendarType calendarType, double sg) {
        double julianDayNumber = WesternDateKernel.westernToJulian(year, month, day, hour, minute, second, calendarType, sg);
        return of(julianDayNumber);
    }

    /**
     * Julian date to Myanmar Date
     *
     * @return {@link MyanmarDate} Object
     */
    public static MyanmarDate of(double julianDayNumber) {
        return MyanmarDateKernel.julianToMyanmarDate(julianDayNumber);
    }

    //-----------------------------------------------------------------------

    public String getBuddhistEra(Language language) {
        return LanguageTranslator.translate(myear + 1182.0, language);
    }

    public String getBuddhistEra() {
        return getBuddhistEra(Config.getInstance().getLanguage());
    }

    public int getBuddhistEraValue() {
        return myear + 1182;
    }

    public String getYear(Language language) {
        return LanguageTranslator.translate(myear, language);
    }

    public String getYear() {
        return getYear(Config.getInstance().getLanguage());
    }

    public int getYearValue() {
        return myear;
    }

    /**
     * @return 0=common, 1=little watat, 2=big watat
     */
    public int getYearType() {
        /* 0=common, 1=little watat, 2=big watat */
        return yearType;
    }

    public String getMnt() {
        return getMnt(Config.getInstance().getLanguage());
    }

    public String getMnt(Language language) {

        /* 0=common, 1=little watat, 2=big watat */
        StringBuilder stringBuilder = new StringBuilder();
        if (monthType > 0) {
            stringBuilder.append(LanguageTranslator.translate("Late", language));
        }

        if (yearType > 0 && mmonth == 4) {
            stringBuilder.append(LanguageTranslator.translate("Second", language));
        }

        return stringBuilder.toString();
    }

    /**
     * Returns the length of the year represented by this date.
     * <p>
     * This returns the length of the year in days.
     * normal=354, small watat=384, big watat=385
     *
     * @return the length of the year in days
     */
    public int lengthOfYear() {
        return yearLength;
    }

    public String getMonthName() {
        return getMonthName(Config.getInstance().getLanguage());
    }

    public String getMonthName(Language language) {
        return LanguageTranslator.translate(EMA[this.mmonth], language);
    }

    /**
     * Month Length
     *
     * @return Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4,
     * Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9,
     * Pyatho=10, Tabodwe=11, Tabaung=12, Late Tagu=13, Late Kason=14,
     */
    public int getMonth() {
        return mmonth;
    }

    /**
     * @return moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon]
     */
    public int getMoonPhaseValue() {
        return moonPhase;
    }

    public String getMoonPhase() {
        return getMoonPhase(Config.getInstance().getLanguage());
    }

    public String getMoonPhase(Language language) {
        return LanguageTranslator.translate(MSA[this.moonPhase], language);
    }

    /**
     * Returns the length of the month represented by this date.
     * <p>
     * This returns the length of the month in days. either 29 or 30
     *
     * @return the length of the month in days
     */
    public int lengthOfMonth() {
        return monthLength;
    }

    /**
     * @return month day [1 to 30]
     */
    public int getDayOfMonth() {
        return monthDay;
    }

    /**
     * @return month type [1 = hnaung, 0 = Oo]
     */
    public int getMonthType() {
        return monthType;
    }

    /**
     * @return fortnight day [1 to 15],
     */
    public int getFortnightDayValue() {
        return fortnightDay;
    }

    public String getFortnightDay() {
        return getFortnightDay(Config.getInstance().getLanguage());
    }

    public String getFortnightDay(Language language) {
        return ((moonPhase % 2) == 0) ? LanguageTranslator.translate(fortnightDay, language) : "";
    }

    /**
     * @return week day [0=sat, 1=sun, ..., 6=fri]
     */
    public int getWeekDayValue() {
        return weekDay;
    }

    public String getWeekDay() {
        return getWeekDay(Config.getInstance().getLanguage());
    }

    public String getWeekDay(Language language) {
        return LanguageTranslator.translate(WDA[this.weekDay], language);
    }

    /**
     * @return julian day number
     */
    public double getJulianDayNumber() {
        return jd;
    }


    public boolean isWeekend() {
        return (weekDay == 0) || (weekDay == 1);
    }

    /**
     * @param pattern the pattern describing the date and time format
     * @return formatted date string
     * @throws NullPointerException if the given pattern is null
     */
    public String format(String pattern) {
        return format(pattern, Config.getInstance().getLanguage());
    }

    /**
     * @param pattern  the pattern describing the date and time format
     * @param language Language Catalog
     * @return specified format
     * @throws NullPointerException if the given pattern is null return String
     */
    public String format(String pattern, Language language) {

        if (pattern == null || language == null) {
            throw new NullPointerException();
        }

        char[] charArray = pattern.toCharArray();

        StringBuilder stringBuilder = new StringBuilder();

        for (char c : charArray) {
            switch (c) {
                case MyanmarDateFormat.SASANA_YEAR:
                    stringBuilder.append(LanguageTranslator.translate("Sasana Year", language));
                    break;
                case MyanmarDateFormat.BUDDHIST_ERA:
                    stringBuilder.append(getBuddhistEra(language));
                    break;
                case MyanmarDateFormat.BURMESE_YEAR:
                    stringBuilder.append(LanguageTranslator.translate("Myanmar Year", language));
                    break;
                case MyanmarDateFormat.MYANMAR_YEAR:
                    stringBuilder.append(getYear(language));
                    break;
                case MyanmarDateFormat.KU:
                    stringBuilder.append(LanguageTranslator.translate("Ku", language));
                    break;
                case MyanmarDateFormat.MONTH_IN_YEAR:
                    stringBuilder.append(getMonthName(language));
                    break;
                case MyanmarDateFormat.MOON_PHASE:
                    stringBuilder.append(getMoonPhase(language));
                    break;
                case MyanmarDateFormat.FORTNIGHT_DAY:
                    stringBuilder.append(getFortnightDay(language));
                    break;
                case MyanmarDateFormat.DAY_NAME_IN_WEEK:
                    stringBuilder.append(getWeekDay(language));
                    break;
                case MyanmarDateFormat.NAY:
                    stringBuilder.append(LanguageTranslator.translate("Nay", language));
                    break;
                case MyanmarDateFormat.YAT:
                    stringBuilder.append(LanguageTranslator.translate("Yat", language));
                    break;
                default:
                    stringBuilder.append(c);
                    break;
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        Language language = Config.getInstance().getLanguage();
        return toString(language);
    }

    public String toString(Language language) {
        return format(MyanmarDateFormat.SIMPLE_MYANMAR_DATE_FORMAT_PATTERN, language);
    }

    //-----------------------------------------------------------------------

    /**
     * Gets the hour-of-day field.
     *
     * @return the hour-of-day, from 0 to 23
     */
    public int getHour() {
        return toMyanmarLocalTime().getHour();
    }

    /**
     * Gets the minute-of-hour field.
     *
     * @return the minute-of-hour, from 0 to 59
     */
    public int getMinute() {
        return toMyanmarLocalTime().getMinute();
    }

    /**
     * Gets the second-of-minute field.
     *
     * @return the second-of-minute, from 0 to 59
     */
    public int getSecond() {
        return toMyanmarLocalTime().getSecond();
    }

    //-----------------------------------------------------------------------

    public ZonedDateTime toMyanmarZonedDateTime() {
        return toZonedDateTime(MYANMAR_ZONE_ID);
    }

    public ZonedDateTime toZonedDateTime(ZoneId zoneId) {
        long epochSecond = DateTimeUtils.julianToUnixTime(this.jd);
        return LocalDateTime.ofEpochSecond(
                        epochSecond,
                        0,
                        ZoneOffset.ofTotalSeconds(0)
                ).atZone(MYANMAR_ZONE_ID)
                .withZoneSameInstant(zoneId);
    }


    /**
     * Java has a rough support for Julian day number.
     * It counts the days correctly.
     * It doesn’t begin a new day at noon as the Julian day defines,
     * and it doesn’t support fraction of day.
     * We can still use the support and make the proper adjustment ourselves.
     *
     * @return {@link LocalDateTime} with Myanmar Timezone
     */
    public LocalDateTime toMyanmarLocalDateTime() {
        return toLocalDateTime(MYANMAR_ZONE_ID);
    }

    /**
     * Java has a rough support for Julian day number.
     * It counts the days correctly.
     * It doesn’t begin a new day at noon as the Julian day defines,
     * and it doesn’t support fraction of day.
     * We can still use the support and make the proper adjustment ourselves.
     *
     * @param zoneId zone
     * @return {@link LocalDateTime}
     */
    public LocalDateTime toLocalDateTime(ZoneId zoneId) {
        return toZonedDateTime(zoneId).toLocalDateTime();
    }

    /**
     * Java has a rough support for Julian day number.
     * It counts the days correctly.
     * It doesn’t begin a new day at noon as the Julian day defines,
     * and it doesn’t support fraction of day.
     * We can still use the support and make the proper adjustment ourselves.
     *
     * @return {@link LocalDate} with Myanmar Timezone
     */
    public LocalDate toMyanmarLocalDate() {
        return toMyanmarLocalDate(MYANMAR_ZONE_ID);
    }

    /**
     * Java has a rough support for Julian day number.
     * It counts the days correctly.
     * It doesn’t begin a new day at noon as the Julian day defines,
     * and it doesn’t support fraction of day.
     * We can still use the support and make the proper adjustment ourselves.
     *
     * @param zoneId zone
     * @return {@link LocalDate}
     */
    public LocalDate toMyanmarLocalDate(ZoneId zoneId) {
        return toZonedDateTime(zoneId)
                .toLocalDate();
    }

    /**
     * Java has a rough support for Julian day number.
     * It counts the days correctly.
     * It doesn’t begin a new day at noon as the Julian day defines,
     * and it doesn’t support fraction of day.
     * We can still use the support and make the proper adjustment ourselves.
     *
     * @return {@link LocalTime} with Myanmar Timezone
     */
    public LocalTime toMyanmarLocalTime() {
        return toLocalTime(MYANMAR_ZONE_ID);
    }


    /**
     * Java has a rough support for Julian day number.
     * It counts the days correctly.
     * It doesn’t begin a new day at noon as the Julian day defines,
     * and it doesn’t support fraction of day.
     * We can still use the support and make the proper adjustment ourselves.
     *
     * @param zoneId zone
     * @return {@link LocalTime}
     */
    public LocalTime toLocalTime(ZoneId zoneId) {
        return toZonedDateTime(zoneId)
                .toLocalTime();
    }

    //-----------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fortnightDay;
        long temp;
        temp = Double.doubleToLongBits(jd);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + mmonth;
        result = prime * result + monthDay;
        result = prime * result + monthLength;
        result = prime * result + monthType;
        result = prime * result + moonPhase;
        result = prime * result + myear;
        result = prime * result + weekDay;
        result = prime * result + yearLength;
        result = prime * result + yearType;
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
        MyanmarDate other = (MyanmarDate) obj;
        if (fortnightDay != other.fortnightDay)
            return false;
        if (Double.doubleToLongBits(jd) != Double.doubleToLongBits(other.jd))
            return false;
        if (mmonth != other.mmonth)
            return false;
        if (monthDay != other.monthDay)
            return false;
        if (monthLength != other.monthLength)
            return false;
        if (monthType != other.monthType)
            return false;
        if (moonPhase != other.moonPhase)
            return false;
        if (myear != other.myear)
            return false;
        if (weekDay != other.weekDay)
            return false;
        if (yearLength != other.yearLength)
            return false;
        return yearType == other.yearType;
    }

    public static MyanmarDate copyOf(MyanmarDate original) {
        return new MyanmarDate(original);
    }

    /**
     * Checks if the Day, Month, and Year are the same,
     * while disregarding the Hour, Minute, and Second.
     *
     * @param other Compare MyanmarDate
     * @return true if the day is identical, false otherwise
     */
    public boolean hasSameDay(MyanmarDate other) {
        return (this.myear == other.myear) && (this.mmonth == other.mmonth) && (this.weekDay == other.weekDay);
    }
}
