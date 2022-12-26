package consulting.sit.catenax.controller.dtos.serialparttypization;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = ManufacturingInformationDTO.DTO_NAME, description = "Adaptive data transfer object for " + ManufacturingInformationDTO.DTO_NAME)
public class ManufacturingInformationDTO {

    public static final String DTO_NAME = "ManufacturingInformationDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "2022-02-04T14:48:54", required = true)
    private String date;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "HUR")
    private String country;
}
