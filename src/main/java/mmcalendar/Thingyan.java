package mmcalendar;

import java.io.Serializable;
import java.time.DateTimeException;

/**
 * Thingyan (Atat Time, Akya Time, Atat Day, Akya Day)
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0.2
 * @since 1.0
 */
public class Thingyan implements Serializable {

    private static final long serialVersionUID = 7874420766164176538L;

    /**
     * Atat Time
     * သင်္ကြန်တက်ချိန်
     */
    private final double ja;
    /**
     * Akya Time
     * သင်္ကြန်ကျချိန်
     */
    private final double jk;
    /**
     * Atat Day
     * သင်္ကြန်အတက်နေ့
     */
    private final double da;

    /**
     * Akya Day
     * အကျနေ့
     */
    private final double dk;

    /**
     * @param ja Atat Time Julian date time (သင်္ကြန်တက်ချိန်)
     * @param jk Akya Time Julian date time (သင်္ကြန်ကျချိန်)
     * @param da Atat Day (သင်္ကြန်အတက်နေ့)
     * @param dk Akya Day (အကျနေ့)
     */
    private Thingyan(double ja, double jk, double da, double dk) {
        this.ja = ja;
        this.jk = jk;
        this.da = da;
        this.dk = dk;
    }

    //-----------------------------------------------------------------------

    /**
     * Calculate the Thingyan (Myanmar new year)
     *
     * @param myear - Myanmar year
     * @return {@link Thingyan} Object
     */
    public static Thingyan of(int myear) {
        // start of Thingyan (BGNTG)
        int bgntg = 1100;
        if (myear < bgntg) {
            throw new DateTimeException("Thingyan starting from " + bgntg + "of myanmar year.");
        }
        return Thingyan.algorithm(myear);
    }

    //-----------------------------------------------------------------------

    /**
     * Calculate the Thingyan (Myanmar new year)
     *
     * @param myear - Myanmar year
     * @return {@link Thingyan} Object
     */
    private static Thingyan algorithm(int myear) {
        // Atat Time
        double ja = Constants.SY * myear + Constants.MO;
        // Akya Time
        double jk;

        if (myear >= Constants.SE3) {
            jk = ja - 2.169918982;
        } else {
            jk = ja - 2.1675;
        }

        // Atat Day
        double da = Math.round(ja);
        // Akya Day
        double dk = Math.round(jk);

        return new Thingyan(ja, jk, da, dk);
    }

    //-----------------------------------------------------------------------

    /**
     * Atat Time (သင်္ကြန်တက်ချိန်)
     *
     * @return Julian date time
     */
    public double getAtatTime() {
        return ja;
    }

    /**
     * Akya time (သင်္ကြန်ကျချိန်)
     *
     * @return Julian date time
     */
    public double getAkyaTime() {
        return jk;
    }

    /**
     * Atat day (သင်္ကြန်အတက်နေ့)
     *
     * @return Julian date
     */
    public double getAtatDay() {
        return da;
    }

    /**
     * Akya day (အကျနေ့)
     *
     * @return Julian date
     */
    public double getAkyaDay() {
        return dk;
    }

    /**
     * Thingyan Akyo day (သင်္ကြန်အကြိုနေ့)
     *
     * @return Julian date
     */
    public double getAkyoDay() {
        return dk - 1;
    }

    /**
     * Thingyan Akyat day (အကြတ်နေ့)
     *
     * @return Julian dates
     */
    public double[] getAkyatDay() {
        if ((da - dk) > 2) {
            return new double[]{dk + 1, dk + 2};
        }
        return new double[]{dk + 1};
    }

    /**
     * Myanmar New Year's Day (နှစ်ဆန်းတစ်ရက်နေ့)
     *
     * @return Julian date
     */
    public double getMyanmarNewYearDay() {
        return da + 1;
    }

    @Override
    public String toString() {
        return "Thingyan [Atat Time =" + ja + ", Akya Time =" + jk + ", Atat Day =" + da + ", Akya Day =" + dk + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(da);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dk);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ja);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(jk);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Thingyan other = (Thingyan) obj;
        if (Double.doubleToLongBits(da) != Double.doubleToLongBits(other.da))
            return false;
        if (Double.doubleToLongBits(dk) != Double.doubleToLongBits(other.dk))
            return false;
        if (Double.doubleToLongBits(ja) != Double.doubleToLongBits(other.ja))
            return false;
        return Double.doubleToLongBits(jk) == Double.doubleToLongBits(other.jk);
    }

}
