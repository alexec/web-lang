package wl.domain;

import wl.domain.step.Step;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JourneysBuilder {
    private final List<Journey> journeys = new LinkedList<>();
    private final Map<String, Page> pages = new HashMap<>();
    private Object last;

    public JourneysBuilder addJourney(String name) {
        Journey journey = new Journey(name, pages);
        journeys.add(journey);
        last = journey;
        return this;
    }

    public JourneysBuilder addStep(Step step) {
        if (last instanceof Journey) {
            ((Journey) last).addStep(step);
        } else {
            (lastPage()).addStep(step);
        }
        return this;
    }

    public List<Journey> build() {
        return journeys;
    }

    public JourneysBuilder addPage(String name) {
        Page page = new Page(name);
        pages.put(name, page);
        last = page;
        return this;
    }

    public JourneysBuilder setPageUrl(URI url) {
        lastPage().setUrl(url);
        return this;
    }

    private Page lastPage() {
        return (Page) last;
    }

    public JourneysBuilder addPageElement(String selector, String name) {
        lastPage().addPageElement(selector, name);
        return this;
    }
}
