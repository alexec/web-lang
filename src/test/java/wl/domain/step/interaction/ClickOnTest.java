package wl.domain.step.interaction;

import org.junit.Test;
import wl.domain.step.Fixtures;

import static org.junit.Assert.assertEquals;

public class ClickOnTest {
    private final ClickOn clickOn = ClickOn.selector(Fixtures.SELECTOR);

    @Test
    public void description() throws Exception {
        assertEquals("click on \"selector\"", clickOn.getDescription());
    }

}