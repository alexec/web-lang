package wl.domain.step.interaction;

import wl.domain.ExecutionContext;
import wl.domain.step.Step;

public enum DismissAlert implements Step {
    INSTANCE {
        @Override
        public void execute(ExecutionContext context) {
            context.getDriver().switchTo().alert().dismiss();
        }

        @Override
        public String getDescription() {
            return "dismiss alert";
        }
    }
}
