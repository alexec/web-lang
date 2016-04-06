package wl.domain.step.interaction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwitchToFrameByNameTest {
    private final SwitchToFrameByName switchToFrameByName = SwitchToFrameByName.name("name");

    @Test
    public void description() {
        assertEquals("switch to frame \"name\"", switchToFrameByName.getDescription());
    }
}