package wl.domain.step.interaction;

import org.junit.Assert;
import org.junit.Test;

public class DismissAlertTest {
    private final DismissAlert dismissAlert = DismissAlert.INSTANCE;

    @Test
    public void description() {
        Assert.assertEquals("dismiss alert", dismissAlert.getDescription());
    }
}