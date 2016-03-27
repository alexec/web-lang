package wl.domain.step.interaction;

import lombok.Data;
import lombok.NonNull;
import wl.domain.ExecutionContext;
import wl.domain.Page;
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
        Page page = context.getPage(place);
        String url = page != null ? page.getUrl().toString() : place;

        context.getDriver().get(url);

        context.setCurrentPage(page);
    }

    @Override
    public String getDescription() {
        return String.format("go to \"%s\"", place);
    }

}
