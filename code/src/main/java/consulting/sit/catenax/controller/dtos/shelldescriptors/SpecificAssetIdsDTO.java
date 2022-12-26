package consulting.sit.catenax.controller.dtos.shelldescriptors;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = SpecificAssetIdsDTO.DTO_NAME, description = "Adaptive data transfer object for " + SpecificAssetIdsDTO.DTO_NAME)
@Data
public class SpecificAssetIdsDTO {

    public static final String DTO_NAME = "SpecificAssetIdsDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "Battery_ID_DMC_Code", required = true)
    private String key;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "IMR18650V1", required = true)
    private String value;
}
