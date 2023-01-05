package consulting.sit.catenax.controller.rest;

import consulting.sit.catenax.controller.dtos.materialforrecycling.MaterialDTO;
import consulting.sit.catenax.controller.dtos.serialparttypization.SerialPartTypizationDTO;
import consulting.sit.catenax.facade.materialforrecycling.MaterialFacade;
import consulting.sit.catenax.facade.serialparttypization.SerialPartTypizationFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/serial")
@Slf4j
public class SerialPartTypizationController {

    private final SerialPartTypizationFacade serialPartTypizationFacade;

    public SerialPartTypizationController(final SerialPartTypizationFacade serialPartTypizationFacade) {
        this.serialPartTypizationFacade = serialPartTypizationFacade;
    }
    
    @Operation(
            summary = "Get all serial part typization."
            , description = "Get all serial part typization."
            , responses = {
    })
    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<SerialPartTypizationDTO>> getAllMaterial() {
        final HttpHeaders headers = new HttpHeaders();
        final Optional<List<SerialPartTypizationDTO>> entityOpt = getSerialPartTypizationFacade().getAllSerialPartTypization();
        if (entityOpt.isPresent()) {
            return new ResponseEntity<>(entityOpt.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    protected SerialPartTypizationFacade getSerialPartTypizationFacade() {
        return this.serialPartTypizationFacade;
    }
}
