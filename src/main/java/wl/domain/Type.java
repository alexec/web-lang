package wl.domain;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.By;

@Data
@Builder
public class Type implements Step {
    private final String text;
    private final String selector;

    private Type(String text, String selector) {
        this.text = text;
        this.selector = selector;
    }

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().findElement(By.cssSelector(selector)).sendKeys(text);
    }

    @Override
    public String getDescription() {
        return String.format("type \"%s\" into \"%s\"", text, selector);
    }
}
