package wl.domain.step;

import wl.domain.ExecutionContext;

public interface Step {
    void execute(ExecutionContext context);

    String getDescription();
}
