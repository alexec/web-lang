package wl.infrastructure;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import wl.domain.Journey;
import wl.domain.Page;
import wl.domain.Selector;
import wl.domain.step.examination.TitleShouldBe;
import wl.domain.step.interaction.ClickOn;
import wl.domain.step.interaction.GoTo;
import wl.domain.step.interaction.Submit;
import wl.domain.step.interaction.Type;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class JourneyFactoryTest {

    private final JourneyFactory journeyFactory = new JourneyFactory();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void goodExample() throws Exception {
        List<Journey> journeys = journeyFactory.create(JourneyFactoryTest.class.getResourceAsStream("example.journey"));

        Map<String, Page> pages = Collections.singletonMap("Search", new Page("Search"));
        Journey expectedJourney = new Journey("Searching on Google", pages);
        List<Journey> expectedJourneys = Collections.singletonList(expectedJourney);
        expectedJourney.getSteps().addAll(Arrays.asList(
                GoTo.place("http://www.google.com"),
                ClickOn.selector(Selector.valueOf("input[name=q]")),
                Type.builder().selector(Selector.valueOf("input[name=q]")).text("Cheese!").build(),
                Submit.INSTANCE,
                TitleShouldBe.expectedTitle("Cheese! - Google Search")
        ));

        assertEquals(expectedJourneys, journeys);
    }

    @Test
    public void badExample() throws Exception {
        expectedException.expect(Exception.class);
        journeyFactory.create(JourneyFactoryTest.class.getResourceAsStream("bad.journey"));
    }

}