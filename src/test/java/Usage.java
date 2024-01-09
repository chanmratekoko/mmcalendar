import mmcalendar.HolidayCalculator;
import mmcalendar.Language;
import mmcalendar.MyanmarCalendarKernel;
import mmcalendar.MyanmarDate;

import java.util.List;

public class Usage {
    public static void main(String[] args) {
//        String header = MyanmarCalendarKernel.getCalendarHeader(1380, 2, Language.ENGLISH);
//        System.out.println(header);

        // Output : သာသနာနှစ် ၂၅၆၇ - ၂၅၆၈ ခု မြန်မာနှစ် ၁၃၈၅ - ၁၃၈၆ ခု တပေါင်း - တန်ခူး
//        String header = MyanmarCalendarKernel.getCalendarHeaderForWesternStyle(2024, 4, Language.ENGLISH);
//        System.out.println(header);

        List<String> anniversary = HolidayCalculator.getAnniversary(MyanmarDate.of(2017,1 ,1));
        System.out.println(anniversary);

    }

}
