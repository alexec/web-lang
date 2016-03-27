package wl.domain.step.interaction;

import org.junit.Test;
import wl.domain.step.Fixtures;

import static org.junit.Assert.assertEquals;

public class UncheckTest {
    private final Uncheck uncheck = Uncheck.selector(Fixtures.SELECTOR);

    @Test
    public void description() throws Exception {
        assertEquals("un-check \"selector\"", uncheck.getDescription());
    }
}