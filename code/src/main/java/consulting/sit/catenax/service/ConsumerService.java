package consulting.sit.catenax.service;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRespornDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessRespornDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;


@Service
public class ConsumerService {

    private static final String DATADESTIANTION = "";

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
    public OfferRespornDTO requestOfferResporn(OfferRequestDTO offerRequestDTO) {
        OfferRespornDTO offerRespornDTO = webClientBuilder.build()
                .post()
                .uri("http://sokrates-edc-controlplane:30902/data/contractnegotiations")
                .body(Mono.just(offerRequestDTO), OfferRequestDTO.class)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(OfferRespornDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(2000))
                .block();
        return offerRespornDTO;
    }
    public ContractNegotiationsDTO requestContractNegotiationID(OfferRespornDTO offerRespornDTO) {
        ContractNegotiationsDTO contractNegotiationsDTO = new ContractNegotiationsDTO();
        for (int i=1; i <= 5; i ++) {
            if (contractNegotiationsDTO.getContractAgreementId() == null) {
                contractNegotiationsDTO = webClientBuilder.build()
                        .get()
                        .uri("http://sokrates-edc-controlplane:30902/data/contractnegotiations/" + offerRespornDTO.getId())
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

    public TransferProcessRespornDTO requestInitiateTransfer(ContractNegotiationsDTO contractNegotiationsDTO, OfferRequestDTO offerRequestDTO) {
        TransferProcessRequestDTO transferProcessRequestDTO = new TransferProcessRequestDTO();
        UUID randomId = UUID.randomUUID();
        transferProcessRequestDTO.setId(randomId.toString());
        transferProcessRequestDTO.setAssetId(offerRequestDTO.getOffer().getAssetId());
        transferProcessRequestDTO.setConnectorAddress(offerRequestDTO.getConnectorAddress());
        transferProcessRequestDTO.setConnectorId(offerRequestDTO.getConnectorId());
        transferProcessRequestDTO.setContractId(contractNegotiationsDTO.getContractAgreementId());
        transferProcessRequestDTO.setDataDestination("a");
        transferProcessRequestDTO.setManagedResources("false");

               TransferProcessRespornDTO transferProcessRespornDTO = webClientBuilder.build()
                .post()
                .uri("http://sokrates-edc-controlplane:30902/data/contractnegotiations")
                .body(Mono.just(offerRequestDTO), OfferRequestDTO.class)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(TransferProcessRespornDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(2000))
                .block();

        return transferProcessRespornDTO;
    }

}
