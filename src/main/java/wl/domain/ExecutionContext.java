package wl.domain;


import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ExecutionContext {
    private static final Consumer<WebDriver> SLEEP =
            (WebDriver ignored) -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            };
    private final EventFiringWebDriver driver;
    private final Map<String, Page> pages;
    @Getter
    private By lastBy;
    private Page currentPage;

    public ExecutionContext(WebDriver driver, Map<String, Page> pages) {
        this.pages = pages;
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

    public By by(Selector selector) {
        return selector.isTargetName() ? targetToBy(selector) : selector.toBy();
    }

    private By targetToBy(Selector selector) {
        Objects.requireNonNull(currentPage);
        String cssSelector = currentPage.getElements().get(selector.toTargetName());
        if (cssSelector == null) {
            throw new IllegalStateException(String.format("target \"%s\" not found on page \"%s\"", selector, currentPage.getName()));
        }
        return By.cssSelector(cssSelector);
    }

    public void waitUntil(Predicate<WebDriver> condition) {
        repeatUntil(condition, webDriver -> {
        });
    }

    public void repeatUntil(Predicate<WebDriver> condition, Consumer<WebDriver> advance) {
        repeatUntilAux(condition, SLEEP.andThen(advance));
    }

    private void repeatUntilAux(Predicate<WebDriver> condition, Consumer<WebDriver> advance) {
        for (int i = 0; i < 3; i++) {
            if (condition.test(driver)) {
                return;
            }
            advance.accept(driver);
        }
    }

    public Page getPage(String pageName) {
        return pages.get(pageName);
    }

    public void setCurrentPage(Page page) {
        this.currentPage = page;
    }
}
