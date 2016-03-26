package wl.domain.step.interaction;

import lombok.Data;
import lombok.NonNull;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

@Data
public class SwitchToFrameByName implements Step {
    @NonNull
    private final String name;

    public static SwitchToFrameByName name(String name) {
        return new SwitchToFrameByName(name);
    }

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().switchTo().frame(name);
    }

    @Override
    public String getDescription() {
        return "switch to frame \"" + name + "\"";
    }
}
