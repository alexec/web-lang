package wl.domain;

import org.openqa.selenium.WebDriver;

public interface Step {
    void execute(WebDriver driver);

    String getDescription();
}
