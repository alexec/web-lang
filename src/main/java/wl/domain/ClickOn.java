package wl.domain;

import lombok.EqualsAndHashCode;
import org.openqa.selenium.By;


@EqualsAndHashCode
public class ClickOn implements Step {
    private final String selector;

    private ClickOn(String selector) {
        this.selector = selector;
    }

    public static Step selector(String selector) {
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
