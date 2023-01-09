package consulting.sit.catenax.facade;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferResponseDTO;
import consulting.sit.catenax.controller.dtos.consumer.StateDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessResponseDTO;
import consulting.sit.catenax.service.ConsumerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumerFacade {

    private final ConsumerService consumerService;


    public ConsumerFacade(final ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    public Optional<CatalogDTO> getContractOfferCatalog(final String providerUrl){
        CatalogDTO catalogDTO = consumerService.getContractOfferCatalog(providerUrl);
        return Optional.of(catalogDTO);
    }

    public Optional<TransferProcessResponseDTO> requestContractNegotiations(final OfferRequestDTO offerRequestDTO) {
        OfferResponseDTO offerResponseDTO = consumerService.requestOfferResponse(offerRequestDTO);
        ContractNegotiationsDTO contractNegotiationsDTO = consumerService.requestContractNegotiationID(offerResponseDTO);
        TransferProcessResponseDTO transferProcessResponseDTO = consumerService.requestInitiateTransfer(contractNegotiationsDTO, offerRequestDTO);
        return Optional.of(transferProcessResponseDTO);
    }

    public Optional<String> getDataTransferProcess(final String transferProcessId) {
        String data = consumerService.getTransferProcessData(transferProcessId);
        return Optional.of(data);
    }

    public Optional<StateDTO> checkStateOfTheTransferProcess(final String transferId) {
        StateDTO stateDTO = consumerService.checkStateOfTheTransfer(transferId);
        return Optional.of(stateDTO);
    }
}
