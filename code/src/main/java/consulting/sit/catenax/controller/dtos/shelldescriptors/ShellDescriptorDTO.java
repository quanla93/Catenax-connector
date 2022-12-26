package consulting.sit.catenax.controller.dtos.shelldescriptors;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = ShellDescriptorDTO.DTO_NAME, description = "Adaptive data transfer object for " + ShellDescriptorDTO.DTO_NAME)
@Data
public class ShellDescriptorDTO {

    public static final String DTO_NAME = "ShellDescriptorDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "demo_catena_test_1.txt", required = true)
    private String idShort;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "urn:uuid:365e6fbe-bb34-11ec-8422-0242ac120002", required = true)
    private String identification;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "urn:uuid:365e6fbe-bb34-11ec-8422-0242ac120002", required = true)
    private GlobalAssetIdDTO globalAssetId;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = DTO_NAME, required = true)
    private List<DescriptionShellDescriptorsDTO> descriptors;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = DTO_NAME, required = true)
    private List<SpecificAssetIdsDTO> specificAssetIds;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = DTO_NAME, required = true)
    private List<SubmodelDescriptorsDTO> submodelDescriptors;

}
