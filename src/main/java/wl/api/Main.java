package wl.api;

import lombok.val;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import wl.infrastructure.PrettyListener;

@RunWith(JourneysRunner.class)
public class Main {

    public static void main(String[] args) {
        val core = new JUnitCore();
        core.addListener(new PrettyListener());
        val result = core.run(Main.class);
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}
