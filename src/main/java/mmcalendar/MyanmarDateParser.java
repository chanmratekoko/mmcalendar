package mmcalendar;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for converting formatted Myanmar date strings back to MyanmarDate objects.
 * <p>
 * This parser leverages the existing {@link LanguageTranslator} and {@link MyanmarDate#create} 
 * factory methods to ensure consistency with the rest of the library.
 * </p>
 * 
 * <p>Supported pattern characters (same as {@link MyanmarDateFormat}):</p>
 * <ul>
 *   <li>S - Sasana Year literal</li>
 *   <li>s - Buddhist Era year (number)</li>
 *   <li>B - Myanmar Year literal</li>
 *   <li>y - Myanmar year (number)</li>
 *   <li>k - Ku literal</li>
 *   <li>M - Month name</li>
 *   <li>p - Moon phase</li>
 *   <li>f - Fortnight day (number)</li>
 *   <li>r - Yat literal</li>
 *   <li>E - Weekday name (optional, for validation)</li>
 *   <li>n - Nay literal</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * // English
 * String text = "Myanmar Year 1385 Ku, Nadaw Waning 5 Yat";
 * MyanmarDate date = MyanmarDateParser.parse(text, "B y k, M p f r", Language.ENGLISH);
 * 
 * // Myanmar Unicode
 * String text = "မြန်မာနှစ် ၁၃၈၅ ခု၊ နတ်တော် လဆုတ် ၅ ရက်";
 * MyanmarDate date = MyanmarDateParser.parse(text, "B y k, M p f r", Language.MYANMAR);
 * </pre>
 *
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @version 1.0.0
 * @since 1.0.12
 */
public class MyanmarDateParser {

    /**
     * Parsed components from the input text
     */
    private static class ParsedComponents {
        Integer myanmarYear;
        Integer buddhistEra;
        String monthName;      // In source language
        String moonPhaseName;  // In source language
        Integer fortnightDay;
        String weekDayName;    // In source language (optional, for validation)
        
        boolean hasRequiredFields() {
            // Need: year + month + moon phase + fortnight day
            return myanmarYear != null && monthName != null && 
                   moonPhaseName != null && fortnightDay != null;
        }
        
        @Override
        public String toString() {
            return String.format("ParsedComponents{year=%d, month='%s', phase='%s', fortnight=%d, weekday='%s'}", 
                myanmarYear, monthName, moonPhaseName, fortnightDay, weekDayName);
        }
    }

    /**
     * Pattern cache for each language
     */
    private static final Map<CacheKey, CompiledPattern> PATTERN_CACHE = new HashMap<>();
    
    private static class CacheKey {
        final String pattern;
        final Language language;
        
        CacheKey(String pattern, Language language) {
            this.pattern = pattern;
            this.language = language;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CacheKey)) return false;
            CacheKey that = (CacheKey) o;
            return pattern.equals(that.pattern) && language == that.language;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(pattern, language);
        }
    }
    
    private static class CompiledPattern {
        final Pattern regex;
        final List<Character> captureGroups; // Which pattern chars have capture groups
        
        CompiledPattern(Pattern regex, List<Character> captureGroups) {
            this.regex = regex;
            this.captureGroups = captureGroups;
        }
    }

    /**
     * Parses a Myanmar date string using the default pattern and configured language.
     *
     * @param text The formatted Myanmar date string
     * @return Parsed MyanmarDate object
     * @throws IllegalArgumentException if parsing fails
     */
    public static MyanmarDate parse(String text) {
        return parse(text, MyanmarDateFormat.SIMPLE_MYANMAR_DATE_FORMAT_PATTERN, 
                    Config.getInstance().getLanguage());
    }

    /**
     * Parses a Myanmar date string using the specified pattern and configured language.
     *
     * @param text    The formatted Myanmar date string
     * @param pattern The pattern describing the date format
     * @return Parsed MyanmarDate object
     * @throws IllegalArgumentException if parsing fails
     */
    public static MyanmarDate parse(String text, String pattern) {
        return parse(text, pattern, Config.getInstance().getLanguage());
    }

    /**
     * Parses a Myanmar date string using the specified pattern and language.
     *
     * @param text     The formatted Myanmar date string
     * @param pattern  The pattern describing the date format
     * @param language The language of the input text
     * @return Parsed MyanmarDate object
     * @throws IllegalArgumentException if parsing fails
     */
    public static MyanmarDate parse(String text, String pattern, Language language) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be null or empty");
        }
        if (language == null) {
            throw new IllegalArgumentException("Language cannot be null");
        }

        // Get or compile the pattern
        CompiledPattern compiled = getOrCompilePattern(pattern, language);
        
        // Parse components from text
        ParsedComponents components = extractComponents(text, compiled, language);

        if (!components.hasRequiredFields()) {
            throw new IllegalArgumentException(
                "Failed to parse required fields from text. " + components
            );
        }

        // Build MyanmarDate using existing factory methods
        return buildMyanmarDate(components, language);
    }

    /**
     * Gets or compiles a pattern and caches it
     */
    private static synchronized CompiledPattern getOrCompilePattern(String pattern, Language language) {
        CacheKey key = new CacheKey(pattern, language);
        CompiledPattern cached = PATTERN_CACHE.get(key);
        
        if (cached == null) {
            cached = compilePattern(pattern, language);
            PATTERN_CACHE.put(key, cached);
        }
        
        return cached;
    }

    /**
     * Compiles a format pattern into a regex pattern
     */
    private static CompiledPattern compilePattern(String pattern, Language language) {
        StringBuilder regex = new StringBuilder();
        List<Character> captureGroups = new ArrayList<>();
        
        for (char c : pattern.toCharArray()) {
            switch (c) {
                case MyanmarDateFormat.SASANA_YEAR:
                    regex.append(Pattern.quote(LanguageTranslator.translate("Sasana Year", language)));
                    break;
                    
                case MyanmarDateFormat.BUDDHIST_ERA:
                    regex.append("(").append(getNumberRegex(language)).append(")");
                    captureGroups.add(c);
                    break;
                    
                case MyanmarDateFormat.BURMESE_YEAR:
                    regex.append(Pattern.quote(LanguageTranslator.translate("Myanmar Year", language)));
                    break;
                    
                case MyanmarDateFormat.MYANMAR_YEAR:
                    regex.append("(").append(getNumberRegex(language)).append(")");
                    captureGroups.add(c);
                    break;
                    
                case MyanmarDateFormat.KU:
                    String ku = LanguageTranslator.translate("Ku", language);
                    if (!ku.isEmpty()) {
                        regex.append(Pattern.quote(ku));
                    }
                    break;
                    
                case MyanmarDateFormat.MONTH_IN_YEAR:
                    regex.append("(").append(buildMonthRegex(language)).append(")");
                    captureGroups.add(c);
                    break;
                    
                case MyanmarDateFormat.MOON_PHASE:
                    regex.append("(").append(buildMoonPhaseRegex(language)).append(")");
                    captureGroups.add(c);
                    break;
                    
                case MyanmarDateFormat.FORTNIGHT_DAY:
                    // Make fortnight day optional (empty for full moon/new moon)
                    // Use * (zero or more) instead of + (one or more)
                    if (language == Language.ENGLISH || language == Language.TAI) {
                        regex.append("(\\d*)");
                    } else {
                        regex.append("([\\d၀-၉]*)");
                    }
                    captureGroups.add(c);
                    break;
                    
                case MyanmarDateFormat.DAY_NAME_IN_WEEK:
                    regex.append("(").append(buildWeekDayRegex(language)).append(")");
                    captureGroups.add(c);
                    break;
                    
                case MyanmarDateFormat.NAY:
                    String nay = LanguageTranslator.translate("Nay", language);
                    if (!nay.isEmpty()) {
                        regex.append(Pattern.quote(nay));
                    }
                    break;
                    
                case MyanmarDateFormat.YAT:
                    String yat = LanguageTranslator.translate("Yat", language);
                    if (!yat.isEmpty()) {
                        regex.append(Pattern.quote(yat));
                    }
                    break;
                    
                default:
                    // Escape special regex characters in literals
                    // Allow flexible whitespace matching
                    if (c == ' ') {
                        // Use flexible whitespace - zero or more
                        regex.append("\\s*");
                    } else if ("[](){}.*+?^$|\\".indexOf(c) >= 0) {
                        regex.append("\\").append(c);
                    } else {
                        regex.append(c);
                    }
                    break;
            }
        }
        
        return new CompiledPattern(Pattern.compile(regex.toString()), captureGroups);
    }

    /**
     * Extracts components from text using the compiled pattern
     */
    private static ParsedComponents extractComponents(String text, CompiledPattern compiled, Language language) {
        Matcher matcher = compiled.regex.matcher(text);
        

        if (!matcher.find()) {
            throw new IllegalArgumentException(
                "Text does not match pattern. Text: '" + text + "', Language: " + language
            );
        }

        ParsedComponents components = new ParsedComponents();
        int groupIndex = 1;
        
        for (char patternChar : compiled.captureGroups) {
            String captured = matcher.group(groupIndex++);
            
            switch (patternChar) {
                case MyanmarDateFormat.BUDDHIST_ERA:
                    components.buddhistEra = parseNumber(captured, language);
                    break;
                case MyanmarDateFormat.MYANMAR_YEAR:
                    components.myanmarYear = parseNumber(captured, language);
                    break;
                case MyanmarDateFormat.MONTH_IN_YEAR:
                    if (captured != null && !captured.trim().isEmpty()) {
                        components.monthName = captured.trim();
                    }
                    break;
                case MyanmarDateFormat.MOON_PHASE:
                    if (captured != null && !captured.trim().isEmpty()) {
                        components.moonPhaseName = captured.trim();
                    }
                    break;
                case MyanmarDateFormat.FORTNIGHT_DAY:
                    // Fortnight day may be empty for full moon/new moon
                    if (captured != null && !captured.trim().isEmpty()) {
                        components.fortnightDay = parseNumber(captured, language);
                    } else {
                        // Default to 15 for full/new moon when fortnight day is empty
                        components.fortnightDay = 15;
                    }
                    break;
                case MyanmarDateFormat.DAY_NAME_IN_WEEK:
                    if (captured != null && !captured.trim().isEmpty()) {
                        components.weekDayName = captured.trim();
                    }
                    break;
            }
        }

        return components;
    }

    /**
     * Builds MyanmarDate from parsed components using existing factory methods
     */
    private static MyanmarDate buildMyanmarDate(ParsedComponents components, Language language) {
        int myear = components.myanmarYear;
        
        // Translate month name to English using sentence translation for multi-word phrases
        // (e.g., "Second Waso", "Late Tagu")
        String monthNameEn = LanguageTranslator.translateSentence(components.monthName, language, Language.ENGLISH);
        
        // Translate moon phase to English
        String moonPhaseEn = LanguageTranslator.translateSentence(components.moonPhaseName, language, Language.ENGLISH);
        
        int fortnightDay = components.fortnightDay;
        
        // Validate fortnight day
        if (fortnightDay < 1 || fortnightDay > 15) {
            throw new IllegalArgumentException(
                "Fortnight day must be between 1 and 15, got: " + fortnightDay
            );
        }
        
        try {
            // Handle special case: "Second Waso" should be just "Waso" (month 4)
            // "First Waso" is already handled correctly (month 0)
            if (monthNameEn.equalsIgnoreCase("Second Waso")) {
                monthNameEn = "Waso";
            }
            
            // Convert month name to number
            int mmonth = MyanmarDateKernel.searchMyanmarMonthNumber(monthNameEn);
            
            // Convert moon phase name to number
            int moonPhase = MyanmarDateKernel.searchMoonPhase(moonPhaseEn);
            
            // Calculate day of month from moon phase and fortnight day
            int monthDay = MyanmarCalendarKernel.calculateDayOfMonth(myear, mmonth, moonPhase, fortnightDay);
            
            // Create date from year, month, and day of month
            // This ensures the date has the correct moon phase
            MyanmarDate result = MyanmarDate.create(myear, mmonth, monthDay);
            
            // Optional: validate weekday if provided
            if (components.weekDayName != null) {
                String parsedWeekday = components.weekDayName;
                String actualWeekday = result.getWeekDay(language);
                if (!parsedWeekday.equals(actualWeekday)) {
                    // Log warning but don't fail - weekday is just for validation
                    System.err.println("Warning: Parsed weekday '" + parsedWeekday + 
                                     "' doesn't match calculated weekday '" + actualWeekday + "'");
                }
            }
            
            return result;
            
        } catch (Exception e) {
            throw new IllegalArgumentException(
                "Failed to create MyanmarDate with year=" + myear + 
                ", month='" + monthNameEn + "', moonPhase='" + moonPhaseEn + 
                "', fortnightDay=" + fortnightDay + ": " + e.getMessage(), e
            );
        }
    }

    // ========== Helper Methods ==========

    /**
     * Gets regex pattern for numbers in the given language
     */
    private static String getNumberRegex(Language language) {
        if (language == Language.ENGLISH || language == Language.TAI) {
            return "\\d+";
        } else {
            // Myanmar, Zawgyi, Mon, Karen use Myanmar digits
            return "[\\d၀-၉]+";
        }
    }

    /**
     * Parses a number string in the given language
     */
    private static Integer parseNumber(String numberStr, Language language) {
        if (numberStr == null || numberStr.trim().isEmpty()) {
            return null;
        }
        
        numberStr = numberStr.trim();
        
        // For English and Tai, parse directly
        if (language == Language.ENGLISH || language == Language.TAI) {
            try {
                return Integer.parseInt(numberStr);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        
        // For other languages, convert Myanmar digits to Arabic
        StringBuilder arabic = new StringBuilder();
        for (char c : numberStr.toCharArray()) {
            if (c >= '၀' && c <= '၉') {
                arabic.append((char) ('0' + (c - '၀')));
            } else if (c >= '0' && c <= '9') {
                arabic.append(c);
            }
        }
        
        try {
            return Integer.parseInt(arabic.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Builds regex for month names (handles prefixes like First, Second, Late)
     */
    private static String buildMonthRegex(Language language) {
        Set<String> monthPatterns = new LinkedHashSet<>();
        
        // Add prefixes
        String first = LanguageTranslator.translate("First", language);
        String second = LanguageTranslator.translate("Second", language);
        String late = LanguageTranslator.translate("Late", language);
        
        // Standard months
        String[] monthKeys = {"Tagu", "Kason", "Nayon", "Waso", "Wagaung", "Tawthalin",
                             "Thadingyut", "Tazaungmon", "Nadaw", "Pyatho", "Tabodwe", "Tabaung"};
        
        // Add all combinations
        for (String key : monthKeys) {
            String month = LanguageTranslator.translate(key, language);
            
            // Plain month name
            monthPatterns.add(Pattern.quote(month));
            
            // With prefixes (with optional space)
            if (key.equals("Waso")) {
                monthPatterns.add(Pattern.quote(first) + "\\s*" + Pattern.quote(month));
                monthPatterns.add(Pattern.quote(second) + "\\s*" + Pattern.quote(month));
            }
            if (key.equals("Tagu") || key.equals("Kason")) {
                monthPatterns.add(Pattern.quote(late) + "\\s*" + Pattern.quote(month));
            }
        }
        
        // Special late months
        monthPatterns.add(Pattern.quote(LanguageTranslator.translate("Late Tagu", language)));
        monthPatterns.add(Pattern.quote(LanguageTranslator.translate("Late Kason", language)));
        
        // Join with OR, longest first to match greedily
        List<String> sorted = new ArrayList<>(monthPatterns);
        sorted.sort((a, b) -> b.length() - a.length());
        
        return String.join("|", sorted);
    }

    /**
     * Builds regex for moon phase names
     */
    private static String buildMoonPhaseRegex(Language language) {
        String[] phaseKeys = {"Waxing", "Full Moon", "Waning", "New Moon"};
        List<String> phases = new ArrayList<>();
        
        for (String key : phaseKeys) {
            String translated = LanguageTranslator.translate(key, language);
            phases.add(Pattern.quote(translated));
        }
        
        // Longest first
        phases.sort((a, b) -> b.length() - a.length());
        return String.join("|", phases);
    }

    /**
     * Builds regex for weekday names
     */
    private static String buildWeekDayRegex(Language language) {
        String[] weekdayKeys = {"Saturday", "Sunday", "Monday", "Tuesday", 
                               "Wednesday", "Thursday", "Friday"};
        List<String> weekdays = new ArrayList<>();
        
        for (String key : weekdayKeys) {
            String translated = LanguageTranslator.translate(key, language);
            weekdays.add(Pattern.quote(translated));
        }
        
        return String.join("|", weekdays);
    }

    /**
     * Private constructor to prevent instantiation
     */
    private MyanmarDateParser() {
        throw new AssertionError("Utility class - do not instantiate");
    }
}
