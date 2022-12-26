package consulting.sit.catenax.controller.dtos.serialparttypization;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = LocalIdentifiersDTO.DTO_NAME, description = "Adaptive data transfer object for " + LocalIdentifiersDTO.DTO_NAME)
@Data
public class LocalIdentifiersDTO {

    public static final String DTO_NAME = "LocalIdentifiersDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = DTO_NAME, required = true)
    private String value;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = DTO_NAME, required = true)
    private String key;
}
