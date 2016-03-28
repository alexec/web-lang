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
    public void startingWithAtSymbolIsTargetName() throws Exception {
        assertTrue(VALID_TARGET_NAME_SELECTOR.isTargetName());
    }

    @Test
    public void validTargetName() throws Exception {
        assertEquals("targetName", VALID_TARGET_NAME_SELECTOR.toTargetName());
    }

    @Test(expected = IllegalStateException.class)
    public void invalidTargetName() throws Exception {
        VALID_CSS_SELECTOR.toTargetName();
    }

    @Test
    public void startingWithoutAtSymbolIsNotTargetName() throws Exception {
        assertFalse(VALID_CSS_SELECTOR.isTargetName());
    }

    @Test
    public void validCssSelector() throws Exception {
        assertEquals(By.cssSelector("cssSelector"), VALID_CSS_SELECTOR.toBy());
    }

    @Test(expected = IllegalStateException.class)
    public void invalidCssSelector() throws Exception {
        VALID_TARGET_NAME_SELECTOR.toBy();
    }

    @Test
    public void validById() throws Exception {
        assertEquals(By.id("foo"), by("id:foo"));
    }

    @Test
    public void validByLinkText() throws Exception {
        assertEquals(By.linkText("foo"), by("linkText:foo"));
    }

    @Test
    public void validPartialLinkText() throws Exception {
        assertEquals(By.partialLinkText("foo"), by("partialLinkText:foo"));
    }

    @Test
    public void validName() throws Exception {
        assertEquals(By.name("foo"), by("name:foo"));
    }

    @Test
    public void validTagName() throws Exception {
        assertEquals(By.tagName("foo"), by("tagName:foo"));
    }

    @Test
    public void validXPath() throws Exception {
        assertEquals(By.xpath("foo"), by("xpath:foo"));
    }
}