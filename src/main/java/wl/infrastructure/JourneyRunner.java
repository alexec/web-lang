package wl.infrastructure;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import wl.domain.ExecutionContext;
import wl.domain.Journey;
import wl.domain.Step;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

// https://github.com/cucumber/cucumber-jvm/blob/master/junit/src/main/java/cucumber/runtime/junit/FeatureRunner.java
// https://github.com/cucumber/cucumber-jvm/blob/20db608a5535850139ba25fcdb9be3ae46991855/junit/src/main/java/cucumber/runtime/junit/ExecutionUnitRunner.java
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

        WebDriver driver = createDriver();
        context = new ExecutionContext(driver);
        try {
            super.run(notifier);
        } finally {
            driver.quit();
            context = null;
        }
    }

    private WebDriver createDriver() {
        try {
            return WebDriver.class.cast(config.getClass().getMethod("webDriver").invoke(config));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("failed to create driver", e);
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

        Path file = takesScreenshot.getScreenshotAs(OutputType.FILE).toPath();

        Path screenshots = Paths.get("target", "screenshots");
        try {
            if (!screenshots.toFile().exists()) {
                Files.createDirectory(screenshots);
            }
            Path dest = screenshots.resolve(description + ".png");
            System.out.println("captured " + dest);
            Files.move(file, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("failed to capture screenshot", e);
        }
    }
}