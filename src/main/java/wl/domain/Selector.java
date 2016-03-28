package wl.domain;

import lombok.NonNull;
import lombok.Value;
import org.openqa.selenium.By;

/**
 * Both a `By` and an alias.
 */
@Value(staticConstructor = "valueOf")
public class Selector {
    @NonNull
    private final String value;

    boolean isTargetName() {
        return value.startsWith("@");
    }

    String toTargetName() {
        if (!isTargetName()) {
            throw new IllegalStateException("not a target name");
        }
        return value.substring(1);
    }

    By toBy() {
        if (isTargetName()) {
            throw new IllegalStateException("not a by name");
        }
        return value.startsWith("id:") ? By.id(value.substring(3)) :
                value.startsWith("linkText:") ? By.linkText(value.substring(9)) :
                        value.startsWith("partialLinkText:") ? By.partialLinkText(value.substring(16)) :
                                By.cssSelector(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
