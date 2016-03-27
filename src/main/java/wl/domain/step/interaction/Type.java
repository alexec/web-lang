package wl.domain.step.interaction;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import wl.domain.ExecutionContext;
import wl.domain.Selector;
import wl.domain.step.Step;

@Data
@Builder
public class Type implements Step {
    @NonNull
    private final String text;
    @NonNull
    private final Selector selector;

    private Type(String text, Selector selector) {
        this.text = text;
        this.selector = selector;
    }

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().findElement(context.by(selector)).sendKeys(text);
    }

    @Override
    public String getDescription() {
        return String.format("type \"%s\" into \"%s\"", text, selector);
    }
}
