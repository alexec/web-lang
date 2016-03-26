package wl.domain;

import lombok.Data;
import lombok.NonNull;

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

    public boolean addStep(Step step) {
        return steps.add(step);
    }
}
