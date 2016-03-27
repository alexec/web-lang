package wl.domain.step.examination;

import lombok.Data;
import lombok.NonNull;
import wl.domain.ExecutionContext;
import wl.domain.Selector;
import wl.domain.step.Step;

import static org.junit.Assert.assertTrue;

@Data
public class ShouldNotBeChecked implements Step {
    @NonNull
    private final Selector selector;

    private ShouldNotBeChecked(Selector selector) {
        this.selector = selector;
    }

    public static ShouldNotBeChecked selector(Selector selector) {
        return new ShouldNotBeChecked(selector);
    }

    @Override
    public void execute(ExecutionContext context) {
        assertTrue(getDescription(), !context.getDriver().findElement(context.by(selector)).isSelected());
    }

    @Override
    public String getDescription() {
        return "\"" + selector + "\" should not be checked";
    }
}
