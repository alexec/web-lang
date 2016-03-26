package wl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import wl.model.Go;
import wl.model.Journey;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JourneyTest {

    private final JourneyFactory journeyFactory = new JourneyFactory();
    private final String text;
    private final Journey expectedJourney;

    public JourneyTest(String text, Journey expectedJourney) {
        this.text = text;
        this.expectedJourney = expectedJourney;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Journey: Searching on Google\n", Journey.builder().name("Searching on Google").build()},
                {"Journey: Searching on Google\n" +
                        "open https://www.google.co.uk", Journey.builder()
                        .name("Searching on Google")
                        .steps(Collections.singletonList(Go.builder().url(URI.create("http://www.google.co.uk")).build()))
                        .build()}
        });

    }

    @Test
    public void example() throws Exception {
        assertEquals(expectedJourney, journeyFactory.create(text));
    }

}