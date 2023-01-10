package consulting.sit.catenax.controller.dtos.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PolicyDefinitionsDTO {

    private String id;
    @JsonProperty("policy")
    private PolicyDTO policy;
}
