package wl.domain.step.interaction;

import lombok.Data;
import lombok.NonNull;
import lombok.val;
import org.openqa.selenium.JavascriptExecutor;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

@Data
public class ExecuteScript implements Step {
    @NonNull
    private final String script;

    public static ExecuteScript script(String script) {
        return new ExecuteScript(script);
    }

    @Override
    public void execute(ExecutionContext context) {
        val executor = JavascriptExecutor.class.cast(context.getDriver());

        executor.executeScript(script);
    }

    @Override
    public String getDescription() {
        return String.format("execute script \"%s\"", script);
    }
}
