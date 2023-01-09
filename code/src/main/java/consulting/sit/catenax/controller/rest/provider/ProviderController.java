package consulting.sit.catenax.controller.rest.provider;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetResponseDTO;
import consulting.sit.catenax.facade.ProviderFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/provider/data")
@Slf4j
public class ProviderController {

    private final ProviderFacade providerFacade;

    public ProviderController(final ProviderFacade providerFacade) {
        this.providerFacade = providerFacade;
    }

    @Operation(
            summary = "Create an asset."
            , description = " Create an asset.")
    @PostMapping(value = "/assets", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createAnAssets(
            @Parameter(description = "AssetRequestDTO", in = ParameterIn.DEFAULT, required = true)
            @RequestBody final AssetRequestDTO assetRequestDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        getProviderFacade().createAnAsset(assetRequestDTO);
    }

    @Operation(
            summary = "Get contract offer catalog."
            , description = "Get contract offer catalog."
            , responses = {
    })
    @GetMapping(value = "/assets", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AssetResponseDTO>> getAllAssets()
    {
        final HttpHeaders headers = new HttpHeaders();
        Optional<List<AssetResponseDTO>> assetResponseDTOs = getProviderFacade().getAllAssets();
        if (assetResponseDTOs.isPresent()) {
            return new ResponseEntity<>(assetResponseDTOs.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    protected ProviderFacade getProviderFacade() {
        return this.providerFacade;
    }
}
