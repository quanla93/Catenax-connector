package consulting.sit.catenax.controller.dtos.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AssetResponseDTOs {

    @JsonProperty("")
    private List<AssetResponseDTO> assetResponseDTOs;
}
