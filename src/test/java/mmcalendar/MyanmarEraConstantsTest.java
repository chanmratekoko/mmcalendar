package mmcalendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyanmarEraConstantsTest {

    @Test
    public void constructorAndGetters() {
        MyanmarEraConstants constants = new MyanmarEraConstants(3.0, -0.5, 8.0, 1.0);
        assertEquals(3.0, constants.getEraId(), 0.0);
        assertEquals(-0.5, constants.getWatatOffset(), 0.0);
        assertEquals(8.0, constants.getNumberOfMonths(), 0.0);
        assertEquals(1.0, constants.getExceptionInWatatYear(), 0.0);
    }

    @Test
    public void zeroValues() {
        MyanmarEraConstants constants = new MyanmarEraConstants(0.0, 0.0, 0.0, 0.0);
        assertEquals(0.0, constants.getEraId(), 0.0);
        assertEquals(0.0, constants.getWatatOffset(), 0.0);
        assertEquals(0.0, constants.getNumberOfMonths(), 0.0);
        assertEquals(0.0, constants.getExceptionInWatatYear(), 0.0);
    }

    @Test
    public void fractionalEraId() {
        MyanmarEraConstants constants = new MyanmarEraConstants(1.1, -1.0, 4.0, 0.0);
        assertEquals(1.1, constants.getEraId(), 1e-10);
    }

    @Test
    public void toStringContainsAllFields() {
        MyanmarEraConstants constants = new MyanmarEraConstants(2.0, -0.85, 6.0, 1.0);
        String str = constants.toString();
        assertTrue(str.contains("eraId=2.0"));
        assertTrue(str.contains("watatOffset=-0.85"));
        assertTrue(str.contains("numberOfMonths=6.0"));
        assertTrue(str.contains("exceptionInWatatYear=1.0"));
    }
}
