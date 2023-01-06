package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ContractOfferDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("policy")
    private PolicyDTO policy;
    @JsonProperty("asset")
    private AssetDTO asset;
    @JsonProperty("assetId")
    private String assetId;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("consumer")
    private String consumer;
    @JsonProperty("offerStart")
    private Date offerStart;
    @JsonProperty("offerEnd")
    private Date offerEnd;
    @JsonProperty("contractStart")
    private Date contractStart;
    @JsonProperty("contractEnd")
    private Date contractEnd;
}
