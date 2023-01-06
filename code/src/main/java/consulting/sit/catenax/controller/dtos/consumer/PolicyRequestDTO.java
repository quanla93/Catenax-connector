package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class PolicyRequestDTO {

    @JsonProperty("uid")
    private String uid;
    @JsonProperty("prohibitions")
    private List<JsonNode> prohibitions;
    @JsonProperty("obligations")
    private List<JsonNode> obligations;
    @JsonProperty("permissions")
    private List<JsonNode> permissions;

}
