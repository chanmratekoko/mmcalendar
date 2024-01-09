package mmcalendar;


/**
 * Representing different languages and their corresponding punctuation marks.
 */
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

    /**
     * Constructor for the Language enum.
     *
     * @param languageIndex   The index of the language.
     * @param punctuationMark The punctuation mark used in the language for separation.
     * @param punctuation     The punctuation mark used in the language for ending a sentence.
     */
    Language(int languageIndex, String punctuationMark, String punctuation) {
        this.languageIndex = languageIndex;
        this.punctuationMark = punctuationMark;
        this.punctuation = punctuation;
    }


    /**
     * Get the index of the language.
     *
     * @return The language index.
     */
    public int getLanguageIndex() {
        return languageIndex;
    }

    /**
     * Get the punctuation mark used for separation in the language.
     *
     * @return The punctuation mark for separation.
     */
    public String getPunctuationMark() {
        return this.punctuationMark;
    }

    /**
     * Get the punctuation mark used for ending a sentence in the language.
     *
     * @return The punctuation mark for ending a sentence.
     */
    public String getPunctuation() {
        return this.punctuation;
    }

}
