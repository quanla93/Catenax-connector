package consulting.sit.catenax.service;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractOfferDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRespornDTO;
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
                .uri("http://sokrates-edc-controlplane:30902/data/catalog?providerUrl=" + providerUrl)
                .header("X-Api-Key", "password")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CatalogDTO.class)
                .block();

        return catalogDTO;
    }

    public ContractNegotiationsDTO requestContractNegotiationID(OfferRequestDTO offerRequestDTO) {
//        OfferRespornDTO offerRespornDTO = webClientBuilder.build()
//                .post()
//                .uri("http://sokrates-edc-controlplane:30902/data/contractnegotiations")
//                .body(Mono.just(offerRequestDTO), OfferRequestDTO.class)
//                .header("X-Api-Key", "password")
//                .retrieve()
//                .bodyToMono(OfferRespornDTO.class)
//                .timeout(Duration.ofMillis(10_000))
//                .block();

        ContractNegotiationsDTO contractNegotiationsDTO = new ContractNegotiationsDTO();
        for (int i=1; i <= 5; i ++) {
            if (contractNegotiationsDTO.getContractAgreementId() == null) {
                contractNegotiationsDTO = webClientBuilder.build()
                        .get()
                        .uri("http://sokrates-edc-controlplane:30902/data/contractnegotiations/" + "35d928a0-fd5b-4f6b-be9b-468e59f21f14")
                        .header("X-Api-Key", "password")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(ContractNegotiationsDTO.class)
                        .delaySubscription(Duration.ofMillis(2000))
                        .block();
            } else if (contractNegotiationsDTO.getContractAgreementId() == null || i >5) {
                return null; // here to return error
            }
        }

        return contractNegotiationsDTO;



    }
}
