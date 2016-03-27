package wl.domain.step.interaction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoToTest {
    private final GoTo goTo = GoTo.place("http://dummy");

    @Test
    public void description() throws Exception {
        assertEquals("go to \"http://dummy\"", goTo.getDescription());
    }

}