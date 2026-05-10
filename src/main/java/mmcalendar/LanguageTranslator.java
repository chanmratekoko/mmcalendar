package mmcalendar;

import java.util.*;

/**
 * Language Translator
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 2.0.0
 * @since 1.0.10 Release
 */
public class LanguageTranslator {

    private static final int LANG_COUNT = 6;

    /**
     * Language catalog mapping words/phrases across 6 languages
     * <pre>
     * Index:
     *  0: English
     *  1: Myanmar (Unicode)
     *  2: Zawgyi
     *  3: Mon
     *  4: Tai
     *  5: Karen (Sgaw)
     * </pre>
     *
     * Credits:
     * <ul>
     *   <li>Mon: ITVilla (<a href="http://it-villa.blogspot.com/">Link</a>), proofread by Mikau Nyan</li>
     *   <li>Tai: Jao Tai Num, Tai Dictionary (<a href="https://www.taidictionary.com/">Link</a>)</li>
     * </ul>
     */
    private static final String[][] CATALOG = {
            {"Myanmar Year", "မြန်မာနှစ်", "ျမန္မာႏွစ္", "သက္ကရာဇ်ဍုၚ်", "ပီၵေႃးၸႃႇ", "ကီၢ်ပယီၤ"},
            {"Good Friday", "သောကြာနေ့ကြီး", "ေသာၾကာေန႔ႀကီး", "သောကြာနေ့ကြီး", "ဢၼ်လီဝၼ်းသုၵ်", "မုၢ်ဖီဖး"},
            {"New Year's", "နှစ်ဆန်း", "ႏွစ္ဆန္း", "လှာဲသၞာံ", "ပီမႂ်ႇ", "နှစ်ဆန်း"},
            {"Independence", "လွတ်လပ်ရေး", "လြတ္လပ္ေရး", "သၠးပွး", "ဢၼ်လွတ်ႈလႅဝ်", "လွတ်လပ်ရေး"},
            {"Union", "ပြည်ထောင်စု", "ျပည္ေထာင္စု", "ကၟိန်ဍုၚ်", "ၸိုင်ႈမိူင်းႁူမ်ႈတုမ်ႊ", "ပြည်ထောင်စု"},
            {"Peasants'", "တောင်သူလယ်သမား", "ေတာင္သူလယ္သမား", "သၟာဗ္ၚ", "ၸဝ်ႈႁႆႈၸဝ်ႈၼႃး", "တောင်သူလယ်သမား"},
            {"Resistance", "တော်လှန်ရေး", "ေတာ္လွန္ေရး", "ပၠန်ဂတးဗၟာ", "လွင်ႈလုၵ်ႉၽိုၼ်", "တော်လှန်ရေး"},
            {"Labour", "အလုပ်သမား", "အလုပ္သမား", "သၟာကမၠောန်", "ၵူၼ်းႁဵတ်းၵၢၼ်", "အလုပ်သမား"},
            {"Martyrs'", "အာဇာနည်", "အာဇာနည္", "အာဇာနဲ", "ၽူႈႁတ်းငၢၼ်", "အာဇာနည်"},
            {"Christmas", "ခရစ္စမတ်", "ခရစၥမတ္", "ခရေဿမာတ်", "ပွႆးၶရိတ်ႉသမတ်ႉၸ်", "ခရံာ်အိၣ်ဖျဲၣ်မူးပွဲန့ၣ်"},
            {"Buddha", "ဗုဒ္ဓ", "ဗုဒၶ", "သ္ဘၚ်ဖဍာ်ဇြဲ", "ပုတ်ႉထ", "ဗုဒ္ဓ"},
            {"Start of Buddhist Lent", "ဓမ္မစကြာနေ့", "ဓမၼစၾကာေန႔", "တ္ၚဲတွံဓဝ်ဓမ္မစက်", "ဓမ္မစကြာနေ့", "ဓမ္မစကြာနေ့"},
            {"End of Buddhist Lent", "မီးထွန်းပွဲ", "မီးထြန္းပြဲ", "တ္ၚဲအဘိဓရ်", "ပွႆတႆႈၾႆး", "မီးထွန်းပွဲ"},
            {"Tazaungdaing", "တန်ဆောင်တိုင်", "တန္ေဆာင္တိုင္", "သ္ဘၚ်ပူဇဴပၟတ်ပၞာၚ်", "တန်ဆောင်တိုင်", "တန်ဆောင်တိုင်"},
            {"National", "အမျိုးသား", "အမ်ိဳးသား", "ကောန်ဂကူဗၟာ", "ၵူၼ်းမိူင်", "အမျိုးသား"},
            {"Karen", "ကရင်", "ကရင္", "ကရေၚ်", "ယၢင်းၽိူၵ်ႇ", "ကရင်"},
            {"Pwe", "ပွဲ", "ပြဲ", "သ္ဘၚ်", "ပွႆ", "ပွဲ"},
            {"Thingyan", "သင်္ကြန်", "သၾကၤန္", "အတး", "သၢင်းၵျၢၼ်ႇ", "သင်္ကြန်"},
            {"Akyo", "အကြို", "အႀကိဳ", "ဒစး", "အကြို", "ႁပ်ႉ"},
            {"Akyat", "အကြတ်", "အၾကတ္", "ကြာပ်", "ၵျၢပ်ႈ", "အကြတ်"},
            {"Akya", "အကျ", "အက်", "စှေ်", "တူၵ်း", "အကျ"},
            {"Atat", "အတက်", "အတက္", "တိုန်", "ၶိုၼ်ႈ", "အတက်"},
            {"Amyeittasote", "အမြိတ္တစုတ်", "အၿမိတၱစုတ္", "ကိုန်အမြိုတ်", "အမြိတ္တစုတ်", "အမြိတ္တစုတ်"},
            {"Warameittugyi", "ဝါရမိတ္တုကြီး", "ဝါရမိတၱဳႀကီး", "ကိုန်ဝါရမိတ္တုဇၞော်", "ဝါရမိတ္တုကြီး", "ဝါရမိတ္တုကြီး"},
            {"Warameittunge", "ဝါရမိတ္တုငယ်", "ဝါရမိတၱဳငယ္", "ကိုန်ဝါရမိတ္တုဍောတ်", "ဝါရမိတ္တုငယ်", "ဝါရမိတ္တုငယ်"},
            {"Thamaphyu", "သမားဖြူ", "သမားျဖဴ", "ကိုန်လေၚ်ဒိုက်", "သမားဖြူ", "သမားဖြူ"},
            {"Thamanyo", "သမားညို", "သမားညိဳ", "ကိုန်ဟုံဗြမ်", "သမားညို", "သမားညို"},
            {"Yatpote", "ရက်ပုပ်", "ရက္ပုပ္", "ကိုန်လီုလာ်", "ရက်ပုပ်", "ရက်ပုပ်"},
            {"Yatyotema", "ရက်ယုတ်မာ", "ရက္ယုတ္မာ", "ကိုန်ယုတ်မာ", "ရက်ယုတ်မာ", "ရက်ယုတ်မာ"},
            {"Mahayatkyan", "မဟာရက်ကြမ်း", "မဟာရက္ၾကမ္း", "ကိုန်ဟွံခိုဟ်", "မဟာရက်ကြမ်း", "မဟာရက်ကြမ်း"},
            {"Nagapor", "နဂါးပေါ်", "နဂါးေပၚ", "နာ်မံက်", "နဂါးပေါ်", "နဂါးပေါ်"},
            {"Shanyat", "ရှမ်းရက်", "ရွမ္းရက္", "တ္ၚဲဒတန်", "ရှမ်းရက်", "ရှမ်းရက်"},
            {"'Mon'", "မွန်", "မြန္", "ပၠန်", "မွၼ်း", "မွန်"},
            {"G. Aung San BD", "ဗိုလ်ချုပ်မွေးနေ့", "ဗိုလ္ခ်ဳပ္ေမြးေန႔", "တ္ၚဲသၟိၚ်ဗၟာ အံၚ်သာန်ဒှ်မၞိဟ်", "ဝၼ်းၵိူတ်ၸွမ်သိုၵ်", "ဗိုလ်ချုပ်မွေးနေ့"},
            {"Valentines", "ချစ်သူများ", "ခ်စ္သူမ်ား", "ဝုတ်ဗၠာဲ", "ၵေႃႈႁၵ်ႉ", "ချစ်သူများ"},
            {"Earth", "ကမ္ဘာမြေ", "ကမၻာေျမ", "ဂၠးကဝ်", "လိၼ်မိူင်း", "ကမ္ဘာမြေ"},
            {"April Fools'", "ဧပြီအရူး", "ဧၿပီအ႐ူး", "သ္ပပရအ်", "ဢေႇပရႄႇၵူၼ်းယွင်ႇ", "အ့ဖြ့ၣ် fool"},
            {"Red Cross", "ကြက်ခြေနီ", "ၾကက္ေျခနီ", "ဇိုၚ်ခ္ဍာ်ဍာဲ", "ဢၼ်မီးသီလႅင်ႁၢင်ႈၶႂၢႆႇၶႃပေ", "ကြက်ခြေနီ"},
            {"United Nations", "ကုလသမ္မဂ္ဂ", "ကုလသမၼဂၢ", "ကုလသမ္မဂ္ဂ", "ဢၼ်ၽွမ်ႉႁူမ်ႈၸိူဝ်ႉၶိူဝ်းၼမ်", "ကုလသမ္မဂ္ဂ"},
            {"Halloween", "သရဲနေ့", "သရဲေန႔", "ဟေဝ်လဝ်ဝိန်", "ဝၼ်းၽဵတ်", "သရဲနေ့"},
            {"Shan", "ရှမ်း", "ရွမ္း", "သေံ", "တႆး", "ရှမ်း"},
            {"Mothers'", "အမေများ", "အေမမ်ား", "မိအံက်", "မႄႈ", "မိၢ်အ"},
            {"Fathers'", "အဖေများ", "အေဖမ်ား", "မအံက်", "ပေႃ", "ပၢ်အ"},
            {"Sasana Year", "သာသနာနှစ်", "သာသနာႏွစ္", "သက္ကရာဇ်သာသနာ", "ပီသႃႇသၼႃႇ", "နံၣ်သာသနာ"},
            {"Eid", "အိဒ်", "အိဒ္", "အိဒ်", "အိဒ်", "အိဒ်"},
            {"Diwali", "ဒီဝါလီ", "ဒီဝါလီ", "ဒီဝါလီ", "ဒီဝါလီ", "ဒီဝါလီ"},
            {"Mahathamaya", "မဟာသမယ", "မဟာသမယ", "မဟာသမယ", "ဢၼ်ယႂ်ႇၽွမ်ႉႁူမ်ႈ", "မဟာသမယ"},
            {"Garudhamma", "ဂရုဓမ္မ", "ဂ႐ုဓမၼ", "ဂရုဓမ္မ", "ဂရုဓမ္မ", "ဂရုဓမ္မ"},
            {"Metta", "မေတ္တာ", "ေမတၱာ", "မေတ္တာ", "မႅတ်ႉတႃႇ", "မေတ္တာ"},
            {"Taungpyone", "တောင်ပြုန်း", "ေတာင္ျပဳန္း", "တောင်ပြုန်း", "တောင်ပြုန်း", "တောင်ပြုန်း"},
            {"Yadanagu", "ရတနာ့ဂူ", "ရတနာ့ဂူ", "ရတနာ့ဂူ", "ရတနာ့ဂူ", "ရတနာ့ဂူ"},
            {"Authors'", "စာဆိုတော်", "စာဆိုေတာ္", "စာဆိုတော်", "ၽူႈတႅမ်ႈၽႅၼ်", "စာဆိုတော်"},
            {"World", "ကမ္ဘာ့", "ကမၻာ့", "ကမ္ဘာ့", "လူၵ်", "ကမ္ဘာ့"},
            {"Teachers'", "ဆရာများ", "ဆရာမ်ား", "ဆရာများ", "ၶူးသွၼ်", "ဆရာများ"},
            {"Holiday", "ရုံးပိတ်ရက်", "႐ုံးပိတ္ရက္", "ရုံးပိတ်ရက်", "ဝၼ်းပိၵ်ႉလုမ်း", "ရုံးပိတ်ရက်"},
            {"Chinese", "တရုတ်", "တ႐ုတ္", "တရုတ်", "ၵူၼ်းၸၢဝ်းၶေ", "တရုတ်"},
            {"Easter", "ထမြောက်ရာနေ့", "ထေျမာက္ရာေန႔", "ထမြောက်ရာနေ့", "ပၢင်ႇပွႆးၶွပ်ႈၶူပ်ႇၸဝ်ႈၶရိတ်", "ထမြောက်ရာနေ့"},
            {"0", "၀", "၀", "၀", "0", "၀"},
            {"1", "၁", "၁", "၁", "1", "၁"},
            {"2", "၂", "၂", "၂", "2", "၂"},
            {"3", "၃", "၃", "၃", "3", "၃"},
            {"4", "၄", "၄", "၄", "4", "၄"},
            {"5", "၅", "၅", "၅", "5", "၅"},
            {"6", "၆", "၆", "၆", "6", "၆"},
            {"7", "၇", "၇", "၇", "7", "၇"},
            {"8", "၈", "၈", "၈", "8", "၈"},
            {"9", "၉", "၉", "၉", "9", "၉"},
            {"Sunday", "တနင်္ဂနွေ", "တနဂၤေႏြ", "တ္ၚဲအဒိုတ်", "ဝၼ်းဢႃးတိတ်ႉ", "မုၢ်ဒဲး"},
            {"Monday", "တနင်္လာ", "တနလၤာ", "တ္ၚဲစန်", "ဝၼ်းၸၼ်", "မုၢ်ဆၣ်"},
            {"Tuesday", "အင်္ဂါ", "အဂၤါ", "တ္ၚဲအင္ၚာ", "ဝၼ်းဢၢင်းၵၢၼ်း", "မုၢ်ယူာ်"},
            {"Wednesday", "ဗုဒ္ဓဟူး", "ဗုဒၶဟူး", "တ္ၚဲဗုဒ္ဓဝါ", "ဝၼ်းပုတ်ႉ", "မုၢ်ပျဲၤ"},
            {"Thursday", "ကြာသပတေး", "ၾကာသပေတး", "တ္ၚဲဗြဴဗတိ", "ဝၼ်းၽတ်း", "မုၢ်လ့ၤဧိၤ"},
            {"Friday", "သောကြာ", "ေသာၾကာ", "တ္ၚဲသိုက်", "ဝၼ်းသုၵ်း", "မုၢ်ဖီဖး"},
            {"Saturday", "စနေ", "စေန", "တ္ၚဲသ္ၚိသဝ်", "ဝၼ်းသဝ်", "မုၢ်ဘူၣ်"},
            {"Sabbath Eve", "အဖိတ်", "အဖိတ္", "တ္ၚဲတိၚ်", "ၽိတ်ႈ", "အဖိတ်"},
            {"Sabbath", "ဥပုသ်", "ဥပုသ္", "တ္ၚဲသဳ", "သိၼ်", "အိၣ်ဘှံး"},
            {"Yatyaza", "ရက်ရာဇာ", "ရက္ရာဇာ", "တ္ၚဲရာဇာ", "ဝၼ်းထုၼ်း", "ရက်ရာဇာ"},
            {"Pyathada", "ပြဿဒါး", "ျပႆဒါး", "တ္ၚဲပြာဗ္ဗဒါ", "ဝၼ်းပျၢတ်ႈ", "ပြဿဒါး"},
            {"Afternoon", "မွန်းလွဲ", "မြန္းလြဲ", "မွန်းလွဲ", "ဝၢႆးဝၼ်း", "မွန်းလွဲ"},
            {"January", "ဇန်နဝါရီ", "ဇန္နဝါရီ", "ဂျာန်နျူအာရဳ", "ၸၼ်ႇဝႃႇရီႇ", "ယနူၤအါရံၤ"},
            {"February", "ဖေဖော်ဝါရီ", "ေဖေဖာ္ဝါရီ", "ဝှေဝ်ဗျူအာရဳ", "ၾႅပ်ႉဝႃႇရီႇ", "ဖ့ၤဘြူၤအါရံၤ"},
            {"March", "မတ်", "မတ္", "မာတ်ချ်", "မျၢတ်ႉၶျ်", "မၢ်ၡး"},
            {"April", "ဧပြီ", "ဧၿပီ", "ဨပြေယ်လ်", "ဢေႇပရႄႇ", "အ့ဖြ့ၣ်"},
            {"May", "မေ", "ေမ", "မေ", "မေ", "မ့ၤ"},
            {"June", "ဇွန်", "ဇြန္", "ဂျုန်", "ၵျုၼ်ႇ", "ယူၤ"},
            {"July", "ဇူလိုင်", "ဇူလိုင္", "ဂျူလာၚ်", "ၵျူႇလၢႆႇ", "ယူၤလံ"},
            {"August", "ဩဂုတ်", "ဩဂုတ္", "အဝ်ဂါတ်", "ဢေႃးၵၢတ်ႉ", "အီကူး"},
            {"September", "စက်တင်ဘာ", "စက္တင္ဘာ", "သိတ်ထီဗာ", "သႅပ်ႇထႅမ်ႇပႃႇ", "စဲးပတ့ဘၢၣ်"},
            {"October", "အောက်တိုဘာ", "ေအာက္တိုဘာ", "အံက်ထဝ်ဗာ", "ဢွၵ်ႇထူဝ်ႇပႃႇ", "အီးကထိဘၢၣ်"},
            {"November", "နိုဝင်ဘာ", "နိုဝင္ဘာ", "နဝ်ဝါမ်ဗာ", "ၼူဝ်ႇဝႅမ်ႇပႃႇ", "နိၣ်ဝ့ဘၢၣ်"},
            {"December", "ဒီဇင်ဘာ", "ဒီဇင္ဘာ", "ဒီဇြေန်ဗာ", "တီႇသႅမ်ႇပႃႇ", "ဒံၣ်စ့ဘၢၣ်"},
            {"Tagu", "တန်ခူး", "တန္ခူး", "ဂိတုစဲ", "ႁႃႈ", "လါချံ"},
            {"Kason", "ကဆုန်", "ကဆုန္", "ဂိတုပသာ်", "ႁူၵ်း", "ဒ့ၣ်ညါ"},
            {"Nayon", "နယုန်", "နယုန္", "ဂိတုဇှေ်", "ၸဵတ်း", "လါနွံ"},
            {"Waso", "ဝါဆို", "ဝါဆို", "ဂိတုဒ္ဂိုန်", "ပႅတ်ႇ", "လါဃိး"},
            {"Wagaung", "ဝါခေါင်", "ဝါေခါင္", "ဂိတုခ္ဍဲသဳ", "ၵဝ်ႈ", "လါခူး"},
            {"Tawthalin", "တော်သလင်း", "ေတာ္သလင္း", "ဂိတုဘတ်", "သိပ်း", "ဆံးမုၢ်"},
            {"Thadingyut", "သီတင်းကျွတ်", "သီတင္းကြ်တ္", "ဂိတုဝှ်", "သိပ်းဢဵတ်း", "ဆံးဆၣ်"},
            {"Tazaungmon", "တန်ဆောင်မုန်း", "တန္ေဆာင္မုန္း", "ဂိတုက္ထိုန်", "သိပ်းသွင်", "လါနီ"},
            {"Nadaw", "နတ်တော်", "နတ္ေတာ္", "ဂိတုမြေက္ကသဵု", "ၸဵင်", "လါပျုၤ"},
            {"Pyatho", "ပြာသို", "ျပာသို", "ဂိတုပှော်", "ၵမ်", "သလ့ၤ"},
            {"Tabodwe", "တပို့တွဲ", "တပို႔တြဲ", "ဂိတုမာ်", "သၢမ်", "ထ့ကူး"},
            {"Tabaung", "တပေါင်း", "တေပါင္း", "ဂိတုဖဝ်ရဂိုန်", "သီႇ", "သွ့ကီ"},
            {"First", "ပ", "ပ", "ပ", "ပ", "၁ "},
            {"Second", "ဒု", "ဒု", "ဒု", "တု", "၂ "},
            {"Late", "နှောင်း", "ေႏွာင္း", "နှောင်း", "ဝၢႆး", "စဲၤ"},
            {"Late Tagu", "နှောင်းတန်ခူး", "ေႏွာင္းတန္ခူး", "နှောင်းဂိတုစဲ", "ဝၢႆးႁႃႈ", "စဲၤလါချံ"},
            {"Late Kason", "နှောင်းကဆုန်", "ေႏွာင္းကဆုန္", "နှောင်းဂိတုပသာ်", "ဝၢႆးႁူၵ်း", "စဲၤဒ့ၣ်ညါ"},
            {"Waxing", "လဆန်း", "လဆန္း", "မံက်", "လိူၼ်မႂ်ႇ", "လါထီၣ်"},
            {"Waning", "လဆုတ်", "လဆုတ္", "စွေက်", "လိူၼ်လွင်ႈ", "လါလီၤ"},
            {"Full Moon", "လပြည့်", "လျပည့္", "ပေၚ်", "လိူၼ်မူၼ်း", "လါပှဲၤ"},
            {"New Moon", "လကွယ်", "လကြယ္", "အိုတ်", "လိူၼ်လပ်း", "လါဘၢ"},
            {"Nay", "နေ့", "ေန႔", "တ္ၚဲ", "ဝၼ်း", "နံၤ"},
            {"Day", "နေ့", "ေန႔", "တ္ၚဲ", "ဝၼ်း", "နံၤ"},
            {"Yat", "ရက်", "ရက္", "ရက်", "ဝၼ်း", "ရက်"},
            {"Year", "နှစ်", "ႏွစ္", "နှစ်", "ပီ", "နံၣ်"},
            {"Ku", "ခု", "ခု", "သၞာံ", "ၶု", ""},
            {"Naga", "နဂါး", "နဂါး", "နဂါး", "ႁူဝ်", "နဂါး"},
            {"Head", "ခေါင်း", "ေခါင္း", "ခေါင်း", "ၼၵႃး", "ခေါင်း"},
            {"Facing", "လှည့်", "လွည့္", "လှည့်", "ဝၢႆႇ", "လှည့်"},
            {"East", "အရှေ့", "အေရွ႕", "အရှေ့", "တၢင်းဢွၵ်ႇ", "အရှေ့"},
            {"West", "အနောက်", "အေနာက္", "အနောက်", "တၢင်းတူၵ်း", "အနောက်"},
            {"South", "တောင်", "ေတာင္", "တောင်", "တၢင်းၸၢၼ်း", "တောင်"},
            {"North", "မြောက်", "ေျမာက္", "မြောက်", "တၢင်းႁွင်ႇ", "မြောက်"},
            {"Mahabote", "မဟာဘုတ်", "မဟာဘုတ္", "မဟာဘုတ်", "မဟာဘုတ်", "မဟာဘုတ်"},
            {"Born", "ဖွား", "ဖြား", "ဖွား", "ဢၼ်မီးပႃႇရမီ", "ဖွား"},
            {"Binga", "ဘင်္ဂ", "ဘဂၤ", "ဘင်္ဂ", "ဘင်္ဂ", "ဘင်္ဂ"},
            {"Atun", "အထွန်း", "အထြန္း", "အထွန်း", "အထွန်း", "အထွန်း"},
            {"Yaza", "ရာဇ", "ရာဇ", "ရာဇ", "ရာဇ", "ရာဇ"},
            {"Adipati", "အဓိပတိ", "အဓိပတိ", "အဓိပတိ", "အဓိပတိ", "အဓိပတိ"},
            {"Marana", "မရဏ", "မရဏ", "မရဏ", "မရဏ", "မရဏ"},
            {"Thike", "သိုက်", "သိုက္", "သိုက်", "သိုက်", "သိုက်"},
            {"Puti", "ပုတိ", "ပုတိ", "ပုတိ", "ပုတိ", "ပုတိ"},
            {"Ogre", "ဘီလူး", "ဘီလူး", "ဘီလူး", "ၽီလူ", "ဘီလူး"},
            {"Elf", "နတ်", "နတ္", "နတ်", "ၽီမႅၼ်းဢွၼ်", "နတ်"},
            {"Human", "လူ", "လူ", "လူ", "ဢၼ်ပဵၼ်ၵူၼ်", "လူ"},
            {"Nakhat", "နက္ခတ်", "နကၡတ္", "နက္ခတ်", "လႅင်ႊလၢဝ်", "နက္ခတ်"},
            {"Hpusha", "ပုဿ", "ပုႆ", "ပုဿ", "ပုဿ", "ပုဿ"},
            {"Magha", "မာခ", "မာခ", "မာခ", "မာခ", "မာခ"},
            {"Phalguni", "ဖ္လကိုန်", "ဖႅကိုန္", "ဖ္လကိုန်", "ဖ္လကိုန်", "ဖ္လကိုန်"},
            {"Chitra", "စယ်", "စယ္", "စယ်", "စယ်", "စယ်"},
            {"Visakha", "ပိသျက်", "ပိသ်က္", "ပိသျက်", "ပိသျက်", "ပိသျက်"},
            {"Jyeshtha", "စိဿ", "စိႆ", "စိဿ", "စိဿ", "စိဿ"},
            {"Ashadha", "အာသတ်", "အာသတ္", "အာသတ်", "အာသတ်", "အာသတ်"},
            {"Sravana", "သရဝန်", "သရဝန္", "သရဝန်", "သရဝန်", "သရဝန်"},
            {"Bhadrapaha", "ဘဒြ", "ဘျဒ", "ဘဒြ", "ဘဒြ", "ဘဒြ"},
            {"Asvini", "အာသိန်", "အာသိန္", "အာသိန်", "အာသိန်", "အာသိန်"},
            {"Krittika", "ကြတိုက်", "ၾကတိုက္", "ကြတိုက်", "ကြတိုက်", "ကြတိုက်"},
            {"Mrigasiras", "မြိက္ကသိုဝ်", "ၿမိကၠသိုဝ္", "မြိက္ကသိုဝ်", "မြိက္ကသိုဝ်", "မြိက္ကသိုဝ်"},
            {"Calculator", "တွက်စက်", "တြက္စက္", "တွက်စက်", "သွၼ်", "တွက်စက်"},
            {"Myanmar", "မြန်မာ", "ျမန္မာ", "မြန်မာ", "မြန်မာ", "မြန်မာ"},
            //{". ","။ ","။ ","။ ","။ ","။ "},
            //{", ","၊ ","၊ ","၊ ","၊ ","၊ "},
    };


    // ═══════════════════════════════════════════════════════════════
    // CACHED DATA STRUCTURES
    // ═══════════════════════════════════════════════════════════════

    /**
     * Direct word translation maps: [fromLang][toLang] → Map<sourceWord, targetWord>
     */
    @SuppressWarnings("unchecked")
    private static final Map<String, String>[][] DIRECT_MAP = new Map[LANG_COUNT][LANG_COUNT];

    /**
     * Trie-based prefix index for sentence translation: [fromLang][toLang][firstChar] → sorted entries
     * Enables efficient longest-prefix matching without regex overhead
     */
    @SuppressWarnings("unchecked")
    private static final Map<Character, TrieNode>[][] TRIE_ROOT = new Map[LANG_COUNT][LANG_COUNT];

    /**
     * Pre-cached digit translations for all languages
     * Avoids repeated catalog lookups during number translation
     */
    private static final String[][] DIGITS = new String[10][LANG_COUNT];

    /**
     * Initialization flag for lazy loading
     */
    private static volatile boolean initialized = false;

    /**
     * Trie node for efficient prefix matching
     */
    private static final class TrieNode {
        final Map<Character, TrieNode> children = new HashMap<>(8);
        String translation = null;

        boolean isLeaf() {
            return translation != null;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // STATIC INITIALIZATION
    // ═══════════════════════════════════════════════════════════════

    static {
        initialize();
    }

    /**
     * Initializes all translation data structures
     * Thread-safe with double-checked locking
     */
    private static void initialize() {
        if (initialized) return;

        synchronized (LanguageTranslator.class) {
            if (initialized) return;

            // long startTime = System.nanoTime();

            initializeDirectMaps();
            initializeTrieStructures();
            initializeDigitCache();

            initialized = true;
        }
    }

    /**
     * Builds HashMap-based direct translation maps for O(1) word lookup
     * Memory: ~134 entries × 36 language pairs = ~4,824 map entries
     */
    private static void initializeDirectMaps() {
        for (int from = 0; from < LANG_COUNT; from++) {
            for (int to = 0; to < LANG_COUNT; to++) {
                Map<String, String> map = new HashMap<>((int) (CATALOG.length / 0.75) + 1);

                for (String[] row : CATALOG) {
                    String source = row[from];
                    String target = row[to];

                    if (source != null && !source.isEmpty()) {
                        map.put(source, target);
                    }
                }

                DIRECT_MAP[from][to] = Collections.unmodifiableMap(map);
            }
        }
    }

    /**
     * Builds Trie structures for longest-prefix matching in sentence translation
     * Replaces character-indexed lists with proper Trie for better performance
     */
    private static void initializeTrieStructures() {
        for (int from = 0; from < LANG_COUNT; from++) {
            int finalFrom = from;
            for (int to = 0; to < LANG_COUNT; to++) {
                Map<Character, TrieNode> rootMap = new HashMap<>(64);

                // Sort by length descending for proper longest-match semantics
                List<String[]> sortedCatalog = new ArrayList<>(Arrays.asList(CATALOG));

                sortedCatalog.sort((a, b) -> Integer.compare(b[finalFrom].length(), a[finalFrom].length()));

                for (String[] row : sortedCatalog) {
                    String source = row[from];
                    String target = row[to];

                    if (source == null || source.isEmpty()) continue;

                    insertIntoTrie(rootMap, source, target);
                }

                TRIE_ROOT[from][to] = Collections.unmodifiableMap(rootMap);
            }
        }
    }

    /**
     * Inserts a word and its translation into the Trie
     */
    private static void insertIntoTrie(Map<Character, TrieNode> rootMap, String word, String translation) {
        if (word.isEmpty()) return;

        char firstChar = word.charAt(0);
        TrieNode node = rootMap.computeIfAbsent(firstChar, k -> new TrieNode());

        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }

        node.translation = translation;
    }

    /**
     * Pre-caches digit translations for fast number conversion
     */
    private static void initializeDigitCache() {
        for (int digit = 0; digit < 10; digit++) {
            String digitStr = String.valueOf(digit);

            for (int lang = 0; lang < LANG_COUNT; lang++) {
                for (String[] row : CATALOG) {
                    if (digitStr.equals(row[0])) {
                        DIGITS[digit][lang] = row[lang];
                        break;
                    }
                }
            }
        }
    }

    /**
     * Private constructor to prevent instantiation
     */
    private LanguageTranslator() {
        throw new AssertionError("Utility class - do not instantiate");
    }

    // ═══════════════════════════════════════════════════════════════
    // PUBLIC API - WORD TRANSLATION
    // ═══════════════════════════════════════════════════════════════

    /**
     * Translates to the specific language
     *
     * @param str the string to translate
     * @param to  Translate Language to
     * @return translated result
     */
    protected static String translate(String str, Language to){
        return translate(str, Language.ENGLISH, to);
    }

    /**
     * Translates a single word from one language to another
     *
     * @param str  The word to translate
     * @param from Source language
     * @param to   Target language
     * @return Translated word, or original if not found in catalog
     * @throws NullPointerException if any parameter is null
     */
    public static String translate(String str, Language from, Language to) {
        Objects.requireNonNull(str, "Input string cannot be null");
        Objects.requireNonNull(from, "Source language cannot be null");
        Objects.requireNonNull(to, "Target language cannot be null");

        Map<String, String> map = DIRECT_MAP[from.getLanguageIndex()][to.getLanguageIndex()];
        return map.getOrDefault(str, str);
    }

    // ═══════════════════════════════════════════════════════════════
    // PUBLIC API - SENTENCE TRANSLATION
    // ═══════════════════════════════════════════════════════════════

    /**
     * Translates a sentence using Trie-based longest-prefix matching
     *
     * <p>Performance: O(n) where n is text length, with efficient prefix matching
     * <p>Algorithm: Greedy longest-match from left to right
     *
     * @param text Sentence to translate
     * @param from Source language
     * @param to   Target language
     * @return Translated sentence
     * @throws NullPointerException if any parameter is null
     */
    public static String translateSentence(String text, Language from, Language to) {
        Objects.requireNonNull(text, "Text cannot be null");
        Objects.requireNonNull(from, "Source language cannot be null");
        Objects.requireNonNull(to, "Target language cannot be null");

        if (text.isEmpty()) return text;

        int fromIdx = from.getLanguageIndex();
        int toIdx = to.getLanguageIndex();
        Map<Character, TrieNode> trieRoot = TRIE_ROOT[fromIdx][toIdx];

        StringBuilder result = new StringBuilder(text.length() * 2);
        int i = 0;
        int len = text.length();

        while (i < len) {
            char c = text.charAt(i);
            TrieNode node = trieRoot.get(c);

            if (node == null) {
                // No match starting with this character
                result.append(c);
                i++;
                continue;
            }

            // Find longest matching prefix
            int matchEnd = i;
            String bestTranslation = null;

            int j = i + 1;
            if (node.isLeaf()) {
                matchEnd = j;
                bestTranslation = node.translation;
            }

            while (j < len) {
                char nextChar = text.charAt(j);
                node = node.children.get(nextChar);

                if (node == null) break;

                j++;
                if (node.isLeaf()) {
                    matchEnd = j;
                    bestTranslation = node.translation;
                }
            }

            if (bestTranslation != null) {
                result.append(bestTranslation);
                i = matchEnd;
            } else {
                result.append(c);
                i++;
            }
        }

        return result.toString();
    }

    /**
     * Translates a list of sentences
     *
     * <p>Performance: O(m × n) where m is list size, n is average sentence length
     *
     * @param sentences List of sentences to translate
     * @param from      Source language
     * @param to        Target language
     * @return List of translated sentences in same order
     * @throws NullPointerException if any parameter is null
     */
    public static List<String> translateSentenceList(List<String> sentences, Language from, Language to) {
        Objects.requireNonNull(sentences, "Sentence list cannot be null");
        Objects.requireNonNull(from, "Source language cannot be null");
        Objects.requireNonNull(to, "Target language cannot be null");

        List<String> result = new ArrayList<>(sentences.size());
        for (String sentence : sentences) {
            result.add(translateSentence(sentence, from, to));
        }
        return result;
    }

    // ═══════════════════════════════════════════════════════════════
    // PUBLIC API - NUMBER TRANSLATION
    // ═══════════════════════════════════════════════════════════════

    /**
     * Converts a number to its string representation in the target language
     *
     * <p>Performance: O(log n) where n is the number value
     * <p>Uses pre-cached digit translations for maximum speed
     *
     * @param number   Number to translate (truncated to long)
     * @param language Target language
     * @return Number as a string in target language
     * @throws NullPointerException if language is null
     */
    public static String translate(double number, Language language) {
        Objects.requireNonNull(language, "Language cannot be null");

        int langIdx = language.getLanguageIndex();

        // Handle negative numbers
        if (number < 0) {
            return "-" + translate(-number, language);
        }

        // Handle zero
        if (number == 0) {
            return DIGITS[0][langIdx];
        }

        // Convert to long and process digits
        long num = (long) number;
        StringBuilder result = new StringBuilder(20);

        while (num > 0) {
            int digit = (int) (num % 10);
            result.insert(0, DIGITS[digit][langIdx]);
            num /= 10;
        }

        return result.toString();
    }

    // ═══════════════════════════════════════════════════════════════
    // UTILITY METHODS
    // ═══════════════════════════════════════════════════════════════

    /**
     * Returns the number of translation entries in the catalog
     *
     * @return Catalog size (134 entries)
     */
    public static int getCatalogSize() {
        return CATALOG.length;
    }

    /**
     * Returns whether the translator has been initialized
     *
     * @return true if initialized
     */
    public static boolean isInitialized() {
        return initialized;
    }

    /**
     * Forces re-initialization of all data structures
     * <p>Useful for testing or memory management
     */
    public static synchronized void reinitialize() {
        initialized = false;
        initialize();
    }

}