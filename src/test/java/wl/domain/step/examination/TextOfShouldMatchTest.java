package wl.domain.step.examination;

import org.junit.Test;
import wl.domain.step.Fixtures;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TextOfShouldMatchTest {
    private final TextOfShouldMatch textOfShouldMatch = TextOfShouldMatch.builder().selector(Fixtures.SELECTOR).expectedText(Pattern.compile("text")).build();

    @Test
    public void description() {
        assertEquals("text of \"selector\" should match \"text\"", textOfShouldMatch.getDescription());
    }
}