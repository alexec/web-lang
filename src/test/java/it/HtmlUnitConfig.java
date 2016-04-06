package it;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import wl.api.Config;

public class HtmlUnitConfig implements Config {

    @Override
    public WebDriver webDriver() {
        return new HtmlUnitDriver();
    }
}
