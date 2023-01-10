package consulting.sit.catenax.facade;


import consulting.sit.catenax.controller.dtos.provider.AssetRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetResponseDTO;
import consulting.sit.catenax.controller.dtos.provider.ContractDefinitionDTO;
import consulting.sit.catenax.controller.dtos.provider.ContractDefinitionResponseDTO;
import consulting.sit.catenax.controller.dtos.provider.PolicyDefinitionsRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.PolicyDefinitionsResponseDTO;
import consulting.sit.catenax.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderFacade {

    private final ProviderService providerService;

    public ProviderFacade(final ProviderService providerService) {
        this.providerService = providerService;
    }

    public void createAnAsset(AssetRequestDTO assetRequestDTO) {
        if (assetRequestDTO != null) {
            providerService.createAnAssets(assetRequestDTO);
        }
    }

    public void createPolicyDefinitions(PolicyDefinitionsRequestDTO policyDefinitionsRequestDTO) {
        if (policyDefinitionsRequestDTO != null) {
            providerService.createPolicyDefinitions(policyDefinitionsRequestDTO);
        }
    }

    public void createContractDefinitions(ContractDefinitionDTO contractDefinitionDTO) {
        if (contractDefinitionDTO != null) {
            providerService.createContractDefinitions(contractDefinitionDTO);
        }
    }

    public void deleteContractDefinitions(String contractDefinitionId) throws Exception {
        ContractDefinitionDTO contractDefinitionDTO = providerService.getContractDefinition(contractDefinitionId);
        if (contractDefinitionDTO != null) {
            providerService.deleteContractDefinitions(contractDefinitionId);
        } else {
            throw new Exception("Can't find the Contract Definition");
        }
    }

    public void deletePolicyDefinitions(String policyDefinitionId) throws Exception {
        PolicyDefinitionsRequestDTO policyDefinitionsRequestDTO = providerService.getPolicyDefinition(policyDefinitionId);
        if (policyDefinitionsRequestDTO != null) {
            providerService.deletePolicyDefinitions(policyDefinitionId);
        } else {
            throw new Exception("Can't find the Policy Definition");
        }
    }

    public void deleteAsset(String assetId) throws Exception {
        AssetResponseDTO assetResponseDTO = providerService.getAsset(assetId);
        if (assetResponseDTO != null) {
            providerService.deleteAsset(assetId);
        } else {
            throw new Exception("Can't find the Asset");
        }
    }
    public Optional<List<AssetResponseDTO>> getAllAssets() throws Exception {
        List<AssetResponseDTO> assetResponseDTOs = providerService.getAllAssets();
        if (assetResponseDTOs.isEmpty()) {
            throw new Exception("Can't find the Asset");
        }
        return Optional.of(assetResponseDTOs);
    }

    public Optional<List<PolicyDefinitionsResponseDTO>> getAllPolicyDefinitions() throws Exception {
        List<PolicyDefinitionsResponseDTO> policyDefinitionsResponseDTOs = providerService.getAllPolicyDefinitions();
        if (policyDefinitionsResponseDTOs.isEmpty()) {
            throw new Exception("Can't find the Asset");
        }
        return Optional.of(policyDefinitionsResponseDTOs);
    }

    public Optional<List<ContractDefinitionResponseDTO>> getAllContractDefinitions() throws Exception {
        List<ContractDefinitionResponseDTO> contractDefinitionResponseDTOs = providerService.getAllContractDefinitions();
        if (contractDefinitionResponseDTOs.isEmpty()) {
            throw new Exception("Can't find the Asset");
        }
        return Optional.of(contractDefinitionResponseDTOs);
    }
}
