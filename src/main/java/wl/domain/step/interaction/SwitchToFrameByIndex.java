package wl.domain.step.interaction;

import lombok.Data;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

@Data
public class SwitchToFrameByIndex implements Step {
    private final int index;

    public static SwitchToFrameByIndex index(int index) {
        return new SwitchToFrameByIndex(index);
    }

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().switchTo().frame(index);
    }

    @Override
    public String getDescription() {
        return "switch to frame " + index;
    }
}
