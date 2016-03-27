package wl.domain.step.examination;

import org.junit.Test;
import wl.domain.step.Fixtures;

import static org.junit.Assert.assertEquals;

public class ShouldBeCheckedTest {
    private final ShouldBeChecked shouldBeChecked = ShouldBeChecked.selector(Fixtures.SELECTOR);

    @Test
    public void description() throws Exception {
        assertEquals("\"selector\" should be checked", shouldBeChecked.getDescription());
    }
}