package wl.api;


import lombok.val;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import wl.domain.Journey;
import wl.infrastructure.JourneyFactory;
import wl.infrastructure.JourneyParsingException;
import wl.infrastructure.JourneyRunner;
import wl.infrastructure.Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

// https://github.com/cucumber/cucumber-jvm/blob/master/junit/src/main/java/cucumber/api/junit/Cucumber.java
public class JourneysRunner extends ParentRunner<JourneyRunner> {
    private static final String PATH_SUFFIX = ".journey";
    private final List<JourneyRunner> children = new ArrayList<>();
    private final JourneyFactory journeyFactory = new JourneyFactory();
    private final String journeyNameFilter = System.getProperty("wl.journey", "");

    public JourneysRunner(Class<?> clazz) throws InitializationError, IOException, URISyntaxException {
        super(clazz);

        val path = System.getProperty("wl.path", "");
        if (!path.isEmpty()) {
            addChildrenFromPath(Paths.get(path));
        } else {
            addChildrenFromClassPath(clazz);
        }

        if (children.isEmpty()) {
            throw new InitializationError("no journeys found in either \"" + path + "\" or classpath");
        }
    }

    private void addChildrenFromClassPath(Class<?> clazz) throws InitializationError, IOException, URISyntaxException {
        for (val url : Resources.getResources(clazz, u -> u.getPath().endsWith(PATH_SUFFIX))) {
            try (InputStream in = url.openStream()) {
                for (Journey journey : journeyFactory.create(in)) {
                    if (journey.getName().contains(journeyNameFilter)) {
                        children.add(new JourneyRunner(journey));
                    }
                }
            } catch (JourneyParsingException | IOException e) {
                throw new InitializationError("cannot parse " + url + " due to " + e.getMessage());
            }
        }
    }

    private void addChildrenFromPath(Path path) throws InitializationError, IOException {
        val paths = new LinkedList<Path>();
        Files.walkFileTree(path, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (path.toString().endsWith(PATH_SUFFIX)) {
                    paths.add(path);
                }
                return super.visitFile(path, attrs);
            }
        });

        for (val journeyPath : paths) {
            try (FileInputStream in = new FileInputStream(journeyPath.toFile())) {
                for (Journey journey : journeyFactory.create(in)) {
                    if (journey.getName().contains(journeyNameFilter)) {
                        children.add(new JourneyRunner(journey));
                    }
                }
            } catch (JourneyParsingException e) {
                throw new InitializationError("cannot parse " + path + " due to  " + e.getMessage());
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
