package wl;

import org.junit.Test;
import wl.domain.*;
import wl.infrastructure.JourneyFactory;

import java.net.URI;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class JourneyTest {

    private final Journey journey = new JourneyFactory().create(JourneyTest.class.getResourceAsStream("google/google-search.journey"));

    @Test
    public void example() throws Exception {
        assertEquals(Journey.builder()
                        .name("Searching on Google")
                        .steps(Arrays.asList(
                                GoTo.builder().url(URI.create("http://www.google.com")).build(),
                                ClickOn.selector("input[name=q]"),
                                Type.builder().selector("input[name=q]").text("Cheese!").build(),
                                Submit.INSTANCE,
                                TitleShouldBe.builder().expectedTitle("Cheese! - Google Search").build()
                        ))
                        .build()
                , journey);
    }
}