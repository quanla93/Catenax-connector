package consulting.sit.catenax.controller.dtos.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PolicyDefinitionsResponseDTO {

    private String createdAt;
    private String id;
    @JsonProperty("policy")
    private PolicyResponseDTO policy;
}
