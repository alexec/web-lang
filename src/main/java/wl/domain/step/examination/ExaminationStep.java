package wl.domain.step.examination;

import wl.domain.step.Step;

public interface ExaminationStep extends Step {

     default String descriptionOfMismatch(String value) {
        return getDescription() + " was \"" + value + "\"";
    }
}
