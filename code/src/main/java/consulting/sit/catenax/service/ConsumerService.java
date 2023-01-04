package consulting.sit.catenax.service;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractOfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;


@Service
public class ConsumerService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public CatalogDTO getContractOfferCatalog(String providerUrl){
        CatalogDTO catalogDTO = webClientBuilder.build()
                .get()
                .uri("http://plato-edc-controlplane:32583/data/catalog?providerUrl=" + providerUrl)
                .header("X-Api-Key", "password")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CatalogDTO.class)
                .block();

        return catalogDTO;
    }

    public ContractOfferDTO createContractNegotiationID(ContractOfferDTO contractOfferDTO) {
        return webClientBuilder.build()
                .post()
                .uri("http://plato-edc-controlplane:32583/data/contractnegotiations")
                .body(Mono.just(contractOfferDTO), ContractOfferDTO.class)
                .retrieve()
                .bodyToMono(ContractOfferDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .block();
    }

}
