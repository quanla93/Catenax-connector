package consulting.sit.catenax.service;

import consulting.sit.catenax.controller.dtos.consumer.MessageErrorDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetResponseDTO;
import consulting.sit.catenax.controller.dtos.provider.ContractDefinitionDTO;
import consulting.sit.catenax.controller.dtos.provider.ContractDefinitionResponseDTO;
import consulting.sit.catenax.controller.dtos.provider.PolicyDefinitionsRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.PolicyDefinitionsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderService {

    private static final String POSTANASSET = "/data/assets";

    private static final String POLICYDEFINITIONS = "/data/policydefinitions";

    private static final String CONTRACTDEFINITIONS = "/data/contractdefinitions";
    @Value("${edc.provider.control.plane}")
    private String edcProviderControlplane;

    @Autowired
    private WebClient.Builder webClientBuilder;

    /**
     * Create an assets
     *
     * @param assetRequestDTO
     * @return
     */
    public void createAnAssets(AssetRequestDTO assetRequestDTO) {
        String createAnAssetUrl = new StringBuilder().append(edcProviderControlplane).append(POSTANASSET).toString();
         webClientBuilder.build()
                .post()
                .uri(createAnAssetUrl)
                .body(Mono.just(assetRequestDTO), AssetRequestDTO.class)
                .headers(h -> h.set("X-Api-Key", "password"))
                 .retrieve()
                 .bodyToMono(AssetRequestDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();

    }

    /**
     * Get all assets
     *
     * @param
     * @return
     */
    public List<AssetResponseDTO> getAllAssets(){
        String getAllAssetUrl = new StringBuilder().append(edcProviderControlplane).append(POSTANASSET).toString();
        Flux<AssetResponseDTO> assetDTOs = webClientBuilder.build()
                .get()
                .uri(getAllAssetUrl)
                .headers(h -> h.set("X-Api-Key", "password"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(AssetResponseDTO.class);

        List<AssetResponseDTO> assetResponseDTOs = assetDTOs
                .collect(Collectors.toList())
                .share().block();
        return assetResponseDTOs;
    }

    /**
     * Get all assets
     *
     * @param
     * @return
     */

    public List<PolicyDefinitionsResponseDTO> getAllPolicyDefinitions(){
        String getAllPolicyDefinitionsUrl = new StringBuilder().append(edcProviderControlplane).append(POLICYDEFINITIONS).toString();
        Flux<PolicyDefinitionsResponseDTO> policyDefinitionsResponseDTOFlux = webClientBuilder.build()
                .get()
                .uri(getAllPolicyDefinitionsUrl)
                .headers(h -> h.set("X-Api-Key", "password"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(PolicyDefinitionsResponseDTO.class);

        List<PolicyDefinitionsResponseDTO> policyDefinitionsResponseDTOs = policyDefinitionsResponseDTOFlux
                .collect(Collectors.toList())
                .share().block();
        return policyDefinitionsResponseDTOs;
    }

    /**
     * Get All Contract Definitions
     *
     * @param
     * @return
     */

    public List<ContractDefinitionResponseDTO> getAllContractDefinitions(){
        String getAllContractDefinitionsUrl = new StringBuilder().append(edcProviderControlplane).append(CONTRACTDEFINITIONS).toString();
        Flux<ContractDefinitionResponseDTO> contractDefinitionResponseDTOFlux = webClientBuilder.build()
                .get()
                .uri(getAllContractDefinitionsUrl)
                .headers(h -> h.set("X-Api-Key", "password"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ContractDefinitionResponseDTO.class);

        List<ContractDefinitionResponseDTO> contractDefinitionResponseDTOs = contractDefinitionResponseDTOFlux
                .collect(Collectors.toList())
                .share().block();
        return contractDefinitionResponseDTOs;
    }

    /**
     * Create Policy Definitions
     *
     * @param policyDefinitionsRequestDTO
     * @return
     */

    public void createPolicyDefinitions(PolicyDefinitionsRequestDTO policyDefinitionsRequestDTO) {
        String createPolicyDefinitionUrl = new StringBuilder().append(edcProviderControlplane).append(POLICYDEFINITIONS).toString();
        webClientBuilder.build()
                .post()
                .uri(createPolicyDefinitionUrl)
                .body(Mono.just(policyDefinitionsRequestDTO), PolicyDefinitionsRequestDTO.class)
                .headers(h -> h.set("X-Api-Key", "password"))
                .retrieve()
                .bodyToMono(PolicyDefinitionsRequestDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    /**
     * Create Contract Definitions
     *
     * @param contractDefinitionDTO
     * @return
     */

    public void createContractDefinitions(ContractDefinitionDTO contractDefinitionDTO) {
        String createContractDefinitionUrl = new StringBuilder().append(edcProviderControlplane).append(CONTRACTDEFINITIONS).toString();
        webClientBuilder.build()
                .post()
                .uri(createContractDefinitionUrl)
                .body(Mono.just(contractDefinitionDTO), ContractDefinitionDTO.class)
                .headers(h -> h.set("X-Api-Key", "password"))
                .retrieve()
                .bodyToMono(ContractDefinitionDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    /**
     * Get Contract Definition
     *
     * @param contractDefinitionId
     * @return
     */

    public ContractDefinitionDTO getContractDefinition(String contractDefinitionId) {
        String getContractDefinitionUrl = new StringBuilder().append(edcProviderControlplane).append(CONTRACTDEFINITIONS).append("/" + contractDefinitionId).toString();
        return  webClientBuilder.build()
                .get()
                .uri(getContractDefinitionUrl)
                .headers(h -> h.set("X-Api-Key", "password"))
                .retrieve()
                .bodyToMono(ContractDefinitionDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    /**
     * Delete Contract Definitions
     *
     * @param contractDefinitionId
     * @return
     */

    public void deleteContractDefinitions(String contractDefinitionId) {
        String deleteContractDefinitionUrl = new StringBuilder().append(edcProviderControlplane).append(CONTRACTDEFINITIONS).append("/" + contractDefinitionId).toString();
        webClientBuilder.build()
                .delete()
                .uri(deleteContractDefinitionUrl)
                .headers(h -> h.set("X-Api-Key", "password"))
                .retrieve()
                .bodyToMono(ContractDefinitionDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    /**
     * Get Policy Definition
     *
     * @param policyDefinitionId
     * @return
     */

    public PolicyDefinitionsRequestDTO getPolicyDefinition(String policyDefinitionId) {
        String getPolicyDefinitionUrl = new StringBuilder().append(edcProviderControlplane).append(POLICYDEFINITIONS).append("/" + policyDefinitionId).toString();
        return  webClientBuilder.build()
                .get()
                .uri(getPolicyDefinitionUrl)
                .headers(h -> h.set("X-Api-Key", "password"))
                .retrieve()
                .bodyToMono(PolicyDefinitionsRequestDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    /**
     * Delete Policy Definitions
     *
     * @param policyDefinitionId
     * @return
     */

    public void deletePolicyDefinitions(String policyDefinitionId) {
        String deletePolicyDefinitionUrl = new StringBuilder().append(edcProviderControlplane).append(POLICYDEFINITIONS).append("/" + policyDefinitionId).toString();
        webClientBuilder.build()
                .delete()
                .uri(deletePolicyDefinitionUrl)
                .headers(h -> h.set("X-Api-Key", "password"))
                .retrieve()
                .bodyToMono(PolicyDefinitionsRequestDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    /**
     * Get Assets
     *
     * @param assetId
     * @return
     */

    public AssetResponseDTO getAsset(String assetId) {
        String getAssetUrl = new StringBuilder().append(edcProviderControlplane).append(POSTANASSET).append("/" + assetId).toString();
        return  webClientBuilder.build()
                .get()
                .uri(getAssetUrl)
                .headers(h -> h.set("X-Api-Key", "password"))
                .retrieve()
                .bodyToMono(AssetResponseDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    /**
     * Delete Assets
     *
     * @param assetId
     * @return
     */

    public void deleteAsset(String assetId) {
        String deleteAssetUrl = new StringBuilder().append(edcProviderControlplane).append(POSTANASSET).append("/" + assetId).toString();
        webClientBuilder.build()
                .delete()
                .uri(deleteAssetUrl)
                .headers(h -> h.set("X-Api-Key", "password"))
                .retrieve()
                .bodyToMono(AssetResponseDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    private static Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        HttpStatus status = response.statusCode();
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            return response.bodyToMono(MessageErrorDTO.class)
                    .flatMap(body -> Mono.error(new Exception()));
        }
        if (HttpStatus.BAD_REQUEST.equals(status)) {
            return response.bodyToMono(MessageErrorDTO.class)
                    .flatMap(body -> Mono.error(new Exception()));
        }
        return Mono.just(response);
    }
}
