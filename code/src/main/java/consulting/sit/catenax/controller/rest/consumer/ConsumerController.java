package consulting.sit.catenax.controller.rest.consumer;


import consulting.sit.catenax.controller.GenericControllerSingleId;
import consulting.sit.catenax.model.materialforrecycling.ComponentModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/data")
@Slf4j
public class ConsumerController extends GenericControllerSingleId<ComponentModel, Integer> {


    @Operation(
            summary = "Get contract offer catalog."
            , description = "Get contract offer catalog."
            , responses = {
    })
    @GetMapping(value = "/catalog", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getContractOfferByProviderURL(
            @Parameter(description = "The provider url", in = ParameterIn.DEFAULT, required = true)
            @RequestParam("providerUrl") final String providerUrl )
    {
       return null;
    }

    @Operation(
            summary = "Get Negotiate Contract."
            , description = "Get Negotiate Contract."
            , responses = {
    })
    @PostMapping(value = "/contractnegotiations", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createNegotiateContract(
            @Parameter(description = "Get Negotiate Contract.", in = ParameterIn.DEFAULT, required = true)
            @RequestBody final  String connectorId)
    {
        return null;
    }


    @Operation(
            summary = "Get Negotiations."
            , description = "Get Negotiations."
            , responses = {
    })
    @GetMapping(value = "/contractnegotiations/{negotiationId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNegotiateContract(
            @Parameter(description = "Get Negotiations", in = ParameterIn.DEFAULT, required = true)
            @PathVariable("negotiationId") final String negotiationId )
    {
        return null;
    }

    @Operation(
            summary = "Get Negotiate Contract."
            , description = "Get Negotiate Contract."
            , responses = {
    })
    @PostMapping(value = "/transferprocess", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> transferData(
            @Parameter(description = "Get Negotiate Contract.", in = ParameterIn.DEFAULT, required = true)
            @RequestBody final  String connectorId)
    {
        return null;
    }

    @Operation(
            summary = "Verify data transfer."
            , description = "Verify data transfer."
            , responses = {
    })
    @GetMapping(value = "/contractnegotiations/{transferId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> verifyDataTransfer(
            @Parameter(description = "Verify data transfer", in = ParameterIn.DEFAULT, required = true)
            @PathVariable("transferId") final String transferId )
    {
        return null;
    }


}
