package mmcalendar;


/**
 * Calculation Algorithms for Myanmar Astro
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0
 */
public final class AstroKernel {

    /**
     * Don't let anyone instantiate this class.
     */
    private AstroKernel() {
    }

    /**
     * Calculate thamaphyu
     *
     * @param md      day of the month [1-30],
     * @param weekDay week day  [0=sat, 1=sun, ..., 6=fri])
     * @return [1=thamaphyu, 0=else]
     */
    public static int calculateThamaphyu(int md, int weekDay) {
        // get fortnight
        int mf = MyanmarCalendarKernel.calculateFortnightDay(md);

        int[] wda = {1, 2, 6, 6, 5, 6, 7};
        int[] wdb = {0, 1, 0, 0, 0, 3, 3};
        return (mf == wda[weekDay]
                || mf == wdb[weekDay]
                || (mf == 4 && weekDay == 5)
        ) ? 1 : 0;
    }

    /**
     * nagapor
     *
     * @param md      day of the month [1-30],
     * @param weekDay week day  [0=sat, 1=sun, ..., 6=fri])
     * @return [1=nagapor, 0=else]
     */
    static int calculateNagapor(int md, int weekDay) {
        int[] wda = {26, 21, 2, 10, 18, 2, 21};
        int[] wdb = {17, 19, 1, 0, 9, 0, 0};

        return (md == wda[weekDay]
                || md == wdb[weekDay]
                || ((md == 2 && weekDay == 1)
                || ((md == 12 || md == 4 || md == 18) && weekDay == 2))
        ) ? 1 : 0;
    }

    /**
     * yatyotema
     *
     * @param mmonth month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *               Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *               Tabaung=12, Late Tagu=13, Late Kason=14],
     * @param md     day of the month [1-30]
     * @return [1=yatyotema, 0=else]
     */
    static int calculateYatyotema(int mmonth, int md) {
        int mmt = (mmonth / 13);
        // to 1-12 with month type
        mmonth = mmonth % 13 + mmt;
        if (mmonth <= 0) {
            // First Waso is considered Waso
            mmonth = 4;
        }
        // fortnight day [0-15]
        int mf = MyanmarCalendarKernel.calculateFortnightDay(md);
        int m1 = (mmonth % 2 != 0) ? mmonth : (mmonth + 9) % 12;
        m1 = (m1 + 4) % 12 + 1;
        return (mf == m1) ? 1 : 0;
    }

    /**
     * mahayatkyan
     *
     * @param mmonth month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *               Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *               Tabaung=12, Late Tagu=13, Late Kason=14 ],
     * @param md     day of the month [1-30]
     * @return [1=mahayatkyan, 0=else]
     */
    static int calculateMahayatkyan(int mmonth, int md) {

        if (mmonth <= 0) {
            // First Waso is considered Waso
            mmonth = 4;
        }

        // fortnight day [0-15]
        int mf = MyanmarCalendarKernel.calculateFortnightDay(md);
        int m1 = (((mmonth % 12) / 2) + 4) % 6 + 1;
        return (mf == m1) ? 1 : 0;
    }

    /**
     * shanyat
     *
     * @param mmonth month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *               Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *               Tabaung=12, Late Tagu=13, Late Kason=14 ],
     * @param md     day of the month [1-30])
     * @return [1=shanyat, 0=else]
     */
    static int calculateShanyat(int mmonth, int md) {
        int mmt = (mmonth / 13);
        // to 1-12 with month type
        mmonth = mmonth % 13 + mmt;
        if (mmonth <= 0) {
            // First Waso is considered Waso
            mmonth = 4;
        }
        // fortnight day [0-15]
        int mf = MyanmarCalendarKernel.calculateFortnightDay(md);
        int[] sya = {8, 8, 2, 2, 9, 3, 3, 5, 1, 4, 7, 4};
        return (mf == sya[mmonth - 1]) ? 1 : 0;
    }

    /**
     * Calculate sabbath day and sabbath eve from day of the month, month,
     * and year type. (cal_sabbath)
     *
     * @param yearType year type [0=common, 1=little watat, 2=big watat])
     * @param mmonth   month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *                 Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *                 Tabaung=12, Late Tagu=13, Late Kason=14 ],
     * @param md       day of the month [1-30],
     * @return [1=sabbath, 2=sabbath eve, 0=else]
     */
    static int calculateSabbath(int yearType, int mmonth, int md) {
        int mml = MyanmarCalendarKernel.calculateLengthOfMonth(mmonth, yearType);
        int sabbath = 0;

        if ((md == 8) || (md == 15) || (md == 23) || (md == mml)) {
            sabbath = 1;
        }

        if ((md == 7) || (md == 14) || (md == 22) || (md == (mml - 1))) {
            sabbath = 2;
        }

        return sabbath;
    }

    /**
     * Calculate yatyaza from month, and weekday (cal_yatyaza)
     *
     * @param mm      month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *                Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *                Tabaung=12, Late Tagu=13, Late Kason=14 ],
     * @param weekDay week day  [0=sat, 1=sun, ..., 6=fri]
     * @return [1=yatyaza, 0=else]
     */
    static int calculateYatyaza(int mm, int weekDay) {
        // First Waso is considered Waso
        int m1 = mm % 4;
        int yatyaza = 0;
        int wd1 = (m1 / 2) + 4;
        int wd2 = ((1 - (m1 / 2)) + m1 % 2) * (1 + 2 * (m1 % 2));

        if ((weekDay == wd1) || (weekDay == wd2)) {
            yatyaza = 1;
        }

        return yatyaza;
    }

    /**
     * Calculate pyathada from month, and weekday (cal_pyathada)
     *
     * @param mmonth  month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *                Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *                Tabaung=12, Late Tagu=13, Late Kason=14 ],
     * @param weekDay weekday  [0=sat, 1=sun, ..., 6=fri])
     * @return [1=pyathada, 2=afternoon pyathada, 0=else]
     */
    static int calculatePyathada(int mmonth, int weekDay) {

        //first waso is considered waso
        int m1 = mmonth % 4;

        int[] wda = {1, 3, 3, 0, 2, 1, 2};

        int pyathada = 0;

        if (m1 == wda[weekDay]) {
            pyathada = 1;
        }

        if (m1 == 0 && weekDay == 4) {
            // afternoon pyathada
            pyathada = 2;
        }

        return pyathada;
    }

    /**
     * Calculate nagahle from Myanmar month
     *
     * @param mmonth month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *               Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *               Tabaung=12, Late Tagu=13, Late Kason=14 ]
     * @return [0=west, 1=north, 2=east, 3=south]
     */
    static int calculateNagahle(int mmonth) {
        // First Waso is considered Waso
        if (mmonth <= 0) {
            mmonth = 4;
        }
        return (mmonth % 12) / 3;
    }

    /**
     * mahabote
     *
     * @param myear   Myanmar Year
     * @param weekDay [0=sat, 1=sun, ..., 6=fri]
     * @return [0=Binga, 1=Atun, 2=Yaza, 3=Adipati, 4= Marana, 5=Thike, 6=Puti]
     */
    static int calculateMahabote(int myear, int weekDay) {
        return (myear - weekDay) % 7;
    }

    /**
     * nakhat
     *
     * @param myear Myanmar Year
     * @return [0=Ogre, 1=Elf, 2=Human]
     */
    static int calculateNakhat(int myear) {
        return myear % 3;
    }

    /**
     * thamanyo
     *
     * @param mmonth  [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
     *                Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
     *                Tabaung=12, Late Tagu=13, Late Kason=14 ]
     * @param weekDay [0=sat, 1=sun, ..., 6=fri]
     * @return [1=thamanyo, 0=else]
     */
    static int calculateThamanyo(int mmonth, int weekDay) {
        int mmt = mmonth / 13;

        // to 1-12 with month type
        mmonth = mmonth % 13 + mmt;

        if (mmonth <= 0) {
            // First Waso is considered Waso (looks like no need here)
            mmonth = 4;
        }

        int m1 = mmonth - 1 - (mmonth / 9);
        int wd1 = (m1 * 2 - (m1 / 8)) % 7;
        int wd2 = (weekDay + 7 - wd1) % 7;

        return (wd2 <= 1) ? 1 : 0;
    }

    /**
     * amyeittasote
     *
     * @param md      day of the month [1-30]
     * @param weekDay week day  [0=sat, 1=sun, ..., 6=fri])
     * @return [1=amyeittasote, 0=else]
     */
    static int calculateAmyeittasote(int md, int weekDay) {
        // fortnight day [0-15]
        int mf = MyanmarCalendarKernel.calculateFortnightDay(md);
        int[] wda = {5, 8, 3, 7, 2, 4, 1};
        return (mf == wda[weekDay]) ? 1 : 0;
    }

    /**
     * warameittugyi
     *
     * @param md      day of the month [1-30],
     * @param weekDay [0=sat, 1=sun, ..., 6=fri]
     * @return [1=warameittugyi, 0=else]
     */
    static int calculateWarameittugyi(int md, int weekDay) {
        // fortnight day [0-15]
        int mf = MyanmarCalendarKernel.calculateFortnightDay(md);
        int[] wda = {7, 1, 4, 8, 9, 6, 3};
        return (mf == wda[weekDay]) ? 1 : 0;
    }

    /**
     * warameittunge
     *
     * @param md      day of the month [1-30]
     * @param weekDay week day  [0=sat, 1=sun, ..., 6=fri]
     * @return [1=warameittunge, 0=else]
     */
    static int calculateWarameittunge(int md, int weekDay) {
        // fortnight day [0-15]
        int mf = MyanmarCalendarKernel.calculateFortnightDay(md);
        int wn = (weekDay + 6) % 7;
        return ((12 - mf) == wn) ? 1 : 0;
    }

    /**
     * yatpote
     *
     * @param md day of the month [1-30],
     * @param wd weekday  [0=sat, 1=sun, ..., 6=fri])
     * @return [1=yatpote, 0=else]
     */
    static int calculateYatpote(int md, int wd) {
        // fortnight day [0-15]
        int mf = MyanmarCalendarKernel.calculateFortnightDay(md);
        int[] wda = {8, 1, 4, 6, 9, 8, 7};
        return (mf == wda[wd]) ? 1 : 0;
    }

    static int calculateYearName(int myear) {
        return myear % 12;
    }

}
