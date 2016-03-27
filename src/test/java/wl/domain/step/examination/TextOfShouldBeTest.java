package wl.domain.step.examination;

import org.junit.Test;
import wl.domain.step.Fixtures;

import static org.junit.Assert.assertEquals;

public class TextOfShouldBeTest {
    private final TextOfShouldBe textOfShouldBe = TextOfShouldBe.builder().selector(Fixtures.SELECTOR).expectedText("text").build();

    @Test
    public void description() throws Exception {
        assertEquals("text of \"selector\" should be \"text\"", textOfShouldBe.getDescription());
    }
}