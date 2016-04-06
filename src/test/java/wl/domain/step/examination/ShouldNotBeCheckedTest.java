package wl.domain.step.examination;

import org.junit.Test;
import wl.domain.step.Fixtures;

import static org.junit.Assert.assertEquals;

public class ShouldNotBeCheckedTest {
    private final ShouldNotBeChecked shouldNotBeChecked = ShouldNotBeChecked.selector(Fixtures.SELECTOR);

    @Test
    public void description() {
        assertEquals("\"selector\" should not be checked", shouldNotBeChecked.getDescription());
    }
}