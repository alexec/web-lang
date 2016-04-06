package wl.domain.step.examination;

import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class CaptureTest {
    private final Capture capture = Capture.to(Paths.get("path"));

    @Test
    public void description() {
        assertEquals("capture to \"path\"", capture.getDescription());

    }
}