package mmcalendar;

import java.io.Serializable;

/**
 * Astrological information
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0.2
 * @since 1.0
 */
public class Astro implements Serializable, Cloneable {

    private static final long serialVersionUID = 704868696259464344L;

    int sabbath;
    int sabbatheve;
    int yatyaza;
    int pyathada;
    int thamanyo;
    int amyeittasote;
    int warameittugyi;
    int warameittunge;
    int yatpote;
    int thamaphyu;
    int nagapor;
    int yatyotema;
    int mahayatkyan;
    int shanyat;
    /* 0 = west, 1 = north, 2 = east, 3 = south */
    int nagahle;
    /* 0 = Binga, 1 = Atun, 2 = Yaza, 3 = Adipati, 4 = Marana, 5 = Thike, 6 = Puti */
    int mahabote;
    /* 0 = orc, 1 = elf, 2 = human */
    int nakhat;
    //{"ပုဿနှစ်","မာခနှစ်","ဖ္လကိုန်သံဝစ္ဆိုဝ်ရနှစ်","စယ်နှစ်",
    //"ပိသျက်နှစ်","စိဿနှစ်","အာသတ်နှစ်","သရဝန်နှစ်",
    //"ဘဒ္ဒြသံဝစ္ဆုံရ်နှစ်","အာသိန်နှစ်","ကြတိုက်နှစ်","မြိက္ကသိုဝ်နှစ်"}
    int yearName;

    protected Astro() {
    }

    /**
     * @return [ if greater 0 is Yatyaza ]
     */
    public int getYatyaza() {
        return yatyaza;
    }

    /**
     * @return [ 0 = none ,  1 = Pyathada , 2 = Afternoon Pyathada]
     */
    public int getPyathada() {
        return pyathada;
    }

    /**
     * @return String ["Yatyaza", "Pyathada" or "Afternoon Pyathada" or None ""]
     * Output String Depend On Default LanguageCatalog on Config
     */
    public String getAstroligicalDay() {
        return getAstroligicalDay(LanguageCatalog.getInstance());
    }

    /**
     * @param languageCatalog LanguageCatalog
     * @return String ["Yatyaza", "Pyathada" or Afternoon Pyathada or None ""]
     * Output String Depend On LanguageCatalog
     */
    public String getAstroligicalDay(LanguageCatalog languageCatalog) {
        StringBuilder stringBuilder = new StringBuilder();
        if (yatyaza > 0) {
            stringBuilder.append(languageCatalog.translate("Yatyaza"));
        }
        if (pyathada == 1) {
            stringBuilder.append(languageCatalog.translate("Pyathada"));
        } else if (pyathada == 2) {
            stringBuilder.append(languageCatalog.translate("Afternoon Pyathada"));
        }
        return stringBuilder.toString();
    }

    public boolean isSabbath() {
        return (sabbath > 0) || (sabbatheve > 0);
    }

    public String getSabbath() {
        return getSabbath(LanguageCatalog.getInstance());
    }

    /**
     * @param languageCatalog LanguageCatalog
     * @return String ["Sabbath" , "Sabbath Eve" or none "" ]
     * Output String Depend On LanguageCatalog
     * {Calculation depend on sabbath or sabbatheve}
     */
    public String getSabbath(LanguageCatalog languageCatalog) {
        StringBuilder stringBuilder = new StringBuilder();
        if (sabbath > 0) {
            stringBuilder.append(languageCatalog.translate("Sabbath"));
        } else if (sabbatheve > 0) {
            stringBuilder.append(languageCatalog.translate("Sabbath Eve"));
        }
        return stringBuilder.toString();
    }

    public boolean isThamanyo() {
        return (thamanyo > 0);
    }

    public String getThamanyo() {
        return getThamanyo(LanguageCatalog.getInstance());
    }

    public String getThamanyo(LanguageCatalog languageCatalog) {
        return isThamanyo() ? languageCatalog.translate("Thamanyo") : "";
    }

    public boolean isAmyeittasote() {
        return (amyeittasote > 0);
    }

    public String getAmyeittasote() {
        return getAmyeittasote(LanguageCatalog.getInstance());
    }

    public String getAmyeittasote(LanguageCatalog languageCatalog) {
        return isAmyeittasote() ? languageCatalog.translate("Amyeittasote") : "";
    }

    public boolean isWarameittugyi() {
        return (warameittugyi > 0);
    }

    public String getWarameittugyi() {
        return getWarameittugyi(LanguageCatalog.getInstance());
    }

    public String getWarameittugyi(LanguageCatalog languageCatalog) {
        return isWarameittugyi() ? languageCatalog.translate("Warameittugyi") : "";
    }

    public boolean isWarameittunge() {
        return (warameittunge > 0);
    }

    public String getWarameittunge() {
        return getWarameittunge(LanguageCatalog.getInstance());
    }

    public String getWarameittunge(LanguageCatalog languageCatalog) {
        return isWarameittunge() ? languageCatalog.translate("Warameittunge") : "";
    }

    public boolean isYatpote() {
        return (yatpote > 0);
    }

    public String getYatpote() {
        return getYatpote(LanguageCatalog.getInstance());
    }

    public String getYatpote(LanguageCatalog languageCatalog) {
        return isYatpote() ? languageCatalog.translate("Yatpote") : "";
    }

    public boolean isThamaphyu() {
        return (thamaphyu > 0);
    }

    public String getThamaphyu() {
        return getThamaphyu(LanguageCatalog.getInstance());
    }

    public String getThamaphyu(LanguageCatalog languageCatalog) {
        return isThamaphyu() ? languageCatalog.translate("Thamaphyu") : "";
    }

    public boolean isNagapor() {
        return (nagapor > 0);
    }

    public String getNagapor() {
        return getNagapor(LanguageCatalog.getInstance());
    }

    public String getNagapor(LanguageCatalog languageCatalog) {
        return isNagapor() ? languageCatalog.translate("Nagapor") : "";
    }

    public boolean isYatyotema() {
        return (yatyotema > 0);
    }

    public String getYatyotema() {
        return getYatyotema(LanguageCatalog.getInstance());
    }

    public String getYatyotema(LanguageCatalog languageCatalog) {
        return isYatyotema() ? languageCatalog.translate("Yatyotema") : "";
    }

    public boolean isMahayatkyan() {
        return (mahayatkyan > 0);
    }

    public String getMahayatkyan() {
        return getMahayatkyan(LanguageCatalog.getInstance());
    }

    public String getMahayatkyan(LanguageCatalog languageCatalog) {
        return isMahayatkyan() ? languageCatalog.translate("Mahayatkyan") : "";
    }

    public boolean isShanyat() {
        return (shanyat > 0);
    }

    public String getShanyat() {
        return getShanyat(LanguageCatalog.getInstance());
    }

    public String getShanyat(LanguageCatalog languageCatalog) {
        return isShanyat() ? languageCatalog.translate("Shanyat") : "";
    }

    public String getNagahle() {
        return getNagahle(LanguageCatalog.getInstance());
    }

    /**
     * @return 0=west, 1=north, 2=east, 3=south
     */
    public int getNagahleInt() {
        return nagahle;
    }

    public String getNagahle(LanguageCatalog languageCatalog) {
        String[] na = new String[]{"West", "North", "East", "South"};
        return languageCatalog.translate(na[(int) nagahle]);
    }

    public String getMahabote() {
        return getMahabote(LanguageCatalog.getInstance());
    }

    /**
     * @return 0=Binga, 1=Atun, 2=Yaza, 3=Adipati, 4= Marana, 5=Thike, 6=Puti
     */
    public int getMahaboteInt() {
        return mahabote;
    }

    public String getMahabote(LanguageCatalog languageCatalog) {
        String[] pa = new String[]{"Binga", "Atun", "Yaza", "Adipati", "Marana", "Thike", "Puti"};
        return languageCatalog.translate(pa[(int) mahabote]);
    }

    public String getNakhat() {
        return getNakhat(LanguageCatalog.getInstance());
    }

    /**
     * @return 0 = orc, 1 = elf, 2=human
     */
    public int getNakhatInt() {
        return nakhat;
    }

    /**
     * @param languageCatalog LanguageCatalog
     * @return String [ "Orc" or "Elf" or "Human"]
     */
    public String getNakhat(LanguageCatalog languageCatalog) {
        String[] nk = new String[]{"Orc", "Elf", "Human"};
        return languageCatalog.translate(nk[(int) nakhat]);
    }

    /**
     * @return String Myanmar Year Name
     */
    public String getYearName() {
        String[] yearNames = new String[]{"\u1015\u102F\u103F\u1014\u103E\u1005\u103A", "\u1019\u102C\u1001\u1014\u103E\u1005\u103A", "\u1016\u1039\u101C\u1000\u102D\u102F\u1014\u103A\u101E\u1036\u101D\u1005\u1039\u1006\u102D\u102F\u101D\u103A\u101B\u1014\u103E\u1005\u103A", "\u1005\u101A\u103A\u1014\u103E\u1005\u103A",
                "\u1015\u102D\u101E\u103B\u1000\u103A\u1014\u103E\u1005\u103A", "\u1005\u102D\u103F\u1014\u103E\u1005\u103A", "\u1021\u102C\u101E\u1010\u103A\u1014\u103E\u1005\u103A", "\u101E\u101B\u101D\u1014\u103A\u1014\u103E\u1005\u103A",
                "\u1018\u1012\u1039\u1012\u103C\u101E\u1036\u101D\u1005\u1039\u1006\u102F\u1036\u101B\u103A\u1014\u103E\u1005\u103A", "\u1021\u102C\u101E\u102D\u1014\u103A\u1014\u103E\u1005\u103A", "\u1000\u103C\u1010\u102D\u102F\u1000\u103A\u1014\u103E\u1005\u103A", "\u1019\u103C\u102D\u1000\u1039\u1000\u101E\u102D\u102F\u101D\u103A\u1014\u103E\u1005\u103A"};
        return yearNames[yearName];
    }

    @Override
    public String toString() {
        return toString(LanguageCatalog.getInstance());
    }

    public String toString(LanguageCatalog languageCatalog) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getAstroligicalDay());

        if (isSabbath()) {
            stringBuilder
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getSabbath(languageCatalog));
        }

        if (isThamanyo()) {
            stringBuilder
                    .append(" ")
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getThamanyo(languageCatalog));
        }

        if (isThamaphyu()) {
            stringBuilder
                    .append(" ")
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getThamaphyu(languageCatalog));
        }

        if (isAmyeittasote()) {
            stringBuilder
                    .append(" ")
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getAmyeittasote(languageCatalog));
        }

        if (isWarameittugyi()) {
            stringBuilder
                    .append(" ")
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getWarameittugyi(languageCatalog));
        }

        if (isWarameittunge()) {
            stringBuilder
                    .append(" ")
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getWarameittunge(languageCatalog));
        }

        if (isYatpote()) {
            stringBuilder
                    .append(" ")
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getYatpote(languageCatalog));
        }

        if (isNagapor()) {
            stringBuilder
                    .append(" ")
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getNagapor());
        }

        if (isYatyotema()) {
            stringBuilder.append(" ")
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getYatyotema(languageCatalog));
        }

        if (isMahayatkyan()) {
            stringBuilder.append(" ")
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getMahayatkyan(languageCatalog));
        }

        if (isShanyat()) {
            stringBuilder
                    .append(" ")
                    .append(languageCatalog.getLanguage().getComma())
                    .append(" ")
                    .append(getShanyat(languageCatalog));
        }


        stringBuilder
                .append("\u1014\u1002\u102B\u1038\u1001\u1031\u102B\u1004\u103A\u1038")
                .append(getNagahle(languageCatalog))
                .append("\u101E\u102D\u102F\u1037\u101C\u103E\u100A\u103A\u1037\u101E\u100A\u103A\u104B");

        stringBuilder
                .append(" ")
                .append(languageCatalog.getLanguage().getComma())
                .append(" ");

        stringBuilder
                .append(getMahabote(languageCatalog))
                .append("\u1016\u103D\u102C\u1038");

        stringBuilder
                .append(" ")
                .append(languageCatalog.getLanguage().getComma())
                .append(" ");

        stringBuilder
                .append(getNakhat(languageCatalog))
                .append("\u1014\u1000\u1039\u1001\u1010\u103A");

        stringBuilder
                .append(" ")
                .append(languageCatalog.getLanguage().getComma())
                .append(" ");

        stringBuilder.append(getYearName());

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + amyeittasote;
        result = prime * result + mahabote;
        result = prime * result + mahayatkyan;
        result = prime * result + nagahle;
        result = prime * result + nagapor;
        result = prime * result + nakhat;
        result = prime * result + pyathada;
        result = prime * result + sabbath;
        result = prime * result + sabbatheve;
        result = prime * result + shanyat;
        result = prime * result + thamanyo;
        result = prime * result + thamaphyu;
        result = prime * result + warameittugyi;
        result = prime * result + warameittunge;
        result = prime * result + yatpote;
        result = prime * result + yatyaza;
        result = prime * result + yatyotema;
        result = prime * result + yearName;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Astro other = (Astro) obj;
        if (amyeittasote != other.amyeittasote)
            return false;
        if (mahabote != other.mahabote)
            return false;
        if (mahayatkyan != other.mahayatkyan)
            return false;
        if (nagahle != other.nagahle)
            return false;
        if (nagapor != other.nagapor)
            return false;
        if (nakhat != other.nakhat)
            return false;
        if (pyathada != other.pyathada)
            return false;
        if (sabbath != other.sabbath)
            return false;
        if (sabbatheve != other.sabbatheve)
            return false;
        if (shanyat != other.shanyat)
            return false;
        if (thamanyo != other.thamanyo)
            return false;
        if (thamaphyu != other.thamaphyu)
            return false;
        if (warameittugyi != other.warameittugyi)
            return false;
        if (warameittunge != other.warameittunge)
            return false;
        if (yatpote != other.yatpote)
            return false;
        if (yatyaza != other.yatyaza)
            return false;
        if (yatyotema != other.yatyotema)
            return false;
        return yearName == other.yearName;
    }

}
