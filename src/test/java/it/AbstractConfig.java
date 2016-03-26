package it;

import org.openqa.selenium.WebDriver;
import wl.api.AfterJourney;
import wl.api.BeforeJourney;
import wl.domain.Journey;

abstract class AbstractConfig {
    abstract WebDriver webDriver();

    @BeforeJourney
    public void beforeJourney(Journey journey) {
        System.out.printf("journey \"%s\" starting%n", journey.getName());
    }

    @AfterJourney
    public void afterJourney(Journey journey) {
        System.out.printf("journey \"%s\" over%n", journey.getName());
    }
}
