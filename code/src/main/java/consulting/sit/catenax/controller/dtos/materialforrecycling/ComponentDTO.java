package consulting.sit.catenax.controller.dtos.materialforrecycling;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import consulting.sit.catenax.model.materialforrecycling.enums.EAggregateState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = ComponentDTO.DTO_NAME, description = "Adaptive data transfer object for " + ComponentDTO.DTO_NAME)
@Data
public class ComponentDTO {

    public static final String DTO_NAME = "componentDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "Polyamid", required = true)
    private String materialName;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "plastic", required = true)
    private String materialClass;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "-1.7976931348623157e+308")
    private Integer recycledContent;

    @Schema(description = "Please specify the quantity for this quantity")
    private QuantityDTO quantity;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "solid")
    private EAggregateState aggregateState;

    @Schema(description = "Please specify the material Abbreviation for this material Abbreviation")
    private String materialAbbreviation;
}
