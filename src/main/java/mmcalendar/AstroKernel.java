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
     * More Detail
     * <a href=
     * "http://cool-emerald.blogspot.sg/2013/12/myanmar-astrological-calendar-days.html">
     * More details</a>
     *
     * @param mmonth      Myanmar Month
     * @param monthLength Llength of the month
     * @param monthDay    day of the month [0-30]
     * @param weekDay     weekday [0=sat, 1=sun, ..., 6=fri]
     * @param myear       Myanmar year
     * @return {@link Astro} Object (Astrological informations)
     */
    public static Astro astro(int mmonth, int monthLength, int monthDay, int weekDay, int myear) {

        double d, m1, wd1, wd2;
        int sabbath, sabbatheve, yatyaza, pyathada, thamanyo, amyeittasote;
        int warameittugyi, warameittunge, yatpote, thamaphyu, nagapor, yatyotema;
        int mahayatkyan, shanyat, nagahle, mahabote, nakhat;
        int[] wda;
        int[] sya;

        if (mmonth <= 0) {
            mmonth = 4;// first waso is considered waso
        }

        d = monthDay - 15 * Math.floor(monthDay / 16.0);// waxing or waning day [0-15]
        sabbath = 0;

        if ((monthDay == 8) || (monthDay == 15) || (monthDay == 23) || (monthDay == monthLength)) {
            sabbath = 1;
        }

        sabbatheve = 0;

        if ((monthDay == 7) || (monthDay == 14) || (monthDay == 22) || (monthDay == (monthLength - 1))) {
            sabbatheve = 1;
        }

        yatyaza = 0;
        m1 = mmonth % 4;
        wd1 = Math.floor(m1 / 2.0) + 4;
        wd2 = ((1 - Math.floor(m1 / 2.0)) + m1 % 2) * (1 + 2 * (m1 % 2));

        //if ((wd == wd1) || (wd == wd2))
        if ((Math.abs(weekDay - wd1) < 0.0000001) || (Math.abs(weekDay - wd2) < 0.0000001)) {
            yatyaza = 1;
        }

        pyathada = 0;
        wda = new int[]{1, 3, 3, 0, 2, 1, 2};

        if (m1 == wda[weekDay]) {
            pyathada = 1;
        }

        if ((m1 == 0) && (weekDay == 4)) {
            pyathada = 2;// afternoon pyathada
        }

        thamanyo = 0;
        m1 = mmonth - 1 - Math.floor(mmonth / 9.0);
        wd1 = (m1 * 2 - Math.floor(m1 / 8.0)) % 7;

        wd2 = (weekDay + 7 - wd1) % 7;

        if (wd2 <= 1) {
            thamanyo = 1;
        }

        amyeittasote = 0;
        wda = new int[]{5, 8, 3, 7, 2, 4, 1};

        if ((int) d == wda[weekDay]) {
            amyeittasote = 1;
        }

        warameittugyi = 0;
        wda = new int[]{7, 1, 4, 8, 9, 6, 3};

        if (d == wda[weekDay]) {
            warameittugyi = 1;
        }

        warameittunge = 0;
        double wn = (weekDay + 6) % 7;

        if ((12 - d) == wn) {
            warameittunge = 1;
        }

        yatpote = 0;
        wda = new int[]{8, 1, 4, 6, 9, 8, 7};

        if (d == wda[weekDay]) {
            yatpote = 1;
        }

        thamaphyu = 0;
        wda = new int[]{1, 2, 6, 6, 5, 6, 7};

        if (d == wda[weekDay]) {
            thamaphyu = 1;
        }

        wda = new int[]{0, 1, 0, 0, 0, 3, 3};

        if (d == wda[weekDay]) {
            thamaphyu = 1;
        }

        if ((d == 4) && (weekDay == 5)) {
            thamaphyu = 1;
        }

        nagapor = 0;
        wda = new int[]{26, 21, 2, 10, 18, 2, 21};

        if (monthDay == wda[weekDay]) {
            nagapor = 1;
        }

        wda = new int[]{17, 19, 1, 0, 9, 0, 0};

        if (monthDay == wda[weekDay]) {
            nagapor = 1;
        }

        if (((monthDay == 2) && (weekDay == 1)) || (((monthDay == 12) || (monthDay == 4) || (monthDay == 18)) && (weekDay == 2))) {
            nagapor = 1;
        }

        yatyotema = 0;

        // m1 = (mm % 2) ? mm : ((mm + 9) % 12);

        m1 = (mmonth % 2 > 0) ? mmonth : ((mmonth + 9) % 12);
        m1 = (m1 + 4) % 12 + 1;

        if (d == m1) {
            yatyotema = 1;
        }

        mahayatkyan = 0;
        m1 = (Math.floor((mmonth % 12) / 2.0) + 4) % 6 + 1;

        if (d == m1) {
            mahayatkyan = 1;
        }

        shanyat = 0;
        sya = new int[]{8, 8, 2, 2, 9, 3, 3, 5, 1, 4, 7, 4};

        if (d == sya[mmonth - 1]) {
            shanyat = 1;
        }

        nagahle = (int) Math.floor((mmonth % 12) / 3.0);
        mahabote = (myear - weekDay) % 7;
        nakhat = myear % 3;

        Astro astro = new Astro();
        astro.sabbath = sabbath;
        astro.sabbatheve = sabbatheve;
        astro.yatyaza = yatyaza;
        astro.pyathada = pyathada;
        astro.thamanyo = thamanyo;
        astro.amyeittasote = amyeittasote;
        astro.warameittugyi = warameittugyi;
        astro.warameittunge = warameittunge;
        astro.yatpote = yatpote;
        astro.thamaphyu = thamaphyu;
        astro.nagapor = nagapor;
        astro.yatyotema = yatyotema;
        astro.mahayatkyan = mahayatkyan;
        astro.shanyat = shanyat;
        /* 0=west, 1=north, 2=east, 3=south */
        astro.nagahle = nagahle;
        /* 0=Binga, 1=Atun, 2=Yaza, 3=Adipati, 4=Marana, 5=Thike, 6=Puti */
        astro.mahabote = mahabote;
        astro.nakhat = nakhat;
        astro.yearName = myear % 12;

        return astro;
    }

}
