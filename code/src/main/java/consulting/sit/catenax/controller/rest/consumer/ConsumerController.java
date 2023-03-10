package consulting.sit.catenax.controller.rest.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.StateDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsResponseDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetResponseDTO;

import consulting.sit.catenax.facade.ConsumerFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consumer/data")
@Slf4j
public class ConsumerController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    private final ConsumerFacade consumerFacade;

    public ConsumerController(final ConsumerFacade consumerFacade) {
        this.consumerFacade = consumerFacade;
    }

    /**
     * Get contract offer catalog
     * @param providerUrl
     * @return
     */

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

    /**
     * Request a Contract Negotiation
     * @body OfferRequestDTO
     * @return
     */

    @Operation(
            summary = " Request a Contract Negotiation ID."
            , description = " Request a Contract Negotiation ID.")
    @PostMapping(value = "/contractnegotiations", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContractNegotiationsResponseDTO> requestContractNegotiations(
            @Parameter(description = "OfferRequestDTO", in = ParameterIn.DEFAULT, required = true)
            @RequestBody final OfferRequestDTO offerRequestDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        Optional<ContractNegotiationsResponseDTO> transferProcessResponseDTO = getConsumerFacade().requestContractNegotiations(offerRequestDTO);
        if (transferProcessResponseDTO.isPresent()) {
            return new ResponseEntity<>(transferProcessResponseDTO.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    /**
     * Check State
     * @param transferId
     * @return
     */

    @Operation(
            summary = "Check State of the Transfer Process."
            , description = "Check State of the Transfer Process."
            , responses = {
    })
    @GetMapping(value = "/transferprocess/{transferId}/state", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StateDTO> checkStateOfTransferProcess(
            @Parameter(description = "Check State of the Transfer Process", in = ParameterIn.DEFAULT, required = true)
            @PathVariable("transferId") final String transferId)
    {
        final HttpHeaders headers = new HttpHeaders();
        Optional<StateDTO> stateDTOOpt = getConsumerFacade().checkStateOfTheTransferProcess(transferId);
        if (stateDTOOpt.isPresent()) {
            return new ResponseEntity<>(stateDTOOpt.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    /**
     * Get all transfer process
     * @param
     * @return
     */

    @Operation(
            summary = "Get all transfer process."
            , description = "Get all transfer process."
            , responses = {
    })
    @GetMapping(value = "/transferprocess", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TransferProcessDTO>> getAllAssets() throws Exception
    {
        final HttpHeaders headers = new HttpHeaders();
        Optional<List<TransferProcessDTO>> transferProcessDTOs = getConsumerFacade().getAllTransferProcess();
        if (transferProcessDTOs.isPresent()) {
            return new ResponseEntity<>(transferProcessDTOs.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    /**
     * Lookup Asset
     * @param assetIds
     * @return
     */
    @GetMapping("/asset")
    public ResponseEntity<String> lookupAsset(@RequestParam String assetIds) throws JsonMappingException, JsonProcessingException {
//        SpecificAssetIdsDTO prod = objectMapper.readValue(assetIds, SpecificAssetIdsDTO.class);
        // call API to lookup asset
        Optional<String> providerUrlOpt = consumerFacade.lookupProviderUrl(assetIds);
        final HttpHeaders headers = new HttpHeaders();
        if (providerUrlOpt.isPresent()) {
            return new ResponseEntity<>(providerUrlOpt.get(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

    }

    protected ConsumerFacade getConsumerFacade() {
        return consumerFacade;
    }


}
