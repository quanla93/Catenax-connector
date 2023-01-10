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

    public void createAnAssets(AssetRequestDTO assetRequestDTO) {
         webClientBuilder.build()
                .post()
                .uri(edcProviderControlplane + POSTANASSET)
                .body(Mono.just(assetRequestDTO), AssetRequestDTO.class)
                .header("X-Api-Key", "password")
                 .retrieve()
                 .bodyToMono(AssetRequestDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();

    }

    public List<AssetResponseDTO> getAllAssets(){
        Flux<AssetResponseDTO> assetDTOs = webClientBuilder.build()
                .get()
                .uri(edcProviderControlplane + POSTANASSET)
                .header("X-Api-Key", "password")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(AssetResponseDTO.class);

        List<AssetResponseDTO> assetResponseDTOs = assetDTOs
                .collect(Collectors.toList())
                .share().block();
        return assetResponseDTOs;
    }

    public List<PolicyDefinitionsResponseDTO> getAllPolicyDefinitions(){
        Flux<PolicyDefinitionsResponseDTO> policyDefinitionsResponseDTOFlux = webClientBuilder.build()
                .get()
                .uri(edcProviderControlplane + POLICYDEFINITIONS)
                .header("X-Api-Key", "password")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(PolicyDefinitionsResponseDTO.class);

        List<PolicyDefinitionsResponseDTO> policyDefinitionsResponseDTOs = policyDefinitionsResponseDTOFlux
                .collect(Collectors.toList())
                .share().block();
        return policyDefinitionsResponseDTOs;
    }

    public List<ContractDefinitionResponseDTO> getAllContractDefinitions(){
        Flux<ContractDefinitionResponseDTO> contractDefinitionResponseDTOFlux = webClientBuilder.build()
                .get()
                .uri(edcProviderControlplane + CONTRACTDEFINITIONS)
                .header("X-Api-Key", "password")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ContractDefinitionResponseDTO.class);

        List<ContractDefinitionResponseDTO> contractDefinitionResponseDTOs = contractDefinitionResponseDTOFlux
                .collect(Collectors.toList())
                .share().block();
        return contractDefinitionResponseDTOs;
    }

    public void createPolicyDefinitions(PolicyDefinitionsRequestDTO policyDefinitionsRequestDTO) {
        webClientBuilder.build()
                .post()
                .uri(edcProviderControlplane + POLICYDEFINITIONS)
                .body(Mono.just(policyDefinitionsRequestDTO), PolicyDefinitionsRequestDTO.class)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(PolicyDefinitionsRequestDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    public void createContractDefinitions(ContractDefinitionDTO contractDefinitionDTO) {
        webClientBuilder.build()
                .post()
                .uri(edcProviderControlplane + CONTRACTDEFINITIONS)
                .body(Mono.just(contractDefinitionDTO), ContractDefinitionDTO.class)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(ContractDefinitionDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    public ContractDefinitionDTO getContractDefinition(String contractDefinitionId) {
        return  webClientBuilder.build()
                .get()
                .uri(edcProviderControlplane + CONTRACTDEFINITIONS + "/" + contractDefinitionId)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(ContractDefinitionDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    public void deleteContractDefinitions(String contractDefinitionId) {
        webClientBuilder.build()
                .delete()
                .uri(edcProviderControlplane + CONTRACTDEFINITIONS + "/" + contractDefinitionId)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(ContractDefinitionDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    public PolicyDefinitionsRequestDTO getPolicyDefinition(String policyDefinitionId) {
        return  webClientBuilder.build()
                .get()
                .uri(edcProviderControlplane + POLICYDEFINITIONS + "/" + policyDefinitionId)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(PolicyDefinitionsRequestDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    public void deletePolicyDefinitions(String policyDefinitionId) {
        webClientBuilder.build()
                .delete()
                .uri(edcProviderControlplane + POLICYDEFINITIONS + "/" + policyDefinitionId)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(PolicyDefinitionsRequestDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    public AssetResponseDTO getAsset(String assetId) {
        return  webClientBuilder.build()
                .get()
                .uri(edcProviderControlplane + POSTANASSET + "/" + assetId)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(AssetResponseDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
    }

    public void deleteAsset(String assetId) {
        webClientBuilder.build()
                .delete()
                .uri(edcProviderControlplane + POSTANASSET + "/" + assetId)
                .header("X-Api-Key", "password")
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
