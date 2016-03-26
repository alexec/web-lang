package wl.domain;

import lombok.Data;
import lombok.NonNull;
import wl.domain.step.Step;

import java.util.ArrayList;
import java.util.List;

@Data
public class Journey {
    @NonNull
    private final List<Step> steps = new ArrayList<>();
    @NonNull
    private final String name;

    public Journey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Step> getSteps() {
        return steps;
    }

    boolean addStep(Step step) {
        return steps.add(step);
    }
}
