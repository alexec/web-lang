package wl.domain;

import lombok.Data;

import java.net.URI;

@Data
public class GoTo implements Step {
    private final URI url;

    private GoTo(URI url) {
        this.url = url;
    }

    @Override
    public void execute(ExecutionContext context) {
        context.getDriver().get(url.toString());
    }

    @Override
    public String getDescription() {
        return String.format("go to \"%s\"", url);
    }

    public static GoTo url(URI uri) {
        return new GoTo(uri);
    }

}
