package consulting.sit.catenax.controller.dtos.provider;

import lombok.Data;
import java.util.List;

@Data
public class ContractDefinitionDTO {

    private String id;
    private List<CriteriaDTO> criteria;
    private String accessPolicyId;
    private String contractPolicyId;

}
