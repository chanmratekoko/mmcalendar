package mmcalendar;

public enum Language {

    ENGLISH(0, ", ", "."),
    MYANMAR(1, "၊ ", "။ "),
    ZAWGYI(2, "၊ ", "။ "),
    MON(3, "၊ ", "။ "),
    TAI(4, "၊ ", "။ "),
    KAREN(5, "၊ ", "။ ");

    private final int languageIndex;

    private final String punctuationMark;

    private final String punctuation;

    Language(int languageIndex, String punctuationMark, String punctuation) {
        this.languageIndex = languageIndex;
        this.punctuationMark = punctuationMark;
        this.punctuation = punctuation;
    }

    public int getLanguageIndex() {
        return languageIndex;
    }

    public String getPunctuationMark() {
        return this.punctuationMark;
    }

    public String getPunctuation() {
        return this.punctuation;
    }

}
