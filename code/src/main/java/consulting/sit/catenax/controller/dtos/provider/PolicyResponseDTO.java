package consulting.sit.catenax.controller.dtos.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class PolicyResponseDTO {

    @JsonProperty("permissions")
    private List<JsonNode> permissions;
    @JsonProperty("prohibitions")
    private List<JsonNode> prohibitions;
    @JsonProperty("obligations")
    private List<JsonNode> obligations;
    @JsonProperty("extensibleProperties")
    private JsonNode extensibleProperties;
    @JsonProperty("inheritsFrom")
    private String inheritsFrom;
    @JsonProperty("assigner")
    private String assigner;
    @JsonProperty("assignee")
    private String assignee;
    @JsonProperty("target")
    private String target;
    @JsonProperty("@type")
    private JsonNode type;
    @JsonProperty("uid")
    private String uid;

}
