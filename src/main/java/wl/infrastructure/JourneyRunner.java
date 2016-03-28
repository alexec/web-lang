package wl.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import wl.api.AfterJourney;
import wl.api.BeforeJourney;
import wl.domain.ExecutionContext;
import wl.domain.Journey;
import wl.domain.step.Step;
import wl.infrastructure.context.Beans;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
public class JourneyRunner extends ParentRunner<Step> {

    private final Object config;
    private final Journey journey;
    private ExecutionContext context;

    public JourneyRunner(Object config, Journey journey) throws InitializationError {
        super(JourneyRunner.class);
        this.config = config;
        this.journey = journey;
    }

    @Override
    protected List<Step> getChildren() {
        return journey.getSteps();
    }

    @Override
    protected String getName() {
        return journey.getName();
    }

    @Override
    protected Description describeChild(Step child) {
        return Description.createTestDescription(getName(), child.getDescription());
    }

    @Override
    public void run(RunNotifier notifier) {

        WebDriver driver = Beans.getBeanByType(config, WebDriver.class);

        context = new ExecutionContext(driver, journey.getPages());
        Beans.invokeMethodsByAnnotation(config, BeforeJourney.class, journey);
        try {
            super.run(notifier);
        } finally {
            Beans.invokeMethodsByAnnotation(config, AfterJourney.class, journey);
            driver.quit();
            context = null;
        }
    }

    @Override
    protected void runChild(Step child, RunNotifier notifier) {
        Description description = describeChild(child);
        notifier.fireTestStarted(description);
        try {
            child.execute(context);
        } catch (Throwable t) {
            notifier.fireTestFailure(new Failure(description, t));
            captureScreenshot(description);
        } finally {
            notifier.fireTestFinished(description);
        }
    }

    private void captureScreenshot(Description description) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) context.getDriver();
        Path file;
        try {
            file = takesScreenshot.getScreenshotAs(OutputType.FILE).toPath();
        } catch (UnsupportedOperationException e) {
            //noinspection ThrowablePrintedToSystemOut
            log.error("failed to take screenshot: " + e.getMessage());
            return;
        }

        Path screenshots = Paths.get("target", "screenshots");
        try {
            if (!screenshots.toFile().exists()) {
                Files.createDirectory(screenshots);
            }
            Path dest = screenshots.resolve(description + ".png");
            log.debug("captured " + dest);
            Files.move(file, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("failed to capture screenshot", e);
        }
    }
}