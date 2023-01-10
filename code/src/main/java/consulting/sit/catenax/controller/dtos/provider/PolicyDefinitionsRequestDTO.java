package consulting.sit.catenax.controller.dtos.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PolicyDefinitionsRequestDTO {

    private String id;
    @JsonProperty("policy")
    private PolicyRequestDTO policy;
}
