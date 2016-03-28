package wl.domain;

import lombok.Data;
import lombok.NonNull;
import wl.domain.step.Step;

import java.util.List;

@Data
public class Background {
    @NonNull
    private final Journey journey;

    public List<Step> getSteps() {
        return journey.getSteps();
    }
}
