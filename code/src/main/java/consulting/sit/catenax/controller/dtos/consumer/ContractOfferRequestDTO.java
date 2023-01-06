package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ContractOfferRequestDTO {
    @JsonProperty("offerId")
    private String offerId;
    @JsonProperty("assetId")
    private String assetId;
    @JsonProperty("policy")
    private PolicyRequestDTO policy;
}
