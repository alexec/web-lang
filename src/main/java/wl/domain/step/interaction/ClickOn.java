package wl.domain.step.interaction;

import lombok.Data;
import lombok.NonNull;
import wl.domain.ExecutionContext;
import wl.domain.Selector;
import wl.domain.step.Step;


@Data
public class ClickOn implements Step {
    @NonNull
    private final Selector selector;

    private ClickOn(Selector selector) {
        this.selector = selector;
    }

    public static ClickOn selector(Selector selector) {
        return new ClickOn(selector);
    }

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().findElement(context.by(selector)).click();
    }

    @Override
    public String getDescription() {
        return String.format("click on \"%s\"", selector);
    }

}
