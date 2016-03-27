package wl.domain.step.examination;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TitleShouldContainTest {
    private final TitleShouldContain titleShouldContain = TitleShouldContain.expectedTitle("value");

    @Test
    public void description() throws Exception {
        assertEquals("title should contain \"value\"", titleShouldContain.getDescription());
    }
}