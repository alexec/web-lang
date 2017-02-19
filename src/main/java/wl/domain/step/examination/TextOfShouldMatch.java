package wl.domain.step.examination;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.val;
import wl.domain.ExecutionContext;
import wl.domain.Selector;
import wl.domain.step.Step;

import static org.junit.Assert.assertEquals;

@Builder
@Data
public class TextOfShouldBe implements Step {
    @NonNull
    private final Selector selector;
    @NonNull
    private final String expectedText;

    private TextOfShouldBe(Selector selector, String expectedText) {
        this.selector = selector;
        this.expectedText = expectedText;
    }

    @Override
    public void execute(ExecutionContext context) {
        val element = context.getDriver().findElement(context.by(selector));
        val value = element.getTagName().equals("input") ? element.getAttribute("value") : element.getText();
        context.retry(() ->  assertEquals(getDescription(), expectedText, value));
    }

    @Override
    public String getDescription() {
        return String.format("text of \"%s\" should be \"%s\"", selector, expectedText);
    }
}
