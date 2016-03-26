package wl.domain.step.interaction;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class Select implements Step {
    @NonNull
    private final String selector;
    @NonNull
    private final List<String> values;

    private Select(String selector, List<String> values) {
        this.selector = selector;
        this.values = values;
    }

    @Override
    public void execute(ExecutionContext context) {
        WebElement element = context.getDriver().findElement(By.cssSelector(selector));

        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(element);

        values.forEach(select::selectByValue);
    }

    @Override
    public String getDescription() {
        String valuesAsString = values.stream().map(s -> String.format("\"%s\"", s)).collect(Collectors.joining(","));
        return String.format("select %s in \"%s\"", valuesAsString, selector);
    }
}
