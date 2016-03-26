package wl.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClickOnTest {
    private final ClickOn clickOn = ClickOn.selector("selector");

    @Test
    public void description() throws Exception {
        assertEquals("click on \"selector\"", clickOn.getDescription());
    }

}