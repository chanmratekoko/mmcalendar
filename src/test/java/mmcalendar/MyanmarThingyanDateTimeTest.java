package mmcalendar;

import org.junit.Test;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyanmarThingyanDateTimeTest {

    @Test
    public void create() {
        MyanmarThingyanDateTime myanmarThingyanDateTime = MyanmarThingyanDateTime.of(1386);

        // Thingyan Akyo day (သင်္ကြန်အကြိုနေ့)
        MyanmarDate akyoDay = myanmarThingyanDateTime.getAkyoDay();
        // Akya time (သင်္ကြန်ကျချိန်)
        LocalTime akyaTime = myanmarThingyanDateTime.getAkyaTime().toMyanmarLocalTime();
        // Akya day (အကျနေ့)
        MyanmarDate akyaDay = myanmarThingyanDateTime.getAkyaDay();
        // Atat Time (သင်္ကြန်တက်ချိန်)
        LocalTime atatTime = myanmarThingyanDateTime.getAtatTime().toMyanmarLocalTime();
        // Atat day (သင်္ကြန်အတက်နေ့)
        MyanmarDate atatDay = myanmarThingyanDateTime.getAtatDay();
        // Thingyan Akyat day (အကြတ်နေ့)
        MyanmarDate[] akyatDays = myanmarThingyanDateTime.getAkyatDays();
        //  Myanmar New Year's Day (နှစ်ဆန်းတစ်ရက်နေ့)
        MyanmarDate myanmarNewYearDate = myanmarThingyanDateTime.getMyanmarNewYearDay();

        String acturl = "သာသနာနှစ် ၂၅၆၈ ခု, မြန်မာနှစ် ၁၃၈၆ ခု, တန်ခူး လဆန်း ၉ ရက် ဗုဒ္ဓဟူး နေ့";
        assertEquals(acturl, myanmarNewYearDate.toString());

        ZonedDateTime zonedDateTime = myanmarThingyanDateTime.getAkyaTime().toMyanmarZonedDateTime();
        assertEquals("2024-04-14T00:24:45+06:30[Asia/Rangoon]", zonedDateTime.toString());
    }
}
