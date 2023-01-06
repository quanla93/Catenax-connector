package consulting.sit.catenax.controller.rest.consumer;


import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessRespornDTO;
import consulting.sit.catenax.facade.ConsumerFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/consumer/data")
@Slf4j
public class ConsumerController {

    private final ConsumerFacade consumerFacade;

    public ConsumerController(final ConsumerFacade consumerFacade) {
        this.consumerFacade = consumerFacade;
    }

    @Operation(
            summary = "Get contract offer catalog."
            , description = "Get contract offer catalog."
            , responses = {
    })
    @GetMapping(value = "/catalog", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CatalogDTO> getContractOfferByProviderURL(
            @Parameter(description = "The provider url", in = ParameterIn.DEFAULT, required = true)
            @RequestParam("providerUrl") final String providerUrl)
    {
        final HttpHeaders headers = new HttpHeaders();
       Optional<CatalogDTO> catalogDTOOpt = getConsumerFacade().getContractOfferCatalog(providerUrl);
       if (catalogDTOOpt.isPresent()) {
           return new ResponseEntity<>(catalogDTOOpt.get(), headers, HttpStatus.OK);
       }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = " Request a Contract Negotiation ID."
            , description = " Request a Contract Negotiation ID.")
    @PostMapping(value = "/contractnegotiations", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TransferProcessRespornDTO> requestContractNegotiations(
            @Parameter(description = "OfferRequestDTO", in = ParameterIn.DEFAULT, required = true)
            @RequestBody final OfferRequestDTO offerRequestDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        Optional<TransferProcessRespornDTO> transferProcessRespornDTO = getConsumerFacade().requestContractNegotiations(offerRequestDTO);
        if (transferProcessRespornDTO.isPresent()) {
            return new ResponseEntity<>(transferProcessRespornDTO.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

//    @Operation(
//            summary = "Check State of the Transfer Process."
//            , description = "Check State of the Transfer Process."
//            , responses = {
//    })
//    @GetMapping(value = "/transferprocess/{transferId}/state", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<CatalogDTO> checkStateOfTransferProcess(
//            @Parameter(description = "Check State of the Transfer Process", in = ParameterIn.DEFAULT, required = true)
//            @PathVariable("transferId") final String transferId)
//    {
//        final HttpHeaders headers = new HttpHeaders();
//        Optional<CatalogDTO> catalogDTOOpt = getConsumerFacade().getContractOfferCatalog(providerUrl);
//        if (catalogDTOOpt.isPresent()) {
//            return new ResponseEntity<>(catalogDTOOpt.get(), headers, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
//    }

    protected ConsumerFacade getConsumerFacade() {
        return consumerFacade;
    }


}
