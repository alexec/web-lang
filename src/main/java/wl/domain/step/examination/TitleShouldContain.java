package wl.domain.step.examination;

import lombok.Data;
import lombok.NonNull;
import lombok.val;
import org.openqa.selenium.WebDriver;
import wl.domain.ExecutionContext;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

@Data
public class TitleShouldContain implements ExaminationStep {
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
        context.waitUntil((WebDriver driver) -> driver.getTitle().contains(expectedTitle));
        val value = context.getDriver().getTitle();
        assertThat(descriptionOfMismatch(value), value, containsString(expectedTitle));
    }

    @Override
    public String getDescription() {
        return String.format("title should contain \"%s\"", expectedTitle);
    }
}
