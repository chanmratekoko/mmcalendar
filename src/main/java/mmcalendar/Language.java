package mmcalendar;

/**
 * Language that support for Output
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0
 */
public enum Language {

    MYANMAR("\u104a\u200b", "\u104b\u200b"),
    ENGLISH(",\u0020", "."),
    MON("\u104a\u200b", "\u104b\u200b"),
    ZAWGYI("\u104a\u200b", "\u104b\u200b");

    private String comma;

    private String period;

    Language(String comma, String period) {
        this.comma = comma;
        this.period = period;
    }

    public String getComma() {
        return this.comma;
    }

    public String getPeriod() {
        return this.period;
    }
}
