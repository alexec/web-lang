package wl.api;

import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DefaultConfig implements Config {

    @Override
    public WebDriver webDriver() {
        val browser = System.getProperty("wl.browser", "firefox");
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