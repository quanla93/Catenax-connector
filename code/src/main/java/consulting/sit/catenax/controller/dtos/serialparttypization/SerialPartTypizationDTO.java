package consulting.sit.catenax.controller.dtos.serialparttypization;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import consulting.sit.catenax.model.serialparttypization.LocalIdentifiersModel;
import consulting.sit.catenax.model.serialparttypization.ManufacturingInformationModel;
import consulting.sit.catenax.model.serialparttypization.PartTypeInformationModel;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = SerialPartTypizationDTO.DTO_NAME, description = "Adaptive data transfer object for " + SerialPartTypizationDTO.DTO_NAME)
public class SerialPartTypizationDTO {

    public static final String DTO_NAME = "SerialPartTypizationDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "urn:uuid:580d3adf-1981-44a0-a214-13d6ceed9379", required = true)
    private String catenaXId;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, required = true)
    private List<LocalIdentifiersModel> localIdentifiersModels;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, required = true)
    private ManufacturingInformationModel manufacturingInformation;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, required = true)
    private PartTypeInformationModel partTypeInformation;

}
