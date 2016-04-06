package wl.domain.step.interaction;

import org.junit.Test;
import wl.domain.step.Fixtures;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SelectTest {
    private final Select select = Select.builder()
            .selector(Fixtures.SELECTOR)
            .values(Collections.singletonList("value"))
            .build();

    @Test
    public void description() {
        assertEquals("select \"value\" in \"selector\"", select.getDescription());
    }
}