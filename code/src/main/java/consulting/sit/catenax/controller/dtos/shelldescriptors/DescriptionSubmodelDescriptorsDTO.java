package consulting.sit.catenax.controller.dtos.shelldescriptors;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = DescriptionSubmodelDescriptorsDTO.DTO_NAME, description = "Adaptive data transfer object for " + DescriptionSubmodelDescriptorsDTO.DTO_NAME)
public class DescriptionSubmodelDescriptorsDTO{

    public static final String DTO_NAME = "DescriptionSubmodelDescriptorsDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "en", required = true)
    private String language;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "Battery Passport Submodel", required = true)
    private String text;
}
