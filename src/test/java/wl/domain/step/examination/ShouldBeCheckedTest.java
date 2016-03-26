package wl.domain.step.examination;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShouldBeCheckedTest {
    private final ShouldBeChecked shouldBeChecked = ShouldBeChecked.selector("selector");

    @Test
    public void description() throws Exception {
        assertEquals("\"selector\" should be checked", shouldBeChecked.getDescription());
    }
}