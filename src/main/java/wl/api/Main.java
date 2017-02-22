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
        val core = new JUnitCore();
        core.addListener(new PrettyListener());
        core.addListener(new JUnitResultFormatterAsRunListener(new XMLJUnitResultFormatter()) {
            @Override
            public void testStarted(Description description) throws Exception {
                formatter.setOutput(new FileOutputStream(new File("reports", "TEST-" + description.getDisplayName() + ".xml")));
                super.testStarted(description);
            }
        });
        val result = core.run(Main.class);
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}
