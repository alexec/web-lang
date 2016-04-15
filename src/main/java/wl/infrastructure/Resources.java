package wl.infrastructure;

import lombok.val;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Resources {
    private static void collectURL(Predicate<URL> f, Set<URL> s, URL u) {
        if (f.test(u)) {
            s.add(u);
        }
    }

    private static void iterateFileSystem(File r, Predicate<URL> f, Set<URL> s) throws IOException {
        val files = r.listFiles();
        if (files != null) {
            for (val file : files) {
                if (file.isDirectory()) {
                    iterateFileSystem(file, f, s);
                } else if (file.isFile()) {
                    collectURL(f, s, file.toURI().toURL());
                }
            }
        }
    }

    private static void iterateJarFile(File file, Predicate<URL> f, Set<URL> s) throws IOException {
        val jFile = new JarFile(file);
        for (Enumeration<JarEntry> je = jFile.entries(); je.hasMoreElements(); ) {
            val j = je.nextElement();
            if (!j.isDirectory()) {
                collectURL(f, s, new URL("jar", "", file.toURI() + "!/" + j.getName()));
            }
        }
    }

    private static void iterateEntry(File p, Predicate<URL> f, Set<URL> s) throws IOException {
        if (p.isDirectory()) {
            iterateFileSystem(p, f, s);
        } else if (p.isFile() && p.getName().toLowerCase().endsWith(".jar")) {
            iterateJarFile(p, f, s);
        }
    }

    public static Set<URL> getResources(Class rootClass, Predicate<URL> filter) throws IOException, URISyntaxException {
        val collectedURLs = new HashSet<URL>();
        val codeRoot = new File(rootClass.getProtectionDomain().getCodeSource().getLocation().toURI());
        val searchRoot = new File(codeRoot, rootClass.getPackage().getName().replace(".", "/"));

        iterateEntry(searchRoot, filter, collectedURLs);
        return collectedURLs;
    }
}