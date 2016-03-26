package wl.domain.step.examination;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

import static org.junit.Assert.assertEquals;

@Builder
@Data
public class TextOfShouldBe implements Step {
    @NonNull
    private final String selector;
    @NonNull
    private final String expectedText;

    private TextOfShouldBe(String selector, String expectedText) {
        this.selector = selector;
        this.expectedText = expectedText;
    }

    @Override
    public void execute(ExecutionContext context) {
        WebElement element = context.getDriver().findElement(By.cssSelector(selector));
        assertEquals(getDescription(), expectedText, element.getTagName().equals("input") ? element.getAttribute("value") : element.getText());
    }

    @Override
    public String getDescription() {
        return String.format("text of \"%s\" should be \"%s\"", selector, expectedText);
    }
}
