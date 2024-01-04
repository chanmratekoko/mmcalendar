package mmcalendar;

import java.io.Serializable;

import static mmcalendar.AstroKernel.*;

/**
 * Astrological information
 * <p>
 * Note : All fields have default package visibility, ensuring that the specific
 * numbers are not necessary for external use.
 * This design facilitates testing in batch tests while maintaining the desired
 * level of encapsulation.
 * </p>
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 2.0.0
 * @since 1.0
 */
public class Astro implements Serializable {

    private static final long serialVersionUID = 704868696259464344L;

    final int sabbath;
    final int yatyaza;
    final int pyathada;
    final int thamanyo;
    final int amyeittasote;
    final int warameittugyi;
    final int warameittunge;
    final int yatpote;
    final int thamaphyu;
    final int nagapor;
    final int yatyotema;
    final int mahayatkyan;
    final int shanyat;
    /* 0 = west, 1 = north, 2 = east, 3 = south */
    final int nagahle;
    /* 0 = Binga, 1 = Atun, 2 = Yaza, 3 = Adipati, 4 = Marana, 5 = Thike, 6 = Puti */
    final int mahabote;
    /* 0 = orc, 1 = elf, 2 = human */
    final int nakhat;

    /**
     * "ပုဿနှစ်","မာခနှစ်","ဖ္လကိုန်သံဝစ္ဆိုဝ်ရနှစ်","စယ်နှစ်",
     * "ပိသျက်နှစ်","စိဿနှစ်","အာသတ်နှစ်","သရဝန်နှစ်",
     * "ဘဒ္ဒြသံဝစ္ဆုံရ်နှစ်","အာသိန်နှစ်","ကြတိုက်နှစ်","မြိက္ကသိုဝ်နှစ်"
     */
    final int yearName;

    /**
     * Parameter constructor
     *
     * @param sabbath       sabbath
     * @param yatyaza       yatyaza
     * @param pyathada      pyathada
     * @param thamanyo      thamanyo
     * @param amyeittasote  amyeittasote
     * @param warameittugyi warameittugyi
     * @param warameittunge warameittunge
     * @param yatpote       yatpote
     * @param thamaphyu     thamaphyu
     * @param nagapor       nagapor
     * @param yatyotema     yatyotema
     * @param mahayatkyan   mahayatkyan
     * @param shanyat       shanyat
     * @param nagahle       nagahle
     * @param mahabote      mahabote
     * @param nakhat        nakhat
     * @param yearName      yearName
     */
    private Astro(int sabbath, int yatyaza, int pyathada, int thamanyo,
                  int amyeittasote, int warameittugyi, int warameittunge,
                  int yatpote, int thamaphyu, int nagapor, int yatyotema,
                  int mahayatkyan, int shanyat, int nagahle, int mahabote,
                  int nakhat, int yearName) {
        this.sabbath = sabbath;
        this.yatyaza = yatyaza;
        this.pyathada = pyathada;
        this.thamanyo = thamanyo;
        this.amyeittasote = amyeittasote;
        this.warameittugyi = warameittugyi;
        this.warameittunge = warameittunge;
        this.yatpote = yatpote;
        this.thamaphyu = thamaphyu;
        this.nagapor = nagapor;
        this.yatyotema = yatyotema;
        this.mahayatkyan = mahayatkyan;
        this.shanyat = shanyat;
        /* 0=west, 1=north, 2=east, 3=south */
        this.nagahle = nagahle;
        /* 0=Binga, 1=Atun, 2=Yaza, 3=Adipati, 4=Marana, 5=Thike, 6=Puti */
        this.mahabote = mahabote;
        this.nakhat = nakhat;
        this.yearName = yearName;
    }

    /**
     * Myanmar Date to Astro
     *
     * @param myanmarDate {@link MyanmarDate}
     * @return {@link Astro}
     */
    public static Astro of(MyanmarDate myanmarDate) {

        int myear = myanmarDate.getYearValue();
        int yearType = myanmarDate.getYearType();
        int mmonth = myanmarDate.getMonth();
        int monthDay = myanmarDate.getDayOfMonth();
        int weekDay = myanmarDate.getWeekDayValue();

        return new Astro(
                calculateSabbath(yearType, mmonth, monthDay),
                calculateYatyaza(mmonth, weekDay),
                calculatePyathada(mmonth, weekDay),
                calculateThamanyo(mmonth, weekDay),
                calculateAmyeittasote(monthDay, weekDay),
                calculateWarameittugyi(monthDay, weekDay),
                calculateWarameittunge(monthDay, weekDay),
                calculateYatpote(monthDay, weekDay),
                calculateThamaphyu(monthDay, weekDay),
                calculateNagapor(monthDay, weekDay),
                calculateYatyotema(mmonth, monthDay),
                calculateMahayatkyan(mmonth, monthDay),
                calculateShanyat(mmonth, monthDay),
                calculateNagahle(mmonth),
                calculateMahabote(myear, weekDay),
                calculateNakhat(myear),
                calculateYearName(myear)
        );
    }

    /**
     * @return true if the day is Yatyaza
     */
    public boolean isYatyaza() {
        // if greater 0 is Yatyaza
        return yatyaza > 0;
    }

    /**
     * @return Yatyaza or none
     */
    public String getYatyaza() {
        return getYatyaza(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Yatyaza or none
     */
    public String getYatyaza(Language language) {
        return isYatyaza() ? LanguageTranslator.translate("Yatyaza", language) : "";
    }

    /**
     * @return true if Pyathada or Afternoon Pyathada
     */
    public boolean isPyathada() {
        //  0 = none ,  1 = Pyathada , 2 = Afternoon Pyathada
        return pyathada > 0;
    }


    /**
     * @return Pyathada or Afternoon Pyathada or none
     */
    public String getPyathada() {
        return getPyathada(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Pyathada or Afternoon Pyathada or none
     */
    public String getPyathada(Language language) {
        StringBuilder stringBuilder = new StringBuilder();
        if (pyathada == 1) {
            stringBuilder.append(LanguageTranslator.translate("Pyathada", language));
        } else if (pyathada == 2) {
            stringBuilder.append(LanguageTranslator.translate("Afternoon", language));
            stringBuilder.append(" ");
            stringBuilder.append(LanguageTranslator.translate("Pyathada", language));
        }
        return stringBuilder.toString();
    }

    /**
     * @return String ["Yatyaza", "Pyathada" or "Afternoon Pyathada" or None ""]
     * Output String Depend On Default Language on Config
     */
    public String getAstrologicalDay() {
        return getAstrologicalDay(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return String ["Yatyaza", "Pyathada" or Afternoon Pyathada or None ""]
     * Output String Depend On Language
     */
    public String getAstrologicalDay(Language language) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getYatyaza(language));

        if (isYatyaza() && isPyathada()) {
            stringBuilder.append("၊ ");
        }

        stringBuilder.append(getPyathada(language));

        return stringBuilder.toString();
    }

    public boolean isSabbath() {
        return sabbath > 0;
    }

    public String getSabbath() {
        return getSabbath(Config.getInstance().getLanguage());
    }

    /**
     * @param language {@link Language}
     * @return ["Sabbath" , "Sabbath Eve" or none "" ]
     * Output String Depend On Language
     * {Calculation depend on sabbath or sabbatheve}
     */
    public String getSabbath(Language language) {
        StringBuilder stringBuilder = new StringBuilder();
        if (sabbath == 1) {
            stringBuilder.append(LanguageTranslator.translate("Sabbath", language));
        } else if (sabbath == 2) {
            stringBuilder.append(LanguageTranslator.translate("Sabbath Eve", language));
        }
        return stringBuilder.toString();
    }

    public boolean isThamanyo() {
        return thamanyo > 0;
    }

    /**
     * @return Thamanyo or Empty
     */
    public String getThamanyo() {
        return getThamanyo(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Thamanyo or Empty
     */
    public String getThamanyo(Language language) {
        return isThamanyo() ? LanguageTranslator.translate("Thamanyo", language) : "";
    }

    public boolean isAmyeittasote() {
        return amyeittasote > 0;
    }

    /**
     * @return Amyeittasote or Empty
     */
    public String getAmyeittasote() {
        return getAmyeittasote(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Amyeittasote or Empty
     */
    public String getAmyeittasote(Language language) {
        return isAmyeittasote() ? LanguageTranslator.translate("Amyeittasote", language) : "";
    }

    public boolean isWarameittugyi() {
        return warameittugyi > 0;
    }

    /**
     * @return Warameittugyi or Empty
     */
    public String getWarameittugyi() {
        return getWarameittugyi(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Warameittugyi or Empty
     */
    public String getWarameittugyi(Language language) {
        return isWarameittugyi() ? LanguageTranslator.translate("Warameittugyi", language) : "";
    }

    public boolean isWarameittunge() {
        return warameittunge > 0;
    }

    /**
     * @return Warameittunge or Empty
     */
    public String getWarameittunge() {
        return getWarameittunge(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Warameittunge or Empty
     */
    public String getWarameittunge(Language language) {
        return isWarameittunge() ? LanguageTranslator.translate("Warameittunge", language) : "";
    }

    public boolean isYatpote() {
        return yatpote > 0;
    }

    /**
     * @return Yatpote or Empty
     */
    public String getYatpote() {
        return getYatpote(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Yatpote or Empty
     */
    public String getYatpote(Language language) {
        return isYatpote() ? LanguageTranslator.translate("Yatpote", language) : "";
    }

    public boolean isThamaphyu() {
        return thamaphyu > 0;
    }

    /**
     * @return Thamaphyu or Empty
     */
    public String getThamaphyu() {
        return getThamaphyu(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Thamaphyu or Empty
     */
    public String getThamaphyu(Language language) {
        return isThamaphyu() ? LanguageTranslator.translate("Thamaphyu", language) : "";
    }

    public boolean isNagapor() {
        return nagapor > 0;
    }

    /**
     * @return Nagapor or Empty
     */
    public String getNagapor() {
        return getNagapor(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Nagapor or Empty
     */
    public String getNagapor(Language language) {
        return isNagapor() ? LanguageTranslator.translate("Nagapor", language) : "";
    }

    public boolean isYatyotema() {
        return yatyotema > 0;
    }

    /**
     * @return Yatyotema or Empty
     */
    public String getYatyotema() {
        return getYatyotema(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Yatyotema or Empty
     */
    public String getYatyotema(Language language) {
        return isYatyotema() ? LanguageTranslator.translate("Yatyotema", language) : "";
    }

    public boolean isMahayatkyan() {
        return mahayatkyan > 0;
    }

    /**
     * @return Mahayatkyan or Empty
     */
    public String getMahayatkyan() {
        return getMahayatkyan(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Mahayatkyan or Empty
     */
    public String getMahayatkyan(Language language) {
        return isMahayatkyan() ? LanguageTranslator.translate("Mahayatkyan", language) : "";
    }

    public boolean isShanyat() {
        return shanyat > 0;
    }

    /**
     * @return Shanyat or Empty
     */
    public String getShanyat() {
        return getShanyat(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return Shanyat or Empty
     */
    public String getShanyat(Language language) {
        return isShanyat() ? LanguageTranslator.translate("Shanyat", language) : "";
    }

    public String getNagahle() {
        return getNagahle(Config.getInstance().getLanguage());
    }

    /**
     * @return 0=west, 1=north, 2=east, 3=south
     */
    public int getNagahleValue() {
        return nagahle;
    }

    public String getNagahle(Language language) {
        String[] na = new String[]{"West", "North", "East", "South"};
        return LanguageTranslator.translate(na[nagahle], language);
    }

    public String getMahabote() {
        return getMahabote(Config.getInstance().getLanguage());
    }

    /**
     * @return 0=Binga, 1=Atun, 2=Yaza, 3=Adipati, 4= Marana, 5=Thike, 6=Puti
     */
    public int getMahaboteValue() {
        return mahabote;
    }

    public String getMahabote(Language language) {
        String[] pa = new String[]{"Binga", "Atun", "Yaza", "Adipati", "Marana", "Thike", "Puti"};
        return LanguageTranslator.translate(pa[mahabote], language);
    }

    public String getNakhat() {
        return getNakhat(Config.getInstance().getLanguage());
    }

    /**
     * @return 0 = orc, 1 = elf, 2=human
     */
    public int getNakhatValue() {
        return nakhat;
    }

    /**
     * @param language Language
     * @return String [ "Ogre" or "Elf" or "Human"]
     */
    public String getNakhat(Language language) {
        String[] nk = {"Ogre", "Elf", "Human"};
        return LanguageTranslator.translate(nk[nakhat], language);
    }

    /**
     * @return String Myanmar Year Name
     */
    public String getYearName() {
        return getYearName(Config.getInstance().getLanguage());
    }

    /**
     * @param language Language
     * @return String Myanmar Year Name
     */
    public String getYearName(Language language) {
        String[] yna = {"Hpusha", "Magha", "Phalguni", "Chitra",
                "Visakha", "Jyeshtha", "Ashadha", "Sravana",
                "Bhadrapaha", "Asvini", "Krittika", "Mrigasiras"};
        return LanguageTranslator.translate(yna[yearName], language);
    }

    @Override
    public String toString() {
        return toString(Config.getInstance().getLanguage());
    }

    public String toString(Language language) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getAstrologicalDay());

        if (isSabbath()) {
            stringBuilder
                    .append(language.getPunctuationMark())
                    .append(getSabbath(language));
        }

        if (isThamanyo()) {
            stringBuilder
                    .append(" ")
                    .append(language.getPunctuationMark())
                    .append(getThamanyo(language));
        }

        if (isThamaphyu()) {
            stringBuilder
                    .append(" ")
                    .append(language.getPunctuationMark())
                    .append(getThamaphyu(language));
        }

        if (isAmyeittasote()) {
            stringBuilder
                    .append(" ")
                    .append(language.getPunctuationMark())
                    .append(getAmyeittasote(language));
        }

        if (isWarameittugyi()) {
            stringBuilder
                    .append(" ")
                    .append(language.getPunctuationMark())
                    .append(getWarameittugyi(language));
        }

        if (isWarameittunge()) {
            stringBuilder
                    .append(" ")
                    .append(language.getPunctuationMark())
                    .append(getWarameittunge(language));
        }

        if (isYatpote()) {
            stringBuilder
                    .append(" ")
                    .append(language.getPunctuationMark())
                    .append(getYatpote(language));
        }

        if (isNagapor()) {
            stringBuilder
                    .append(" ")
                    .append(language.getPunctuationMark())
                    .append(getNagapor());
        }

        if (isYatyotema()) {
            stringBuilder.append(" ")
                    .append(language.getPunctuationMark())
                    .append(getYatyotema(language));
        }

        if (isMahayatkyan()) {
            stringBuilder.append(" ")
                    .append(language.getPunctuationMark())
                    .append(getMahayatkyan(language));
        }

        if (isShanyat()) {
            stringBuilder
                    .append(" ")
                    .append(language.getPunctuationMark())
                    .append(getShanyat(language));
        }


        stringBuilder
                .append(" ")
                .append(language.getPunctuationMark())
                .append(LanguageTranslator.translate("Naga", language))
                .append(" ")
                .append(LanguageTranslator.translate("Head", language))
                .append(" ")
                .append(getNagahle(language))
                .append(" ")
                .append(LanguageTranslator.translate("Facing", language));

        stringBuilder
                .append(" ")
                .append(language.getPunctuationMark());

        stringBuilder
                .append(getMahabote(language))
                .append(LanguageTranslator.translate("Born", language));

        stringBuilder
                .append(" ")
                .append(language.getPunctuationMark());

        stringBuilder
                .append(getNakhat(language))
                .append(" ")
                .append(LanguageTranslator.translate("Nakhat", language));

        stringBuilder
                .append(" ")
                .append(language.getPunctuationMark());

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
