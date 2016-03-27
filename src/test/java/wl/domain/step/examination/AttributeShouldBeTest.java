package wl.domain.step.examination;

import org.junit.Test;
import wl.domain.step.Fixtures;

import static org.junit.Assert.assertEquals;

public class AttributeShouldBeTest {
    private final AttributeShouldBe attributeShouldBe = AttributeShouldBe.builder()
            .attributeName("attributeName")
            .expectedValue("expectedValue")
            .selector(Fixtures.SELECTOR)
            .build();

    @Test
    public void description() throws Exception {

        assertEquals(
                "attribute \"attributeName\" of \"selector\" should be \"expectedValue\"",
                attributeShouldBe.getDescription()
        );

    }
}