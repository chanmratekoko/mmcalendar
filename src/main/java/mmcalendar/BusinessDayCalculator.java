package mmcalendar;

import java.util.ArrayList;
import java.util.List;

public class BusinessDayCalculator {

    /**
     * Don't let anyone instantiate this class.
     */
    private BusinessDayCalculator() {
    }

    public static List<String> substituteBusinessDay(int gy, int gm, int gd) {
        List<String> businessDay = new ArrayList<>();

        // Update For 2025 Calendar Year
        if ((gy == 2025 && gm == 1 && gd == 11)
                || (gy == 2025 && gm == 3 && (gd == 22 || gd == 29))
                || (gy == 2025 && gm == 11 && gd == 8)
                || (gy == 2026 && gm == 1 && gd == 3)
        ) {
            businessDay.add("Business Day");
        }

        return businessDay;
    }
}
