package wl;


import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// https://github.com/cucumber/cucumber-jvm/blob/master/junit/src/main/java/cucumber/api/junit/Cucumber.java
public class JourneysRunner extends ParentRunner<JourneyRunner> {
    private final List<JourneyRunner> children = new ArrayList<>();

    public JourneysRunner(Class clazz) throws InitializationError, IOException {
        super(clazz);

        JourneyFactory journeyFactory = new JourneyFactory();
        try (InputStream in = clazz.getResourceAsStream(clazz.getSimpleName() + ".journey")) {
            children.add(new JourneyRunner(journeyFactory.create(in)));
        }
    }

    @Override
    public List<JourneyRunner> getChildren() {
        return children;
    }

    @Override
    protected Description describeChild(JourneyRunner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(JourneyRunner child, RunNotifier notifier) {
        child.run(notifier);
    }
}
