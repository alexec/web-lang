package wl.api;

import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DefaultConfig implements Config {

    @Override
    public WebDriver webDriver() {
        val browser = System.getProperty("wl.browser", "htmlunit");
        switch (browser) {
            case "htmlunit":
                return new HtmlUnitDriver(true);
            case "firefox":
                return new FirefoxDriver();
            case "chrome":
                return new ChromeDriver();
            default:
                throw new IllegalStateException("unknown browser \"" + browser + "\"");
        }
    }
}