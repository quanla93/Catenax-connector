package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContractNegotiationsResponseDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("transfer_process_id")
    private String transferProcessId;


}
