package mmcalendar;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AstroBatchTest {

    @Test
    public void batchTest() throws IOException {
        for (int i = 2023; i < 2024; i++) {
            textByYear(i);
        }
    }

    public void textByYear(int year) throws IOException {

        String fileName = "./astro/astro-" + year + ".txt";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                testJulianToMyanmarDate(line);
            }

        } else {
            System.err.println("File not found: " + fileName);
        }
    }

    public void testJulianToMyanmarDate(String line) {
        int[] date = Arrays.stream(line.split("\\|"))
                .mapToInt(Integer::parseInt).toArray();

        int jd = date[0];
        int sabbath = date[1];
        int yatyaza = date[2];
        int pyathada = date[3];
        int thamanyo = date[4];
        int amyeittasote = date[5];
        int warameittugyi = date[6];
        int warameittunge = date[7];
        int yatpote = date[8];
        int thamaphyu = date[9];
        int nagapor = date[10];
        int yatyotema = date[11];
        int mahayatkyan = date[12];
        int shanyat = date[13];
        int nagahle = date[14];
        int mahabote = date[15];
        int nakhat = date[16];
        int yearName = date[17];


        MyanmarDate myanmarDate = MyanmarDateKernel.julianToMyanmarDate(jd);

        Astro astro = Astro.of(myanmarDate);

        assertThat(sabbath, is(astro.sabbath));
        assertThat(yatyaza, is(astro.yatyaza));
        assertThat(pyathada, is(astro.pyathada));
        assertThat(thamanyo, is(astro.thamanyo));
        assertThat(amyeittasote, is(astro.amyeittasote));
        assertThat(warameittugyi, is(astro.warameittugyi));
        assertThat(warameittunge, is(astro.warameittunge));
        assertThat(yatpote, is(astro.yatpote));
        assertThat(thamaphyu, is(astro.thamaphyu));
        assertThat(nagapor, is(astro.nagapor));
        assertThat(yatyotema, is(astro.yatyotema));
        assertThat(mahayatkyan, is(astro.mahayatkyan));
        assertThat(shanyat, is(astro.shanyat));
        assertThat(nagahle, is(astro.nagahle));
        assertThat(mahabote, is(astro.mahabote));
        assertThat(nakhat, is(astro.nakhat));
        assertThat(yearName, is(astro.yearName));

    }
}
