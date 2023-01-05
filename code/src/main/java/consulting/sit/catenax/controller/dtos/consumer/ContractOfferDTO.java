package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ContractOfferDTO {
    @JsonProperty("id")
    String id;
    @JsonProperty("policy")
    PolicyDTO policy;
    @JsonProperty("asset")
    AssetDTO asset;
    @JsonProperty("assetId")
    String assetId;
    @JsonProperty("provider")
    String provider;
    @JsonProperty("consumer")
    String consumer;

    @JsonProperty("offerStart")
    Date offerStart;
    @JsonProperty("offerEnd")
    Date offerEnd;
    @JsonProperty("contractStart")
    Date contractStart;
    @JsonProperty("contractEnd")
    Date contractEnd;
}
