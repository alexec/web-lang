package wl.domain.step.interaction;

import lombok.Data;
import lombok.NonNull;
import lombok.val;
import wl.domain.ExecutionContext;
import wl.domain.Selector;
import wl.domain.step.Step;

@Data
public class Check implements Step {
    @NonNull
    private final Selector selector;

    private Check(Selector selector) {
        this.selector = selector;
    }

    public static Check selector(Selector selector) {
        return new Check(selector);
    }

    @Override
    public void execute(ExecutionContext context) {
        val by = context.by(selector);
        context.repeatUntil(driver -> driver.findElement(by).isSelected(), driver -> driver.findElement(by).click());
    }

    @Override
    public String getDescription() {
        return String.format("check \"%s\"", selector);
    }
}
