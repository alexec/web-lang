package wl.domain.step.examination;

import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.WebDriver;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

import static org.junit.Assert.assertEquals;

@Data
public class TitleShouldBe implements Step {
    @NonNull
    private final String expectedTitle;

    private TitleShouldBe(String expectedTitle) {
        this.expectedTitle = expectedTitle;
    }

    public static TitleShouldBe expectedTitle(String value) {
        return new TitleShouldBe(value);
    }

    @Override
    public void execute(ExecutionContext context) {
        context.waitUntil((WebDriver driver) -> driver.getTitle().equals(expectedTitle));
        String title = context.getDriver().getTitle();
        assertEquals(getDescription(), expectedTitle, title);
    }

    @Override
    public String getDescription() {
        return String.format("title should be \"%s\"", expectedTitle);
    }
}
