package mmcalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Holiday Calculator
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0
 * The dates for holidays in Myanmar vary annually, contingent upon changes in the government. Therefore, it is necessary to revise the calendar each year.
 */
public final class HolidayCalculator {

    /**
     * Don't let anyone instantiate this class.
     */
    private HolidayCalculator() {
    }

    /**
     * Eid (ghEid2)
     */
    private static final int[] GH_EID_2 = {2456936, 2457290, 2457644, 2457998, 2458353};

    /**
     * Chinese New Year (ghCNY)
     * <a href="http://www.mom.gov.sg/employment-practices/public-holidays">Ref</a>
     * <a href="https://en.wikipedia.org/wiki/Chinese_New_Year">Chinese New Year</a>
     */
    private static final int[] GH_CHINESE_NEW_YEAR = {2456689, 2456690, 2457073, 2457074, 2457427, 2457428, 2457782,
            2457783, 2458166, 2458167};

    /**
     * Diwali (ghDiwali)
     */
    private static final int[] GH_DIWALI = {2456599, 2456953, 2457337, 2457691, 2458045, 2458429};

    /**
     * EID ghEid
     */
    private static final int[] GH_EID = {2456513, 2456867, 2457221, 2457576, 2457930, 2458285};

    /**
     * Check for English Holiday
     *
     * @param gy year
     * @param gm month [Jan=1, ... , Dec=12]
     * @param gd day [0-31]
     * @return Name of Holiday Strings List if exist
     */
    static List<String> englishHoliday(int gy, int gm, int gd) {

        List<String> holiday = new ArrayList<>();

        if (gy >= 2018 && gm == 1 && gd == 1) {
            holiday.add("New Year Day");
        } else if (gy >= 1948 && gm == 1 && gd == 4) {
            holiday.add("Independence Day");
        } else if (gy >= 1947 && gm == 2 && gd == 12) {
            holiday.add("Union Day");
        } else if (gy >= 1958 && gm == 3 && gd == 2) {
            holiday.add("Peasants Day");
        } else if (gy >= 1945 && gm == 3 && gd == 27) {
            holiday.add("Resistance Day");
        } else if (gy >= 1923 && gm == 5 && gd == 1) {
            holiday.add("Labour Day");
        } else if (gy >= 1947 && gm == 7 && gd == 19) {
            holiday.add("Martyrs Day");
        } else if (gm == 12 && gd == 25) {
            holiday.add("Christmas Day");
        } else if (gy == 2017 && gm == 12 && gd == 30) {
            holiday.add("Holiday");
        } else if (gy >= 2017 && gm == 12 && gd == 31) {
            holiday.add("Holiday");
        }

        return holiday;
    }

    /**
     * Check for Myanmar Holiday
     *
     * @param myear     Myanmar Year
     * @param mmonth    Myanmar month [Tagu=1, ... , Tabaung=12]
     * @param monthDay  Myanmar Month day [0-30]
     * @param moonPhase Moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon]
     * @return Name of Holiday Strings List if exist
     */
    static List<String> myanmarHoliday(double myear, int mmonth, int monthDay, int moonPhase) {

        List<String> holiday = new ArrayList<>();

        if ((mmonth == 2) && (moonPhase == 1)) {
            holiday.add("Buddha Day");
        } // Vesak day
        else if ((mmonth == 4) && (moonPhase == 1)) {
            holiday.add("Start of Buddhist Lent");
        } // Warso day
        else if ((mmonth == 7) && (moonPhase == 1)) {
            holiday.add("End of Buddhist Lent");
        } else if ((myear >= 1379) && (mmonth == 7) && (monthDay == 14 || monthDay == 16)) {
            holiday.add("Holiday");
        } else if ((mmonth == 8) && (moonPhase == 1)) {
            holiday.add("Tazaungdaing");
        } else if ((myear >= 1379) && (mmonth == 8) && (monthDay == 14)) {
            holiday.add("Holiday");
        } else if ((myear >= 1282) && (mmonth == 8) && (monthDay == 25)) {
            holiday.add("National Day");
        } else if ((mmonth == 10) && (monthDay == 1)) {
            holiday.add("Karen New Year Day");
        } else if ((mmonth == 12) && (moonPhase == 1)) {
            holiday.add("Tabaung Pwe");
        }

        return holiday;
    }

    /**
     * @param jdn       Julian Day Number to check
     * @param myear     Myanmar year
     * @param monthType Myanmar month type [oo=0, hnaung=1]
     * @return List of holiday String
     */
    public static List<String> thingyan(double jdn, double myear, int monthType) {

        // start of Thingyan (BGNTG)
        int bgntg = 1100;

        List<String> holiday = new ArrayList<>();

        double akn;
        double atn;
        // start of third era
        final int SE3 = 1312;

        double ja = Constants.SY * (myear + monthType) + Constants.MO;
        double jk;

        if (myear >= SE3) {
            jk = ja - 2.169918982;
        } else {
            jk = ja - 2.1675;
        }

        akn = Math.round(jk);
        atn = Math.round(ja);

        if (Math.abs(jdn - (atn + 1)) < 0.0000001) {
            holiday.add("Myanmar New Year Day");
        }

        if ((myear + monthType) >= bgntg) {
            if (jdn == atn) {
                holiday.add("Thingyan Atat");
            } else if ((jdn > akn) && (jdn < atn)) {
                holiday.add("Thingyan Akyat");
            } else if (jdn == akn) {
                holiday.add("Thingyan Akya");
            } else if (jdn == (akn - 1)) {
                holiday.add("Thingyan Akyo");
            } else if (((myear + monthType) >= 1369) && ((myear + monthType) < 1379)
                    && ((jdn == (akn - 2)) || ((jdn >= (atn + 2)) && (jdn <= (akn + 7))))) {
                holiday.add("Holiday");
            }
        }

        return holiday;
    }

    /**
     * Other holidays (ohol) Diwali or Eid
     *
     * @param jd Julian day number
     * @return List of holiday String
     */
    public static List<String> getOtherHolidays(double jd) {

        List<String> holiday = new ArrayList<>();

        if (BinarySearchUtil.search(jd, GH_DIWALI) >= 0) {
            holiday.add("Diwali");
        }
        if (BinarySearchUtil.search(jd, GH_EID) >= 0) {
            holiday.add("Eid");
        }

        return holiday;
    }

    /**
     * Anniversary day
     *
     * @param jd           Julian Day Number,
     * @param calendarType calendar type [Optional argument: 0=english (default),
     *                     1=Gregorian, 2=Julian]
     * @return dependency: DoE(), j2w()
     */
    private static List<String> getAnniversaryDay(double jd, CalendarType calendarType) {

        if (calendarType == null) {
            calendarType = CalendarType.ENGLISH;
        }

        List<String> holiday = new ArrayList<>();

        WesternDate wd = WesternDateConverter.convert(jd, calendarType);
        double doe = dateOfEaster(wd.getYear());

        if ((wd.getYear() <= 2017) && (wd.getMonth() == 1) && (wd.getDay() == 1)) {
            holiday.add("New Year Day");
        } else if ((wd.getYear() >= 1915) && (wd.getMonth() == 2) && (wd.getDay() == 13)) {
            holiday.add("G. Aung San BD");
        } else if ((wd.getYear() >= 1969) && (wd.getMonth() == 2) && (wd.getDay() == 14)) {
            holiday.add("Valentines Day");
        } else if ((wd.getYear() >= 1970) && (wd.getMonth() == 4) && (wd.getDay() == 22)) {
            holiday.add("Earth Day");
        } else if ((wd.getYear() >= 1392) && (wd.getMonth() == 4) && (wd.getDay() == 1)) {
            holiday.add("April Fools Day");
        } else if ((wd.getYear() >= 1948) && (wd.getMonth() == 5) && (wd.getDay() == 8)) {
            holiday.add("Red Cross Day");
        } else if ((wd.getYear() >= 1994) && (wd.getMonth() == 10) && (wd.getDay() == 5)) {
            holiday.add("World Teachers Day");
        } else if ((wd.getYear() >= 1947) && (wd.getMonth() == 10) && (wd.getDay() == 24)) {
            holiday.add("United Nations Day");
        } else if ((wd.getYear() >= 1753) && (wd.getMonth() == 10) && (wd.getDay() == 31)) {
            holiday.add("Halloween");
        }

        if ((wd.getYear() >= 1876) && (jd == doe)) {
            holiday.add("Easter");
        } else if ((wd.getYear() >= 1876) && (jd == (doe - 2))) {
            holiday.add("Good Friday");
        } else if (BinarySearchUtil.search(jd, GH_EID_2) >= 0) {
            holiday.add("Eid");
        }
        if (BinarySearchUtil.search(jd, GH_CHINESE_NEW_YEAR) >= 0) {
            holiday.add("Chinese New Year");
        }

        return holiday;
    }

    /**
     * Date of Easter (DoE) using "Meeus/Jones/Butcher" algorithm Reference: Peter
     * Duffett-Smith, Jonathan Zwart',
     * "Practical Astronomy with your Calculator or Spreadsheet," 4th Etd,
     * Cambridge university press, 2011. Page-4.
     *
     * @param year Western year
     * @return julian day number dependency: w2j()
     */
    private static double dateOfEaster(int year) {
        double a = year % 19;
        double b = Math.floor((double) year / 100);
        double c = year % 100;
        double d = Math.floor(b / 4);
        double e = b % 4;
        double f = Math.floor((b + 8) / 25);
        double g = Math.floor((b - f + 1) / 3);
        double h = (19 * a + b - d - g + 15) % 30;
        double i = Math.floor(c / 4);
        double k = c % 4;
        double l = (32 + 2 * e + 2 * i - h - k) % 7;
        double m = Math.floor((a + 11 * h + 22 * l) / 451);
        double q = h + l - 7 * m + 114;
        int day = (int) ((q % 31) + 1);
        int month = (int) Math.floor(q / 31);
        // this is for Gregorian
        return WesternDateKernel.westernToJulian(year, month, day, 1, 0);
    }

    /**
     * Myanmar Anniversary day (mcd)
     *
     * @param myear     Myanmar year
     * @param mmonth    Myanmar month [Tagu=1, ... , Tabaung=12]
     * @param monthDay  Month day [1 to 30]
     * @param moonPhase Moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon]
     * @return List of holiday String
     */
    private static List<String> getMyanmarAnniversaryDay(double myear, int mmonth, int monthDay, int moonPhase) {

        List<String> holiday = new ArrayList<>();

        if ((myear >= 1309) && (mmonth == 11) && (monthDay == 16)) {
            holiday.add("Mon National Day");
        } // the ancient founding of Hanthawady
        else if ((mmonth == 9) && (monthDay == 1)) {
            holiday.add("Shan New Year Day");
            if (myear >= 1306) {
                holiday.add("Authors Day");
            }
        } // Nadaw waxing moon 1
        else if ((mmonth == 3) && (moonPhase == 1)) {
            holiday.add("Mahathamaya Day");
        } // Nayon full moon
        else if ((mmonth == 6) && (moonPhase == 1)) {
            holiday.add("Garudhamma Day");
        } // Tawthalin full moon
        else if ((myear >= 1356) && (mmonth == 10) && (moonPhase == 1)) {
            holiday.add("Mothers Day");
        } // Pyatho full moon
        else if ((myear >= 1370) && (mmonth == 12) && (moonPhase == 1)) {
            holiday.add("Fathers Day");
        } // Tabaung full moon
        else if ((mmonth == 5) && (moonPhase == 1)) {
            holiday.add("Metta Day");
        } // Waguang full moon
        else if ((mmonth == 5) && (monthDay == 10)) {
            holiday.add("Taungpyone Pwe");
        } // Taung Pyone Pwe
        else if ((mmonth == 5) && (monthDay == 23)) {
            holiday.add("Yadanagu Pwe");
        } // Yadanagu Pwe

        return holiday;
    }

    /**
     * @param myanmarDate MyanmarDate
     * @return List of holiday String
     */
    public static List<String> getHoliday(MyanmarDate myanmarDate) {
        return getHoliday(myanmarDate, Config.getInstance().getCalendarType());
    }

    /**
     * @param myanmarDate  MyanmarDate
     * @param calendarType CalendarType
     * @return List of holiday String
     */
    public static List<String> getHoliday(MyanmarDate myanmarDate, CalendarType calendarType) {

        WesternDate westernDate = WesternDateConverter.convert(myanmarDate.getJulianDayNumber(), calendarType);
        // Office Off
        List<String> hde = englishHoliday(westernDate.getYear(), westernDate.getMonth(), westernDate.getDay());
        List<String> hdm = myanmarHoliday(myanmarDate.getYearValue(), myanmarDate.getMonth(), myanmarDate.getDayOfMonth(),
                myanmarDate.getMoonPhaseValue());
        List<String> hdt = thingyan(myanmarDate.getJulianDayNumber(), myanmarDate.getYearValue(), myanmarDate.getMonthType());
        List<String> hdo = getOtherHolidays(myanmarDate.getJulianDayNumber()); // Diwali Eid

        List<String> holiday = new ArrayList<>();

        holiday.addAll(hde);
        holiday.addAll(hdm);
        holiday.addAll(hdt);
        holiday.addAll(hdo);

        return holiday;
    }

    /**
     * @param myanmarDate MyanmarDate object
     * @return boolean
     */
    public static boolean isHoliday(MyanmarDate myanmarDate) {
        return !getHoliday(myanmarDate).isEmpty();
    }

    /**
     * @param holidayList List Of Holiday
     * @return boolean
     */
    public static boolean isHoliday(List<String> holidayList) {
        return !holidayList.isEmpty();
    }

    /**
     * @param myanmarDate MyanmarDate Object
     * @return List of holiday String
     */
    public static List<String> getAnniversary(MyanmarDate myanmarDate) {
        return getAnniversary(myanmarDate, Config.getInstance().getCalendarType());
    }

    /**
     * @param myanmarDate  MyanmarDate
     * @param calendarType CalendarType
     * @return List of holiday String
     */
    public static List<String> getAnniversary(MyanmarDate myanmarDate, CalendarType calendarType) {
        List<String> ecd = getAnniversaryDay(myanmarDate.getJulianDayNumber(), calendarType); // anniversary day
        List<String> mcd = getMyanmarAnniversaryDay(myanmarDate.getYearValue(), myanmarDate.getMonth(), myanmarDate.getDayOfMonth(), myanmarDate.getMoonPhaseValue());

        List<String> holiday = new ArrayList<>();

        holiday.addAll(ecd);
        holiday.addAll(mcd);

        return holiday;
    }

}
