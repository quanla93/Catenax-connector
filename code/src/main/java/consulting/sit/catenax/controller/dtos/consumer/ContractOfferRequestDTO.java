package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ContractOfferRequestDTO {
    @JsonProperty("offerId")
    String offerId;
    @JsonProperty("assetId")
    String assetId;
    @JsonProperty("policy")
    PolicyRequestDTO policy;
}
