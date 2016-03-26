package wl.domain;

import org.junit.Test;
import wl.domain.step.interaction.GoTo;

import java.net.URI;

import static org.junit.Assert.assertEquals;

public class GoToTest {
    private final String url = "http://dummy";
    private final GoTo goTo = GoTo.url(URI.create(url));

    @Test
    public void description() throws Exception {
        assertEquals("go to \"http://dummy\"", goTo.getDescription());
    }

}