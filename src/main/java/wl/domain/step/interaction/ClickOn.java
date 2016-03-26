package wl.domain.step.interaction;

import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.By;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;


@Data
public class ClickOn implements Step {
    @NonNull
    private final String selector;

    private ClickOn(String selector) {
        this.selector = selector;
    }

    public static ClickOn selector(String selector) {
        return new ClickOn(selector);
    }

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().findElement(By.cssSelector(selector)).click();
    }

    @Override
    public String getDescription() {
        return String.format("click on \"%s\"", selector);
    }

}
