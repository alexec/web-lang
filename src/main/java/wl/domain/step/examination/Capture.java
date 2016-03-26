package wl.domain.step.examination;

import lombok.Data;
import lombok.NonNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import wl.domain.ExecutionContext;
import wl.domain.step.Step;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Data
public class Capture implements Step {
    @NonNull
    private final Path path;

    private Capture(Path path) {
        this.path = path;
    }

    public static Capture to(Path path) {
        return new Capture(path);
    }

    @Override
    public void execute(ExecutionContext context) {
        TakesScreenshot driver = TakesScreenshot.class.cast(context.getDriver());

        Path file = driver.getScreenshotAs(OutputType.FILE).toPath();
        try {
            Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String getDescription() {
        return String.format("capture to \"%s\"", path);
    }
}
