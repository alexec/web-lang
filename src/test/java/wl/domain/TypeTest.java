package wl.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeTest {
    private final Type type = Type.builder().selector("selector").text("text").build();

    @Test
    public void description() throws Exception {
        assertEquals("type \"text\" into \"selector\"", type.getDescription());
    }
}