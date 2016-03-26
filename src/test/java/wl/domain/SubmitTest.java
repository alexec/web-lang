package wl.domain;

import org.junit.Test;
import wl.domain.step.interaction.Submit;

import static org.junit.Assert.assertEquals;

public class SubmitTest {
    private final Submit submit = Submit.INSTANCE;

    @Test
    public void description() throws Exception {
        assertEquals("submit", submit.getDescription());
    }
}