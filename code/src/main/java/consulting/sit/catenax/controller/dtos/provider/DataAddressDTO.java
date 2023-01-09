package consulting.sit.catenax.controller.dtos.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataAddressDTO {

    @JsonProperty("properties")
    private DataAddressPropertiesDTO properties;
}
