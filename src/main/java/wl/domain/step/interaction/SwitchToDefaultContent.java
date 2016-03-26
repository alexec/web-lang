package wl.domain.step.interaction;

import wl.domain.ExecutionContext;
import wl.domain.step.Step;

public enum SwitchToDefaultContent implements Step {
    INSTANCE;

    @Override
    public void execute(ExecutionContext executionContext) {
        executionContext.getDriver().switchTo().defaultContent();
    }

    @Override
    public String getDescription() {
        return "switch to default content";
    }
}
