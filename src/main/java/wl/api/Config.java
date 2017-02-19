package wl.api;

import org.openqa.selenium.WebDriver;

public interface Config {

    static String getJourney() {
        return System.getProperty("wl.journey", "");
    }

    WebDriver webDriver();
}
