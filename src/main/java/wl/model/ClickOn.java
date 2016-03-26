package wl.model;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Builder
@Data
public class ClickOn implements Step {
    private By selector;

    @Override
    public void execute(WebDriver driver) {
        driver.findElement(selector).click();
    }
}
