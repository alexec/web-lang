package wl.domain;

import java.util.Deque;
import java.util.LinkedList;

public class JourneysBuilder {
    private final Deque<Journey> values = new LinkedList<>();

    public JourneysBuilder addJourney(String name) {
        values.add(new Journey(name));
        return this;
    }

    public JourneysBuilder addStep(Step step) {
        values.getLast().addStep(step);
        return this;
    }

    public Deque<Journey> build() {
        return values;
    }
}
