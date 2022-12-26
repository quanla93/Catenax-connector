package consulting.sit.catenax.controller.dtos.serialparttypization;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import consulting.sit.catenax.model.serialparttypization.enums.EClassification;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = PartTypeInformationDTO.DTO_NAME, description = "Adaptive data transfer object for " + PartTypeInformationDTO.DTO_NAME)
@Data
public class PartTypeInformationDTO {

    public static final String DTO_NAME = "PartTypeInformationDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "123-0.740-3434-A", required = true)
    private String manufacturerPartId;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "PRT-12345")
    private String customerPartId;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "product", required = true)
    private EClassification classification;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "Mirror left", required = true)
    private String nameAtManufacturer;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "side element A")
    private String nameAtCustomer;
}
