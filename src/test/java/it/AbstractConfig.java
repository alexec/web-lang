package it;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import wl.api.AfterJourney;
import wl.api.BeforeJourney;
import wl.domain.Journey;

@Slf4j
abstract class AbstractConfig {
    abstract WebDriver webDriver();

    @BeforeJourney
    public void beforeJourney(Journey journey) {
        log.info("journey \"%s\" starting%n", journey.getName());
    }

    @AfterJourney
    public void afterJourney(Journey journey) {
        log.info("journey \"%s\" over%n", journey.getName());
    }
}
