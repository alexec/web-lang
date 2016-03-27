package wl.domain;

import lombok.Data;
import lombok.NonNull;
import wl.domain.step.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Journey {
    @NonNull
    private final List<Step> steps = new ArrayList<>();
    @NonNull
    private final String name;
    @NonNull
    private final Map<String, Page> pages;

    public String getName() {
        return name;
    }

    public List<Step> getSteps() {
        return steps;
    }

    boolean addStep(Step step) {
        return steps.add(step);
    }

    public Map<String, Page> getPages() {
        return pages;
    }
}
