package wl.domain.step.interaction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwitchToDefaultContentTest {
    private final SwitchToDefaultContent switchToDefaultContent = SwitchToDefaultContent.INSTANCE;

    @Test
    public void description() throws Exception {
        assertEquals("switch to default content", switchToDefaultContent.getDescription());
    }
}