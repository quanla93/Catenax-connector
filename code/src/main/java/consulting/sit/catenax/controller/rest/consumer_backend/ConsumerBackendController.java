package consulting.sit.catenax.controller.rest.consumer_backend;


import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/consumer_backend")
@Slf4j
public class ConsumerBackendController {

    private final ConsumerFacade consumerFacade;

    public ConsumerBackendController(final ConsumerFacade consumerFacade) {
        this.consumerFacade = consumerFacade;
    }

    @Operation(
            summary = "Transfer data."
            , description = "Transfer data."
            , responses = {
    })
    @GetMapping(value = "/{transfer_process_Id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTransferData(
            @Parameter(description = "Transfer data", in = ParameterIn.DEFAULT, required = true)
            @PathVariable("transfer_process_Id") final String transferProcessId )
    {
        final HttpHeaders headers = new HttpHeaders();
        Optional<String> data = getConsumerFacade().getDataTransferProcess(transferProcessId);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    protected ConsumerFacade getConsumerFacade() {
        return consumerFacade;
    }

}
