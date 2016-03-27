package wl.domain;

import lombok.Data;
import lombok.NonNull;
import wl.domain.step.Step;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Page {
    @NonNull
    private final String name;
    private final Map<String, String> elements = new HashMap<>();
    private final List<Step> steps = new ArrayList<>();
    private URI url;

    void addPageElement(String selector, String name) {
        elements.put(name, selector);
    }

    void addStep(Step step) {
        steps.add(step);
    }
}
