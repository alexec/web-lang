package wl.infrastructure;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrettyListener extends RunListener {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private final PrintStream out = System.out;
    private Failure failure;
    private String journeyName;

    @Override
    public void testStarted(Description description) {

        String displayName = description.getDisplayName();

        Matcher matcher = Pattern.compile("(.*)\\((.*)\\)").matcher(displayName);

        if (!matcher.find()) {
            throw new AssertionError();
        }

        String journeyName = matcher.group(2);
        String step = matcher.group(1);

        if (!journeyName.equals(this.journeyName)) {
            out.println("Journey: " + journeyName);
            this.journeyName = journeyName;
        }

        out.printf("\t%s ... ", step);
        out.flush();
        failure = null;
    }

    @Override
    public void testFinished(Description description) {
        if (failure != null) {
            out.printf("%sFAIL %s%s", ANSI_RED, ANSI_RESET, failure.getMessage().replaceAll("\n.*", ""));
        } else {
            out.printf("%sPASS%s", ANSI_GREEN, ANSI_RESET);
        }
        out.println();
    }

    @Override
    public void testFailure(Failure failure) {
        this.failure = failure;
    }
}
