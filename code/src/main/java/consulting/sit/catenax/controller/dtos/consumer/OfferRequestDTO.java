package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(title = OfferRequestDTO.DTO_NAME, description = "Adaptive data transfer object for " + OfferRequestDTO.DTO_NAME)
public class OfferRequestDTO implements Serializable {

    public static final String DTO_NAME = "OfferRequestDTO";

    @JsonProperty("connectorId")
    private String connectorId;
    @JsonProperty("connectorAddress")
    private String connectorAddress;
    @JsonProperty("offer")
    private ContractOfferRequestDTO offer;

}
