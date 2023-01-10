package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class TransferProcessDTO {

    private String createdAt;
    private String updatedAt;
    private String id;
    private String type;
    private String state;
    private String stateTimestamp;
    private String errorDetail;
    private JsonNode dataRequest;
    private JsonNode dataDestination;

}
