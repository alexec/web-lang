package wl.infrastructure;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import wl.domain.Journey;
import wl.domain.step.examination.TitleShouldBe;
import wl.domain.step.interaction.ClickOn;
import wl.domain.step.interaction.GoTo;
import wl.domain.step.interaction.Submit;
import wl.domain.step.interaction.Type;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class JourneyFactoryTest {

    private final JourneyFactory journeyFactory = new JourneyFactory();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void goodExample() throws Exception {
        List<Journey> journeys = journeyFactory.create(JourneyFactoryTest.class.getResourceAsStream("google-search.journey"));

        Journey expectedJourney = new Journey("Searching on Google");
        List<Journey> expectedJourneys = Collections.singletonList(expectedJourney);
        expectedJourney.getSteps().addAll(Arrays.asList(
                GoTo.url(URI.create("http://www.google.com")),
                ClickOn.selector("input[name=q]"),
                Type.builder().selector("input[name=q]").text("Cheese!").build(),
                Submit.INSTANCE,
                TitleShouldBe.expectedTitle("Cheese! - Google Search")
        ));

        assertEquals(expectedJourneys, journeys);
    }

    @Test
    public void badExample() throws Exception {
        expectedException.expect(IllegalStateException.class);
        journeyFactory.create(JourneyFactoryTest.class.getResourceAsStream("bad.journey"));
    }

}