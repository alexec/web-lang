
package wl.domain.step.examination;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.val;
import wl.domain.ExecutionContext;
import wl.domain.Selector;
import wl.domain.step.Step;

import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

@Builder
@Data
public class TextOfShouldMatch implements Step {
    @NonNull
    private final Selector selector;
    @NonNull
    private final Pattern expectedText;

    private TextOfShouldMatch(Selector selector, Pattern expectedText) {
        this.selector = selector;
        this.expectedText = expectedText;
    }

    @Override
    public void execute(ExecutionContext context) {
        val element = context.getDriver().findElement(context.by(selector));
        val value = element.getTagName().equals("input") ? element.getAttribute("value") : element.getText();
        assertTrue(getDescription(), expectedText.matcher(value).find());
    }

    @Override
    public String getDescription() {
        return String.format("text of \"%s\" should match \"%s\"", selector, expectedText);
    }
}
