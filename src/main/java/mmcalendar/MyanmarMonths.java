package mmcalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Myanmar Months List for Specific Myanmar Year
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0.3
 * @since 1.0.1
 */
public class MyanmarMonths implements Serializable {

    private final List<Integer> monthList;
    private final List<String> monthNameList;
    private final int calculationMonth; //Calculation Month

    protected MyanmarMonths(List<Integer> monthList, List<String> monthNameList, int calculationMonth) {
        this.monthList = new ArrayList<>(monthList);
        this.monthNameList = new ArrayList<>(monthNameList);
        this.calculationMonth = calculationMonth;
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
    public static MyanmarMonths of(int myear, int mmonth) {
        return MyanmarCalendarKernel.calculateRelatedMyanmarMonths(myear, mmonth);
    }

    public List<Integer> getMonthList() {
        return monthList;
    }

    public List<String> getMonthNameList() {
        return monthNameList;
    }

    public List<String> getMonthNameList(Language language) {
        if (language == Language.ENGLISH) {
            return monthNameList;
        }
        List<String> temp = new ArrayList<>();
        for (String string : monthNameList) {
            temp.add(LanguageTranslator.translateSentence(string, Language.ENGLISH, language));
        }
        return temp;
    }

    /**
     * @return Calculation Month
     */
    public int getCalculationMonth() {
        return calculationMonth;
    }

    /**
     * @return Current Month Name
     */
    public String getCalculationMonthName() {
        int month = monthList.indexOf(calculationMonth);
        return monthNameList.get(month);
    }

    public int getCalculationMonthIndex() {
        return monthList.indexOf(calculationMonth);
    }

    @Override
    public String toString() {
        return "MyanmarMonths{" +
                "monthList=" + monthList +
                ", monthNameList=" + monthNameList +
                ", calculationMonthIndex=" + calculationMonth +
                '}';
    }
}
