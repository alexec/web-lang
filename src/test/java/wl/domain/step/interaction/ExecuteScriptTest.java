package wl.domain.step.interaction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExecuteScriptTest {
    private final ExecuteScript executeScript = ExecuteScript.script("script");

    @Test
    public void description() throws Exception {
        assertEquals("execute script \"script\"", executeScript.getDescription());
    }
}