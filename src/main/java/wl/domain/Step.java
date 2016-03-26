package wl.domain;

public interface Step {
    void execute(ExecutionContext context);

    String getDescription();
}
