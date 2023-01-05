package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContractNegotiationsDTO {

    @JsonProperty("createdAt")
    String createdAt;
    @JsonProperty("updatedAt")
    String updatedAt;
    @JsonProperty("contractAgreementId")
    String contractAgreementId;
    @JsonProperty("counterPartyAddress")
    String counterPartyAddress;
    @JsonProperty("errorDetail")
    String errorDetail;
    @JsonProperty("id")
    String id;
    @JsonProperty("protocol")
    String protocol;
    @JsonProperty("state")
    String state;
    @JsonProperty("type")
    String type;

}
