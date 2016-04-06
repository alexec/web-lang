package wl.domain.step.interaction;

import org.junit.Test;
import wl.domain.step.Fixtures;

import static org.junit.Assert.assertEquals;

public class TypeTest {
    private final Type type = Type.builder().selector(Fixtures.SELECTOR).text("text").build();

    @Test
    public void description() {
        assertEquals("type \"text\" into \"selector\"", type.getDescription());
    }
}