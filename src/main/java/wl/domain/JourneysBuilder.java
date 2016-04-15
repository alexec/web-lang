package wl.domain;

import lombok.val;
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
            lastJourney().addStep(step);
        } else {
            lastPage().addStep(step);
        }
        return this;
    }

    private Journey lastJourney() {
        return (Journey) last;
    }

    public List<Journey> build() {
        return journeys;
    }

    public JourneysBuilder addPage(String name) {
        val page = new Page(name);
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

    public JourneysBuilder setBackground(String journeyName) {
        val journey = journeys.stream().filter(j -> j.getName().equals(journeyName)).findFirst();
        if (!journey.isPresent()) {
            throw new IllegalStateException(String.format("cannot find journey name \"%s\"", journeyName));
        }
        lastJourney().setBackground(new Background(journey.get()));
        return this;
    }
}
