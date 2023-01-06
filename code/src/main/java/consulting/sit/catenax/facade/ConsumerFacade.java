package consulting.sit.catenax.facade;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRespornDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessRespornDTO;
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

    public Optional<TransferProcessRespornDTO> requestContractNegotiations(final OfferRequestDTO offerRequestDTO) {
        OfferRespornDTO offerRespornDTO = consumerService.requestOfferResporn(offerRequestDTO);
        ContractNegotiationsDTO contractNegotiationsDTO = consumerService.requestContractNegotiationID(offerRespornDTO);
        TransferProcessRespornDTO transferProcessRespornDTO = consumerService.requestInitiateTransfer(contractNegotiationsDTO, offerRequestDTO);
        return Optional.of(transferProcessRespornDTO);
    }
}
