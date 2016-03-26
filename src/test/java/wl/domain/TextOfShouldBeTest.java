package wl.domain;

import org.junit.Test;
import wl.domain.step.examination.TextOfShouldBe;

import static org.junit.Assert.assertEquals;

public class TextOfShouldBeTest {
    private final TextOfShouldBe textOfShouldBe = TextOfShouldBe.builder().selector("selector").expectedText("text").build();

    @Test
    public void description() throws Exception {
        assertEquals("text of \"selector\" should be \"text\"", textOfShouldBe.getDescription());
    }
}