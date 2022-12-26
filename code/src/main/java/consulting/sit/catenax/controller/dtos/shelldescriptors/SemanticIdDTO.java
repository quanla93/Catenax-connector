package consulting.sit.catenax.controller.dtos.shelldescriptors;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = SemanticIdDTO.DTO_NAME, description = "Adaptive data transfer object for " + SemanticIdDTO.DTO_NAME)
@Data
public class SemanticIdDTO {

    public static final String DTO_NAME = "SemanticIdDTO";

    private List<String> value;
}
