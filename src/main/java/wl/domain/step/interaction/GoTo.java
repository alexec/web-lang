package wl.domain.step.interaction;

import lombok.Data;
import lombok.NonNull;
import lombok.val;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

@Data
public class GoTo implements Step {
    @NonNull
    private final String place;

    private GoTo(String place) {
        this.place = place;
    }

    public static GoTo place(String place) {
        return new GoTo(place);
    }

    @Override
    public void execute(ExecutionContext context) {
        val page = context.getPage(place);
        val url = page != null ? page.getUrl().toString() : place;
        val relativeUrl = !url.startsWith("http");

        context.getDriver().get(relativeUrl ? System.getProperty("wl.root") + url : url);

        context.setCurrentPage(page);
    }

    @Override
    public String getDescription() {
        return String.format("go to \"%s\"", place);
    }

}
