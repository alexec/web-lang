package it;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@SuppressWarnings({"WeakerAccess", "unused"})
public class HtmlUnitConfig {

    public WebDriver webDriver() {
        return new HtmlUnitDriver();
    }
}
