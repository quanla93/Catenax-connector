package consulting.sit.catenax.service;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.MessageErrorDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class ProviderService {

    private static final String POSTANASSET = "/data/assets";
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
        List<AssetResponseDTO> assetResponseDTOs = (List<AssetResponseDTO>) webClientBuilder.build()
                .get()
                .uri(edcProviderControlplane + POSTANASSET)
                .header("X-Api-Key", "password")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AssetResponseDTO.class)
                .block();

        return assetResponseDTOs;
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
