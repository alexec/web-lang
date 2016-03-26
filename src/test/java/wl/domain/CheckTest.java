package wl.domain;

import org.junit.Test;
import wl.domain.step.interaction.Check;

import static org.junit.Assert.assertEquals;

public class CheckTest {
    private final Check check = Check.selector("selector");

    @Test
    public void description() throws Exception {
        assertEquals("check \"selector\"", check.getDescription());
    }
}