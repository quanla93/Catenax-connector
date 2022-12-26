package consulting.sit.catenax.controller.dtos.materialforrecycling;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import consulting.sit.catenax.model.materialforrecycling.MaterialModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = MaterialDTO.DTO_NAME, description = "Adaptive data transfer object for " + MaterialDTO.DTO_NAME)
@Data
public class MaterialDTO {

    public static final String DTO_NAME = "MaterialDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "Polyamid", required = true)
    private String materialName;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "plastic", required = true)
    private String materialClass;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "-1.7976931348623157e+308")
    private String recycledContent;

    @Schema(description = "componentDTOS", required = true)
    private List<ComponentDTO> Components;

}
