package consulting.sit.catenax.controller.rest.provider;

import consulting.sit.catenax.controller.dtos.provider.AssetRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetResponseDTO;
import consulting.sit.catenax.controller.dtos.provider.ContractDefinitionDTO;
import consulting.sit.catenax.controller.dtos.provider.ContractDefinitionResponseDTO;
import consulting.sit.catenax.controller.dtos.provider.PolicyDefinitionsRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.PolicyDefinitionsResponseDTO;
import consulting.sit.catenax.facade.ProviderFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/provider/data")
@Slf4j
public class ProviderController {

    private final ProviderFacade providerFacade;

    public ProviderController(final ProviderFacade providerFacade) {
        this.providerFacade = providerFacade;
    }

    /**
     * Create an asset
     * @param assetRequestDTO
     * @return
     */

    @Operation(
            summary = "Create an asset."
            , description = " Create an asset.")
    @PostMapping(value = "/assets", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createAnAssets(
            @Parameter(description = "AssetRequestDTO", in = ParameterIn.DEFAULT, required = true)
            @RequestBody final AssetRequestDTO assetRequestDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        getProviderFacade().createAnAsset(assetRequestDTO);
    }

    /**
     * Create Policy Definitions
     * @param policyDefinitionsRequestDTO
     * @return
     */

    @Operation(
            summary = "Create Policy Definitions."
            , description = "Create Policy Definitions.")
    @PostMapping(value = "/policydefinitions", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createPolicyDefinitions(
            @Parameter(description = "PolicyDefinitionsDTO", in = ParameterIn.DEFAULT, required = true)
            @RequestBody final PolicyDefinitionsRequestDTO policyDefinitionsRequestDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        getProviderFacade().createPolicyDefinitions(policyDefinitionsRequestDTO);
    }

    /**
     * Create Contract Definitions
     * @param contractDefinitionDTO
     * @return
     */

    @Operation(
            summary = "Create Contract Definitions."
            , description = "Create Contract Definitions.")
    @PostMapping(value = "/contractdefinitions", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createContractDefinitions(
            @Parameter(description = "ContractDefinitionDTO", in = ParameterIn.DEFAULT, required = true)
            @RequestBody final ContractDefinitionDTO contractDefinitionDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        getProviderFacade().createContractDefinitions(contractDefinitionDTO);
    }

    /**
     * Delete Contract Definitions
     * @param contractDefinitionId
     * @return
     */

    @Operation(
            summary = "Delete Contract Definitions."
            , description = "Delete Contract Definitions.")
    @DeleteMapping(value = "/contractdefinitions/{contractDefinitionId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteContractDefinitions(
            @Parameter(description = "ContractDefinitionDTO", in = ParameterIn.DEFAULT, required = true)
            @PathVariable("contractDefinitionId") final String contractDefinitionId) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        getProviderFacade().deleteContractDefinitions(contractDefinitionId);
    }

    /**
     * Delete Policy Definitions
     * @param policyDefinitionId
     * @return
     */

    @Operation(
            summary = "Delete Policy Definitions."
            , description = "Delete Policy Definitions.")
    @DeleteMapping(value = "/policydefinitions/{policyDefinitionId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePolicyDefinitions(
            @Parameter(description = "PolicyDefinitionDTO", in = ParameterIn.DEFAULT, required = true)
            @PathVariable("policyDefinitionId") final String policyDefinitionId) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        getProviderFacade().deletePolicyDefinitions(policyDefinitionId);
    }

    /**
     * Delete Asset
     * @param assetId
     * @return
     */

    @Operation(
            summary = "Delete Asset."
            , description = "Delete Asset.")
    @DeleteMapping(value = "/assets/{assetId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAsset(
            @Parameter(description = "AssetsResponseDTO", in = ParameterIn.DEFAULT, required = true)
            @PathVariable("assetId") final String assetId) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        getProviderFacade().deleteAsset(assetId);
    }


    /**
     * Get all assets
     * @param
     * @return
     */

    @Operation(
            summary = "Get all assets."
            , description = "Get all assets."
            , responses = {
    })
    @GetMapping(value = "/assets", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AssetResponseDTO>> getAllAssets() throws Exception
    {
        final HttpHeaders headers = new HttpHeaders();
        Optional<List<AssetResponseDTO>> assetResponseDTOs = getProviderFacade().getAllAssets();
        if (assetResponseDTOs.isPresent()) {
            return new ResponseEntity<>(assetResponseDTOs.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    /**
     * Get all policy definitions
     * @param
     * @return
     */

    @Operation(
            summary = "Get all policy definitions."
            , description = "Get all policy definitions."
            , responses = {
    })
    @GetMapping(value = "/policydefinitions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<PolicyDefinitionsResponseDTO>> getAllPolicyDefinitions() throws Exception
    {
        final HttpHeaders headers = new HttpHeaders();
        Optional<List<PolicyDefinitionsResponseDTO>> policyDefinitionsResponseDTOs = getProviderFacade().getAllPolicyDefinitions();
        if (policyDefinitionsResponseDTOs.isPresent()) {
            return new ResponseEntity<>(policyDefinitionsResponseDTOs.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    /**
     * Get all contract definitions
     * @param
     * @return
     */

    @Operation(
            summary = "Get all contract definitions."
            , description = "Get all contract definitions."
            , responses = {
    })
    @GetMapping(value = "/contractdefinitions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ContractDefinitionResponseDTO>> getAllContractDefinitions() throws Exception
    {
        final HttpHeaders headers = new HttpHeaders();
        Optional<List<ContractDefinitionResponseDTO>> contractDefinitionResponseDTOs = getProviderFacade().getAllContractDefinitions();
        if (contractDefinitionResponseDTOs.isPresent()) {
            return new ResponseEntity<>(contractDefinitionResponseDTOs.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }
    protected ProviderFacade getProviderFacade() {
        return this.providerFacade;
    }
}
