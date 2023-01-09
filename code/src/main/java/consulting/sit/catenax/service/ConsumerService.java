package consulting.sit.catenax.service;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsDTO;
import consulting.sit.catenax.controller.dtos.consumer.DataDestinationDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferResponseDTO;
import consulting.sit.catenax.controller.dtos.consumer.StateDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessRespornDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;


@Service
public class ConsumerService {

    private static final String TYPE = "HttpProxy";
    private static final String MANAGEDRESOURCES = "false";
    private static final String CONTRACTOFFERCATALOG = "/data/catalog?providerUrl=";
    private static final String CONTRACTNEGOTIATIONIDURL = "/data/contractnegotiations";
    private static final String INITIATETRANSFERURL = "/data/transferprocess";

    @Value("${edc.consumer.control.plane}")
    private String edcConsumerControlplane;

    @Value("${edc.consumer.backend.application}")
    private String edcConsumerBackend;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public CatalogDTO getContractOfferCatalog(String providerUrl){
        CatalogDTO catalogDTO = webClientBuilder.build()
                .get()
                .uri(edcConsumerControlplane + CONTRACTOFFERCATALOG + providerUrl)
                .header("X-Api-Key", "password")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CatalogDTO.class)
                .block();

        return catalogDTO;
    }
    public OfferResponseDTO requestOfferResporn(OfferRequestDTO offerRequestDTO) {
        OfferResponseDTO offerResponseDTO = webClientBuilder.build()
                .post()
                .uri(edcConsumerControlplane + CONTRACTNEGOTIATIONIDURL)
                .body(Mono.just(offerRequestDTO), OfferRequestDTO.class)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(OfferResponseDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
        return offerResponseDTO;
    }

    public ContractNegotiationsDTO requestContractNegotiationID(OfferResponseDTO offerResponseDTO) {
        ContractNegotiationsDTO contractNegotiationsDTO = new ContractNegotiationsDTO();
        for (int i=1; i <= 5; i ++) {
            if (contractNegotiationsDTO.getContractAgreementId() == null) {
                contractNegotiationsDTO = webClientBuilder.build()
                        .get()
                        .uri(edcConsumerControlplane + CONTRACTNEGOTIATIONIDURL + "/" + offerResponseDTO.getId())
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
        transferProcessRequestDTO.setManagedResources(MANAGEDRESOURCES);
        DataDestinationDTO dataDestinationDTO = new DataDestinationDTO();
        dataDestinationDTO.setType(TYPE);
        transferProcessRequestDTO.setDataDestination(dataDestinationDTO);

               TransferProcessRespornDTO transferProcessRespornDTO = webClientBuilder.build()
                .post()
                .uri(edcConsumerControlplane + INITIATETRANSFERURL)
                .body(Mono.just(transferProcessRequestDTO), TransferProcessRequestDTO.class)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(TransferProcessRespornDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
        transferProcessRespornDTO.setTransferProcessId(transferProcessRequestDTO.getId());
        return transferProcessRespornDTO;
    }

    public String getTransferProcessData(String transferProcessId){
        return webClientBuilder.build()
                .get()
                .uri(edcConsumerBackend + "/" + transferProcessId)
                .header("Accept", "application/octet-stream")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public StateDTO checkStateOfTheTransfer(final String transferId) {
        StateDTO stateDTO = webClientBuilder.build()
                .get()
                .uri(edcConsumerControlplane + INITIATETRANSFERURL + "/" + transferId + "/state")
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(StateDTO.class)
                .block();

        return stateDTO;
    }
}
