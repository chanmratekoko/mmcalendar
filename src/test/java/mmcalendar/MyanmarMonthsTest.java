package mmcalendar;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;

public class MyanmarMonthsTest {

    @Test
    public void getMyanmarMonth() {
        MyanmarMonths myanmarMonths = MyanmarCalendarKernel.calculateRelatedMyanmarMonths(1380, 1);

        assertThat(0, is(myanmarMonths.getCalculationMonthIndex()));
        assertThat(2, is(myanmarMonths.getCalculationMonth()));
        Assert.assertEquals("Kason", myanmarMonths.getCalculationMonthName());
    }

    @Test
    public void getAllMonthNames() {
        int year = 1381;
        List<String> myanmarNameList = calculateRelatedMyanmarMonthNames(year);
        myanmarNameList.forEach(System.out::println);

        String[] aspects = {
                "Tagu",
                "Kason",
                "Nayon",
                "Waso",
                "Wagaung",
                "Tawthalin",
                "Thadingyut",
                "Tazaungmon",
                "Nadaw",
                "Pyatho",
                "Tabodwe",
                "Tabaung",
                "Late Tagu",
        };

        assertArrayEquals(aspects, myanmarNameList.toArray());
    }

    public static List<String> calculateRelatedMyanmarMonthNames(int myear) {
        return MyanmarCalendarKernel.calculateRelatedMyanmarMonths(myear, 0).getMonthNameList();
    }
}
