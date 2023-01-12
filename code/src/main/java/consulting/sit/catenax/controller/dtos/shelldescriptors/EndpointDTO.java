package consulting.sit.catenax.controller.dtos.shelldescriptors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EndpointDTO {
    @JsonProperty("interface")
    private String providerInterface;
    private ProtocolInformationDTO protocolInformation;
}
