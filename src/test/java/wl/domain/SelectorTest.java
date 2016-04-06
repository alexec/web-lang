package wl.domain;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class SelectorTest {

    private static final Selector VALID_TARGET_NAME_SELECTOR = Selector.valueOf("@targetName");
    private static final Selector VALID_CSS_SELECTOR = Selector.valueOf("cssSelector");

    private static By by(String value) {
        return Selector.valueOf(value).toBy();
    }

    @Test
    public void startingWithAtSymbolIsTargetName() {
        assertTrue(VALID_TARGET_NAME_SELECTOR.isTargetName());
    }

    @Test
    public void validTargetName() {
        assertEquals("targetName", VALID_TARGET_NAME_SELECTOR.toTargetName());
    }

    @Test(expected = IllegalStateException.class)
    public void invalidTargetName() {
        VALID_CSS_SELECTOR.toTargetName();
    }

    @Test
    public void startingWithoutAtSymbolIsNotTargetName() {
        assertFalse(VALID_CSS_SELECTOR.isTargetName());
    }

    @Test
    public void validCssSelector() {
        assertEquals(By.cssSelector("cssSelector"), VALID_CSS_SELECTOR.toBy());
    }

    @Test(expected = IllegalStateException.class)
    public void invalidCssSelector() {
        VALID_TARGET_NAME_SELECTOR.toBy();
    }

    @Test
    public void validById() {
        assertEquals(By.id("foo"), by("id:foo"));
    }

    @Test
    public void validByLinkText() {
        assertEquals(By.linkText("foo"), by("linkText:foo"));
    }

    @Test
    public void validPartialLinkText() {
        assertEquals(By.partialLinkText("foo"), by("partialLinkText:foo"));
    }

    @Test
    public void validName() {
        assertEquals(By.name("foo"), by("name:foo"));
    }

    @Test
    public void validTagName() {
        assertEquals(By.tagName("foo"), by("tagName:foo"));
    }

    @Test
    public void validXPath() {
        assertEquals(By.xpath("foo"), by("xpath:foo"));
    }

    @Test
    public void validClassName() {
        assertEquals(By.className("foo"), by("className:foo"));
    }
}