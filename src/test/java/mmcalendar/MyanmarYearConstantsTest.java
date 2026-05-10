package mmcalendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyanmarYearConstantsTest {

    private static final double DELTA = 0.001;

    // ---------------------------------------------------------------
    // 1. Era boundary tests
    // ---------------------------------------------------------------

    @Test
    public void testEraBoundary_year797_era1_1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(797);
        assertEquals(1.1, c.getEraId(), DELTA);
        assertEquals(-1.1, c.getWatatOffset(), DELTA);
        assertEquals(-1, c.getNumberOfMonths(), DELTA);
    }

    @Test
    public void testEraBoundary_year798_era1_2() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(798);
        assertEquals(1.2, c.getEraId(), DELTA);
        assertEquals(-1.1, c.getWatatOffset(), DELTA);
        assertEquals(-1, c.getNumberOfMonths(), DELTA);
    }

    @Test
    public void testEraBoundary_year1099_era1_2() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1099);
        assertEquals(1.2, c.getEraId(), DELTA);
        assertEquals(-1.1, c.getWatatOffset(), DELTA);
        assertEquals(-1, c.getNumberOfMonths(), DELTA);
    }

    @Test
    public void testEraBoundary_year1100_era1_3() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1100);
        assertEquals(1.3, c.getEraId(), DELTA);
        assertEquals(-0.85, c.getWatatOffset(), DELTA);
        assertEquals(-1, c.getNumberOfMonths(), DELTA);
    }

    @Test
    public void testEraBoundary_year1216_era1_3() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1216);
        assertEquals(1.3, c.getEraId(), DELTA);
        assertEquals(-0.85, c.getWatatOffset(), DELTA);
        assertEquals(-1, c.getNumberOfMonths(), DELTA);
    }

    @Test
    public void testEraBoundary_year1217_era2() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1217);
        assertEquals(2, c.getEraId(), DELTA);
        assertEquals(-1, c.getWatatOffset(), DELTA);
        assertEquals(4, c.getNumberOfMonths(), DELTA);
    }

    @Test
    public void testEraBoundary_year1311_era2() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1311);
        assertEquals(2, c.getEraId(), DELTA);
        assertEquals(-1, c.getWatatOffset(), DELTA);
        assertEquals(4, c.getNumberOfMonths(), DELTA);
    }

    @Test
    public void testEraBoundary_year1312_era3() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1312);
        assertEquals(3, c.getEraId(), DELTA);
        assertEquals(-0.5, c.getWatatOffset(), DELTA);
        assertEquals(8, c.getNumberOfMonths(), DELTA);
    }

    // ---------------------------------------------------------------
    // 2. Full moon exception (fme) hits
    // ---------------------------------------------------------------

    @Test
    public void testFme_year1377_era3_woChangesBy1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1377);
        assertEquals(3, c.getEraId(), DELTA);
        // base wo = -0.5, fme adjustment = +1 => 0.5
        assertEquals(0.5, c.getWatatOffset(), DELTA);
    }

    @Test
    public void testFme_year1234_era2_woChangesBy1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1234);
        assertEquals(2, c.getEraId(), DELTA);
        // base wo = -1, fme adjustment = +1 => 0
        assertEquals(0, c.getWatatOffset(), DELTA);
    }

    @Test
    public void testFme_year1261_era2_woChangesbyMinus1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1261);
        assertEquals(2, c.getEraId(), DELTA);
        // base wo = -1, fme adjustment = -1 => -2
        assertEquals(-2, c.getWatatOffset(), DELTA);
    }

    @Test
    public void testFme_year1120_era1_3_woChangesBy1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1120);
        assertEquals(1.3, c.getEraId(), DELTA);
        // base wo = -0.85, fme adjustment = +1 => 0.15
        assertEquals(0.15, c.getWatatOffset(), DELTA);
    }

    @Test
    public void testFme_year813_era1_2_woChangesByMinus1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(813);
        assertEquals(1.2, c.getEraId(), DELTA);
        // base wo = -1.1, fme adjustment = -1 => -2.1
        assertEquals(-2.1, c.getWatatOffset(), DELTA);
    }

    @Test
    public void testFme_year205_era1_1_woChangesBy1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(205);
        assertEquals(1.1, c.getEraId(), DELTA);
        // base wo = -1.1, fme adjustment = +1 => -0.1
        assertEquals(-0.1, c.getWatatOffset(), DELTA);
    }

    @Test
    public void testFme_year653_era1_1_woChangesBy2() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(653);
        assertEquals(1.1, c.getEraId(), DELTA);
        // base wo = -1.1, fme adjustment = +2 => 0.9
        assertEquals(0.9, c.getWatatOffset(), DELTA);
    }

    // ---------------------------------------------------------------
    // 3. Watat exception (wte) hits
    // ---------------------------------------------------------------

    @Test
    public void testWte_year1344_era3_ew1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1344);
        assertEquals(3, c.getEraId(), DELTA);
        assertEquals(1, c.getExceptionInWatatYear(), DELTA);
    }

    @Test
    public void testWte_year1345_era3_ew1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1345);
        assertEquals(3, c.getEraId(), DELTA);
        assertEquals(1, c.getExceptionInWatatYear(), DELTA);
    }

    @Test
    public void testWte_year1263_era2_ew1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1263);
        assertEquals(2, c.getEraId(), DELTA);
        assertEquals(1, c.getExceptionInWatatYear(), DELTA);
    }

    @Test
    public void testWte_year1264_era2_ew1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1264);
        assertEquals(2, c.getEraId(), DELTA);
        assertEquals(1, c.getExceptionInWatatYear(), DELTA);
    }

    @Test
    public void testWte_year1201_era1_3_ew1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1201);
        assertEquals(1.3, c.getEraId(), DELTA);
        assertEquals(1, c.getExceptionInWatatYear(), DELTA);
    }

    @Test
    public void testWte_year1202_era1_3_ew1() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1202);
        assertEquals(1.3, c.getEraId(), DELTA);
        assertEquals(1, c.getExceptionInWatatYear(), DELTA);
    }

    // ---------------------------------------------------------------
    // 4. Non-exception years (ew = 0)
    // ---------------------------------------------------------------

    @Test
    public void testNonException_year1380_era3_ew0() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1380);
        assertEquals(3, c.getEraId(), DELTA);
        assertEquals(0, c.getExceptionInWatatYear(), DELTA);
    }

    @Test
    public void testNonException_year1250_era2_ew0() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1250);
        assertEquals(2, c.getEraId(), DELTA);
        assertEquals(0, c.getExceptionInWatatYear(), DELTA);
    }

    @Test
    public void testNonException_year1150_era1_3_ew0_fmeHit() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(1150);
        assertEquals(1.3, c.getEraId(), DELTA);
        assertEquals(0, c.getExceptionInWatatYear(), DELTA);
        // fme hit: base wo = -0.85, fme adjustment = +1 => 0.15
        assertEquals(0.15, c.getWatatOffset(), DELTA);
    }

    @Test
    public void testNonException_year900_era1_2_ew0() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(900);
        assertEquals(1.2, c.getEraId(), DELTA);
        assertEquals(0, c.getExceptionInWatatYear(), DELTA);
    }

    @Test
    public void testNonException_year500_era1_1_ew0() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(500);
        assertEquals(1.1, c.getEraId(), DELTA);
        assertEquals(0, c.getExceptionInWatatYear(), DELTA);
    }

    // ---------------------------------------------------------------
    // 5. Empty wte arrays (era 1.2 and era 1.1)
    // ---------------------------------------------------------------

    @Test
    public void testEmptyWte_year800_era1_2_ew0() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(800);
        assertEquals(1.2, c.getEraId(), DELTA);
        assertEquals(0, c.getExceptionInWatatYear(), DELTA);
    }

    @Test
    public void testEmptyWte_year100_era1_1_ew0() {
        MyanmarEraConstants c = MyanmarYearConstants.getMyConst(100);
        assertEquals(1.1, c.getEraId(), DELTA);
        assertEquals(0, c.getExceptionInWatatYear(), DELTA);
    }
}
