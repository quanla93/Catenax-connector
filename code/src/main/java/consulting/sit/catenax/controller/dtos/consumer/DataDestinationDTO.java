package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataDestinationDTO {

    @JsonProperty("type")
    private String type = "HttpProxy";
}
