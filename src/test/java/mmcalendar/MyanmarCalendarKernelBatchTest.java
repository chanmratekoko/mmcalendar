package mmcalendar;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MyanmarCalendarKernelBatchTest {

    @Test
    public void batchTest() throws IOException {
        for (int i = 1923; i <= 2023; i++) {
            textByYear(i);
        }
    }

    public void textByYear(int year) throws IOException {

        String fileName = "./mmdate/" + year + ".txt";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                calculateDayOfMonth(line);
//                testMyanmarDateToJulian(line);
            }

        } else {
            System.err.println("File not found: " + fileName);
        }
    }

    public void calculateDayOfMonth(String line) {
        int[] date = Arrays.stream(line.split("\\|"))
                .mapToInt(Integer::parseInt).toArray();

        int jd = date[0];
        int yearType = date[1];
        int year = date[2];
        int month = date[3];
        int monthDay = date[4];

        MyanmarDate myanmarDate = MyanmarDateKernel.julianToMyanmarDate(jd);

        int md = MyanmarCalendarKernel.calculateDayOfMonth(
                myanmarDate.getYearValue(),
                myanmarDate.getMonth(),
                myanmarDate.getMoonPhaseValue(),
                myanmarDate.getFortnightDayValue()
        );

        assertThat(monthDay, is(md));

    }

}
