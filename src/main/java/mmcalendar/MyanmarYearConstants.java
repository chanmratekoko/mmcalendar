package mmcalendar;

/**
 * Provides era-specific constants for Myanmar calendar calculations.
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.1.2
 */
public class MyanmarYearConstants {

    private MyanmarYearConstants() {
    }

    /**
     * Get Myanmar year constants depending on era.
     *
     * @param my myanmar year
     * @return {@link MyanmarEraConstants} containing era-specific calculation constants
     */
    public static MyanmarEraConstants getMyConst(int my) {

        double eraId;
        double watatOffset;
        double numberOfMonths;
        double exceptionInWatatYear = 0;

        int[][] fme;
        int[] wte;

        // The third era (the era after Independence 1312 ME and after)
        if (my >= 1312) {
            eraId = 3;
            watatOffset = -0.5;
            numberOfMonths = 8;
            fme = new int[][]{{1377, 1}};
            wte = new int[]{1344, 1345};
        }
        // The second era (the era under British colony: 1217 ME - 1311 ME)
        else if (my >= 1217) {
            eraId = 2;
            watatOffset = -1;
            numberOfMonths = 4;
            fme = new int[][]{{1234, 1}, {1261, -1}};
            wte = new int[]{1263, 1264};
        }
        // The first era (the era of Myanmar kings: ME1216 and before)
        // Thandeikta (ME 1100 - 1216)
        else if (my >= 1100) {
            eraId = 1.3;
            watatOffset = -0.85;
            numberOfMonths = -1;
            fme = new int[][]{{1120, 1}, {1126, -1}, {1150, 1}, {1172, -1}, {1207, 1}};
            wte = new int[]{1201, 1202};
        }
        // Makaranta system 2 (ME 798 - 1099)
        else if (my >= 798) {
            eraId = 1.2;
            watatOffset = -1.1;
            numberOfMonths = -1;
            fme = new int[][]{
                    {813, -1}, {849, -1}, {851, -1}, {854, -1}, {927, -1}, {933, -1},
                    {936, -1}, {938, -1}, {949, -1}, {952, -1}, {963, -1}, {968, -1},
                    {1039, -1}
            };
            wte = new int[]{};
        }
        // Makaranta system 1 (ME 0 - 797)
        else {
            eraId = 1.1;
            watatOffset = -1.1;
            numberOfMonths = -1;
            fme = new int[][]{
                    {205, 1}, {246, 1}, {471, 1}, {572, -1}, {651, 1}, {653, 2},
                    {656, 1}, {672, 1}, {729, 1}, {767, -1}
            };
            wte = new int[]{};
        }

        int i = BinarySearchUtil.search(my, fme);
        if (i >= 0) {
            watatOffset += fme[i][1];
        }
        i = BinarySearchUtil.search(my, wte);
        if (i >= 0) {
            exceptionInWatatYear = 1;
        }

        return new MyanmarEraConstants(eraId, watatOffset, numberOfMonths, exceptionInWatatYear);
    }
}
