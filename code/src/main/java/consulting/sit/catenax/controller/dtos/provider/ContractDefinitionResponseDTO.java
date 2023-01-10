package consulting.sit.catenax.controller.dtos.provider;

import lombok.Data;

import java.util.List;

@Data
public class ContractDefinitionResponseDTO {

    private String createdAt;
    private String id;
    private String accessPolicyId;
    private String contractPolicyId;
    private List<CriteriaDTO> criteria;

}
