package wl.domain.step.interaction;

import wl.domain.ExecutionContext;
import wl.domain.step.Step;

public enum Submit implements Step {
    INSTANCE;

    @Override
    public void execute(ExecutionContext executionContext) {
        executionContext.getDriver().findElement(executionContext.getLastBy()).submit();
    }

    @Override
    public String getDescription() {
        return "submit";
    }
}
