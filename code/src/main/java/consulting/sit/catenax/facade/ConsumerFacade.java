package consulting.sit.catenax.facade;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferResponseDTO;
import consulting.sit.catenax.controller.dtos.consumer.StateDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsResponseDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetResponseDTO;
import consulting.sit.catenax.service.ConsumerService;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Optional<ContractNegotiationsResponseDTO> requestContractNegotiations(final OfferRequestDTO offerRequestDTO) {
        OfferResponseDTO offerResponseDTO = consumerService.requestOfferResponse(offerRequestDTO);
        ContractNegotiationsDTO contractNegotiationsDTO = consumerService.requestContractNegotiationID(offerResponseDTO);
        ContractNegotiationsResponseDTO contractNegotiationsResponseDTO = consumerService.requestInitiateTransfer(contractNegotiationsDTO, offerRequestDTO);
        return Optional.of(contractNegotiationsResponseDTO);
    }

    public Optional<String> getDataTransferProcess(final String transferProcessId) {
        String data = consumerService.getTransferProcessData(transferProcessId);
        return Optional.of(data);
    }

    public Optional<StateDTO> checkStateOfTheTransferProcess(final String transferId) {
        StateDTO stateDTO = consumerService.checkStateOfTheTransfer(transferId);
        return Optional.of(stateDTO);
    }

    public Optional<List<TransferProcessDTO>> getAllTransferProcess() throws Exception {
        List<TransferProcessDTO> transferProcessDTOs = consumerService.getAllTransferProcessData();
        if (transferProcessDTOs.isEmpty()) {
            throw new Exception("Can't find the Transfer Process");
        }
        return Optional.of(transferProcessDTOs);
    }

}
