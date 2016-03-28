package wl.domain.step.examination;

import lombok.Data;
import lombok.NonNull;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

@Data
public class PageShouldBe implements Step {
    @NonNull
    private final String pageName;

    private PageShouldBe(String pageName) {
        this.pageName = pageName;
    }

    public static PageShouldBe expectedPage(String value) {
        return new PageShouldBe(value);
    }

    @Override
    public void execute(ExecutionContext context) {
        for (Step step : context.getPage(pageName).getSteps()) {
            step.execute(context);
        }
    }

    @Override
    public String getDescription() {
        return String.format("page should be \"%s\"", pageName);
    }
}
