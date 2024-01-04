package mmcalendar;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class MyanmarThingyanDateTime implements Serializable {

    /**
     * Thingyan Akyo day (သင်္ကြန်အကြိုနေ့)
     */
    private final MyanmarDate akyoDay;

    /**
     * Akya time (သင်္ကြန်ကျချိန်)
     */
    private final MyanmarDate akyaTime;

    /**
     * Akya day (အကျနေ့)
     */
    private final MyanmarDate akyaDay;

    /**
     * Atat Time (သင်္ကြန်တက်ချိန်)
     */
    private final MyanmarDate atatTime;

    /**
     * Atat day (သင်္ကြန်အတက်နေ့)
     */
    private final MyanmarDate atatDay;

    /**
     * Thingyan Akyat day (အကြတ်နေ့)
     */
    private final MyanmarDate[] akyatDays;

    /**
     * Myanmar New Year's Day (နှစ်ဆန်းတစ်ရက်နေ့)
     */
    private final MyanmarDate myanmarNewYearDay;

    /**
     * Constructor
     *
     * @param myear - Myanmar year
     */
    private MyanmarThingyanDateTime(int myear) {
        Thingyan thingyan = Thingyan.of(myear);

        this.akyoDay = MyanmarDate.of(thingyan.getAkyoDay());

        this.akyaTime = MyanmarDate.of(thingyan.getAkyaTime());
        this.akyaDay = MyanmarDate.of(thingyan.getAkyaDay());

        this.atatTime = MyanmarDate.of(thingyan.getAtatTime());
        this.atatDay = MyanmarDate.of(thingyan.getAtatDay());

        if (thingyan.getAkyatDay().length > 1) {
            akyatDays = new MyanmarDate[]{
                    MyanmarDate.of(thingyan.getAkyatDay()[0]),
                    MyanmarDate.of(thingyan.getAkyatDay()[1])
            };
        } else {
            akyatDays = new MyanmarDate[]{
                    MyanmarDate.of(thingyan.getAkyatDay()[0])
            };
        }

        this.myanmarNewYearDay = MyanmarDate.of(thingyan.getMyanmarNewYearDay());

    }

    /**
     * Creates a MyanmarThingyanDateTime instance for the specified Myanmar year.
     *
     * @param myear The Myanmar year for which to create the instance.
     * @return A new instance of MyanmarThingyanDateTime for the given year.
     */
    public static MyanmarThingyanDateTime of(int myear) {
        return new MyanmarThingyanDateTime(myear);
    }

    /**
     * @return Thingyan Akyo day (သင်္ကြန်အကြိုနေ့)
     */
    public MyanmarDate getAkyoDay() {
        return akyoDay;
    }

    /**
     * @return Akya time (သင်္ကြန်ကျချိန်)
     */
    public MyanmarDate getAkyaTime() {
        return akyaTime;
    }

    /**
     * @return Akya day (အကျနေ့)
     */
    public MyanmarDate getAkyaDay() {
        return akyaDay;
    }

    /**
     * @return Atat Time (သင်္ကြန်တက်ချိန်)
     */
    public MyanmarDate getAtatTime() {
        return atatTime;
    }

    /**
     * @return Atat day (သင်္ကြန်အတက်နေ့)
     */
    public MyanmarDate getAtatDay() {
        return atatDay;
    }

    /**
     * @return Thingyan Akyat day (အကြတ်နေ့)
     */
    public MyanmarDate[] getAkyatDays() {
        return akyatDays;
    }

    /**
     * @return Myanmar New Year's Day (နှစ်ဆန်းတစ်ရက်နေ့)
     */
    public MyanmarDate getMyanmarNewYearDay() {
        return myanmarNewYearDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyanmarThingyanDateTime that = (MyanmarThingyanDateTime) o;
        return Objects.equals(akyoDay, that.akyoDay) && Objects.equals(akyaTime, that.akyaTime) && Objects.equals(akyaDay, that.akyaDay) && Objects.equals(atatTime, that.atatTime) && Objects.equals(atatDay, that.atatDay) && Arrays.equals(akyatDays, that.akyatDays) && Objects.equals(myanmarNewYearDay, that.myanmarNewYearDay);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(akyoDay, akyaTime, akyaDay, atatTime, atatDay, myanmarNewYearDay);
        result = 31 * result + Arrays.hashCode(akyatDays);
        return result;
    }

    @Override
    public String toString() {
        return "MyanmarThingyanDateTime{" +
                "akyoDay=" + akyoDay +
                ", akyaTime=" + akyaTime +
                ", akyaDay=" + akyaDay +
                ", atatTime=" + atatTime +
                ", atatDay=" + atatDay +
                ", akyatDays=" + Arrays.toString(akyatDays) +
                ", myanmarNewYearDay=" + myanmarNewYearDay +
                '}';
    }
}
