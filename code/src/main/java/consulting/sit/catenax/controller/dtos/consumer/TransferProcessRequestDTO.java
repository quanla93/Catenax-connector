package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferProcessRequestDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("connectorId")
    private String connectorId;
    @JsonProperty("connectorAddress")
    private String connectorAddress;
    @JsonProperty("contractId")
    private String contractId;
    @JsonProperty("assetId")
    private String assetId;
    @JsonProperty("managedResources")
    private String managedResources;
    @JsonProperty("dataDestination")
    private DataDestinationDTO dataDestination;
}
