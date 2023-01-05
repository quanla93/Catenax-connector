package consulting.sit.catenax.controller.rest;

import consulting.sit.catenax.controller.GenericControllerSingleId;
import consulting.sit.catenax.controller.dtos.materialforrecycling.MaterialDTO;
import consulting.sit.catenax.facade.materialforrecycling.MaterialFacade;
import consulting.sit.catenax.model.materialforrecycling.ComponentModel;
import consulting.sit.catenax.model.materialforrecycling.MaterialModel;
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
@RequestMapping("/data")
@Slf4j
public class MaterialController{

    private final MaterialFacade materialFacade;

    public MaterialController(final MaterialFacade materialFacade) {
        this.materialFacade = materialFacade;
    }

    @Operation(
            summary = "Get all material."
            , description = "Get all material."
            , responses = {
    })
    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<MaterialDTO>> getAllMaterial() {
        final HttpHeaders headers = new HttpHeaders();
        final Optional<List<MaterialDTO>> entityOpt = getMaterialFacade().getAllMaterial();
        if (entityOpt.isPresent()) {
            return new ResponseEntity<>(entityOpt.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }


    protected MaterialFacade getMaterialFacade() {
        return this.materialFacade;
    }
}
