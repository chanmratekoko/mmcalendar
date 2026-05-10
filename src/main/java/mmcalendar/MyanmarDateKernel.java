package mmcalendar;

import java.time.DateTimeException;
import java.util.HashMap;
import java.util.Map;

/**
 * Core Calculation and Algorithms for Myanmar Date.
 * <p>
 * Based on the algorithm by Dr. Yan Naing Aye for Myanmar calendar calculations.
 * Reference: <a href="http://cool-emerald.blogspot.com/2013/06/algorithm-program-and-calculation-of.html">Algorithm, Program and Calculation of Myanmar Calendar</a>
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0.2
 */
public final class MyanmarDateKernel {

    /**
     * Don't let anyone instantiate this class.
     */
    private MyanmarDateKernel() {
    }

    /**
     * Julian day number to Myanmar date
     * <br>
     * dependency: chk_my(my)
     *
     * @param jd : julian day number
     * @return {@link MyanmarDate} Object representing the converted date
     * @throws DateTimeException if the Julian day number is invalid
     */
    public static MyanmarDate julianToMyanmarDate(final double jd) {

        if (jd < 0) {
            throw new DateTimeException("Julian day number cannot be negative.");
        }

        long dd;

        int a;
        int b;
        int c;
        int e;
        int f;
        short mmonth;
        short monthDay;
        int monthLength;
        short moonPhase;
        short fortnightDay;
        short weekDay;


        // convert jd to jdn
        long jdn = Math.round(jd);
        // Myanmar year
        int myear = calculateMyanmarYear(jdn);
        // check year
        final Map<String, Integer> yearInfo = checkMyanmarYear(myear);
        // day count
        dd = jdn - yearInfo.get("tg1") + 1;
        b = yearInfo.get("myt") / 2;
        // big wa and common yr
        c = 1 / (yearInfo.get("myt") + 1);
        // year length
        short yearLength = (short) (354 + (1 - c) * 30 + b);
        // month type: Hnaung =1 or Oo = 0 | late =1 or early = 0
        short monthType = (short) ((dd - 1) / yearLength);
        dd -= (short) (monthType * yearLength);
        // adjust day count and threshold
        a = (int) ((dd + 423) / 512);
        // month
        mmonth = (short) Math.floor((dd - b * a + c * a * 30 + 29.26) / 29.544);
        e = (mmonth + 12) / 16;
        f = (mmonth + 11) / 16;
        // day of month
        monthDay = (short) (dd - Math.floor(29.544 * mmonth - 29.26) - b * e + c * f * 30);
        // adjust month numbers for late months
        mmonth += (short) (f * 3 - e * 4 + 12 * monthType);
        // adjust month and month length
        monthLength = 30 - mmonth % 2;

        if (mmonth == 3) {
            // adjust if Nayon in big watat
            monthLength += yearInfo.get("myt") / 2;
        }

        // moon phase from day of the month, month, and year type
        // [0=waxing, 1=full moon, 2=waning, 3=new moon]
        moonPhase = (short) (((monthDay + 1) / 16) + (monthDay / 16) + (monthDay / monthLength));

        // fortnight day from month day
        // waxing or waning day
        fortnightDay = (short) (monthDay - 15 * (monthDay / 16));
        // week day
        weekDay = (short) ((jdn + 2) % 7);

        return new MyanmarDate(
                myear,
                yearInfo.get("myt"),
                yearLength,
                mmonth,
                monthType,
                monthLength,
                monthDay,
                moonPhase,
                fortnightDay,
                weekDay,
                jd
        );
    }

    private static int calculateMyanmarYear(long jdn) {
        return (int) Math.floor((jdn - 0.5 - Constants.MO) / Constants.SY);
    }


    /**
     * Check Myanmar Year (chk_my)
     * dependency: chk_watat(my)
     *
     * @param myear myanmar year
     * @return map
     * myt = year type [0=common, 1=little watat, 2=big watat]
     * tg1 = the 1st day of Tagu as Julian Day Number (Julian Day Number for MMT)
     * fm = full moon day of [2nd] Waso as Julain Day Number
     * werr = [0=ok, 1= error]
     */
    public static Map<String, Integer> checkMyanmarYear(int myear) {

        Map<String, Integer> y2 = checkWatat(myear);
        int myt = y2.get("watat");

        int yd = 0;

        Map<String, Integer> y1;
        do {
            yd++;
            y1 = checkWatat(myear - yd);
        } while (y1.get("watat") == 0 && yd < 3);

        int fm;
        int werr = 0;

        if (myt > 0) {
            double nd = (y2.get("fm") - y1.get("fm")) % 354;
            myt = (int) Math.floor(nd / 31) + 1;
            fm = y2.get("fm");
            if (nd != 30 && nd != 31) {
                werr = 1;
            }
        } else {
            fm = y1.get("fm") + 354 * yd;
        }

        int tg1 = y1.get("fm") + 354 * yd - 102;

        Map<String, Integer> map = new HashMap<>();
        map.put("myt", myt);
        map.put("tg1", tg1);
        map.put("fm", fm);
        map.put("werr", werr);

        return map;
    }

    /**
     * Check watat (intercalary month) (chk_watat)
     *
     * @param my myanmar year
     * @return watat = intercalary month [1=watat, 0=common]
     * fm = full moon day of 2nd Waso in jdn_mm (jdn+6.5 for MMT) [only valid when watat=1])
     */
    public static Map<String, Integer> checkWatat(int my) {

        // get constants for the corresponding calendar era
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(my);

        // threshold to adjust
        double threshold = (Constants.SY / 12 - Constants.LM) * (12 - c.getNumberOfMonths());
        // excess day
        double ed = (Constants.SY * (my + 3739)) % Constants.LM;

        if (ed < threshold) {
            // adjust excess days
            ed += Constants.LM;
        }

        // full moon day of 2nd Waso
        int fm = (int) Math.round(Constants.SY * my + Constants.MO - ed + 4.5 * Constants.LM + c.getWatatOffset());

        int watat = 0;

        // find watat
        if (c.getEraId() >= 2) {
            // if 2nd era or later find watat based on excess days
            double tw = (Constants.LM - (Constants.SY / 12 - Constants.LM) * c.getNumberOfMonths());

            if (ed >= tw) {
                watat = 1;
            }
        } else {
            // if 1st era, find watat by 19 years metonic cycle
            // Myanmar year is divided by 19 and there is an intercalary month
            // if the remainder is 2, 5, 7, 10, 13, 15, 18
            // https://github.com/kanasimi/CeJS/blob/master/data/date/calendar.js#L2330
            watat = (my * 7 + 2) % 19;
            if (watat < 0) {
                watat += 19;
            }
            watat = (int) Math.floor(watat / 12.0);
        }
        // correct watat exceptions
        watat ^= (int) c.getExceptionInWatatYear();

        Map<String, Integer> map = new HashMap<>();
        map.put("fm", fm);
        map.put("watat", watat);

        return map;
    }

    /**
     * Myanmar date to Julian date dependency: chk_my (my)
     *
     * @param myear  Myanmar Year
     * @param mmonth Myanmar month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd)
     *               Waso=4, Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8,
     *               Nadaw=9, Pyatho=10, Tabodwe=11, Tabaung=12 , Late Tagu = 13, Late Kason = 14]
     * @param mmday  Myanmar's day of month [1 to 29 or 30]
     * @return julian day number
     */
    public static int myanmarDateToJulian(int myear, int mmonth, int mmday) {

        Map<String, Integer> yo = checkMyanmarYear(myear);

        int mmt = mmonth / 13;
        // to 1-12 with month type
        mmonth = mmonth % 13 + mmt;

        int b = (yo.get("myt") / 2);

        //if big watat and common year
        int c = 1 - (int) Math.floor((yo.get("myt") + 1.0) / 2.0);
        //adjust month
        mmonth += 4 - (int) Math.floor((mmonth + 15) / 16.0) * 4 + (int) Math.floor((mmonth + 12) / 16.0);

        int dd = mmday + (int) Math.floor(29.544 * mmonth - 29.26) - c * (int) Math.floor((mmonth + 11) / 16.0) * 30
                + b * (int) Math.floor((mmonth + 12) / 16.0);

        int myl = 354 + (1 - c) * 30 + b;

        //adjust day count with year length
        dd += mmt * myl;

        return (dd + yo.get("tg1") - 1);
    }

    /**
     * Calculate Julian day from Myanmar Year, Myanmar Month name and day of month.
     *
     * @param myear            Myanmar Year (2 to 1500)
     * @param myanmarMonthName Myanmar month name [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *                         Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *                         Tabaung=12, Late Tagu=13, Late Kason=14]
     * @param mmday            day of month [from 1 to 29 or 30]
     * @return Julian Day Number
     */
    public static double getJulianDayNumber(int myear, String myanmarMonthName, int mmday) {
        int mmonth = searchMyanmarMonthNumber(myanmarMonthName);
        if (mmonth < 0) {
            throw new DateTimeException("Invalid value for myanmarMonthName : " + myanmarMonthName);
        }
        return myanmarDateToJulian(myear, mmonth, mmday);
    }

    /**
     * Myanmar Month name to Myanmar month number
     *
     * @param myanmarMonthName Myanmar month name [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *                         Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *                         Tabaung=12, Late Tagu=13, Late Kason=14]
     * @return myanmar month number
     */
    public static int searchMyanmarMonthNumber(String myanmarMonthName) {
        switch (myanmarMonthName.toLowerCase()) {
            case "first waso":
                return 0;
            case "tagu":
                return 1;
            case "kason":
                return 2;
            case "nayon":
                return 3;
            case "waso":
                return 4;
            case "wagaung":
                return 5;
            case "tawthalin":
                return 6;
            case "thadingyut":
                return 7;
            case "tazaungmon":
                return 8;
            case "nadaw":
                return 9;
            case "pyatho":
                return 10;
            case "tabodwe":
                return 11;
            case "tabaung":
                return 12;
            case "late tagu":
                return 13;
            case "late kason":
                return 14;
            default:
                return -1;
        }
    }

    public static int searchMoonPhase(String moonPhase) {
        switch (moonPhase.toLowerCase()) {
            case "waxing":
                return 0;
            case "full moon":
                return 1;
            case "waning":
                return 2;
            case "new moon":
                return 3;
            default:
                return -1;
        }
    }

    static class MyanmarYearInfo {
        private int myanmarYear;

        // year type [0=common, 1=little watat, 2=big watat]
        private YearType yearType;

        // the 1st day of Tagu as Julian Day Number (Julian Day Number for MMT)
        private int firstDayOfTagu;

        // full moon day of [2nd] Waso as Julain Day Number
        private int fullMoonDayOfSecondWaso;

        private boolean waError;

        /**
         * myt = year type [0=common, 1=little watat, 2=big watat]
         * tg1 = the 1st day of Tagu as Julian Day Number (Julian Day Number for MMT)
         * fm = full moon day of [2nd] Waso as Julain Day Number
         * werr = [0=ok, 1= error]
         */


        enum YearType {
            COMMON(0),
            LITTLE_WATAT(1),
            BIG_WATAT(2);

            private final int value;

            YearType(int value) {
                this.value = value;
            }

            public int getValue() {
               return this.value;
            }
        }
    }
}
