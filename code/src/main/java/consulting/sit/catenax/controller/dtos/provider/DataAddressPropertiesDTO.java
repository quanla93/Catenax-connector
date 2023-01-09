package consulting.sit.catenax.controller.dtos.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataAddressPropertiesDTO {

    @JsonProperty("type")
    private String type;
    @JsonProperty("baseUrl")
    private String baseUrl;
}
