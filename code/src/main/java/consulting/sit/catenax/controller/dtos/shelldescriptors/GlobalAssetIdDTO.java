package consulting.sit.catenax.controller.dtos.shelldescriptors;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = GlobalAssetIdDTO.DTO_NAME, description = "Adaptive data transfer object for " + GlobalAssetIdDTO.DTO_NAME)
@Data
public class GlobalAssetIdDTO {

    public static final String DTO_NAME = "GlobalAssetIdDTO";

    private List<String> value;
}
