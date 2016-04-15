package wl.domain.step.interaction;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.val;
import wl.domain.ExecutionContext;
import wl.domain.Selector;
import wl.domain.step.Step;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class Select implements Step {
    @NonNull
    private final Selector selector;
    @NonNull
    private final List<String> values;

    private Select(Selector selector, List<String> values) {
        this.selector = selector;
        this.values = values;
    }

    @Override
    public void execute(ExecutionContext context) {
        val element = context.getDriver().findElement(context.by(selector));

        val select = new org.openqa.selenium.support.ui.Select(element);

        values.forEach(select::selectByValue);
    }

    @Override
    public String getDescription() {
        String valuesAsString = values.stream().map(s -> String.format("\"%s\"", s)).collect(Collectors.joining(","));
        return String.format("select %s in \"%s\"", valuesAsString, selector);
    }
}
