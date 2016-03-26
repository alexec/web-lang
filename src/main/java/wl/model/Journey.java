package wl.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Journey {
    private String name;
    private List<Step> steps = new ArrayList<>();

    public static JourneyBuilder builder() {
        return new JourneyBuilder();
    }

    public void execute() {
        log.info("» Journey \"{}\"", name);
        WebDriver driver = new HtmlUnitDriver();
        try {
            for (Step step : steps) {
                step.execute(driver);
            }
            log.info("» Success");
        } catch (Exception e) {
            log.error("» Error", e);
        } catch (AssertionError e) {
            log.warn("» Failed: {}", e.getMessage());
        } finally {
            driver.quit();
            log.info("» Finished");
        }
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
