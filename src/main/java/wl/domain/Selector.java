package wl.domain;

import lombok.NonNull;
import lombok.Value;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Both a `By` and an alias.
 */
@Value(staticConstructor = "valueOf")
public class Selector {
    private static final Map<String, Function<String, By>> byFactories = new HashMap<>();

    static {
        byFactories.put("id", By::id);
        byFactories.put("linkText", By::linkText);
        byFactories.put("partialLinkText", By::partialLinkText);
        byFactories.put("name", By::name);
        byFactories.put("tagName", By::tagName);
        byFactories.put("className", By::className);
        byFactories.put("xpath", By::xpath);
    }

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

        Optional<Map.Entry<String, Function<String, By>>> first = byFactories.entrySet().stream()
                .filter(e -> value.startsWith(e.getKey() + ":"))
                .findFirst();

        return
                first.isPresent() ? first.get().getValue().apply(value.substring(first.get().getKey().length() + 1)) :
                        By.cssSelector(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
