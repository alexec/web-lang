package wl.domain.step.examination;

import lombok.Data;
import lombok.NonNull;
import lombok.val;
import wl.domain.ExecutionContext;
import wl.domain.Selector;
import wl.domain.step.Step;

import static org.junit.Assert.assertTrue;

@Data
public class ShouldBeChecked implements Step {
    @NonNull
    private final Selector selector;

    private ShouldBeChecked(Selector selector) {
        this.selector = selector;
    }

    public static ShouldBeChecked selector(Selector selector) {
        return new ShouldBeChecked(selector);
    }

    @Override
    public void execute(ExecutionContext context) {
        val checked = context.getDriver().findElement(context.by(selector)).isSelected();
        assertTrue(getDescription(), checked);
    }

    @Override
    public String getDescription() {
        return "\"" + selector + "\" should be checked";
    }
}
