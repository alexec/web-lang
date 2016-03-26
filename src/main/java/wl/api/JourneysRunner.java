package wl.api;


import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import wl.infrastructure.JourneyFactory;
import wl.infrastructure.JourneyRunner;
import wl.infrastructure.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// https://github.com/cucumber/cucumber-jvm/blob/master/junit/src/main/java/cucumber/api/junit/Cucumber.java
public class JourneysRunner extends ParentRunner<JourneyRunner> {
    private final List<JourneyRunner> children = new ArrayList<>();

    public JourneysRunner(Class clazz) throws InitializationError, IOException, URISyntaxException {
        super(clazz);

        JourneyFactory journeyFactory = new JourneyFactory();
        for (URL url : Resources.getResources(clazz, u -> u.getPath().endsWith(".journey"))) {
            try (InputStream in = url.openStream()) {
                children.add(new JourneyRunner(journeyFactory.create(in)));
            }
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
