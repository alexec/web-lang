package wl.domain;

import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.By;

@Data
public class Check implements Step {
    @NonNull
    private final String selector;

    private Check(String selector) {
        this.selector = selector;
    }

    public static Check selector(String selector) {
        return new Check(selector);
    }

    @Override
    public void execute(ExecutionContext context) {
        By by = By.cssSelector(selector);
        context.waitUntil(driver -> driver.findElement(by).isSelected(), driver -> driver.findElement(by).click());
    }

    @Override
    public String getDescription() {
        return String.format("check \"%s\"", selector);
    }
}
