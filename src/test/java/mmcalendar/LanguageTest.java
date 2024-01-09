package mmcalendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LanguageTest {

    @Test
    public void testLanguage() {
        Language language = Language.MYANMAR;

        assertEquals(1, language.getLanguageIndex());
        assertEquals("၊ ", language.getPunctuationMark());
        assertEquals("။ ", language.getPunctuation());
    }
}
