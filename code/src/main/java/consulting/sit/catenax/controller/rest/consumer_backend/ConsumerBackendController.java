package consulting.sit.catenax.controller.rest.consumer_backend;


import consulting.sit.catenax.controller.GenericControllerSingleId;
import consulting.sit.catenax.model.materialforrecycling.ComponentModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer_backend")
@Slf4j
public class ConsumerBackendController extends GenericControllerSingleId<ComponentModel, Integer> {

    @Operation(
            summary = "Transfer data."
            , description = "Transfer data."
            , responses = {
    })
    @GetMapping(value = "/{transferId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTransferData(
            @Parameter(description = "Transfer data", in = ParameterIn.DEFAULT, required = true)
            @PathVariable("transferId") final String transferId )
    {
        return null;
    }


}
