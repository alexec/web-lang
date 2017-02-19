package wl.domain.step.examination;

import lombok.Data;
import lombok.NonNull;
import lombok.val;
import wl.domain.ExecutionContext;
import wl.domain.Selector;

import static org.junit.Assert.assertFalse;

@Data
public class ShouldNotBeChecked implements ExaminationStep {
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
        val checked = context.getDriver().findElement(context.by(selector)).isSelected();
        assertFalse(getDescription(), checked);
    }

    @Override
    public String getDescription() {
        return "\"" + selector + "\" should not be checked";
    }
}
