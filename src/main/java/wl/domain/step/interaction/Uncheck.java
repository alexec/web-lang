package wl.domain.step.interaction;

import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.By;
import wl.domain.ExecutionContext;
import wl.domain.Selector;
import wl.domain.step.Step;

@Data
public class Uncheck implements Step {
    @NonNull
    private final Selector selector;

    private Uncheck(Selector selector) {
        this.selector = selector;
    }

    public static Uncheck selector(Selector selector) {
        return new Uncheck(selector);
    }

    @Override
    public void execute(ExecutionContext context) {
        By by = context.by(selector);
        context.repeatUntil(driver -> !driver.findElement(by).isSelected(), driver -> driver.findElement(by).click());
    }

    @Override
    public String getDescription() {
        return String.format("un-check \"%s\"", selector);
    }
}
