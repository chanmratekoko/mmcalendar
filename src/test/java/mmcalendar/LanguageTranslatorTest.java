package mmcalendar;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LanguageTranslatorTest {

    @Test
    public void translate() {
        Language language = Language.MYANMAR;
        String myanmar = LanguageTranslator.translate("Year", language);
        assertEquals("နှစ်", myanmar);
    }

    @Test
    public void translateTwo() {
        String myanmar = LanguageTranslator.translate("Year", Language.MYANMAR);
        String zawgyi = LanguageTranslator.translate("Year", Language.ZAWGYI);
        String mon = LanguageTranslator.translate("Year", Language.MON);
        String tai = LanguageTranslator.translate("Year", Language.TAI);
        String karen = LanguageTranslator.translate("Year", Language.SGAW_KAREN);

        assertEquals("နှစ်", myanmar);
        assertEquals("ႏွစ္", zawgyi);
        assertEquals("နှစ်", mon);
        assertEquals("ပီ", tai);
        assertEquals("နံၣ်", karen);
    }

    @Test
    public void translateThree() {
        String tai = LanguageTranslator.translate("နှစ်", Language.MYANMAR, Language.TAI);
        assertEquals("ပီ", tai);
    }

    @Test
    public void translateSentence() {
        String sentence = "သာသနာနှစ် ၂၅၆၆ ခု, မြန်မာနှစ် ၁၃၈၄ ခု, နှောင်းတန်ခူး လဆန်း ၁ ရက် အင်္ဂါနေ့";
        String tai = LanguageTranslator.translateSentence(sentence, Language.MYANMAR, Language.TAI);
        String aspect = "ပီသႃႇသၼႃႇ 2566 ၶု, ပီၵေႃးၸႃႇ 1384 ၶု, ဝၢႆးႁႃႈ လိူၼ်မႂ်ႇ 1 ဝၼ်း ဝၼ်းဢၢင်းၵၢၼ်းဝၼ်း";
        assertEquals(aspect, tai);
    }

    @Test
    public void numberToString(){
        Language language = Language.MYANMAR;
        String string = LanguageTranslator.translate(-999, language);
        Assert.assertEquals("-\u1049\u1049\u1049", string);
    }
}
