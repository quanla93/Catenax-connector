package consulting.sit.catenax.controller.dtos.shelldescriptors;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = SubmodelDescriptorsDTO.DTO_NAME, description = "Adaptive data transfer object for " + SubmodelDescriptorsDTO.DTO_NAME)
@Data
public class SubmodelDescriptorsDTO{

    public static final String DTO_NAME = "SubmodelDescriptorsDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "MaterialPass", required = true)
    private String idShort;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "urn:uuid:61125dc3-5e6f-4f4b-838d-447432b97918", required = true)
    private String identification;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "urn_bamm_io.catenax.battery.battery_pass_2.0.0", required = true)
    private SemanticIdDTO semanticId;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = DTO_NAME, required = true)
    private List<DescriptionSubmodelDescriptorsDTO> descriptors;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = DTO_NAME, required = true)
    private List<EndpointsDTO> endpoints;

}
