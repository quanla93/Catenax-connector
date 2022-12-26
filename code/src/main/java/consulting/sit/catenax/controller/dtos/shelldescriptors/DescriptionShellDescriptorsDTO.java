package consulting.sit.catenax.controller.dtos.shelldescriptors;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = DescriptionShellDescriptorsDTO.DTO_NAME, description = "Adaptive data transfer object for " + DescriptionShellDescriptorsDTO.DTO_NAME)
@Data
public class DescriptionShellDescriptorsDTO  {

    public static final String DTO_NAME = "DescriptionShellDescriptorsDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "en", required = true)
    private String language;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "demo_catena_test_1.txt", required = true)
    private String text;
}
