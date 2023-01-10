package consulting.sit.catenax.service;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsDTO;
import consulting.sit.catenax.controller.dtos.consumer.DataDestinationDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferResponseDTO;
import consulting.sit.catenax.controller.dtos.consumer.StateDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsResponseDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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
    public OfferResponseDTO requestOfferResponse(OfferRequestDTO offerRequestDTO) {
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

    public ContractNegotiationsResponseDTO requestInitiateTransfer(ContractNegotiationsDTO contractNegotiationsDTO, OfferRequestDTO offerRequestDTO) {
        ContractNegotiationsRequestDTO contractNegotiationsRequestDTO = new ContractNegotiationsRequestDTO();
        UUID randomId = UUID.randomUUID();
        contractNegotiationsRequestDTO.setId(randomId.toString());
        contractNegotiationsRequestDTO.setAssetId(offerRequestDTO.getOffer().getAssetId());
        contractNegotiationsRequestDTO.setConnectorAddress(offerRequestDTO.getConnectorAddress());
        contractNegotiationsRequestDTO.setConnectorId(offerRequestDTO.getConnectorId());
        contractNegotiationsRequestDTO.setContractId(contractNegotiationsDTO.getContractAgreementId());
        contractNegotiationsRequestDTO.setManagedResources(MANAGEDRESOURCES);
        DataDestinationDTO dataDestinationDTO = new DataDestinationDTO();
        dataDestinationDTO.setType(TYPE);
        contractNegotiationsRequestDTO.setDataDestination(dataDestinationDTO);

               ContractNegotiationsResponseDTO contractNegotiationsResponseDTO = webClientBuilder.build()
                .post()
                .uri(edcConsumerControlplane + INITIATETRANSFERURL)
                .body(Mono.just(contractNegotiationsRequestDTO), ContractNegotiationsRequestDTO.class)
                .header("X-Api-Key", "password")
                .retrieve()
                .bodyToMono(ContractNegotiationsResponseDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
        contractNegotiationsResponseDTO.setTransferProcessId(contractNegotiationsRequestDTO.getId());
        return contractNegotiationsResponseDTO;
    }

    public List<TransferProcessDTO> getAllTransferProcessData(){
        Flux<TransferProcessDTO> transferProcessDTOFlux = webClientBuilder.build()
                .get()
                .uri(edcConsumerControlplane + INITIATETRANSFERURL)
                .header("X-Api-Key", "password")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(TransferProcessDTO.class);

        List<TransferProcessDTO> transferProcessDTOs = transferProcessDTOFlux
                .collect(Collectors.toList())
                .share().block();
        return transferProcessDTOs;
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
