package mmcalendar;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MyanmarDateBatchTest {

    @Test
    public void batchTest() throws IOException {
        for (int i = 1923; i <= 2023; i++) {
            textByYear(i);
        }
    }

    public void textByYear(int year) throws IOException {

        String fileName = "./mmdate/" +year + ".txt";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                testJulianToMyanmarDate(line);
                testMyanmarDateToJulian(line);
            }

        } else {
            System.err.println("File not found: " + fileName);
        }
    }

    public void testJulianToMyanmarDate(String line) {
        int[] date = Arrays.stream(line.split("\\|"))
                .mapToInt(Integer::parseInt).toArray();

        int jd = date[0];
        int yearType = date[1];
        int year = date[2];
        int month = date[3];
        int monthDay = date[4];

        MyanmarDate myanmarDate = MyanmarDateKernel.julianToMyanmarDate(jd);

        assertThat(yearType, is(myanmarDate.getYearType()));
        assertThat(year, is(myanmarDate.getYearValue()));
        assertThat(month, is(myanmarDate.getMonth()));
        assertThat(monthDay, is(myanmarDate.getDayOfMonth()));

    }

    public void testMyanmarDateToJulian(String line) {
        int[] date = Arrays.stream(line.split("\\|"))
                .mapToInt(Integer::parseInt).toArray();

        int jd = date[0];
        int year = date[2];
        int month = date[3];
        int monthDay = date[4];

        int resultJd = MyanmarDateKernel.myanmarDateToJulian(year, month, monthDay);

        assertThat(jd, is(resultJd));
    }
}
