package wl.domain.step.examination;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TitleShouldBeTest {
    private final TitleShouldBe titleShouldBe = TitleShouldBe.expectedTitle("value");

    @Test
    public void description() {
        assertEquals("title should be \"value\"", titleShouldBe.getDescription());
    }
}