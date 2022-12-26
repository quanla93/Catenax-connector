package consulting.sit.catenax.controller.rest.cxregister;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registry")
@Slf4j
public class RegisterController extends GenericControllerSingleId<ComponentModel, Integer> {

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



}
