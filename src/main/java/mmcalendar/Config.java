package mmcalendar;

import mmcalendar.util.ObjectBuilder;

import java.util.function.Function;

/**
 * Configuration For Calendar
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0
 */
public final class Config {

    private final CalendarType calendarType;

    private final Language language;

    private static Config instance;

    /**
     * Set the default Calendar Config
     *
     * @param config the config build using the builder.
     * @see mmcalendar.Config.Builder
     */
    public static void initDefault(Config config) {
        instance = config;
    }

    /**
     * Set the default Calendar Config using a builder lambda.
     * @param fn configure with lambda
     */
    public static void initDefault(Function<Config.Builder, ObjectBuilder<Config>> fn) {
        instance = fn.apply(new Config.Builder()).build();
    }

    /**
     * The current Calendar Config.
     * If not set it will create a default config.
     * @return the current Calendar Config
     */
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config(new Builder());
        }
        return instance;
    }

    private Config(Builder builder) {
        calendarType = builder.getCalendarType();
        language = builder.getLanguage();
    }

    public CalendarType getCalendarType() {
        return calendarType;
    }

    public Language getLanguage() {
        return language;
    }

    public static class Builder implements ObjectBuilder<Config> {

        private CalendarType calendarType = CalendarType.ENGLISH;

        private Language language = Language.MYANMAR;

        /**
         * For Config
         */
        public Builder() {
            // Builder for Config
        }

        public CalendarType getCalendarType() {
            return calendarType;
        }

        public Builder setCalendarType(CalendarType calendarType) {
            this.calendarType = calendarType;
            return this;
        }

        public Language getLanguage() {
            return language;
        }

        public Builder setLanguage(Language language) {
            this.language = language;
            return this;
        }

        @Override
        public Config build() {
            if (calendarType == null || language == null) {
                throw new IllegalArgumentException("CalendarType or Language cannot be null");
            }
            return new Config(this);
        }
    }

}
