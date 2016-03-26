package wl.domain;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.By;

@Builder
@Data
public class Type implements Step {
    private String text;
    private String selector;

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().findElement(By.cssSelector(selector)).sendKeys(text);
    }

    @Override
    public String getDescription() {
        return String.format("type \"%s\" into \"%s\"", text, selector);
    }
}
