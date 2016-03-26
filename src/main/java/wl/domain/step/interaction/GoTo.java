package wl.domain.step.interaction;

import lombok.Data;
import lombok.NonNull;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

import java.net.URI;

@Data
public class GoTo implements Step {
    @NonNull
    private final URI url;

    private GoTo(URI url) {
        this.url = url;
    }

    public static GoTo url(URI uri) {
        return new GoTo(uri);
    }

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().get(url.toString());
    }

    @Override
    public String getDescription() {
        return String.format("go to \"%s\"", url);
    }

}
