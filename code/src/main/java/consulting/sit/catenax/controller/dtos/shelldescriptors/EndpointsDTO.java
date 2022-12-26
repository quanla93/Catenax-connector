package consulting.sit.catenax.controller.dtos.shelldescriptors;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = EndpointsDTO.DTO_NAME, description = "Adaptive data transfer object for " + EndpointsDTO.DTO_NAME)
@Data
public class EndpointsDTO {

    public static final String DTO_NAME = "EndpointsDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "SUBMODEL.SIEM.0002", required = true)
    private String interfaces;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = DTO_NAME, required = true)
    private ProtocolInformationDTO protocolInformation;
}
