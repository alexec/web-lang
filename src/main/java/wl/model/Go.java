package wl.model;

import lombok.Builder;
import lombok.Data;

import java.net.URI;

@Builder
@Data
public class Go {
    private URI url;
}
