package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class AssetDTO {
    @JsonProperty("id")
    String id;
    @JsonProperty("createdAt")
    String createdAt;
    @JsonProperty("properties")
    JsonNode properties;
}
