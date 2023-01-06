package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContractNegotiationsDTO {

    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("contractAgreementId")
    private String contractAgreementId;
    @JsonProperty("counterPartyAddress")
    private String counterPartyAddress;
    @JsonProperty("errorDetail")
    private String errorDetail;
    @JsonProperty("id")
    private String id;
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("state")
    private String state;
    @JsonProperty("type")
    private String type;

}
