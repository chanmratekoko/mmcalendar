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
            temp.add(LanguageTranslator.translate(string, language));
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
