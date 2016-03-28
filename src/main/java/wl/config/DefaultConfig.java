package wl.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DefaultConfig {

    public WebDriver webDriver() {
        String browser = System.getProperty("wl.browser", "htmlunit");
        switch (browser) {
            case "htmlunit":
                return new HtmlUnitDriver();
            case "firefox":
                return new FirefoxDriver();
            default:
                throw new IllegalStateException("unknown browser \"" + browser + "\"");
        }
    }
}