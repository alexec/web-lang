package wl.api;

import junit.framework.Test;
import junit.framework.TestResult;
import org.junit.runner.Description;

class DescriptionAsTest implements Test {
    private final Description description;

    DescriptionAsTest(Description description) {
        this.description = description;
    }

    @Override
    public int countTestCases() {
        return 1;
    }

    @Override
    public void run(TestResult testResult) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DescriptionAsTest that = (DescriptionAsTest) o;

        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
