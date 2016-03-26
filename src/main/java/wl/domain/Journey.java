package wl.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Journey {
    private final String name;
    private final List<Step> steps;

    public Journey(String name, List<Step> steps) {
        this.name = name;
        this.steps = steps;
    }

    public static JourneyBuilder builder() {
        return new JourneyBuilder();
    }

    public String getName() {
        return name;
    }
    public List<Step> getSteps() {
        return steps;
    }

    public static class JourneyBuilder {
        private String name;
        private List<Step> steps = new ArrayList<>();

        public Journey build() {
            return new Journey(name, steps);
        }

        public JourneyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public JourneyBuilder step(Step step) {
            this.steps.add(step);
            return this;
        }

        public JourneyBuilder steps(List<Step> steps) {
            this.steps = steps;
            return this;
        }
    }
}
