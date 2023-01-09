package consulting.sit.catenax.controller.dtos.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AssetResponseDTO {

    private String createdAt;
    @JsonProperty("properties")
    private AssetPropertiesDTO properties;
    private String id;
}
