package wl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@SuppressWarnings({"WeakerAccess", "unused"})
public class FirefoxConfig {

    public WebDriver webDriver() {
        return new FirefoxDriver();
    }
}
