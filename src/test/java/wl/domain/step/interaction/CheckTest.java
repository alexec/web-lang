package wl.domain.step.interaction;

import org.junit.Test;
import wl.domain.step.Fixtures;

import static org.junit.Assert.assertEquals;

public class CheckTest {
    private final Check check = Check.selector(Fixtures.SELECTOR);

    @Test
    public void description() {
        assertEquals("check \"selector\"", check.getDescription());
    }
}