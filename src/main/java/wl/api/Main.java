package wl.api;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import wl.infrastructure.PrettyListener;

@RunWith(JourneysRunner.class)
public class Main {

    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new PrettyListener());
        Result result = core.run(Main.class);
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}
