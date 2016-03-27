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
            throw new IllegalStateException("not a by name name");
        }
        return By.cssSelector(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
