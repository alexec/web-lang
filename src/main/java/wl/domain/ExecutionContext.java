package wl.domain;

import com.google.common.base.Predicate;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExecutionContext {
    private final EventFiringWebDriver driver;
    @Getter
    private By lastBy;

    public ExecutionContext(WebDriver driver) {
        this.driver = new EventFiringWebDriver(driver);
        this.driver.register(new AbstractWebDriverEventListener() {
            @Override
            public void afterFindBy(By by, WebElement element, WebDriver driver) {
                lastBy = by;
            }
        });
    }

    public WebDriver getDriver() {
        return driver;
    }

    void waitUntil(@SuppressWarnings("Guava") Predicate<WebDriver> predicate) {
        new WebDriverWait(driver, 10).until(predicate);
    }
}
