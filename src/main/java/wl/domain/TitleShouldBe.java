package wl.domain;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

@Builder
@Data
public class TitleShouldBe implements Step {
    private String expectedTitle;

    @Override
    public void execute(ExecutionContext context) {
        WebDriverWait wait = context.getWait();
        wait.until((WebDriver driver) -> expectedTitle.equals(driver.getTitle()));
        assertEquals("title", expectedTitle, context.getDriver().getTitle());
    }

    @Override
    public String getDescription() {
        return String.format("title should be \"%s\"", expectedTitle);
    }
}
