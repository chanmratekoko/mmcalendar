package mmcalendar;

import org.junit.Assert;
import org.junit.Test;

public class MyanmarDateTest {

    @Test
    public void outputsInEnglish() {
        MyanmarDate myanmarDate = MyanmarDate.of(1989, 4, 15);
        final String format = "S s k, B y k, M p f r E n";
        Language language = Language.ENGLISH;
        String aspectMyanmarResult = "Sasana Year 2532 Ku, Myanmar Year 1350 Ku, Late Tagu Waxing 10 Yat Saturday Nay";
        Assert.assertEquals(aspectMyanmarResult, myanmarDate.format(format, language));
    }

    @Test
    public void outputsInMyanmarBurmese() {
        MyanmarDate myanmarDate = MyanmarDate.of(1989, 4, 15);
        final String format = "S s k, B y k, M p f r En";
        Language language = Language.MYANMAR;
        String aspectMyanmarResult = "သာသနာနှစ် ၂၅၃၂ ခု, မြန်မာနှစ် ၁၃၅၀ ခု, နှောင်းတန်ခူး လဆန်း ၁၀ ရက် စနေနေ့";
        Assert.assertEquals(aspectMyanmarResult, myanmarDate.format(format, language));
    }

}
