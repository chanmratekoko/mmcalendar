package mmcalendar;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BusinessDayCalculatorTest {

    @Test
    public void substituteBusinessDayTest() {
        List<String> actualList = Collections.singletonList("Business Day");
        assertEquals(actualList, BusinessDayCalculator.substituteBusinessDay(2025, 1, 11));
        assertEquals(actualList, BusinessDayCalculator.substituteBusinessDay(2025, 3, 22));
        assertEquals(actualList, BusinessDayCalculator.substituteBusinessDay(2025, 3, 29));
        assertEquals(actualList, BusinessDayCalculator.substituteBusinessDay(2025, 11, 8));
        assertEquals(actualList, BusinessDayCalculator.substituteBusinessDay(2026, 1, 3));
    }

}
