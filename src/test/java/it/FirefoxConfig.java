package it;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@SuppressWarnings({"WeakerAccess", "unused"})
public class FirefoxConfig extends AbstractConfig {

    @Override
    public WebDriver webDriver() {
        return new FirefoxDriver();
    }
}
