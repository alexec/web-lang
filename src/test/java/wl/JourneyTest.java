package wl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import wl.model.GoTo;
import wl.model.Journey;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JourneyTest {

    private final Journey expectedJourney;
    private final Journey journey;

    public JourneyTest(String text, Journey expectedJourney) {
        this.expectedJourney = expectedJourney;
        journey = new JourneyFactory().create(text);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Journey: Searching on Google\n", Journey.builder().name("Searching on Google").build()},
                {"Journey: Searching on Google\n" +
                        "go to http://www.google.com\n", Journey.builder()
                        .name("Searching on Google")
                        .steps(Collections.singletonList(GoTo.builder().url(URI.create("http://www.google.com")).build()))
                        .build()}
        });

    }

    @Test
    public void example() throws Exception {
        assertEquals(expectedJourney, journey);
    }

    @Test
    public void execute() throws Exception {

        journey.execute();
    }
}