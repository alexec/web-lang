package wl.model;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Builder
@Data
public class Type implements Step {
    private String text;
    private String selector;

    @Override
    public void execute(WebDriver driver) {
        driver.findElement(By.cssSelector(selector)).sendKeys(text);
    }

    @Override
    public String getDescription() {
        return String.format("type \"%s\" into \"%s\"", text, selector);
    }
}
