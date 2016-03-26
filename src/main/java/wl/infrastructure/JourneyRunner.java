package wl.infrastructure;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.firefox.FirefoxDriver;
import wl.domain.ExecutionContext;
import wl.domain.Journey;
import wl.domain.Step;

import java.util.List;

// https://github.com/cucumber/cucumber-jvm/blob/master/junit/src/main/java/cucumber/runtime/junit/FeatureRunner.java
// https://github.com/cucumber/cucumber-jvm/blob/20db608a5535850139ba25fcdb9be3ae46991855/junit/src/main/java/cucumber/runtime/junit/ExecutionUnitRunner.java
public class JourneyRunner extends ParentRunner<Step> {

    private final Journey journey;
    private ExecutionContext context;

    public JourneyRunner(Journey journey) throws InitializationError {
        super(JourneyRunner.class);
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
        context = new ExecutionContext(new FirefoxDriver());
        try {
            super.run(notifier);
        } finally {
            context.close();
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
        } finally {
            notifier.fireTestFinished(description);
        }
    }
}