package wl.domain;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class SelectorTest {

    private static final Selector VALID_TARGET_NAME_SELECTOR = Selector.valueOf("@targetName");
    private static final Selector VALID_CSS_SELECTOR = Selector.valueOf("cssSelector");

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
        assertEquals(By.id("myId"), Selector.valueOf("id:myId").toBy());
    }

    @Test
    public void validByLinkText() throws Exception {
        assertEquals(By.linkText("myLinkText"), Selector.valueOf("linkText:myLinkText").toBy());
    }

    @Test
    public void validPartialLinkText() throws Exception {
        assertEquals(By.partialLinkText("myPartialLinkText"), Selector.valueOf("partialLinkText:myPartialLinkText").toBy());
    }
}