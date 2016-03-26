package wl.infrastructure;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ResourcesTest {

    @Test
    public void getsBothAAndBFiles() throws Exception {
        assertEquals(Arrays.asList("a.txt", "b.txt"), resources(url -> url.getPath().endsWith(".txt")));
    }

    @Test
    public void getsBothAOnly() throws Exception {
        assertEquals(Collections.singletonList("a.txt"), resources(url -> url.getPath().endsWith("a.txt")));
    }

    private List<String> resources(Predicate<URL> filter) throws IOException, URISyntaxException {
        return Resources.getResources(ResourcesTest.class, filter).stream().map((url) -> url.getPath().replaceFirst(".*/", "")).sorted().collect(Collectors.toList());
    }
}