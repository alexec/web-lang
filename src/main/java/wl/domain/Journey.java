package wl.domain;

import lombok.Data;
import lombok.NonNull;
import wl.domain.step.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class Journey {
    private static final Journey EMPTY = new Journey("Empty Journey", Collections.emptyMap());
    private static final Background DEFAULT_BACKGROUND = new Background(Journey.EMPTY);
    @NonNull
    private final List<Step> steps = new ArrayList<>();
    @NonNull
    private final String name;
    @NonNull
    private final Map<String, Page> pages;
    private Background background = DEFAULT_BACKGROUND;

    public String getName() {
        return name;
    }

    public List<Step> getSteps() {
        return steps;
    }

    void addStep(Step step) {
        steps.add(step);
    }

    public Map<String, Page> getPages() {
        return pages;
    }
}
