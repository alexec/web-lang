package wl.domain.step.interaction;

import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.By;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

@Data
public class Uncheck implements Step {
    @NonNull
    private final String selector;

    private Uncheck(String selector) {
        this.selector = selector;
    }

    public static Uncheck selector(String selector) {
        return new Uncheck(selector);
    }

    @Override
    public void execute(ExecutionContext context) {
        By by = By.cssSelector(selector);
        context.waitUntil(driver -> !driver.findElement(by).isSelected(), driver -> driver.findElement(by).click());
    }

    @Override
    public String getDescription() {
        return String.format("un-check \"%s\"", selector);
    }
}
