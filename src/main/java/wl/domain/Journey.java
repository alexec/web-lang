package wl.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Journey {
    private String name;
    private List<Step> steps = new ArrayList<>();

    public static JourneyBuilder builder() {
        return new JourneyBuilder();
    }

    public static class JourneyBuilder {
        private String name;
        private List<Step> steps = new ArrayList<>();

        public Journey build() {
            Journey journey = new Journey();
            journey.setName(name);
            journey.setSteps(steps);
            return journey;
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
