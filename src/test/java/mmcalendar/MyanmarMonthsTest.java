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

        final String[] aspects = {
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

    @Test
    public void getAllMonthNamesTai() {
        int myear = 1381;
        MyanmarMonths myanmarMonths = MyanmarCalendarKernel.calculateRelatedMyanmarMonths(myear, 0);
        List<String> myanmarNameList = myanmarMonths.getMonthNameList(Language.TAI);

        final String[] taiMonthNames = {"ႁႃႈ", "ႁူၵ်း", "ၸဵတ်း", "ပႅတ်ႇ", "ၵဝ်ႈ",
                "သိပ်း", "သိပ်းဢဵတ်း", "သိပ်းသွင်", "ၸဵင်", "ၵမ်", "သၢမ်", "သီႇ", "ဝၢႆး ႁႃႈ",
        };

        assertArrayEquals(taiMonthNames, myanmarNameList.toArray());

        final String[] monthNames = {
                "Tagu", "Kason", "Nayon", "Waso", "Wagaung", "Tawthalin", "Thadingyut",
                "Tazaungmon", "Nadaw", "Pyatho", "Tabodwe", "Tabaung", "Late Tagu",
        };

        assertArrayEquals(monthNames, myanmarMonths.getMonthNameList(Language.ENGLISH).toArray());
    }

    @Test
    public void getAllMonthNamesLate() {
        int myear = 1385;
        MyanmarMonths myanmarMonths = MyanmarCalendarKernel.calculateRelatedMyanmarMonths(myear, 13);

        final String[] monthNames = {
                "Tagu", "Kason", "Nayon", "First Waso", "Second Waso", "Wagaung",
                "Tawthalin", "Thadingyut",
                "Tazaungmon", "Nadaw", "Pyatho", "Tabodwe", "Tabaung", "Late Tagu",
        };

        assertArrayEquals(monthNames, myanmarMonths.getMonthNameList(Language.ENGLISH).toArray());
    }

    @Test
    public void getAllMonthNamesLateMM() {
        int myear = 1385;
        MyanmarMonths myanmarMonths = MyanmarCalendarKernel.calculateRelatedMyanmarMonths(myear, 13);

        final String[] monthNames = {
                "တန်ခူး", "ကဆုန်", "နယုန်", "ပ ဝါဆို", "ဒု ဝါဆို", "ဝါခေါင်",
                "တော်သလင်း", "သီတင်းကျွတ်",
                "တန်ဆောင်မုန်း", "နတ်တော်", "ပြာသို", "တပို့တွဲ", "တပေါင်း", "နှောင်း တန်ခူး",
        };

        assertArrayEquals(monthNames, myanmarMonths.getMonthNameList(Language.MYANMAR).toArray());
    }

    public static List<String> calculateRelatedMyanmarMonthNames(int myear) {
        return MyanmarCalendarKernel.calculateRelatedMyanmarMonths(myear, 0).getMonthNameList();
    }
}
