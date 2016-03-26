package wl.domain.step.examination;

import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.By;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

import static org.junit.Assert.assertTrue;

@Data
public class ShouldNotBeChecked implements Step {
    @NonNull
    private final String selector;

    private ShouldNotBeChecked(String selector) {
        this.selector = selector;
    }

    public static ShouldNotBeChecked selector(String selector) {
        return new ShouldNotBeChecked(selector);
    }

    @Override
    public void execute(ExecutionContext context) {
        assertTrue(getDescription(), !context.getDriver().findElement(By.cssSelector(selector)).isSelected());
    }

    @Override
    public String getDescription() {
        return "\"" + selector + "\" should not be checked";
    }
}
