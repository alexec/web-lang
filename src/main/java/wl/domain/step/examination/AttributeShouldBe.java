package wl.domain.step.examination;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import wl.domain.ExecutionContext;
import wl.domain.Selector;
import wl.domain.step.Step;

import static org.junit.Assert.assertEquals;

@Builder
@Data
public class AttributeShouldBe implements Step {
    @NonNull
    private final String expectedValue, attributeName;
    private final Selector selector;

    @Override
    public void execute(ExecutionContext context) {
        String value = context.getDriver().findElement(context.by(selector)).getAttribute(attributeName);
        assertEquals(getDescription(), expectedValue, value);
    }

    @Override
    public String getDescription() {
        return "attribute \"" + attributeName + "\" of \"" + selector + "\" should be \"" + expectedValue + "\"";
    }
}
