package mmcalendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class MyanmarCalendarKernel {

    /**
     * Don't let anyone instantiate this class.
     */
    private MyanmarCalendarKernel() {
    }

    /**
     * Calculate related Myanmar month names by year
     *
     * @param myear  Myanmar Year
     * @param mmonth Myanmar month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd)
     *               Waso=4, Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8,
     *               Nadaw=9, Pyatho=10, Tabodwe=11, Tabaung=12, Late Tagu=13
     *               Late Kason=14 ]
     * @return {@link MyanmarMonths} Object
     */
    public static MyanmarMonths calculateRelatedMyanmarMonths(int myear, int mmonth) {

        double j1 = Math.round(Constants.SY * myear + Constants.MO) + 1.0;
        double j2 = Math.round(Constants.SY * (myear + 1) + Constants.MO);

        // myanmar year start date
        MyanmarDate m1 = MyanmarDateKernel.julianToMyanmarDate(j1);
        // myanmar year-end date
        MyanmarDate m2 = MyanmarDateKernel.julianToMyanmarDate(j2);

        int si = m1.getMonth();
        int ei = m2.getMonth();

        if (si == 0) {
            si = 4; // si will always Tagu or Kason,
        }
        // in case 1st Waso, start at Waso which will take care in the for loop later
        if (mmonth == 0 && m1.getYearType() == 0) {
            // if non watat year and 1st waso is selected
            mmonth = 4;
        }
        if (mmonth != 0 && mmonth < si) {
            // if the year start at Kason and tagu is selected
            mmonth = si;
        }
        if (mmonth > ei) {
            // if the year-end at late tagu and late Kason is selected
            mmonth = ei;
        }

        return populateMonthLists(m1.getYearType(), si, ei, mmonth);
    }

    /**
     * @param yearType Year Type of start date
     * @param si       Start month
     * @param ei       End month
     * @param mmonth   Current month
     * @return Myanmar Month
     */
    private static MyanmarMonths populateMonthLists(int yearType, int si, int ei, int mmonth) {
        final List<Integer> monthList = new ArrayList<>();
        final List<String> monthNameList = new ArrayList<>();
        int currentIndex = 0;

        for (int i = si; i <= ei; i++) {
            if (i == 4 && yearType != 0) {
                monthList.add(0);
                monthNameList.add(Constants.EMA[0]);
                if (mmonth == 0) {
                    currentIndex = 0;
                }
            }

            monthList.add(i);
            monthNameList.add(((i == 4 && yearType != 0) ? "Second " : "") + Constants.EMA[i]);

            if (i == mmonth) {
                currentIndex = i;
            }
        }

        return new MyanmarMonths(monthList, monthNameList, currentIndex);
    }

    /**
     * Western Calendar Title for month
     *
     * @param year  Western Year
     * @param month Western Month [1 = Jan, ... , 12 = Dec]
     *              Month value is 1-based. e.g., 1 for January.
     * @return Calendar Title for month
     */
    public static String getCalendarHeaderForWesternStyle(int year, int month) {
        return getCalendarHeaderForWesternStyle(year, month, Config.getInstance().getLanguage());
    }

    /**
     * Western Calendar Title for month
     *
     * @param year     Western Year
     * @param month    Western Month [1 = Jan, ... , 12 = Dec]
     *                 Month value is 1-based. e.g., 1 for January.
     * @param language Language
     * @return Calendar Title for month
     */
    public static String getCalendarHeaderForWesternStyle(int year, int month, Language language) {
        // time zone is irrelevant
        int monthLength = WesternDateKernel.getLengthOfMonth(year, month, Config.getInstance().getCalendarType().getNumber());
        MyanmarDate startDate = MyanmarDate.of(year, month, 1);
        double je = startDate.getJulianDayNumber() + monthLength - 1;
        MyanmarDate endDate = MyanmarDate.of(je);
        return getCalendarHeader(startDate, endDate, language);
    }

    /**
     * Calendar Title for month
     *
     * @param myear  Myanmar Year
     * @param mmonth Myanmar month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd)
     *               Waso=4, Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8,
     *               Nadaw=9, Pyatho=10, Tabodwe=11, Tabaung=12, Late Tagu=13
     *               Late Kason=14 ]
     * @return Calendar Title for month
     */
    public static String getCalendarHeader(int myear, int mmonth) {
        return getCalendarHeader(myear, mmonth, Config.getInstance().getLanguage());
    }

    /**
     * Calendar Title for month
     *
     * @param myear    Myanmar Year
     * @param mmonth   Myanmar month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd)
     *                 Waso=4, Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8,
     *                 Nadaw=9, Pyatho=10, Tabodwe=11, Tabaung=12, Late Tagu=13
     *                 Late Kason=14 ]
     * @param language Language
     * @return Calendar Title for month
     */
    public static String getCalendarHeader(int myear, int mmonth, Language language) {
        return getCalendarHeader(myear, mmonth, 1, language);
    }

    /**
     * Calendar Title for month
     *
     * @param myear    Myanmar Year
     * @param mmonth   Myanmar month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd)
     *                 Waso=4, Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8,
     *                 Nadaw=9, Pyatho=10, Tabodwe=11, Tabaung=12, Late Tagu=13
     *                 Late Kason=14 ]
     * @param mday    day of month [from 1 to 29 or 30]
     * @param language Language
     * @return Calendar Title for month
     */
    public static String getCalendarHeader(int myear, int mmonth, int mday, Language language) {
        // Find julian day number of start of the month
        double julianDate = MyanmarDateKernel.myanmarDateToJulian(myear, mmonth, mday);

        MyanmarDate myanmarDate = MyanmarDate.of(julianDate);

        double js = myanmarDate.getJulianDayNumber();
        int eml = myanmarDate.lengthOfMonth();
        double je = js + eml - 1;

        MyanmarDate endMyanmarDate = MyanmarDate.of(je);

        return getCalendarHeader(myanmarDate, endMyanmarDate, language);
    }

    /**
     * Calendar Title for month
     *
     * @param startDateOfMonth start of month (SE)
     * @param endDateOfMonth   end of month (ME)
     * @param language         Language
     * @return Calendar Title for month
     */
    private static String getCalendarHeader(MyanmarDate startDateOfMonth, MyanmarDate endDateOfMonth, Language language) {

        StringBuilder str = new StringBuilder();

        str.append(getHeaderForBuddhistEra(startDateOfMonth, endDateOfMonth, language));

        if (endDateOfMonth.getYearValue() >= 2) {
            //if Myanmar year after 2 ME
            str.append(" ")
                    .append(getHeaderForMyanmarYear(startDateOfMonth, endDateOfMonth, language))
                    .append(" ");

            str.append(getHeaderForMyanmarMonth(startDateOfMonth, endDateOfMonth, language));
        }

        return str.toString();
    }

    public static String getHeaderForBuddhistEra(MyanmarDate startDate, MyanmarDate endDate, Language language) {

        StringBuilder buddhistEraStringBuilder = new StringBuilder();

        buddhistEraStringBuilder.append(LanguageTranslator.translate("Sasana Year", language))
                .append(" ")
                .append(startDate.getBuddhistEra(language));

        if (startDate.getYearValue() != endDate.getYearValue()) {
            buddhistEraStringBuilder.append(" - ")
                    .append(endDate.getBuddhistEra(language));
        }

        buddhistEraStringBuilder.append(" ")
                .append(LanguageTranslator.translate("Ku", language));

        return buddhistEraStringBuilder.toString();
    }

    public static String getHeaderForMyanmarYear(MyanmarDate startDateOfMonth, MyanmarDate endDateOfMonth, Language language) {

        StringBuilder myanmarYearStringBuilder = new StringBuilder();

        myanmarYearStringBuilder.append(LanguageTranslator.translate("Myanmar Year", language))
                .append(" ");

        if (startDateOfMonth.getYearValue() >= 2) {
            myanmarYearStringBuilder.append(startDateOfMonth.getYear(language));
            if (startDateOfMonth.getYearValue() != endDateOfMonth.getYearValue()) {
                myanmarYearStringBuilder.append(" - ");
            }
        }

        if (startDateOfMonth.getYearValue() != endDateOfMonth.getYearValue()) {
            myanmarYearStringBuilder.append(endDateOfMonth.getYear(language));
        }

        myanmarYearStringBuilder.append(" ")
                .append(LanguageTranslator.translate("Ku", language));

        return myanmarYearStringBuilder.toString();
    }

    public static String getHeaderForMyanmarMonth(MyanmarDate startDateOfMonth, MyanmarDate endDateOfMonth, Language language) {

        StringBuilder myanmarMonthStringBuilder = new StringBuilder();

        if (startDateOfMonth.getYearValue() >= 2) {
            myanmarMonthStringBuilder.append(startDateOfMonth.getMonthName(language));
            if (startDateOfMonth.getMonth() != endDateOfMonth.getMonth()) {
                myanmarMonthStringBuilder.append(" - ");
            }
        }

        if (startDateOfMonth.getMonth() != endDateOfMonth.getMonth()) {
            myanmarMonthStringBuilder.append(endDateOfMonth.getMonthName(language));
        }

        return myanmarMonthStringBuilder.toString();
    }

    /**
     * Calculate the Myanmar Year Type
     *
     * @param myear Myanmar Year
     * @return Myanmar year type.
     */
    public static int calculateYearType(int myear) {
        return MyanmarDateKernel.checkMyanmarYear(myear).get("myt");
    }

    /**
     * Calculate length of month from month, and year type. (cal_mml)
     *
     * @param mm  month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5
     *            Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *            Tabaung=12, Late Tagu=13, Late Kason=14 ],
     * @param myt year type [0=common, 1=little watat, 2=big watat])
     * @return (mml = length of the month [29 or 30 days])
     */
    public static int calculateLengthOfMonth(int mm, int myt) {
        // month length
        int mml = 30 - mm % 2;
        if (mm == 3) {
            // adjust if Nayon in big watat
            mml += myt / 2;
        }
        return mml;
    }

    /**
     * Calculate moon phase from day of the month, month, and year type. (cal_mp)
     *
     * @param mm  month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *            Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *            Tabaung=12, Late Tagu=13, Late Kason=14 ],
     * @param myt year type [0=common, 1=little watat, 2=big watat])
     * @param md  the day of the month [1-30],
     * @return (mp = moon phase [0 = waxing, 1 = full moon, 2 = waning, 3 = new moon])
     */
    public static int calculateMoonPhase(int myt, int mm, int md) {
        int mml = calculateLengthOfMonth(mm, myt);
        int d = (md / mml);
        return (int) (Math.floor((md + 1) / 16.0) + Math.floor(md / 16.0) + d);
    }

    /**
     * Calculates the apparent length of the year from year type. (cal_myl)
     *
     * @param myt year type [0=common, 1=little watat, 2=big watat]
     * @return year length [354, 384, or 385 days]
     */
    public static int calculateMyanmarYearLength(int myt) {
        return 354 + (1 - (int) Math.floor(1.0 / (myt + 1))) * 30 + (int) Math.floor(myt / 2.0);
    }

    /**
     * Calculates fortnight day from month day (cal_mf)
     *
     * @param md day of the month [1-30]
     * @return fortnight day [1 to 15]
     */
    public static int calculateFortnightDay(int md) {
        return md - 15 * (md / 16);
    }


    /**
     * Calculates day of month from fortnight day, moon phase,
     * and length of the month (cal_md)
     *
     * @param myt year type [0=common, 1=little watat, 2=big watat])
     * @param mm  month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *            Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *            Tabaung=12, Late Tagu=13, Late Kason=14 ]
     * @param mp  moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon]
     * @param mf  fortnight day [1 to 15]
     * @return (md = day of the month [1 - 30])
     */
    public static int calculateDayOfMonthByYearType(int myt, int mm, int mp, int mf) {
        int mml = calculateLengthOfMonth(mm, myt);
        int m1 = mp % 2;
        int m2 = mp / 2;
        return (m1 * (15 + m2 * (mml - 15)) + (1 - m1) * (mf + 15 * m2));
    }

    /**
     * Get day of month from myanmar year, length of the month, moon phase and fortnight day (cal_md)
     *
     * @param myear        Myanmar Year
     * @param mmonth       month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *                     Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *                     Tabaung=12, Late Tagu=13, Late Kason=14 ]
     * @param moonPhase    moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon]
     * @param fortnightDay fortnight day [1 to 15]
     * @return (md = day of the month [1 - 30])
     */
    public static int calculateDayOfMonth(int myear, int mmonth, int moonPhase, int fortnightDay) {
        // check year
        final Map<String, Integer> yo = MyanmarDateKernel.checkMyanmarYear(myear);

        // adjust month and month length
        int mml = 30 - mmonth % 2;

        if (mmonth == 3) {
            // adjust if Nayon in big watat
            mml += yo.get("myt") / 2;
        }

        int m1 = moonPhase % 2;
        int m2 = moonPhase / 2;

        return m1 * (15 + m2 * (mml - 15)) + (1 - m1) * (fortnightDay + 15 * m2);
    }
}
