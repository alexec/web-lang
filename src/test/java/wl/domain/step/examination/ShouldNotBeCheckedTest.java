package wl.domain.step.examination;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShouldNotBeCheckedTest {
    private final ShouldNotBeChecked shouldNotBeChecked = ShouldNotBeChecked.selector("selector");

    @Test
    public void description() throws Exception {
        assertEquals("\"selector\" should not be checked", shouldNotBeChecked.getDescription());
    }
}