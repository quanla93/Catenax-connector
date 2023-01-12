package consulting.sit.catenax.controller.dtos.shelldescriptors;

import lombok.Data;

import java.util.List;

@Data
public class SubModelDescriptorDTO {
    private List<DescriptionDTO> description;
    private String idShort;
    private String identification;
    private SemanticIdDTO semanticId;
    private List<EndpointDTO> endpoints;
}
