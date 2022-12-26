package consulting.sit.catenax.controller.dtos.materialforrecycling;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = QuantityDTO.DTO_NAME, description = "Adaptive data transfer object for " + QuantityDTO.DTO_NAME)
public class QuantityDTO {

    private Integer id;

    public static final String DTO_NAME = "componentDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "-1.7976931348623157e+308", required = true)
    private Double value;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "kilogram", required = true)
    private String unit;
}
