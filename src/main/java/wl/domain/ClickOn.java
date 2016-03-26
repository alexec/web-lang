package wl.domain;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.By;

// TODO - remove builders
@Builder
@Data
public class ClickOn implements Step {
    private String selector;

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().findElement(By.cssSelector(selector)).click();
    }

    @Override
    public String getDescription() {
        return String.format("click on \"%s\"", selector);
    }
}
