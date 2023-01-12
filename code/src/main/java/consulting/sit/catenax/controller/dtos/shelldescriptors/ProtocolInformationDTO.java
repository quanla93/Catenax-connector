package consulting.sit.catenax.controller.dtos.shelldescriptors;

import lombok.Data;

@Data
public class ProtocolInformationDTO {
    private String endpointAddress;
    private String endpointProtocol;
    private String endpointProtocolVersion;
}
