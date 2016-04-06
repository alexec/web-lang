package it;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import wl.api.Config;

public class FirefoxConfig implements Config {

    @Override
    public WebDriver webDriver() {
        return new FirefoxDriver();
    }
}
