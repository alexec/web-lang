package wl.domain;

import lombok.Builder;
import lombok.Data;

import java.net.URI;

@Builder
@Data
public class GoTo implements Step {
    private URI url;

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().get(url.toString());
    }

    @Override
    public String getDescription() {
        return String.format("go to \"%s\"", url);
    }
}
