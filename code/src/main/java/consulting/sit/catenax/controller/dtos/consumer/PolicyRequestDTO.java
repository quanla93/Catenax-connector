package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class PolicyRequestDTO {

    @JsonProperty("uid")
    String uid;
    @JsonProperty("prohibitions")
    List<JsonNode> prohibitions;
    @JsonProperty("obligations")
    List<JsonNode> obligations;
    @JsonProperty("permissions")
    List<JsonNode> permissions;

}
