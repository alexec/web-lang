package wl;

import org.junit.Test;
import wl.model.GoTo;
import wl.model.Journey;

import java.net.URI;
import java.util.Collections;

import static org.junit.Assert.assertEquals;


public class JourneyTest {

    private final Journey journey = new JourneyFactory().create(JourneyTest.class.getResourceAsStream("/example.journey"));


    @Test
    public void example() throws Exception {
        assertEquals(Journey.builder()
                        .name("Searching on Google")
                        .steps(Collections.singletonList(GoTo.builder().url(URI.create("http://www.google.com")).build()))
                        .build()
                , journey);
    }

    @Test
    public void execute() throws Exception {

        journey.execute();
    }
}