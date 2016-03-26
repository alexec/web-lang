package wl.domain;


import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ExecutionContext {
    public static final int TIME_OUT_IN_SECONDS = 3;
    private static Consumer<WebDriver> SLEEP =
            (WebDriver ignored) -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            };
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

    public void waitUntil(Predicate<WebDriver> condition) {
        waitUntil(condition, webDriver -> {
        });
    }

    public void waitUntil(Predicate<WebDriver> condition, Consumer<WebDriver> advance) {
        repeatUntil(condition, SLEEP.andThen(advance));
    }

    private void repeatUntil(Predicate<WebDriver> condition, Consumer<WebDriver> advance) {
        for (int i = 0; i < 3; i++) {
            if (condition.test(driver)) {
                return;
            }
            advance.accept(driver);
        }
    }
}
