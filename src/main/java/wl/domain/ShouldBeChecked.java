package wl.domain;

import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

@Data
public class ShouldBeChecked implements Step {
    @NonNull
    private final String selector;

    private ShouldBeChecked(String selector) {
        this.selector = selector;
    }

    public static ShouldBeChecked selector(String selector) {
        return new ShouldBeChecked(selector);
    }

    @Override
    public void execute(ExecutionContext context) {
        assertTrue(getDescription(), context.getDriver().findElement(By.cssSelector(selector)).isSelected());
    }

    @Override
    public String getDescription() {
        return "\"" + selector + "\" should be checked";
    }
}
