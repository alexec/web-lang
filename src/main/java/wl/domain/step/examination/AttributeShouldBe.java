package wl.domain.step.examination;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.By;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

import static org.junit.Assert.assertEquals;

@Builder
@Data
public class AttributeShouldBe implements Step {
    @NonNull
    private final String expectedValue, selector, attributeName;

    @Override
    public void execute(ExecutionContext context) {
        assertEquals(getDescription(), expectedValue, context.getDriver().findElement(By.cssSelector(selector)).getAttribute(attributeName));
    }

    @Override
    public String getDescription() {
        return "attribute \"" + attributeName + "\" of \"" + selector + "\" should be \"" + expectedValue + "\"";
    }
}
