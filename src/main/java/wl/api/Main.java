package wl.api;

import lombok.val;
import org.apache.tools.ant.taskdefs.optional.junit.XMLJUnitResultFormatter;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import wl.infrastructure.PrettyListener;

import java.io.File;
import java.io.FileOutputStream;

@RunWith(JourneysRunner.class)
public class Main {

    public static void main(String[] args) {
        File reports = new File("reports");
        ensureReportsCreated(reports);
        val core = new JUnitCore();
        core.addListener(new PrettyListener());
        core.addListener(new JUnitResultFormatterAsRunListener(new XMLJUnitResultFormatter()) {
            @Override
            public void testStarted(Description description) throws Exception {
                formatter.setOutput(new FileOutputStream(new File(reports, "TEST-" + description.getDisplayName().replaceAll("[^-_A-Za-z0-9]", "_") + ".xml")));
                super.testStarted(description);
            }
        });
        val result = core.run(Main.class);
        System.exit(result.wasSuccessful() ? 0 : 1);
    }

    private static void ensureReportsCreated(File reports) {
        if (!reports.exists()) {
            if (!reports.mkdir()) {
                throw new IllegalStateException();
            }
        }
    }
}
