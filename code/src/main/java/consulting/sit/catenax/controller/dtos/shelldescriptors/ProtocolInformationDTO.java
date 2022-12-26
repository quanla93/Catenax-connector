package consulting.sit.catenax.controller.dtos.shelldescriptors;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@Schema(title = ProtocolInformationDTO.DTO_NAME, description = "Adaptive data transfer object for " + ProtocolInformationDTO.DTO_NAME)
@Data
public class ProtocolInformationDTO {

    public static final String DTO_NAME = "ProtocolInformationDTO";

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "https://materialpass.int.demo.catena-x.net/provider/api/v1/ids/data", required = true)
    private String endpointAddress;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "IDS/ECLIPSE DATASPACE CONNECTOR", required = true)
    private String endpointProtocol;

    @Schema(description = "Please specify the type of this adaptive " + DTO_NAME, example = "0.0.1-SNAPSHOT", required = true)
    private String endpointProtocolVersion;
}
