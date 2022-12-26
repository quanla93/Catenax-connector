package consulting.sit.catenax.controller.rest.cxregister;


import consulting.sit.catenax.controller.GenericControllerSingleId;
import consulting.sit.catenax.controller.dtos.shelldescriptors.ShellDescriptorDTO;
import consulting.sit.catenax.facade.materialforrecycling.MaterialFacade;
import consulting.sit.catenax.facade.shelldescriptor.ShellDescriptorFacade;
import consulting.sit.catenax.model.materialforrecycling.ComponentModel;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registry")
@Slf4j
public class RegisterController extends GenericControllerSingleId<ComponentModel, Integer> {

    private final ShellDescriptorFacade shellDescriptorFacade;

    public RegisterController(final ShellDescriptorFacade shellDescriptorFacade) {
        this.shellDescriptorFacade = shellDescriptorFacade;
    }

    @Operation(
            summary = "Look up data."
            , description = "Look up data."
            , responses = {
    })
    @GetMapping(value = "/lookup/shells", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getLookUpData(
            @Parameter(description = "Look up data.", in = ParameterIn.DEFAULT, required = true)
            @RequestParam("assetIds") final String assetIds )
    {
        return null; // return digital twin id
    }
//Get Digital Twin By Id

    @Operation(
            summary = "Get Digital Twin By Id."
            , description = "Get Digital Twin By Id."
            , responses = {
    })
    @GetMapping(value = "/registry/shell-descriptors/{digitalTwinId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getDigitalTwinById(
            @Parameter(description = "Get Digital Twin By Id.", in = ParameterIn.DEFAULT, required = true)
            @PathVariable("digitalTwinId") final String digitalTwinId )
    {
        return null;
    }

//Get specific submodel descripter

    @Operation(
            summary = "Get specific submodel descripter."
            , description = "Get specific submodel descripter."
            , responses = {
    })
    @GetMapping(value = "/registry/shell-descriptors/{digitalTwinId}/submodel-descriptors/{digitalTwinSubmodelId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getSpecificSubmodel(
            @Parameter(description = "Get Digital Twin By Id.", in = ParameterIn.DEFAULT, required = true)
            @PathVariable("digitalTwinId") final String digitalTwinId,
            @PathVariable("digitalTwinSubmodelId") final String digitalTwinSubmodelId)
    {
        return null;
    }

    @Operation(
            summary = "Get contract offer catalog."
            , description = "Get contract offer catalog."
            , responses = {
    })
    @GetMapping(value = "/registry/shell-descriptors", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ShellDescriptorDTO>> getAllMaterial() {
        final HttpHeaders headers = new HttpHeaders();
        final Optional<List<ShellDescriptorDTO>> entityOpt = getShellDescriptorFacade().getAllShellDescriptor();
        if (entityOpt.isPresent()) {
            return new ResponseEntity<>(entityOpt.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    protected ShellDescriptorFacade getShellDescriptorFacade() {
        return this.shellDescriptorFacade;
    }
}
