package wl.domain.step.examination;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PageShouldBeTest {
    private final PageShouldBe pageShouldBe = PageShouldBe.expectedPage("pageName");

    @Test
    public void description() {
        assertEquals("page should be \"pageName\"", pageShouldBe.getDescription());

    }
}