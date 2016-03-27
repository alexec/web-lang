package wl.domain.step.examination;

import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.WebDriver;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

@Data
public class TitleShouldContain implements Step {
    @NonNull
    private final String expectedTitle;

    private TitleShouldContain(String expectedTitle) {
        this.expectedTitle = expectedTitle;
    }

    public static TitleShouldContain expectedTitle(String value) {
        return new TitleShouldContain(value);
    }

    @Override
    public void execute(ExecutionContext context) {
        context.waitUntil((WebDriver driver) -> expectedTitle.contains(driver.getTitle()));
        assertThat(getDescription(), context.getDriver().getTitle(), containsString(expectedTitle));
    }

    @Override
    public String getDescription() {
        return String.format("title should contain \"%s\"", expectedTitle);
    }
}
