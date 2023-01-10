package consulting.sit.catenax.controller.dtos.provider;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class PolicyDTO {

    private List<JsonNode> prohibitions;
    private List<JsonNode> obligations;
    private List<JsonNode> permissions;

}
