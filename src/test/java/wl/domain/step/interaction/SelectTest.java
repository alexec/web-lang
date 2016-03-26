package wl.domain.step.interaction;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SelectTest {
    private final Select select = Select.builder()
            .selector("selector")
            .values(Collections.singletonList("value"))
            .build();

    @Test
    public void description() throws Exception {
        assertEquals("select \"value\" in \"selector\"", select.getDescription());
    }
}