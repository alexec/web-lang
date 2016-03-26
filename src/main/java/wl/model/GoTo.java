package wl.model;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebDriver;

import java.net.URI;

@Builder
@Data
public class GoTo implements Step {
    private URI url;

    @Override
    public void execute(WebDriver driver) {
        driver.get(url.toString());
    }
}
