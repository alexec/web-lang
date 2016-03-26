package wl.domain.step.interaction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwitchToFrameByIndexTest {
    private final SwitchToFrameByIndex switchToFrameByIndex = SwitchToFrameByIndex.index(1);

    @Test
    public void description() throws Exception {
        assertEquals("switch to frame 1", switchToFrameByIndex.getDescription());
    }
}