package consulting.sit.catenax.controller.dtos.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AssetPropertiesDTO {

    @JsonProperty("asset:prop:id")
    private String assetId;
    @JsonProperty("asset:prop:description")
    private String description;

}
