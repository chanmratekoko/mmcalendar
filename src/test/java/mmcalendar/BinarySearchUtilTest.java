package mmcalendar;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BinarySearchUtilTest {

    @Test
    public void search2D() {
        int[][] fme = {{1377, 1}};
        int jd = 1377;
        int index = BinarySearchUtil.search(jd, fme);
        assertThat(0, is(index));
    }

    @Test
    public void search1D() {
        int[] diwali = {2456599, 2456953, 2457337, 2457691, 2458045, 2458429};
        double jd = 2457691;
        int index = BinarySearchUtil.search(jd, diwali);
        assertThat(3, is(index));
    }
}
