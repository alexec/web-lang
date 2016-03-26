package wl.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Journey {
    private String name;
    private List<Object> steps = new ArrayList<>();

    public static JourneyBuilder builder() {
        return new JourneyBuilder();
    }

    public static class JourneyBuilder {
        private String name;
        private List<Object> steps = new ArrayList<>();

        public Journey build() {
            Journey journey = new Journey();
            journey.setName(name);
            return journey;
        }

        public JourneyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public JourneyBuilder step(Object step) {
            this.steps.add(step);
            return this;
        }

        public JourneyBuilder steps(List<Object> steps) {
            this.steps = steps;
            return this;
        }
    }
}
