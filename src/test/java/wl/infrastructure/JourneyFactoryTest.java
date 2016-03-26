package wl.infrastructure;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import wl.domain.*;

import java.net.URI;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class JourneyFactoryTest {

    private final JourneyFactory journeyFactory = new JourneyFactory();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void goodExample() throws Exception {
        Journey journey = journeyFactory.create(JourneyFactoryTest.class.getResourceAsStream("google-search.journey"));
        assertEquals(Journey.builder()
                        .name("Searching on Google")
                        .steps(Arrays.asList(
                                GoTo.url(URI.create("http://www.google.com")),
                                ClickOn.selector("input[name=q]"),
                                Type.builder().selector("input[name=q]").text("Cheese!").build(),
                                Submit.INSTANCE,
                                TitleShouldBe.expectedTitle("Cheese! - Google Search")
                        ))
                        .build()
                , journey);
    }

    @Test
    public void badExample() throws Exception {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Invalid input 'r...'");
        journeyFactory.create(JourneyFactoryTest.class.getResourceAsStream("bad.journey"));
    }

}