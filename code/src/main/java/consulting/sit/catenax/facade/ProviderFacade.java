package consulting.sit.catenax.facade;


import consulting.sit.catenax.controller.dtos.provider.AssetRequestDTO;
import consulting.sit.catenax.controller.dtos.provider.AssetResponseDTO;
import consulting.sit.catenax.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderFacade {

    private final ProviderService providerService;

    public ProviderFacade(final ProviderService providerService) {
        this.providerService = providerService;
    }

    public void createAnAsset(AssetRequestDTO assetRequestDTO) {
        providerService.createAnAssets(assetRequestDTO);
    }

    public Optional<List<AssetResponseDTO>> getAllAssets() {
        List<AssetResponseDTO> assetResponseDTOs = providerService.getAllAssets();
        if (assetResponseDTOs.isEmpty()) {
            return null;
        }
        return Optional.of(assetResponseDTOs);
    }
}
