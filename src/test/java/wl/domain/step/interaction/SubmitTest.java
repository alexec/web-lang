package wl.domain.step.interaction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubmitTest {
    private final Submit submit = Submit.INSTANCE;

    @Test
    public void description() throws Exception {
        assertEquals("submit", submit.getDescription());
    }
}